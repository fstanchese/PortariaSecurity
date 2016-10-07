package br.com.stanchese.portaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.entidades.Cor;
import br.com.stanchese.portaria.modelo.servicos.CorServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;

@Controller
@RequestMapping("/cores")
public class CorController {

	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;

	@Autowired
	private CorServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarCores(Model model) {

		model.addAttribute("titulo", "Listagem de Cores");
		model.addAttribute("coresFiltro", servico.listar());
		return "cor/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarCor(@Valid Cor cor, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(cor);
			model.addAttribute("msgTxt", "Cor salva com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar a Cor!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("coresFiltro", servico.listar());
		return "cor/tabela-cores";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarCor(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Cor buscarCor(@PathVariable Long id) {
		Cor cor = servico.buscar(id);
		return cor;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
	}

}
