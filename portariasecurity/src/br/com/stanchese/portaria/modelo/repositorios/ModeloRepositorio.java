package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.entidades.Modelo;

@Repository
public interface ModeloRepositorio extends JpaRepository<Modelo, Long> {
	@Query("select m from Modelo m order by m.descricao")
	List<Modelo> findAllOrderByDescricao();

	List<Modelo> findAllByMarca(Marca marca);

	@Query(value="select modelo.* from marca,veiculo,modelo where "
			+ "marca.id=modelo.marca_id and modelo.id=veiculo.modelo_id and marca.id=?1 "
			+ "group by marca.id,marca.descricao order by marca.descricao",nativeQuery=true)
	List<Modelo> findAllPorVeiculos(Long idMarca);	
	
	@Query("select m from Modelo m "
			+ "join m.marca u "
			+ "where m.descricao like CONCAT('%',:modeloFiltro,'%') "
			+ "and u.descricao like CONCAT('%',:marcaFiltro,'%') order by m.descricao")	
	List<Modelo> findByFiltros(@Param("marcaFiltro") String marcaFiltro,
							   @Param("modeloFiltro") String modeloFiltro);
}
