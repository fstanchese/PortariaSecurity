package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Funcao;
import br.com.stanchese.portaria.modelo.repositorios.FuncaoRepositorio;

@Service
public class FuncaoServico {

	@Autowired 
	private FuncaoRepositorio repositorio;

	public void salvar(Funcao funcao){
		repositorio.save(funcao);
	}
	
	public Iterable<Funcao> listar(){
		return repositorio.findAll();
	}
	
	public Funcao buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Funcao funcao = this.buscar(id);
		repositorio.delete(funcao);
	}
}
