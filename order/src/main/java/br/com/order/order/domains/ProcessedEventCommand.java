package br.com.order.order.domains;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProcessedEventCommand {

  private UUID aggregateId;
  private String aggregateType;
  private Map<String, Object> eventRequestBody;

  public ProcessedEventData data() {
    return new ProcessedEventData(aggregateId, aggregateType, eventRequestBody);
  }

}
