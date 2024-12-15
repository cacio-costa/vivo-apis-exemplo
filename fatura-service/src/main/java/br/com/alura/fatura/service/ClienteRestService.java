package br.com.alura.fatura.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClienteRestService {

    private WebClient webClient;

    public ClienteRestService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Cliente buscaClientePorId(Long id) {
        Cliente cliente = webClient.get()
                .uri("http://localhost:8080/v1/clientes/{id}", id)
                .retrieve()
                .bodyToMono(Cliente.class)
                .block();

        return cliente;
    }
}
