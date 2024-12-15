package br.com.alura.cliente.controller;

import br.com.alura.cliente.dominio.Cliente;
import br.com.alura.cliente.service.ClienteService;
import br.com.alura.cliente.service.NovoClienteRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v2/clientes")
public class ClienteControllerV2 {

    private ClienteService clienteService;

    public ClienteControllerV2(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.listaTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid NovoClienteRequest novoClienteRequest, UriComponentsBuilder uriBuilder) {
        Cliente clienteSalvo = clienteService.salva(novoClienteRequest);

        URI location = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(location).body(clienteSalvo);
    }
}
