package br.com.alura.fatura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .filter((request, next) -> {
                    Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
                    Jwt jwt = (Jwt) autenticacao.getPrincipal();

                    ClientRequest requisicaoComToken = ClientRequest.from(request)
                            .headers(headers -> headers.setBearerAuth(jwt.getTokenValue()))
                            .build();

                    System.out.println("Request: " + requisicaoComToken.method() + " " + requisicaoComToken.url());

                    return next.exchange(requisicaoComToken);
                })
                .build();
    }
}
