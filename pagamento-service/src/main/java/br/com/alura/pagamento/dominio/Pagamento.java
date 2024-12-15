package br.com.alura.pagamento.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long faturaId;

    private BigDecimal valor;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    public Pagamento() {
    }

    public Pagamento(BigDecimal valor, LocalDate data, StatusPagamento status, Long faturaId) {
        this(null, valor, data, status, faturaId);
    }

    public Pagamento(Long id, BigDecimal valor, LocalDate data, StatusPagamento status, Long faturaId) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.status = status;
        this.faturaId = faturaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public Long getFaturaId() {
        return faturaId;
    }

    public void setFaturaId(Long faturaId) {
        this.faturaId = faturaId;
    }

    public void confirma() {
        this.status = StatusPagamento.CONFIRMADO;
    }
}
