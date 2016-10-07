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

import br.com.stanchese.portaria.modelo.entidades.Bloco;
import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.servicos.BlocoServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;

@Controller
@RequestMapping("/blocos")
public class BlocoController {

	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;

	@Autowired
	private BlocoServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarBlocos(Model model) {

		model.addAttribute("titulo", "Listagem de Blocos");
		model.addAttribute("blocosFiltro", servico.listar());
		return "bloco/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarBloco(@Valid Bloco bloco, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(bloco);
			model.addAttribute("msgTxt", "Bloco salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Bloco!");
			model.addAttribute("msgTipo", "danger");
		}
		model.addAttribute("blocosFiltro", servico.listar());
		return "bloco/tabela-blocos";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarBloco(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Bloco buscarBloco(@PathVariable Long id) {
		Bloco bloco = servico.buscar(id);
		return bloco;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
	}

}
