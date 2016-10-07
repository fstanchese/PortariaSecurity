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

import br.com.stanchese.portaria.modelo.entidades.Andar;
import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.servicos.AndarServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;

@Controller
@RequestMapping("/andares")
public class AndarController {

	@Autowired 
	private CondominioPropertyEditor condominioPropertyEditor;

	@Autowired
	private AndarServico servico;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listarAndares(Model model) {
		model.addAttribute("titulo", "Listagem de Andares");
		model.addAttribute("andaresFiltro", servico.listar());
		return "andar/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarAndar(@Valid Andar andar, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			servico.salvar(andar);
			model.addAttribute("msgTxt", "Andar salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Andar!");
			model.addAttribute("msgTipo", "danger");
		}

		return "andar/tabela-andares";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarAndar(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Andar buscarAndar(@PathVariable Long id) {
		Andar andar = servico.buscar(id);
		return andar;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
	}
}
