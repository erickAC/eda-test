package br.com.order.order.domains;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProcessedEventCommand {

  private UUID aggregateId;
  private String aggregateType;
  private Map<String, Object> eventRequestBody;
  private String eventType;

  public ProcessedEventCommand(UUID aggregateId, String aggregateType, Map<String, Object> eventRequestBody) {
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventRequestBody = eventRequestBody;
  }

  public ProcessedEventCommand eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

  public String getEventType() {
    return Optional.ofNullable(eventType).orElse(TopicsKeys.ORDER_CREATED);
  }

  public ProcessedEventData data() {
    return new ProcessedEventData(aggregateId, aggregateType, eventRequestBody);
  }

}
