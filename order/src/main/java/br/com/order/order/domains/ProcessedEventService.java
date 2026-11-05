package br.com.order.order.domains;

public interface ProcessedEventService {

  void send(ProcessedEventCommand command);

}
