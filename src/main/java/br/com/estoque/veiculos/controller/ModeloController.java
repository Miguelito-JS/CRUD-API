package br.com.estoque.veiculos.controller;

import br.com.estoque.veiculos.dto.ModeloRequest;
import br.com.estoque.veiculos.model.Modelo;
import br.com.estoque.veiculos.service.ModeloService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    private final ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public List<Modelo> listar(@RequestParam(required = false) Long marcaId) {
        return modeloService.listar(marcaId);
    }

    @GetMapping("/{id}")
    public Modelo buscarPorId(@PathVariable Long id) {
        return modeloService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Modelo salvar(@Valid @RequestBody ModeloRequest request) {
        return modeloService.salvar(request);
    }

    @PutMapping("/{id}")
    public Modelo atualizar(@PathVariable Long id, @Valid @RequestBody ModeloRequest request) {
        return modeloService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        modeloService.remover(id);
    }
}
