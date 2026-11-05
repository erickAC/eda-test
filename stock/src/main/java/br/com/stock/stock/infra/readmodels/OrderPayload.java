package br.com.stock.stock.infra.readmodels;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OrderPayload {

  private BigDecimal salePrice;

}
