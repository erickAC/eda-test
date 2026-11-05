package br.com.order.order.domains;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.json.JsonMapper;

@Entity
@Table(name = "processed_events")
@NoArgsConstructor
@Getter
public class ProcessedEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID eventId;
  @Column(name = "aggregate_id", nullable = false)
  private UUID aggregateId;
  @Column(name = "aggregate_type", nullable = false, columnDefinition = "text")
  private String aggregateType;
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "payload", nullable = false, columnDefinition = "jsonb")
  private Map<String, Object> payload;
  private LocalDateTime createdAt = LocalDateTime.now();

  public ProcessedEvent(ProcessedEventData data) {
    aggregateId = data.getAggregateId();
    aggregateType = data.getAggregateType();
    JsonMapper mapper = JsonMapper.builder().build();
    payload = data.getEventRequestBody();
  }

}
