package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Cor;

@Repository
public interface CorRepositorio extends JpaRepository<Cor, Long> {

	@Query("select c from Cor c order by c.descricao")
	List<Cor> findAllOrderByDescricao();
	
	@Query("select c from Cor c where c.descricao like CONCAT('%',:corFiltro,'%') order by c.descricao")	
	List<Cor> findByFiltros(@Param("corFiltro") String corFiltro);

}
