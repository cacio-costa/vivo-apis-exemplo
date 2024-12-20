package br.com.alura.fatura.service.cliente;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.net.ConnectException;
import java.time.Duration;

@Service
public class ClienteRestService {

    private WebClient webClient;

    public ClienteRestService(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "clientes", fallbackMethod = "fallback")
    public Cliente buscaClientePorId(Long id) {
        return webClient.get()
                .uri("http://localhost:8080/v1/clientes/{id}", id)
                .retrieve()
                .bodyToMono(Cliente.class)
                .timeout(Duration.ofSeconds(2))
                .block();
    }

    public Cliente fallback(Long id, Throwable e) {
        System.out.println("Fallback chamado...");
        return new Cliente(id);
    }

}
