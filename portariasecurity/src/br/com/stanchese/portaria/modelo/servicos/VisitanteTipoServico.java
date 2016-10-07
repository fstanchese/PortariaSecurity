package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.VisitanteTipo;
import br.com.stanchese.portaria.modelo.repositorios.VisitanteTipoRepositorio;

@Service
public class VisitanteTipoServico {

	@Autowired 
	private VisitanteTipoRepositorio repositorio;

	public void salvar(VisitanteTipo visitantetipo){
		repositorio.save(visitantetipo);
	}
	
	public Iterable<VisitanteTipo> listar(){
		return repositorio.findAll();
	}
	
	public VisitanteTipo buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		VisitanteTipo visitantetipo = this.buscar(id);
		repositorio.delete(visitantetipo);
	}
}
