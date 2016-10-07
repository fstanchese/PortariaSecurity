package br.com.stanchese.portaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.stanchese.portaria.modelo.servicos.AndarServico;
import br.com.stanchese.portaria.modelo.servicos.BlocoServico;
import br.com.stanchese.portaria.modelo.servicos.CorServico;
import br.com.stanchese.portaria.modelo.servicos.MarcaServico;
import br.com.stanchese.portaria.modelo.servicos.ModeloServico;
import br.com.stanchese.portaria.modelo.servicos.MoradorServico;
import br.com.stanchese.portaria.modelo.servicos.MoradorTipoServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeTipoServico;
import br.com.stanchese.portaria.modelo.servicos.VeiculoServico;

@Controller
public class FiltroController {

	@Autowired
	private MoradorServico servicoMorador;	
	
	@Autowired
	private MarcaServico servicoMarca;

	@Autowired
	private UnidadeServico servicoUnidade;
	
	@Autowired
	private VeiculoServico servicoVeiculo;
	
	@Autowired
	private UnidadeTipoServico servicoUnidadeTipo;
	
	@Autowired
	private MoradorTipoServico servicoMoradorTipo;
	
	@Autowired
	private AndarServico servicoAndar;
	
	@Autowired
	private BlocoServico servicoBloco;

	@Autowired
	private CorServico servicoCor;
	
	@Autowired
	private ModeloServico servicoModelo;
	
	@RequestMapping(method = RequestMethod.GET,value = "/veiculosFiltrar")
	public String buscarVeiculos(@RequestParam(defaultValue="") String moradorFiltro,
			@RequestParam(defaultValue="") String unidadeFiltro,
			@RequestParam(defaultValue="") String placaFiltro,
			@RequestParam(defaultValue="") String modeloFiltro,
			Model model) {
		
		model.addAttribute("veiculos", servicoVeiculo.listarFiltros(moradorFiltro,unidadeFiltro,placaFiltro,modeloFiltro));
		model.addAttribute("moradoresFiltro", servicoMorador.listarMoradoresPorVeiculos());
		model.addAttribute("unidadesFiltro", servicoUnidade.listarUnidadesPorVeiculos());
		model.addAttribute("placas", servicoVeiculo.listar());
		model.addAttribute("marcaEModelos", servicoMarca.listaMarcaModeloPorVeiculos());
		return "veiculo/tabela-veiculos";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/moradoresFiltrar")
	public String buscarMoradores(@RequestParam(defaultValue="") String moradorFiltro,
			@RequestParam(defaultValue="") String unidadeFiltro,
			Model model) {
		
		model.addAttribute("moradores", servicoMorador.listarFiltros(moradorFiltro,unidadeFiltro));
		model.addAttribute("moradoresFiltro", servicoMorador.listar());
		model.addAttribute("unidadesFiltro", servicoUnidade.listarUnidadesPorMorador());
		return "morador/tabela-moradores";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/unidadesFiltrar")
	public String buscarUnidades(@RequestParam(defaultValue="") String unidadeFiltro,
								 @RequestParam(defaultValue="") String moradorTipoFiltro,
								 @RequestParam(defaultValue="") String unidadeTipoFiltro,
							  	 @RequestParam(defaultValue="") String blocoFiltro,
								 @RequestParam(defaultValue="") String andarFiltro,
			Model model) {
		
		model.addAttribute("unidades", servicoUnidade.listarFiltros(unidadeFiltro,moradorTipoFiltro,unidadeTipoFiltro,blocoFiltro,andarFiltro));
		model.addAttribute("unidadesFiltro", servicoUnidade.listar());
		model.addAttribute("moradorTipos", servicoMoradorTipo.listar());
		model.addAttribute("unidadeTipos", servicoUnidadeTipo.listar());
		model.addAttribute("andares", servicoAndar.listar());
		model.addAttribute("blocos", servicoBloco.listar());
		return "unidade/tabela-unidades";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/andaresFiltrar")
	public String buscarAndares(@RequestParam(defaultValue="") String andarFiltro,
								Model model) {
		
		model.addAttribute("andares", servicoAndar.listarFiltros(andarFiltro));
		model.addAttribute("andaresFiltro", servicoAndar.listar());
		return "andar/tabela-andares";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/blocosFiltrar")
	public String buscarBlocos(@RequestParam(defaultValue="") String blocoFiltro,
								Model model) {
		
		model.addAttribute("blocos", servicoBloco.listarFiltros(blocoFiltro));
		model.addAttribute("blocosFiltro", servicoBloco.listar());
		return "bloco/tabela-blocos";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/coresFiltrar")
	public String buscarCores(@RequestParam(defaultValue="") String corFiltro,
								Model model) {
		
		model.addAttribute("cores", servicoCor.listarFiltros(corFiltro));
		model.addAttribute("coresFiltro", servicoCor.listar());
		return "cor/tabela-cores";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/marcasFiltrar")
	public String buscarMarcas(@RequestParam(defaultValue="") String marcaFiltro,
								Model model) {
		
		model.addAttribute("marcas", servicoMarca.listarFiltros(marcaFiltro));
		model.addAttribute("marcasFiltro", servicoMarca.listar());
		return "marca/tabela-marcas";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/modelosFiltrar")
	public String buscarModelos(@RequestParam(defaultValue="") String marcaFiltro,
								@RequestParam(defaultValue="") String modeloFiltro,
								Model model) {
		
		model.addAttribute("modelos", servicoModelo.listarFiltros(marcaFiltro,modeloFiltro));
		model.addAttribute("marcasFiltro", servicoMarca.listar());
		model.addAttribute("modelosFiltro", servicoModelo.listar());
		return "modelo/tabela-modelos";
	}
}