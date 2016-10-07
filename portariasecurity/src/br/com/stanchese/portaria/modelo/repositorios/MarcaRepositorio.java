package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Marca;

@Repository
public interface MarcaRepositorio extends JpaRepository<Marca, Long> {

	@Query("select m from Marca m order by m.descricao")
	List<Marca> findAllOrderByDescricao();

	@Query(value="select marca.* from marca,veiculo,modelo where "
			+ "marca.id=modelo.marca_id and modelo.id=veiculo.modelo_id "
			+ "group by marca.id,marca.descricao order by marca.descricao",nativeQuery=true)
	List<Marca> findAllPorVeiculos();

	@Query("select m from Marca m where m.descricao like CONCAT('%',:marcaFiltro,'%') order by m.descricao")	
	List<Marca> findByFiltros(@Param("marcaFiltro") String marcaFiltro);
	
}
