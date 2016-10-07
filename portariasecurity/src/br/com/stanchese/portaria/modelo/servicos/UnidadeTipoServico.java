package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.UnidadeTipo;
import br.com.stanchese.portaria.modelo.repositorios.UnidadeTipoRepositorio;

@Service
public class UnidadeTipoServico {

	@Autowired 
	private UnidadeTipoRepositorio repositorio;

	public void salvar(UnidadeTipo unidadetipo){
		repositorio.save(unidadetipo);
	}
	
	public Iterable<UnidadeTipo> listar(){
		return repositorio.findAll();
	}
	
	public UnidadeTipo buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		UnidadeTipo unidadetipo = this.buscar(id);
		repositorio.delete(unidadetipo);
	}
}
