package br.com.alura.cliente.service;

import br.com.alura.cliente.dominio.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NovoClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @Email
    private String email;

    @NotBlank
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cliente criaCliente() {
        return new Cliente(nome, telefone, email, endereco);
    }
}
