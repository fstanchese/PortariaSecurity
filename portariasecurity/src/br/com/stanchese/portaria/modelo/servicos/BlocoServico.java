package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Bloco;
import br.com.stanchese.portaria.modelo.repositorios.BlocoRepositorio;

@Service
public class BlocoServico {

	@Autowired 
	private BlocoRepositorio repositorio;

	public void salvar(Bloco bloco){
		repositorio.save(bloco);
	}
	
	public Iterable<Bloco> listar(){
		return repositorio.findAllOrderByDescricao();
	}
	
	public Bloco buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Bloco bloco = this.buscar(id);
		repositorio.delete(bloco);
	}

	public Iterable<Bloco> listarFiltros(String blocoFiltro) {
		return repositorio.findByFiltros(blocoFiltro);
	}
}
