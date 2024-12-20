package br.com.alura.plano.grpc;

import br.com.alura.plano.api.IntValue;
import br.com.alura.plano.api.ListaPlanosRequest;
import br.com.alura.plano.api.ListaPlanosResponse;
import br.com.alura.plano.api.PlanoServiceGrpc;
import br.com.alura.plano.dominio.Plano;
import br.com.alura.plano.dominio.PlanoRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class PlanoGrpc extends PlanoServiceGrpc.PlanoServiceImplBase {

    @Autowired
    private PlanoRepository planoRepository;

    @Override
    public void listaPlanos(ListaPlanosRequest request, StreamObserver<ListaPlanosResponse> responseObserver) {
        System.out.println("Listando planos");

        ListaPlanosResponse.Builder responseBuilder = ListaPlanosResponse.newBuilder();

        planoRepository.findAll()
                .stream()
                .map(this::criaPlanoResponse)
                .forEach(responseBuilder::addPlanos);

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    private br.com.alura.plano.api.Plano criaPlanoResponse(Plano plano) {
        return br.com.alura.plano.api.Plano.newBuilder()
                .setId(plano.getId())
                .setNome(plano.getNome())
                .setTipo(plano.getTipo().name())
                .build();
    }

    @Override
    public void planoPorId(IntValue request, StreamObserver<br.com.alura.plano.api.Plano> responseObserver) {
        System.out.println("Buscando plano por id");

        planoRepository.findById(request.getValue())
                .map(p -> {
                    System.out.println(p);
                    return this.criaPlanoResponse(p);
                })
                .ifPresentOrElse(
                    p -> {
                        responseObserver.onNext(p);
                        responseObserver.onCompleted();
                    },
                    () -> {
                        StatusRuntimeException erro = Status.NOT_FOUND
                                .withDescription("Plano não encontrado")
                                .asRuntimeException();

                        responseObserver.onError(erro);
                    }
                );


    }
}
