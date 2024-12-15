package br.com.alura.cliente;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi v1ApiGroup() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("API v1")
                        .description("Documentação da versão 1 da API")
                        .version("v1")
                        .contact(new Contact()
                                .name("Cácio Costa")
                                .email("cacio.costa@alura.com.br"))))
                .build();
    }

    @Bean
    public GroupedOpenApi v2ApiGroup() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/v2/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("API v2")
                        .description("Documentação da versão 2 da API")
                        .version("v2")
                        .contact(new Contact()
                                .name("Cácio Costa")
                                .email("cacio.costa@alura.com.br"))))
                .build();
    }

}
