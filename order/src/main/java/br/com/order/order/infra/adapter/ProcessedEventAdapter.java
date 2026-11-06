package br.com.order.order.infra.adapter;

import br.com.order.order.domains.ProcessedEvent;
import br.com.order.order.domains.ProcessedEventCommand;
import br.com.order.order.domains.ProcessedEventRepository;
import br.com.order.order.domains.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessedEventAdapter implements ProcessedEventService {

  private final RabbitTemplate rabbitTemplate;
  private final ProcessedEventRepository eventRepository;

  @Override
  public void send(ProcessedEventCommand command) {
    ProcessedEvent processedEvent = new ProcessedEvent(command.data());
    eventRepository.save(processedEvent);
    rabbitTemplate.convertAndSend("order.topic", command.getEventType(), processedEvent);
  }
}
