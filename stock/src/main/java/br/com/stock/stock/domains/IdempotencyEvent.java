package br.com.stock.stock.domains;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "idempotency_event")
@NoArgsConstructor
public class IdempotencyEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "order_id", nullable = false)
  private UUID orderId;
  @Enumerated(EnumType.STRING)
  private IdempotencyStatus status;

  private IdempotencyEvent(UUID orderId, IdempotencyStatus status) {
    this.status = status;
    this.orderId = orderId;
  }

  public static IdempotencyEvent of(UUID orderId) {
    return new IdempotencyEvent(orderId, IdempotencyStatus.PENDING);
  }

}
