package br.com.alura.notificacao;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    public static final String QUEUE = "FaturasPagas";
    public static final String EXCHANGE = "FaturasPagasExchange";

    @Bean
    public Queue fatuasPagasQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange faturasPagasExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding binding() {
        return new Binding(QUEUE, Binding.DestinationType.QUEUE, EXCHANGE, "fatura.paga", null);
    }
}
