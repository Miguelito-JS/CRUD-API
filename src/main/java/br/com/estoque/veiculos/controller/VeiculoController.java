package br.com.estoque.veiculos.controller;

import br.com.estoque.veiculos.dto.AtualizacaoVeiculoRequest;
import br.com.estoque.veiculos.dto.VeiculoRequest;
import br.com.estoque.veiculos.model.StatusVeiculo;
import br.com.estoque.veiculos.model.Veiculo;
import br.com.estoque.veiculos.service.VeiculoService;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> listar(
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) Long modeloId,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) StatusVeiculo status
    ) {
        return veiculoService.listarComFiltros(marcaId, modeloId, precoMin, precoMax, ano, status);
    }

    @GetMapping("/{id}")
    public Veiculo buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo salvar(@Valid @RequestBody VeiculoRequest request) {
        return veiculoService.salvar(request);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @Valid @RequestBody VeiculoRequest request) {
        return veiculoService.atualizar(id, request);
    }

    @PatchMapping("/{id}/informacoes")
    public Veiculo atualizarInformacoes(
            @PathVariable Long id,
            @Valid @RequestBody AtualizacaoVeiculoRequest request
    ) {
        return veiculoService.atualizarInformacoes(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        veiculoService.remover(id);
    }
}
