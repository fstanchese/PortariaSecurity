package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Unidade;

@Repository
public interface UnidadeRepositorio extends JpaRepository<Unidade, Long> {

	// lista de Unidades ordem descricao
	@Query("select u from Unidade u "
			+ "join u.moradorTipo a "
			+ "join u.unidadeTipo b "
			+ "left JOIN u.andar c "
			+ "left JOIN u.bloco d order by u.descricao")
	List<Unidade> findAllOrderByDescricao();

	// lista de Unidades por Veiculos
	@Query(value="select unidade.* from unidade,morador,veiculo where "
			+ " unidade.id=morador.unidade_id and veiculo.morador_id=morador.id group by"
			+ " unidade.id,unidade.descricao order by unidade.descricao",nativeQuery=true)
	List<Unidade> findAllUnidadesPorVeiculos();

	// lista de Unidades por Moradores
	@Query(value="select unidade.* from unidade,morador where "
			+ " unidade.id=morador.unidade_id group by"
			+ " unidade.id,unidade.descricao order by unidade.descricao",nativeQuery=true)	
	List<Unidade> findAllUnidadesPorMorador();

	// Filtro de Unidades
	@Query("select u from Unidade u "
			+ "join u.moradorTipo c "
			+ "join u.unidadeTipo d "
			+ "left JOIN u.andar a "
			+ "left JOIN u.bloco b "
			+ "where "
			+ "(u.descricao like CONCAT('%',:unidadeFiltro,'%')) "
			+ "and "			
			+ "(c.descricao like CONCAT('%',:moradorTipoFiltro,'%')) "
			+ "and "
			+ "(d.descricao like CONCAT('%',:unidadeTipoFiltro,'%')) "
			+ "and "
			+ "(b.descricao like CONCAT('%',:blocoFiltro,'%') or b.descricao is null) "
			+ "and "
			+ "(a.descricao like CONCAT('%',:andarFiltro,'%') or a.descricao is null) "			
			+ "order by u.descricao")
	List<Unidade> findByFiltros(@Param("unidadeFiltro") String unidadeFiltro,
								@Param("moradorTipoFiltro") String moradorTipoFiltro, 
								@Param("unidadeTipoFiltro") String unidadeTipoFiltro, 
								@Param("blocoFiltro") String blocoFiltro,
								@Param("andarFiltro") String andarFiltro);
}
