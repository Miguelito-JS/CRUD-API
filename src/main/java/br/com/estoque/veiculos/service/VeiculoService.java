package br.com.estoque.veiculos.service;

import br.com.estoque.veiculos.dto.AtualizacaoVeiculoRequest;
import br.com.estoque.veiculos.dto.VeiculoRequest;
import br.com.estoque.veiculos.model.Marca;
import br.com.estoque.veiculos.model.Modelo;
import br.com.estoque.veiculos.model.StatusVeiculo;
import br.com.estoque.veiculos.model.Veiculo;
import br.com.estoque.veiculos.repository.VeiculoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ModeloService modeloService;

    public VeiculoService(VeiculoRepository veiculoRepository, ModeloService modeloService) {
        this.veiculoRepository = veiculoRepository;
        this.modeloService = modeloService;
    }

    public List<Veiculo> listarComFiltros(
            Long marcaId,
            Long modeloId,
            BigDecimal precoMin,
            BigDecimal precoMax,
            Integer ano,
            StatusVeiculo status
    ) {
        return veiculoRepository.findAll(filtros(marcaId, modeloId, precoMin, precoMax, ano, status));
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Veiculo nao encontrado."));
    }

    public Veiculo salvar(VeiculoRequest request) {
        Modelo modelo = modeloService.buscarPorId(request.getModeloId());
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modelo);
        veiculo.setAno(request.getAno());
        veiculo.setCor(request.getCor());
        veiculo.setPreco(request.getPreco());
        veiculo.setQuilometragem(request.getQuilometragem());
        veiculo.setStatus(request.getStatus());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, VeiculoRequest request) {
        Veiculo veiculo = buscarPorId(id);
        veiculo.setModelo(modeloService.buscarPorId(request.getModeloId()));
        veiculo.setAno(request.getAno());
        veiculo.setCor(request.getCor());
        veiculo.setPreco(request.getPreco());
        veiculo.setQuilometragem(request.getQuilometragem());
        veiculo.setStatus(request.getStatus());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarInformacoes(Long id, AtualizacaoVeiculoRequest request) {
        Veiculo veiculo = buscarPorId(id);
        veiculo.setPreco(request.getPreco());
        veiculo.setQuilometragem(request.getQuilometragem());
        veiculo.setStatus(request.getStatus());
        return veiculoRepository.save(veiculo);
    }

    public void remover(Long id) {
        veiculoRepository.delete(buscarPorId(id));
    }

    private Specification<Veiculo> filtros(
            Long marcaId,
            Long modeloId,
            BigDecimal precoMin,
            BigDecimal precoMax,
            Integer ano,
            StatusVeiculo status
    ) {
        return (root, query, builder) -> {
            Join<Veiculo, Modelo> modelo = root.join("modelo", JoinType.INNER);
            Join<Modelo, Marca> marca = modelo.join("marca", JoinType.INNER);
            List<Predicate> predicates = new ArrayList<>();

            if (marcaId != null) {
                predicates.add(builder.equal(marca.get("id"), marcaId));
            }
            if (modeloId != null) {
                predicates.add(builder.equal(modelo.get("id"), modeloId));
            }
            if (precoMin != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("preco"), precoMin));
            }
            if (precoMax != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("preco"), precoMax));
            }
            if (ano != null) {
                predicates.add(builder.equal(root.get("ano"), ano));
            }
            if (status != null) {
                predicates.add(builder.equal(root.get("status"), status));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
