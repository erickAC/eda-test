package br.com.stock.stock.infra.readmodels;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public abstract class ReadModel<T> {

  private UUID aggregateId;
  private String aggregateType;
  private T payload;

}
