package br.com.stanchese.portaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stanchese.portaria.modelo.entidades.Andar;
import br.com.stanchese.portaria.modelo.repositorios.AndarRepositorio;

@Service
public class AndarServico {

	@Autowired
	private AndarRepositorio repositorio;

	public void salvar(Andar andar) {
		repositorio.save(andar);
	}

	public Iterable<Andar> listar() {
		return repositorio.findAllOrderByDescricao();
	}

	public Andar buscar(long id) {
		return repositorio.findOne(id);
	}

	public void remover(long id) {
		Andar Andar = this.buscar(id);
		repositorio.delete(Andar);
	}

	public Iterable<Andar> listarFiltros(String andarFiltro) {
		return repositorio.findByFiltros(andarFiltro);
	}
}
