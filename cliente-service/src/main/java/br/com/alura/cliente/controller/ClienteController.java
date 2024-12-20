package br.com.alura.cliente.controller;

import br.com.alura.cliente.dominio.Cliente;
import br.com.alura.cliente.service.ClienteService;
import br.com.alura.cliente.service.NovoClienteRequest;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.listaTodos();
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> getClienteById(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(3000);

        Link self = Link.of("http://localhost:8080/v1/clientes/" + id, "self");
        Link all = Link.of("http://localhost:8080/v1/clientes", "all");
        Link planos = Link.of("http://localhost:8080/v1/clientes/" + id + "/planos", "planos");


        return clienteService.porId(id)
                .map(cliente -> EntityModel.of(cliente, self, all, planos))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente not found"));
    }

    @PostMapping()
    public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid NovoClienteRequest novoClienteRequest, UriComponentsBuilder uriBuilder) {
        Cliente clienteSalvo = clienteService.salva(novoClienteRequest);

        URI location = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(location).body(clienteSalvo);
    }
}
