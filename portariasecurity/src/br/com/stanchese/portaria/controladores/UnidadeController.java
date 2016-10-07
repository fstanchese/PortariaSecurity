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
import br.com.stanchese.portaria.modelo.entidades.Bloco;
import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.entidades.MoradorTipo;
import br.com.stanchese.portaria.modelo.entidades.Unidade;
import br.com.stanchese.portaria.modelo.entidades.UnidadeTipo;
import br.com.stanchese.portaria.modelo.servicos.AndarServico;
import br.com.stanchese.portaria.modelo.servicos.BlocoServico;
import br.com.stanchese.portaria.modelo.servicos.MoradorTipoServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeServico;
import br.com.stanchese.portaria.modelo.servicos.UnidadeTipoServico;
import br.com.stanchese.portaria.propriedades.AndarPropertyEditor;
import br.com.stanchese.portaria.propriedades.BlocoPropertyEditor;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;
import br.com.stanchese.portaria.propriedades.MoradorTipoPropertyEditor;
import br.com.stanchese.portaria.propriedades.UnidadeTipoPropertyEditor;

@Controller
@RequestMapping("/unidades")
public class UnidadeController {

	@Autowired
	private AndarPropertyEditor andarPropertyEditor;
	
	@Autowired
	private BlocoPropertyEditor blocoPropertyEditor;
	
	@Autowired
	private MoradorTipoPropertyEditor moradorTipoPropertyEditor;
	
	@Autowired
	private UnidadeTipoPropertyEditor unidadeTipoPropertyEditor;
	
	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;	

	@Autowired
	private UnidadeServico servico;

	@Autowired
	private UnidadeTipoServico servicoUnidadeTipo;
	
	@Autowired
	private MoradorTipoServico servicoMoradorTipo;
	
	@Autowired
	private AndarServico servicoAndar;
	
	@Autowired
	private BlocoServico servicoBloco;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listarUnidades(Model model) {
		model.addAttribute("titulo", "Listagem de Unidades");
		model.addAttribute("unidadesFiltro", servico.listar());
		model.addAttribute("moradorTipos", servicoMoradorTipo.listar());
		model.addAttribute("unidadeTipos", servicoUnidadeTipo.listar());
		model.addAttribute("andares", servicoAndar.listar());
		model.addAttribute("blocos", servicoBloco.listar());
		return "unidade/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarUnidade(@Valid Unidade unidade, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			if (unidade.getUnidadeTipo().getId()==1) {
				unidade.setAndar(null);
				unidade.setBloco(null);
			}
			servico.salvar(unidade);
			model.addAttribute("msgTxt", "Unidade salva com sucesso!");
			model.addAttribute("msgTipo", "success");
		} else {
			System.out.println(result.getFieldErrors());
			model.addAttribute("msgTxt", "Erro ao salvar a Unidade! ");
			model.addAttribute("msgTipo", "danger");
		}

		return "unidade/tabela-unidades";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarUnidade(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Unidade buscarUnidade(@PathVariable Long id) {
		Unidade unidade = servico.buscar(id);
		return unidade;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
		webDataBinder.registerCustomEditor(Andar.class, andarPropertyEditor);
		webDataBinder.registerCustomEditor(Bloco.class, blocoPropertyEditor);
		webDataBinder.registerCustomEditor(MoradorTipo.class, moradorTipoPropertyEditor);
		webDataBinder.registerCustomEditor(UnidadeTipo.class, unidadeTipoPropertyEditor);
	}
	
}
