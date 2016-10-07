package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Veiculo;
import br.com.stanchese.portaria.modelo.repositorios.VeiculoRepositorio;

@Service
public class VeiculoServico {

	@Autowired 
	private VeiculoRepositorio repositorio;

	public void salvar(Veiculo veiculo){
		repositorio.save(veiculo);
	}
	
	public Iterable<Veiculo> listar(){
		return repositorio.findAllOrderByMorador();
	}
	
	public Veiculo buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Veiculo veiculo = this.buscar(id);
		repositorio.delete(veiculo);
	}

	public Iterable<Veiculo> listarFiltros(String moradorFiltro, String unidadeFiltro, 
										   String placaFiltro, String modeloFiltro) {
		return repositorio.findByFiltros(moradorFiltro,unidadeFiltro,placaFiltro,modeloFiltro);
	}
}
