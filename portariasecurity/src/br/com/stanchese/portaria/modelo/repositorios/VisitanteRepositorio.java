package br.com.stanchese.portaria.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Visitante;

@Repository
public interface VisitanteRepositorio extends JpaRepository<Visitante, Long> {
	
}
