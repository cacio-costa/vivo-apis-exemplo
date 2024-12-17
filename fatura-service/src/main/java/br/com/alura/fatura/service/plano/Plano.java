package br.com.alura.fatura.service.plano;

public class Plano {

    private Long id;
    private String nome;
    private String tipo;


    public Plano(Long id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    Plano(br.com.alura.plano.api.Plano plano) {
        this(plano.getId(), plano.getNome(), plano.getTipo());
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
