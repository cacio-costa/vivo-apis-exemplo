package br.com.alura.fatura.service.cliente;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClienteRestService {

    private WebClient webClient;

    public ClienteRestService(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "cliente-service", fallbackMethod = "fallback")
    public Cliente buscaClientePorId(Long id) {
        Cliente cliente = webClient.get()
                .uri("http://localhost:8080/v1/clientes/{id}", id)
                .retrieve()
                .bodyToMono(Cliente.class)
                .block();

        return cliente;
    }

    public Cliente fallback(Long id, Throwable throwable) {
        System.out.println("Fallback para o cliente de id: " + id);
        return new Cliente(id);
    }
}
