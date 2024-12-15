package br.com.alura.fatura.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Fatura {

    @Id
    private Long id;
    private Long clienteId;

    private String plano;
    private LocalDate vencimento;

    @Enumerated(EnumType.STRING)
    private StatusFatura status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public StatusFatura getStatus() {
        return status;
    }

    public void setStatus(StatusFatura status) {
        this.status = status;
    }
}
