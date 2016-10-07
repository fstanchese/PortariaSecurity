package br.com.stanchese.portaria.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Veiculo;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

	// lista de Veiculos ordem morador
	@Query("select v from Veiculo v join v.morador m order by m.nome")
	Iterable<Veiculo> findAllOrderByMorador();

	// Filtro de Veiculos
	@Query("select v from Veiculo v "
			+ "join v.morador m "
			+ "join m.unidade u "
			+ "join v.modelo o "
			+ "join o.marca r "
			+ "where m.nome like CONCAT('%',:moradorFiltro,'%')  "
			+ "and u.descricao like CONCAT('%',:unidadeFiltro,'%') "
			+ "and v.placa like CONCAT('%',:placaFiltro,'%') "
			+ "and (o.descricao like CONCAT('%',:modeloFiltro,'%') "
			+ "or r.descricao like CONCAT('%',:modeloFiltro,'%')) order by m.nome")
	Iterable<Veiculo> findByFiltros(@Param("moradorFiltro") String moradorFiltro, 
									@Param("unidadeFiltro") String unidadeFiltro, 
									@Param("placaFiltro") String placaFiltro,
									@Param("modeloFiltro") String modeloFiltro);
}
