package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Funcionario;
import br.com.stanchese.portaria.modelo.repositorios.FuncionarioRepositorio;

@Service
public class FuncionarioServico {

	@Autowired 
	private FuncionarioRepositorio repositorio;

	public void salvar(Funcionario funcionario){
		repositorio.save(funcionario);
	}
	
	public Iterable<Funcionario> listar(){
		return repositorio.findAll();
	}
	
	public Funcionario buscar(long id){
		return repositorio.findOne(id);
	}
	
	public void remover(long id){
		Funcionario funcionario = this.buscar(id);
		repositorio.delete(funcionario);
	}
}
