package br.com.stanchese.portaria.modelo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Unidade;
import br.com.stanchese.portaria.modelo.repositorios.UnidadeRepositorio;

@Service
public class UnidadeServico {

	@Autowired 
	private UnidadeRepositorio repositorio;

	public void salvar(Unidade unidade){
		repositorio.save(unidade);
	}
	
	public List<Unidade> listar(){
		return repositorio.findAllOrderByDescricao();
	}
	
	public Unidade buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Unidade unidade = this.buscar(id);
		repositorio.delete(unidade);
	}

	public List<Unidade> listarUnidadesPorVeiculos() {
		return repositorio.findAllUnidadesPorVeiculos();
	}

	public List<Unidade> listarUnidadesPorMorador() {
		return repositorio.findAllUnidadesPorMorador();
	}

	public List<Unidade> listarFiltros(String unidadeFiltro, String moradorTipoFiltro, String unidadeTipoFiltro, String blocoFiltro,
			String andarFiltro) {
		return repositorio.findByFiltros(unidadeFiltro,moradorTipoFiltro,unidadeTipoFiltro,blocoFiltro,andarFiltro);
	}

}
