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

import br.com.stanchese.portaria.modelo.entidades.MoradorTipo;
import br.com.stanchese.portaria.modelo.servicos.MoradorTipoServico;

@Controller
@RequestMapping("/moradortipos")
public class MoradorTipoController {
	
	@Autowired
	private MoradorTipoServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarMoradorTipos(Model model) {
		Iterable<MoradorTipo> moradortipos = servico.listar();

		model.addAttribute("titulo", "Listagem de Tipos de Moradores");
		model.addAttribute("moradortipos", moradortipos);
		return "moradortipo/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarMoradorTipo(@Valid  MoradorTipo moradortipo, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(moradortipo);
			model.addAttribute("msgTxt", "Tipo de Morador salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Tipo de Morador!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("moradortipos", servico.listar());
		return "moradortipo/tabela-moradortipos";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarMoradorTipo(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public MoradorTipo buscarMoradorTipo(@PathVariable Long id) {
		MoradorTipo moradortipo = servico.buscar(id);
		return moradortipo;
	}

}
