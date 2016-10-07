package br.com.stanchese.portaria.modelo.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.repositorios.MarcaRepositorio;
import br.com.stanchese.portaria.modelo.repositorios.ModeloRepositorio;

@Service
public class MarcaServico {

	@Autowired 
	private MarcaRepositorio repositorio;

	@Autowired 
	private ModeloRepositorio repositorioModelo;

	public void salvar(Marca marca){
		repositorio.save(marca);
	}
	
	public Iterable<Marca> listar(){
		return repositorio.findAllOrderByDescricao();
	}
	
	public Marca buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Marca marca = this.buscar(id);
		repositorio.delete(marca);
	}
	
	public List<String> listaMarcaModeloPorVeiculos() {
		ArrayList<String> lista = new ArrayList<String>();
		
		Iterable<Marca> marcas = repositorio.findAllPorVeiculos();
		for (Marca marca : marcas) {
			lista.add(marca.getDescricao());
			List<Modelo> modelos = repositorioModelo.findAllPorVeiculos(marca.getId());
			for (Modelo modelo : modelos) {
				lista.add(modelo.getDescricao());
			}
		}
		return lista;
	}
	
	public Iterable<Marca> listarFiltros(String marcaFiltro) {
		return repositorio.findByFiltros(marcaFiltro);
	}
}
