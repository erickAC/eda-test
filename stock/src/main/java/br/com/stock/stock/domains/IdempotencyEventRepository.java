package br.com.stock.stock.domains;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IdempotencyEventRepository extends JpaRepository<IdempotencyEvent, UUID> {

  @Query("SELECT COUNT(i) > 0 FROM IdempotencyEvent i WHERE i.orderId = :orderId AND i.status = 'PENDING' ")
  boolean existsByOrderIdAndPending(UUID orderId);

  @Modifying
  @Query("UPDATE IdempotencyEvent i SET i.status = 'FINISH' WHERE i.orderId = :orderId")
  void finish(UUID orderId);

}
