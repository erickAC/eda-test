package br.com.stock.stock.infra.config;

import java.util.Map;

import br.com.stock.stock.infra.readmodels.OrderPayload;
import br.com.stock.stock.infra.readmodels.OrderReadModel;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory factory = new CachingConnectionFactory();
    factory.setHost("localhost");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    return factory;
  }

  @Bean
  public JacksonJsonMessageConverter messageConverter() {
    JacksonJsonMessageConverter messageConverter = new JacksonJsonMessageConverter();
    DefaultClassMapper classMapper = new DefaultClassMapper();
    classMapper.setTrustedPackages("*");
    Map<String, Class<?>> mappings = Map.of("br.com.order.order.domains.ProcessedEvent", OrderReadModel.class);
    classMapper.setIdClassMapping(mappings);
    messageConverter.setClassMapper(classMapper);
    return messageConverter;
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }

}
