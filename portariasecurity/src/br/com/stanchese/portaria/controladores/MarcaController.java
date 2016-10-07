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

import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.servicos.MarcaServico;

@Controller
@RequestMapping("/marcas")
public class MarcaController {
	
	@Autowired
	private MarcaServico servico;

	@RequestMapping(method = RequestMethod.GET)
	public String listarMarcas(Model model) {

		model.addAttribute("titulo", "Listagem de Marcas");
		model.addAttribute("marcasFiltro", servico.listar());
		return "marca/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarMarca(@Valid  Marca marca, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			servico.salvar(marca);
			model.addAttribute("msgTxt", "Marca salva com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar a Marca!");
			model.addAttribute("msgTipo", "danger");
		}
		
		model.addAttribute("marcasFiltro", servico.listar());
		return "marca/tabela-marcas";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarMarca(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Marca buscarMarca(@PathVariable Long id) {
		Marca marca = servico.buscar(id);
		return marca;
	}

}
