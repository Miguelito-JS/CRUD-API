package br.com.estoque.veiculos.dto;

import br.com.estoque.veiculos.model.StatusVeiculo;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VeiculoRequest {

    @NotNull
    private Long modeloId;

    @NotNull
    @Min(1900)
    @Max(2100)
    private Integer ano;

    @NotBlank
    private String cor;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal preco;

    @NotNull
    @Min(0)
    private Integer quilometragem;

    @NotNull
    private StatusVeiculo status;

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

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
