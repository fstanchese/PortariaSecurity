package br.com.stanchese.portaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import br.com.stanchese.portaria.modelo.entidades.Funcionario;
import br.com.stanchese.portaria.modelo.entidades.Permissao;
import br.com.stanchese.portaria.modelo.servicos.FuncaoServico;
import br.com.stanchese.portaria.modelo.servicos.FuncionarioServico;
import br.com.stanchese.portaria.modelo.servicos.PermissaoServico;
import br.com.stanchese.portaria.propriedades.CondominioPropertyEditor;
import br.com.stanchese.portaria.propriedades.FuncaoPropertyEditor;
import br.com.stanchese.portaria.propriedades.PermissaoPropertyEditor;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncaoPropertyEditor funcaoPropertyEditor;

	@Autowired
	private CondominioPropertyEditor condominioPropertyEditor;

	@Autowired
	private PermissaoPropertyEditor permissaoPropertyEditor;

	@Autowired
	private FuncionarioServico servico;

	@Autowired
	private FuncaoServico servicoFuncao;

	@Autowired
	private PermissaoServico servicoPermissao;

	@RequestMapping(method = RequestMethod.GET)
	public String listarFuncionarios(Model model) {
		Iterable<Funcionario> funcionarios = servico.listar();
		Iterable<Funcao> funcoes = servicoFuncao.listar();
		Iterable<Permissao> permissoes = servicoPermissao.listar();

		model.addAttribute("titulo", "Listagem de Funcionários");
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("funcoes", funcoes);
		model.addAttribute("permissoes", permissoes);
		return "funcionario/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarFuncionario(@Valid Funcionario funcionario, BindingResult bindingResult, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (!bindingResult.hasErrors()) {
			if (!funcionario.getConfirmacaoSenha().isEmpty()) {
				if (funcionario.getSenha().equals(funcionario.getConfirmacaoSenha())) {
					String Senha = funcionario.getSenha();
					funcionario.setSenha(encoder.encode(Senha));
					servico.salvar(funcionario);
					model.addAttribute("msgTxt", "Funcionario salvo com sucesso!");
					model.addAttribute("msgTipo", "success");
				} else {
					model.addAttribute("msgTxt", "Erro ao salvar o Funcionário, senhas incorretas");
					model.addAttribute("msgTipo", "danger");
				}
			} else {
				servico.salvar(funcionario);
				model.addAttribute("msgTxt", "Funcionario salvo com sucesso!");
				model.addAttribute("msgTipo", "success");			}
		} else {
			model.addAttribute("msgTxt", "Erro ao salvar o Funcionário!");
			model.addAttribute("msgTipo", "danger");
		}

		Iterable<Funcionario> funcionarios = servico.listar();
		model.addAttribute("funcionarios", funcionarios);
		return "funcionario/tabela-funcionarios";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarFuncionario(@PathVariable Long id) {
		try {
			servico.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public Funcionario buscarFuncionario(@PathVariable Long id) {
		Funcionario funcionario = servico.buscar(id);
		return funcionario;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Condominio.class, condominioPropertyEditor);
		webDataBinder.registerCustomEditor(Funcao.class, funcaoPropertyEditor);
		webDataBinder.registerCustomEditor(Permissao.class, permissaoPropertyEditor);
	}
}
