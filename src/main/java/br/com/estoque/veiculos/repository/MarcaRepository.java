package br.com.estoque.veiculos.repository;

import br.com.estoque.veiculos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
