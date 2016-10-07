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
import br.com.stanchese.portaria.modelo.entidades.Funcao;
import br.com.stanchese.portaria.modelo.servicos.FuncaoServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;

@Controller
@RequestMapping("/funcoes")
public class FuncaoController {
	
	@Autowired 
	private CondominioPropertyEditor condominioPropertyEditor;
	
	@Autowired
	private FuncaoServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarFuncoes(Model model) {
		Iterable<Funcao> funcoes = servico.listar();

		model.addAttribute("titulo", "Listagem de Funções");
		model.addAttribute("funcoes", funcoes);
		return "funcao/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarFuncao(@Valid Funcao funcao, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(funcao);
			model.addAttribute("msgTxt", "Função salva com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar a Função!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("funcoes", servico.listar());
		return "funcao/tabela-funcoes";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarFuncao(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Funcao buscarFuncao(@PathVariable Long id) {
		Funcao funcao = servico.buscar(id);
		return funcao;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
	}
	
}
