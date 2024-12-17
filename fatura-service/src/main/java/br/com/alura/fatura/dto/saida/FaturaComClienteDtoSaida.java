package br.com.alura.fatura.dto.saida;

import br.com.alura.fatura.dominio.Fatura;
import br.com.alura.fatura.dominio.StatusFatura;
import br.com.alura.fatura.service.cliente.Cliente;
import br.com.alura.fatura.service.plano.Plano;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FaturaComClienteDtoSaida {

    private Long id;

    private Plano plano;

    private BigDecimal valor;
    private LocalDate vencimento;

    private StatusFatura status;

    private Cliente cliente;

    public FaturaComClienteDtoSaida(Fatura fatura, Cliente cliente, Plano plano) {
        this.id = fatura.getId();
        this.valor = fatura.getValor();
        this.vencimento = fatura.getVencimento();
        this.status = fatura.getStatus();

        this.plano = plano;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
