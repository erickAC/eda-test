package br.com.stock.stock.infra.readmodels;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReadModel<T> {

  private UUID aggregateId;
  private String aggregateType;
  private T payload;

}
