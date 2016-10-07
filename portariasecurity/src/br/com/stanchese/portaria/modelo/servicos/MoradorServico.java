package br.com.stanchese.portaria.modelo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Morador;
import br.com.stanchese.portaria.modelo.repositorios.MoradorRepositorio;

@Service
public class MoradorServico {

	@Autowired 
	private MoradorRepositorio repositorio;

	public void salvar(Morador morador){
		repositorio.save(morador);
	}
	
	public List<Morador> listar(){
		return repositorio.findAllOrderByNome();
	}
	
	public List<Morador> listarMoradoresPorVeiculos() {
		return repositorio.findAllMoradoresPorVeiculos();
	}
	
	public Morador buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Morador morador = this.buscar(id);
		repositorio.delete(morador);
	}

	public Iterable<Morador> listarFiltros(String moradorFiltro, String unidadeFiltro) {
		return repositorio.findByFiltros(moradorFiltro,unidadeFiltro);
	}

}
