package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.repositorios.FuncionarioRepositorio;

@Service
public class AutenticacaoServico implements UserDetailsService {

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return funcionarioRepositorio.findOneByLogin(login);
	}

}
