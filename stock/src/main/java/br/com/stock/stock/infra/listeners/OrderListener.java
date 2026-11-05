package br.com.stock.stock.infra.listeners;

import br.com.stock.stock.infra.readmodels.OrderReadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

  @RabbitListener(queues = "order")
  public void on(OrderReadModel orderReadModel) {
    log.info(orderReadModel.toString());
  }

}
