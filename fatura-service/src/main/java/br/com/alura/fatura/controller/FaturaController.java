package br.com.alura.fatura.controller;

import br.com.alura.fatura.dominio.Fatura;
import br.com.alura.fatura.dominio.FaturaRepository;
import br.com.alura.fatura.dto.saida.FaturaComClienteDtoSaida;
import br.com.alura.fatura.service.cliente.Cliente;
import br.com.alura.fatura.service.cliente.ClienteRestService;
import br.com.alura.fatura.service.plano.Plano;
import br.com.alura.fatura.service.plano.PlanoGrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/faturas")
public class FaturaController {

    private ClienteRestService clienteRestService;
    private FaturaRepository faturaRepository;
    private PlanoGrpcClient planoGrpcClient;

    public FaturaController(FaturaRepository faturaRepository, ClienteRestService clienteRestService, PlanoGrpcClient planoGrpcClient) {
        this.faturaRepository = faturaRepository;
        this.clienteRestService = clienteRestService;
        this.planoGrpcClient = planoGrpcClient;
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
}
