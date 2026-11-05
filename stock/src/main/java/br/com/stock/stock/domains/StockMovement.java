package br.com.stock.stock.domains;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_movement")
@NoArgsConstructor
public class StockMovement {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private UUID productId;
  private BigDecimal price = BigDecimal.ZERO;

  public StockMovement(UUID productId, BigDecimal price) {
    this.productId = productId;
    this.price = price;
  }

  public UUID getId() {
    return id;
  }

  public UUID getProductId() {
    return productId;
  }

  public BigDecimal getPrice() {
    return price;
  }

}
