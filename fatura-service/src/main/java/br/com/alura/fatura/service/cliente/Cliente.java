package br.com.alura.fatura.service.cliente;

public class Cliente {

    private Long id;
    private String nome;

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
