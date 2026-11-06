package br.com.order.order.infra.adapter;

import java.util.Map;

import br.com.order.order.domains.ProcessedEventCommand;
import br.com.order.order.domains.ProcessedEventService;
import br.com.order.order.domains.Product;
import br.com.order.order.domains.ProductCreateCommand;
import br.com.order.order.domains.ProductRepository;
import br.com.order.order.domains.ProductUseCase;
import br.com.order.order.domains.TopicsKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

  private final ProductRepository productRepository;
  private final ProcessedEventService processedEventService;

  @Override
  public Product create(ProductCreateCommand command) {
    Product product = new Product(command.getPrice(), command.getName());
    productRepository.save(product);
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> converted = objectMapper.convertValue(product, new TypeReference<Map<String, Object>>() {});
    var eventCommand = new ProcessedEventCommand(product.getId(), Product.class.getCanonicalName(), converted);
    eventCommand.eventType(TopicsKeys.PRODUCT_CREATED);
    processedEventService.send(eventCommand);
    return product;
  }

}