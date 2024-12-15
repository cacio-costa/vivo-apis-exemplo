package br.com.alura.pagamento.mutation;

import br.com.alura.pagamento.dominio.Pagamento;
import br.com.alura.pagamento.dominio.PagamentoRepository;
import br.com.alura.pagamento.dominio.StatusPagamento;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@DgsComponent
public class NovoPagamento {

    private PagamentoRepository pagamentoRepository;

    public NovoPagamento(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @DgsMutation
    public Mono<Pagamento> novoPagamento(@InputArgument BigDecimal valor, @InputArgument Long faturaId) {
        Pagamento pagamento = new Pagamento(valor, LocalDate.now(), StatusPagamento.CRIADO, faturaId);
        return Mono.just(pagamentoRepository.save(pagamento));
    }
}
