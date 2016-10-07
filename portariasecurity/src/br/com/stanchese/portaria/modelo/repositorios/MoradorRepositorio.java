package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Morador;

@Repository
public interface MoradorRepositorio extends JpaRepository<Morador, Long> {

	// lista de Moradores ordem nome
	@Query("select m from Morador m order by m.nome")
	List<Morador> findAllOrderByNome();
	
	// Lista de Moradores por Veiculos
	@Query(value="select morador.* from morador,veiculo "
			+ "where morador.id=veiculo.morador_id group by Morador.id,morador.nome "
			+ "order by morador.nome", nativeQuery = true)
	List<Morador> findAllMoradoresPorVeiculos();

	// Filtro de Moradores
	@Query("select m from Morador m "
			+ "join m.unidade u "
			+ "where m.nome like CONCAT('%',:moradorFiltro,'%')  "
			+ "and u.descricao like CONCAT('%',:unidadeFiltro,'%') order by m.nome")
	List<Morador> findByFiltros(@Param("moradorFiltro") String moradorFiltro, 
									@Param("unidadeFiltro") String unidadeFiltro);
}
