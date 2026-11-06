package br.com.order.order.domains;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateCommand {

  private String name;
  private BigDecimal price;

}
