package br.com.stock.stock.infra.readmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class OrderReadModel extends ReadModel<OrderPayload> {
}
