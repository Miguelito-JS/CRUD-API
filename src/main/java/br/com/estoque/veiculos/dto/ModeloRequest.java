package br.com.estoque.veiculos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModeloRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long marcaId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }
}
