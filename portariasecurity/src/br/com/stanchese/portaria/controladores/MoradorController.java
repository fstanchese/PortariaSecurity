package br.com.stanchese.portaria.controladores;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.entidades.Morador;
import br.com.stanchese.portaria.modelo.entidades.Unidade;
import br.com.stanchese.portaria.modelo.servicos.MoradorServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;
import br.com.stanchese.portaria.propriedades.UnidadePropertyEditor;

@Controller
@RequestMapping("/moradores")
public class MoradorController {

	@Autowired
	private MoradorServico servico;

	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;

	@Autowired
	private UnidadePropertyEditor unidadePropertyEditor;

	@Autowired
	private UnidadeServico servicoUnidade;

	@Autowired
	private MoradorServico servicoMorador;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listarUnidades(Model model) {

		model.addAttribute("titulo", "Listagem de Moradores");
		model.addAttribute("unidades", servicoUnidade.listar());
		model.addAttribute("moradoresFiltro", servicoMorador.listar());
		model.addAttribute("unidadesFiltro", servicoUnidade.listarUnidadesPorMorador());
		return "morador/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarMorador(@Valid @ModelAttribute("morador") Morador morador, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			servico.salvar(morador);
			model.addAttribute("msgTxt", "Morador salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Morador!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("moradoresFiltro", servicoMorador.listar());
		model.addAttribute("unidadesFiltro", servicoUnidade.listarUnidadesPorMorador());
		return "morador/tabela-moradores";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarMorador(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Morador buscarMorador(@PathVariable Long id) {
		Morador morador = servico.buscar(id);
		return morador;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
		webDataBinder.registerCustomEditor(Unidade.class, unidadePropertyEditor);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));	
	}
}
