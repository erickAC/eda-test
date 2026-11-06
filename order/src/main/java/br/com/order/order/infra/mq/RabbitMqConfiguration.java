package br.com.order.order.infra.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
    cachingConnectionFactory.setHost("localhost");
    cachingConnectionFactory.setPort(5672);
    cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
    cachingConnectionFactory.setUsername("admin");
    cachingConnectionFactory.setPassword("admin");
    return cachingConnectionFactory;
  }

  @Bean
  public org.springframework.amqp.support.converter.JacksonJsonMessageConverter messageConverter() {
    JacksonJsonMessageConverter messageConverter = new JacksonJsonMessageConverter();
    DefaultClassMapper classMapper = new DefaultClassMapper();
    classMapper.setTrustedPackages("*");
    messageConverter.setNullAsOptionalEmpty(true);
    return messageConverter;
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory());
    template.setMessageConverter(messageConverter());
    return template;
  }

  @Bean
  public TopicExchange orderTopic() {
    TopicExchange order = new TopicExchange("order.topic");
    return order;
  }

  @Bean
  public Binding topicBinding(Queue orderQueue, TopicExchange orderTopic) {
    return BindingBuilder.bind(orderQueue).to(orderTopic).with("order.#");
  }

  @Bean
  public Queue orderQueue() {
    return new Queue("order");
  }

}
