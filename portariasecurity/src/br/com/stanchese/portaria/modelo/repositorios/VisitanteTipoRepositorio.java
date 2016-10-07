package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.VisitanteTipo;

@Repository
public interface VisitanteTipoRepositorio extends JpaRepository<VisitanteTipo, Long> {
	
	@Query("select v from VisitanteTipo v order by v.descricao")
	List<VisitanteTipo> findAllOrderByDescricao();
}
