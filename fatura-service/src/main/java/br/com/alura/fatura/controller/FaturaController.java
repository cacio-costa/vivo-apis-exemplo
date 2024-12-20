package br.com.alura.fatura.controller;

import br.com.alura.fatura.dominio.Fatura;
import br.com.alura.fatura.dominio.FaturaRepository;
import br.com.alura.fatura.dominio.StatusFatura;
import br.com.alura.fatura.dto.saida.FaturaComClienteDtoSaida;
import br.com.alura.fatura.service.cliente.Cliente;
import br.com.alura.fatura.service.cliente.ClienteRestService;
import br.com.alura.fatura.service.fatura.FaturaPublisher;
import br.com.alura.fatura.service.plano.Plano;
import br.com.alura.fatura.service.plano.PlanoGrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/faturas")
public class FaturaController {

    private ClienteRestService clienteRestService;
    private FaturaRepository faturaRepository;
    private PlanoGrpcClient planoGrpcClient;

    private FaturaPublisher faturaPublisher;

    public FaturaController(
            FaturaRepository faturaRepository,
            ClienteRestService clienteRestService,
            PlanoGrpcClient planoGrpcClient,
            FaturaPublisher faturaPublisher
    ) {
        this.faturaRepository = faturaRepository;
        this.clienteRestService = clienteRestService;
        this.planoGrpcClient = planoGrpcClient;
        this.faturaPublisher = faturaPublisher;
    }

    @GetMapping
    public List<Fatura> lista() {
        return faturaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaComClienteDtoSaida> buscaPorId(@PathVariable Long id) {
        return faturaRepository.findById(id)
                .map(fatura -> {
                    Cliente cliente = clienteRestService.buscaClientePorId(fatura.getClienteId());

                    Plano plano = planoGrpcClient.buscaPlanoPorId(fatura.getPlanoId());
                    return new FaturaComClienteDtoSaida(fatura, cliente, plano);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/{status}")
    @Transactional
    public ResponseEntity<Fatura> atualizaStatus(@PathVariable Long id, @PathVariable String status) {
        return faturaRepository.findById(id)
                .map(fatura -> {
                    StatusFatura statusFatura = StatusFatura.valueOf(status);
                    fatura.setStatus(statusFatura);

                    return faturaRepository.save(fatura);
                })
                .map(faturaAtualizada -> {
                    faturaPublisher.notificaFaturaPaga(faturaAtualizada);

                    return ResponseEntity.ok(faturaAtualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
