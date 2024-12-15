package br.com.alura.fatura.dto.saida;

import br.com.alura.fatura.dominio.Fatura;
import br.com.alura.fatura.dominio.StatusFatura;
import br.com.alura.fatura.service.Cliente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FaturaComClienteDtoSaida {

    private Long id;

    private String plano;
    private LocalDate vencimento;

    private StatusFatura status;

    private Cliente cliente;

    public FaturaComClienteDtoSaida(Fatura fatura, Cliente cliente) {
        this.id = fatura.getId();
        this.plano = fatura.getPlano();
        this.vencimento = fatura.getVencimento();
        this.status = fatura.getStatus();
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
