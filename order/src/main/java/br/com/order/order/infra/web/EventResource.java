package br.com.order.order.infra.web;

import br.com.order.order.domains.ProcessedEventCommand;
import br.com.order.order.domains.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventResource {

  private final ProcessedEventService processedEventService;


  @PostMapping
  public ResponseEntity<Void> processEvent(@RequestBody ProcessedEventCommand command) {
    processedEventService.send(command);
    return ResponseEntity.accepted().build();
  }

}
