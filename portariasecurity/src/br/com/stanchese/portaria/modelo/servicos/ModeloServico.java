package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.repositorios.MarcaRepositorio;
import br.com.stanchese.portaria.modelo.repositorios.ModeloRepositorio;

@Service
public class ModeloServico {

	@Autowired 
	private MarcaRepositorio repositorioMarca;
	
	@Autowired 
	private ModeloRepositorio repositorio;

	public void salvar(Modelo modelo){
		repositorio.save(modelo);
	}
	
	public Iterable<Modelo> listar(){
		return repositorio.findAllOrderByDescricao();
	}
	
	public Modelo buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Modelo modelo = this.buscar(id);
		repositorio.delete(modelo);
	}

	public Iterable<Modelo> buscarModelosByMarca(Long idMarca) {
		Marca marca = repositorioMarca.findOne(idMarca);
		Iterable<Modelo> modelos = repositorio.findAllByMarca(marca);
		return modelos;
	}
	
	public Iterable<Modelo> listarFiltros(String marcaFiltro,String modeloFiltro) {
		return repositorio.findByFiltros(marcaFiltro,modeloFiltro);
	}
}
