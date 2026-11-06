package br.com.order.order.domains;

public interface ProductUseCase {

  Product create(ProductCreateCommand command);

}
