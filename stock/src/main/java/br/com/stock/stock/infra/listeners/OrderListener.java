package br.com.stock.stock.infra.listeners;

import java.util.UUID;

import br.com.stock.stock.domains.IdempotencyEvent;
import br.com.stock.stock.domains.IdempotencyEventRepository;
import br.com.stock.stock.domains.StockMovementUseCase;
import br.com.stock.stock.infra.readmodels.OrderPayload;
import br.com.stock.stock.infra.readmodels.OrderReadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class OrderListener {

  private final StockMovementUseCase stockMovementUseCase;
  private final IdempotencyEventRepository idempotencyEventRepository;

  @RabbitListener(queues = "order")
  public void on(OrderReadModel orderReadModel) {
    log.info(orderReadModel.toString());
    UUID orderId = orderReadModel.getAggregateId();
    boolean exists = idempotencyEventRepository.existsByOrderIdAndPending(orderId);
    if (!exists) {
      var idempotencyEvent = IdempotencyEvent.of(orderId);
      idempotencyEventRepository.saveAndFlush(idempotencyEvent);
      OrderPayload payload = orderReadModel.getPayload();
      stockMovementUseCase.createFromOrder(orderId, payload.getSalePrice());
      idempotencyEventRepository.finish(orderId);
      return;
    }
    log.info("Evento em execução...");
  }

}
