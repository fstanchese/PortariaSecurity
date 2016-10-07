package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Visitante;
import br.com.stanchese.portaria.modelo.repositorios.VisitanteRepositorio;

@Service
public class VisitanteServico {

	@Autowired 
	private VisitanteRepositorio repositorio;

	public void salvar(Visitante visitante){
		repositorio.save(visitante);
	}
	
	public Iterable<Visitante> listar(){
		return repositorio.findAll();
	}
	
	public Visitante buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Visitante visitante = this.buscar(id);
		repositorio.delete(visitante);
	}
}
