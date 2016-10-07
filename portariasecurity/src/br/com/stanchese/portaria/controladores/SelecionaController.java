package br.com.stanchese.portaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.entidades.Unidade;
import br.com.stanchese.portaria.modelo.servicos.ModeloServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeServico;

@Controller
public class SelecionaController {

	@Autowired
	private ModeloServico servicoModelo;

	@Autowired
	private UnidadeServico servicoUnidade;
	
	@RequestMapping(method = RequestMethod.GET, value = "/modelosSelecionar/{id}")
	@ResponseBody
	public Iterable<Modelo> buscarModelos(@PathVariable Long id) {
		Iterable<Modelo> modelos = servicoModelo.buscarModelosByMarca(id);
		return modelos;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/unidadesSelecionar")
	@ResponseBody
	public Iterable<Unidade> buscarUnidades() {
		Iterable<Unidade> unidades = servicoUnidade.listar();
		return unidades;
	}
}
