package br.com.estoque.veiculos.dto;

import br.com.estoque.veiculos.model.StatusVeiculo;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AtualizacaoVeiculoRequest {

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal preco;

    @NotNull
    @Min(0)
    private Integer quilometragem;

    @NotNull
    private StatusVeiculo status;

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
}
