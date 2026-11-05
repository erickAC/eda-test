package br.com.stock.stock.domains;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

}
