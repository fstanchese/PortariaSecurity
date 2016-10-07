package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Cor;
import br.com.stanchese.portaria.modelo.repositorios.CorRepositorio;

@Service
public class CorServico {

	@Autowired 
	private CorRepositorio repositorio;

	public void salvar(Cor cor){
		repositorio.save(cor);
	}
	
	public Iterable<Cor> listar(){
		return repositorio.findAllOrderByDescricao();
	}
	
	public Cor buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Cor cor = this.buscar(id);
		repositorio.delete(cor);
	}
	
	public Iterable<Cor> listarFiltros(String corFiltro) {
		return repositorio.findByFiltros(corFiltro);
	}
}
