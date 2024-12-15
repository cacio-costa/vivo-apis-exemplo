package br.com.alura.cliente.service;

import br.com.alura.cliente.dominio.Cliente;
import br.com.alura.cliente.dominio.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public Cliente salva(NovoClienteRequest novoClienteRequest) {
        Cliente cliente = novoClienteRequest.criaCliente();
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listaTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> porId(Long id) {
        return clienteRepository.findById(id);
    }
}
