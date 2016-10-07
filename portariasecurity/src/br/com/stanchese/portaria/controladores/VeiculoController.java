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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.entidades.Cor;
import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.entidades.Morador;
import br.com.stanchese.portaria.modelo.entidades.Veiculo;
import br.com.stanchese.portaria.modelo.servicos.CorServico;
import br.com.stanchese.portaria.modelo.servicos.MarcaServico;
import br.com.stanchese.portaria.modelo.servicos.MoradorServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeServico;
import br.com.stanchese.portaria.modelo.servicos.VeiculoServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;
import br.com.stanchese.portaria.propriedades.CorPropertyEditor;
import br.com.stanchese.portaria.propriedades.MarcaPropertyEditor;
import br.com.stanchese.portaria.propriedades.ModeloPropertyEditor;
import br.com.stanchese.portaria.propriedades.MoradorPropertyEditor;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private MoradorPropertyEditor moradorPropertyEditor;
	
	@Autowired
	private MarcaPropertyEditor marcaPropertyEditor;
	
	@Autowired
	private ModeloPropertyEditor modeloPropertyEditor;
	
	@Autowired
	private CorPropertyEditor corPropertyEditor;
	
	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;	
	
	@Autowired
	private VeiculoServico servico;

	@Autowired
	private MoradorServico servicoMorador;

	@Autowired
	private MarcaServico servicoMarca;

	@Autowired
	private CorServico servicoCor;

	@Autowired
	private UnidadeServico servicoUnidade;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listarUnidades(Model model) {

		model.addAttribute("titulo", "Listagem de Veiculos");
		model.addAttribute("moradores", servicoMorador.listar());
		model.addAttribute("marcas", servicoMarca.listar());
		model.addAttribute("cores", servicoCor.listar());
		model.addAttribute("unidades", servicoUnidade.listar());
		
		model.addAttribute("moradoresFiltro", servicoMorador.listarMoradoresPorVeiculos());
		model.addAttribute("unidadesFiltro", servicoUnidade.listarUnidadesPorVeiculos());
		model.addAttribute("placas", servico.listar());
		model.addAttribute("marcaEModelos", servicoMarca.listaMarcaModeloPorVeiculos());
		return "veiculo/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarVeiculo(@Valid Veiculo veiculo, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			String placa = veiculo.getPlaca().toUpperCase();
			veiculo.setPlaca(placa);			
			servico.salvar(veiculo);
			model.addAttribute("msgTxt", "Veiculo salvo com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Veiculo!");
			model.addAttribute("msgTipo", "danger");
		}

		model.addAttribute("moradoresFiltro", servicoMorador.listarMoradoresPorVeiculos());
		model.addAttribute("unidadesFiltro", servicoUnidade.listarUnidadesPorVeiculos());
		model.addAttribute("placas", servico.listar());
		model.addAttribute("marcaEModelos", servicoMarca.listaMarcaModeloPorVeiculos());
		return "veiculo/tabela-veiculos";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarVeiculo(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Veiculo buscarVeiculo(@PathVariable Long id) {
		Veiculo veiculo = servico.buscar(id);
		return veiculo;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
		webDataBinder.registerCustomEditor(Morador.class, moradorPropertyEditor);
		webDataBinder.registerCustomEditor(Marca.class, marcaPropertyEditor);
		webDataBinder.registerCustomEditor(Modelo.class, modeloPropertyEditor);
		webDataBinder.registerCustomEditor(Cor.class, corPropertyEditor);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));		
	}
}
