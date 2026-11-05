package br.com.stock.stock.domains;

import java.math.BigDecimal;
import java.util.UUID;

public interface StockMovementUseCase {

  void createFromOrder(UUID productId, BigDecimal price);

}
