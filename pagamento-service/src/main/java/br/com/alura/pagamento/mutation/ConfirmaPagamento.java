package br.com.alura.pagamento.mutation;

import br.com.alura.pagamento.dominio.PagamentoRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class ConfirmaPagamento {

    private PagamentoRepository pagamentoRepository;

    public ConfirmaPagamento(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @DgsMutation
    public boolean confirmaPagamento(@InputArgument Long id) {
        return pagamentoRepository.findById(id)
            .map(pagamento -> {
                pagamento.confirma();
                pagamentoRepository.save(pagamento);
                return true;
            })
            .orElse(false);

    }
}
