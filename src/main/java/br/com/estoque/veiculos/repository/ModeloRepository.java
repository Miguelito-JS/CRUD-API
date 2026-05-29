package br.com.estoque.veiculos.repository;

import br.com.estoque.veiculos.model.Modelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    List<Modelo> findByMarcaIdOrderByNome(Long marcaId);
}
