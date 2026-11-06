package br.com.stock.stock.domains;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stockable", uniqueConstraints = @UniqueConstraint(columnNames = { "productId" }))
@NoArgsConstructor
public class Stockable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private UUID productId;
  @Column(nullable = false)
  private BigDecimal amount;

  public Stockable(UUID productId) {
    this.productId = productId;
    this.amount = BigDecimal.valueOf(10);
  }

  public UUID getId() {
    return id;
  }

  public UUID getProductId() {
    return productId;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
