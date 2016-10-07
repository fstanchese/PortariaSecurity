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

import br.com.stanchese.portaria.modelo.entidades.VisitanteTipo;
import br.com.stanchese.portaria.modelo.servicos.VisitanteTipoServico;

@Controller
@RequestMapping("/visitantetipos")
public class VisitanteTipoController {

	@Autowired
	private VisitanteTipoServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarVisitantes(Model model) {
		Iterable<VisitanteTipo> visitantetipos = servico.listar();

		model.addAttribute("titulo", "Listagem de Tipos de Visitante");
		model.addAttribute("visitantetipos", visitantetipos);
		return "visitantetipo/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarVisitanteTipo(@Valid VisitanteTipo visitantetipo, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(visitantetipo);
			model.addAttribute("msgTxt", "Tipo de Visitante salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Tipo de Visitante!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("visitantetipos", servico.listar());
		return "visitantetipo/tabela-visitantetipos";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarVisitanteTipo(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public VisitanteTipo buscarVisitanteTipo(@PathVariable Long id) {
		VisitanteTipo visitantetipo = servico.buscar(id);
		return visitantetipo;
	}

}
