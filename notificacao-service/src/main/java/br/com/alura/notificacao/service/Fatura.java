package br.com.alura.notificacao.service;

import java.io.Serializable;
import java.math.BigDecimal;

public class Fatura implements Serializable {

    private Long id;
    private Long clienteId;
    private BigDecimal valor;
    private String status;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                '}';
    }
}
