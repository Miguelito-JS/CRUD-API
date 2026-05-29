package br.com.estoque.veiculos.service;

import br.com.estoque.veiculos.dto.ModeloRequest;
import br.com.estoque.veiculos.model.Marca;
import br.com.estoque.veiculos.model.Modelo;
import br.com.estoque.veiculos.repository.ModeloRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaService marcaService;

    public ModeloService(ModeloRepository modeloRepository, MarcaService marcaService) {
        this.modeloRepository = modeloRepository;
        this.marcaService = marcaService;
    }

    public List<Modelo> listar(Long marcaId) {
        if (marcaId == null) {
            return modeloRepository.findAll();
        }

        return modeloRepository.findByMarcaIdOrderByNome(marcaId);
    }

    public Modelo buscarPorId(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Modelo nao encontrado."));
    }

    public Modelo salvar(ModeloRequest request) {
        Marca marca = marcaService.buscarPorId(request.getMarcaId());
        return modeloRepository.save(new Modelo(null, request.getNome(), marca));
    }

    public Modelo atualizar(Long id, ModeloRequest request) {
        Modelo modelo = buscarPorId(id);
        modelo.setNome(request.getNome());
        modelo.setMarca(marcaService.buscarPorId(request.getMarcaId()));
        return modeloRepository.save(modelo);
    }

    public void remover(Long id) {
        modeloRepository.delete(buscarPorId(id));
    }
}
