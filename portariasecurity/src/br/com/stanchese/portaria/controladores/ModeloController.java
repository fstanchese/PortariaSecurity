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

import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.servicos.MarcaServico;
import br.com.stanchese.portaria.modelo.servicos.ModeloServico;
import br.com.stanchese.portaria.propriedades.MarcaPropertyEditor;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

	@Autowired
	private MarcaPropertyEditor marcaPropertyEditor;
	
	@Autowired
	private MarcaServico servicoMarca;
	
	@Autowired
	private ModeloServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarModelos(Model model) {
		
		model.addAttribute("titulo", "Listagem de Modelos");
		model.addAttribute("marcasFiltro", servicoMarca.listar());
		model.addAttribute("modelosFiltro", servico.listar());

		return "modelo/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarModelo(@Valid Modelo modelo, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(modelo);
			model.addAttribute("msgTxt", "Modelo salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Modelo!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("marcasFiltro", servicoMarca.listar());
		model.addAttribute("modelosFiltro", servico.listar());
		return "modelo/tabela-modelos";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarModelo(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Modelo buscarModelo(@PathVariable Long id) {
		Modelo modelo = servico.buscar(id);
		return modelo;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Marca.class, marcaPropertyEditor);
	}
}
