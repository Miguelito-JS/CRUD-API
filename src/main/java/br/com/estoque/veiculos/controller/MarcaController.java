package br.com.estoque.veiculos.controller;

import br.com.estoque.veiculos.model.Marca;
import br.com.estoque.veiculos.service.MarcaService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public List<Marca> listar() {
        return marcaService.listar();
    }

    @GetMapping("/{id}")
    public Marca buscarPorId(@PathVariable Long id) {
        return marcaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marca salvar(@Valid @RequestBody Marca marca) {
        return marcaService.salvar(marca);
    }

    @PutMapping("/{id}")
    public Marca atualizar(@PathVariable Long id, @Valid @RequestBody Marca marca) {
        return marcaService.atualizar(id, marca);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        marcaService.remover(id);
    }
}
