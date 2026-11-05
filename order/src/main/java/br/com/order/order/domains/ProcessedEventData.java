package br.com.order.order.domains;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProcessedEventData {

  private final UUID aggregateId;
  private final String aggregateType;
  private final Map<String, Object> eventRequestBody;

}
