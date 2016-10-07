package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.entidades.Funcionario;
import br.com.stanchese.portaria.modelo.repositorios.FuncionarioRepositorio;

@Service
public class CondominioServico {

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	public Condominio condominioUsuario() {

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Funcionario funcionario = funcionarioRepositorio.findOneByLogin(login);
		Condominio condominio = funcionario.getCondominio();
		
		return condominio;
	}

}
