package br.com.stock.stock.infra.readmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(force = true)
@ToString(callSuper = true)
public class ProductReadModel extends ReadModel<ProductPayload>{
}
