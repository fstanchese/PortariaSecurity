package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.MoradorTipo;

@Repository
public interface MoradorTipoRepositorio extends JpaRepository<MoradorTipo, Long> {

	@Query("select m from MoradorTipo m order by m.descricao")
	List<MoradorTipo> findAllOrderByDescricao();
}
