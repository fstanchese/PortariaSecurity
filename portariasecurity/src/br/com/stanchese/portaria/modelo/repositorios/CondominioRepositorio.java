package br.com.stanchese.portaria.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Condominio;

@Repository
public interface CondominioRepositorio extends JpaRepository<Condominio, Long> {
	
}
