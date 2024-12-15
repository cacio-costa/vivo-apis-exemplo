package br.com.alura.pagamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .filter((request, next) -> {
                    Mono<ClientRequest> reqComToken = ReactiveSecurityContextHolder.getContext()
                            .map(ctx -> ctx.getAuthentication().getPrincipal())
                            .cast(Jwt.class)
                            .map(Jwt::getTokenValue)
                            .map(token -> ClientRequest.from(request)
                                    .headers(headers -> headers.setBearerAuth(token))
                                    .build());

                    return reqComToken.flatMap(r -> {
                        System.out.println("Request: " + r.method() + " " + r.url());
                        return next.exchange(r);
                    });
                })
                .build();
    }
}
