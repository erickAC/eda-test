package br.com.stock.stock.infra.listeners;

import br.com.stock.stock.infra.readmodels.ProductPayload;
import br.com.stock.stock.infra.readmodels.ReadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListener {

  @RabbitListener(bindings = @QueueBinding(
      value = @Queue("order.product.created"),
      exchange = @Exchange(name = "order.topic", type = ExchangeTypes.TOPIC),
      key = "order.product.created"
  ))
  public void on(ReadModel<ProductPayload> message) {
    ObjectMapper mapper = new ObjectMapper();
    ProductPayload payload = mapper.convertValue(message.getPayload(), ProductPayload.class);
    log.info("Cheguei.... {}", payload);
  }

}
