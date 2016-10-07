package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.UnidadeTipo;

@Repository
public interface UnidadeTipoRepositorio extends JpaRepository<UnidadeTipo, Long> {

	@Query("select u from UnidadeTipo u order by u.descricao")
	List<UnidadeTipo> findAllOrderByDescricao();
}
