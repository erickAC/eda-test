package br.com.order.order.infra.web;

import br.com.order.order.domains.Product;
import br.com.order.order.domains.ProductCreateCommand;
import br.com.order.order.domains.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductResource {

  private final ProductUseCase productUseCase;

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody ProductCreateCommand command) {
    Product product = productUseCase.create(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(product);
  }

}
