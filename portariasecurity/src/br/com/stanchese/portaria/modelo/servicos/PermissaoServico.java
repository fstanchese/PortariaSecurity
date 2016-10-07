package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Permissao;
import br.com.stanchese.portaria.modelo.repositorios.PermissaoRepositorio;

@Service
public class PermissaoServico {

	@Autowired 
	private PermissaoRepositorio repositorio;

	public Iterable<Permissao> listar(){
		return repositorio.findAllOrderByNome();
	}
	
	public Permissao buscar(long id){
		return repositorio.findOne(id);
	}
	
}
