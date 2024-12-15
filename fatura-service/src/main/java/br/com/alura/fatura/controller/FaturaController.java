package br.com.alura.fatura.controller;

import br.com.alura.fatura.dominio.Fatura;
import br.com.alura.fatura.dominio.FaturaRepository;
import br.com.alura.fatura.dto.saida.FaturaComClienteDtoSaida;
import br.com.alura.fatura.service.Cliente;
import br.com.alura.fatura.service.ClienteRestService;
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

    public FaturaController(FaturaRepository faturaRepository, ClienteRestService clienteRestService) {
        this.faturaRepository = faturaRepository;
        this.clienteRestService = clienteRestService;
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

                    return new FaturaComClienteDtoSaida(fatura, cliente);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
