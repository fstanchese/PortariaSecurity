package br.com.stanchese.portaria.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stanchese.portaria.modelo.entidades.Funcionario;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {
	
	Funcionario findOneByLogin(String login);

}
