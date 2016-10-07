package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.MoradorTipo;
import br.com.stanchese.portaria.modelo.repositorios.MoradorTipoRepositorio;

@Service
public class MoradorTipoServico {

	@Autowired 
	private MoradorTipoRepositorio repositorio;

	public void salvar(MoradorTipo moradortipo){
		repositorio.save(moradortipo);
	}
	
	public Iterable<MoradorTipo> listar(){
		return repositorio.findAll();
	}
	
	public MoradorTipo buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		MoradorTipo moradortipo = this.buscar(id);
		repositorio.delete(moradortipo);
	}
}
