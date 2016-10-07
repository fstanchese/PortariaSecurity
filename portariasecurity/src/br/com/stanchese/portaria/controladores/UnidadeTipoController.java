package br.com.stanchese.portaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.stanchese.portaria.modelo.entidades.UnidadeTipo;
import br.com.stanchese.portaria.modelo.servicos.UnidadeTipoServico;

@Controller
@RequestMapping("/unidadetipos")
public class UnidadeTipoController {
	
	@Autowired
	private UnidadeTipoServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarUnidadeTipos(Model model) {
		Iterable<UnidadeTipo> unidadetipos = servico.listar();

		model.addAttribute("titulo", "Listagem de Tipos de Unidade");
		model.addAttribute("unidadetipos", unidadetipos);
		return "unidadetipo/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarUnidadeTipo(@Valid UnidadeTipo unidadetipo, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(unidadetipo);
			model.addAttribute("msgTxt", "Tipo de Unidade salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Tipo de Unidade!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("unidadetipos", servico.listar());
		return "unidadetipo/tabela-unidadetipos";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarUnidadeTipo(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public UnidadeTipo buscarUnidadeTipo(@PathVariable Long id) {
		UnidadeTipo unidadetipo = servico.buscar(id);
		return unidadetipo;
	}

}
