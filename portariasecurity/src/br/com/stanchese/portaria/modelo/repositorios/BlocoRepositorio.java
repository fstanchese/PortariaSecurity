package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Bloco;

@Repository
public interface BlocoRepositorio extends JpaRepository<Bloco, Long> {

	@Query("select b from Bloco b order by b.descricao")
	List<Bloco> findAllOrderByDescricao();

	@Query("select b from Bloco b where b.descricao like CONCAT('%',:blocoFiltro,'%') order by b.descricao")	
	List<Bloco> findByFiltros(@Param("blocoFiltro") String blocoFiltro);

}