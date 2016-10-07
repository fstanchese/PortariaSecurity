package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Andar;

@Repository
public interface AndarRepositorio extends JpaRepository<Andar, Long> {
	
	@Query("select a from Andar a order by a.descricao")
	List<Andar> findAllOrderByDescricao();

	@Query("select a from Andar a where a.descricao like CONCAT('%',:andarFiltro,'%') order by a.descricao")	
	List<Andar> findByFiltros(@Param("andarFiltro") String andarFiltro);
}
