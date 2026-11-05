package br.com.stock.stock.infra.web;

import java.util.List;

import br.com.stock.stock.domains.StockMovement;
import br.com.stock.stock.domains.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
public class StockMovementResource {

  private final StockMovementRepository stockMovementRepository;

  @GetMapping
  public ResponseEntity<List<StockMovement>> findAll() {
    List<StockMovement> movements = stockMovementRepository.findAll();
    return ResponseEntity.ok(movements);
  }

}
