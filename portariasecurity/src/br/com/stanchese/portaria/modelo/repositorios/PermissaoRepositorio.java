package br.com.stanchese.portaria.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Permissao;

@Repository
public interface PermissaoRepositorio extends JpaRepository<Permissao, Long> {
	@Query("select p from Permissao p order by p.nome")
	List<Permissao> findAllOrderByNome();
}
