package br.com.estoque.veiculos.service;

import br.com.estoque.veiculos.model.Marca;
import br.com.estoque.veiculos.repository.MarcaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Marca nao encontrada."));
    }

    public Marca salvar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca atualizar(Long id, Marca dados) {
        Marca marca = buscarPorId(id);
        marca.setNome(dados.getNome());
        return marcaRepository.save(marca);
    }

    public void remover(Long id) {
        marcaRepository.delete(buscarPorId(id));
    }
}
