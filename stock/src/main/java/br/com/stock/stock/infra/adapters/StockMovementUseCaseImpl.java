package br.com.stock.stock.infra.adapters;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.stock.stock.domains.StockMovement;
import br.com.stock.stock.domains.StockMovementRepository;
import br.com.stock.stock.domains.StockMovementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockMovementUseCaseImpl implements StockMovementUseCase {

  private final StockMovementRepository stockMovementRepository;

  @Override
  public void createFromOrder(UUID productId, BigDecimal price) {
    StockMovement stockMovement = new StockMovement(productId, price);
    stockMovementRepository.save(stockMovement);
  }

}
