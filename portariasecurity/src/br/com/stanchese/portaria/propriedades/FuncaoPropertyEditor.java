package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Funcao;
import br.com.stanchese.portaria.modelo.repositorios.FuncaoRepositorio;

@Component
public class FuncaoPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private FuncaoRepositorio funcaoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		Funcao funcao = funcaoRepositorio.findOne(id);
		setValue(funcao);
	}
}
