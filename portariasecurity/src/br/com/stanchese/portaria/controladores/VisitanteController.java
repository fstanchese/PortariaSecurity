package br.com.stanchese.portaria.controladores;

import java.util.Date;

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
import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.entidades.Unidade;
import br.com.stanchese.portaria.modelo.entidades.Visitante;
import br.com.stanchese.portaria.modelo.entidades.VisitanteTipo;
import br.com.stanchese.portaria.modelo.servicos.CorServico;
import br.com.stanchese.portaria.modelo.servicos.MarcaServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeServico;
import br.com.stanchese.portaria.modelo.servicos.VisitanteServico;
import br.com.stanchese.portaria.modelo.servicos.VisitanteTipoServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;
import br.com.stanchese.portaria.propriedades.CorPropertyEditor;
import br.com.stanchese.portaria.propriedades.ModeloPropertyEditor;
import br.com.stanchese.portaria.propriedades.UnidadePropertyEditor;
import br.com.stanchese.portaria.propriedades.VisitanteTipoPropertyEditor;

@Controller
@RequestMapping("/visitantes")
public class VisitanteController {

	@Autowired
	private UnidadePropertyEditor unidadePropertyEditor;	
	
	@Autowired
	private VisitanteTipoPropertyEditor visitanteTipoPropertyEditor;
	
	@Autowired
	private ModeloPropertyEditor modeloPropertyEditor;
	
	@Autowired
	private CorPropertyEditor corPropertyEditor;
	
	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;	
	
	@Autowired
	private VisitanteServico servico;

	@Autowired
	private MarcaServico servicoMarca;

	@Autowired
	private CorServico servicoCor;

	@Autowired
	private UnidadeServico servicoUnidade;

	@Autowired
	private VisitanteTipoServico servicoVisitanteTipo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listarVisitantes(Model model) {

		model.addAttribute("titulo", "Listagem de Visitantes");
		model.addAttribute("visitantes", servico.listar());
		model.addAttribute("marcas", servicoMarca.listar());
		model.addAttribute("cores", servicoCor.listar());
		model.addAttribute("unidades", servicoUnidade.listar());
		model.addAttribute("visitanteTipos", servicoVisitanteTipo.listar());
		return "visitante/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarVisitante(@Valid Visitante visitante, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			if (visitante.getId()==null) {
				Date entrada = new Date();
				visitante.setEntrada(entrada);
			} else {
				Visitante visita = servico.buscar(visitante.getId());
				visitante.setEntrada(visita.getEntrada());
				visitante.setSaida(visita.getSaida());
			}
			String placa = visitante.getPlaca().toUpperCase();
			visitante.setPlaca(placa);
			servico.salvar(visitante);
			model.addAttribute("msgTxt", "Visitante salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			System.out.println(result.getFieldErrors());
			model.addAttribute("msgTxt", "Erro ao salvar o Visitante!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("visitantes", servico.listar());
		model.addAttribute("marcas", servicoMarca.listar());
		model.addAttribute("cores", servicoCor.listar());
		model.addAttribute("unidades", servicoUnidade.listar());
		return "visitante/tabela-visitantes";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarVisitante(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<String> saidaVisitante(@PathVariable Long id) {
		try {
			Visitante visita = servico.buscar(id);
			Date saida = new Date();
			visita.setSaida(saida);
			servico.salvar(visita);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Visitante buscarVeiculo(@PathVariable Long id) {
		Visitante visitante = servico.buscar(id);
		return visitante;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
		webDataBinder.registerCustomEditor(Unidade.class, unidadePropertyEditor);
		webDataBinder.registerCustomEditor(Modelo.class, modeloPropertyEditor);
		webDataBinder.registerCustomEditor(Cor.class, corPropertyEditor);
		webDataBinder.registerCustomEditor(VisitanteTipo.class, visitanteTipoPropertyEditor);
	}
}
