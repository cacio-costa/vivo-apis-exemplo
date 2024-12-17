package br.com.alura.fatura.service.plano;

import br.com.alura.plano.api.IntValue;
import br.com.alura.plano.api.PlanoServiceGrpc.PlanoServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class PlanoGrpcClient {

    @GrpcClient("plano-service")
    private PlanoServiceBlockingStub planoServiceStub;

    public Plano buscaPlanoPorId(Long id) {
        br.com.alura.plano.api.Plano dto = planoServiceStub.planoPorId(IntValue.newBuilder().setValue(id).build());
        return new Plano(dto.getId(), dto.getNome(), dto.getTipo());
    }
}
