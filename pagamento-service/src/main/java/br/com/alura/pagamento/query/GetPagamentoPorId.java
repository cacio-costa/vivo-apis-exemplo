package br.com.alura.pagamento.query;

import br.com.alura.pagamento.dominio.Fatura;
import br.com.alura.pagamento.dominio.Pagamento;
import br.com.alura.pagamento.dominio.PagamentoRepository;
import com.netflix.graphql.dgs.*;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@DgsComponent
public class GetPagamentoPorId {

    private WebClient webClient;
    private PagamentoRepository pagamentoRepository;

    public GetPagamentoPorId(PagamentoRepository pagamentoRepository, WebClient webClient) {
        this.webClient = webClient;
        this.pagamentoRepository = pagamentoRepository;
    }

    @DgsQuery
    public Mono<Pagamento> getPagamentoPorId(@InputArgument Long id) {
        System.out.println("INVOCOU O DATA FETCHER");

        return pagamentoRepository.findById(id)
                .map(Mono::just)
                .orElseGet(Mono::empty);
    }

    @DgsData(parentType = "Pagamento", field = "fatura")
    public Mono<Fatura> buscaFaturaPorId(DgsDataFetchingEnvironment env) {
        System.out.println("INVOCOU FATURA DATA FETCHER");

        Pagamento pagamento = env.getSource();
        return webClient.get()
                .uri("http://localhost:8081/v1/faturas/{id}", pagamento.getFaturaId())
                .retrieve()
                .bodyToMono(Fatura.class);
    }

}
