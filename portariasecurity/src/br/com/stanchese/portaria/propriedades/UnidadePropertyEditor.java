package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Unidade;
import br.com.stanchese.portaria.modelo.repositorios.UnidadeRepositorio;

@Component
public class UnidadePropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private UnidadeRepositorio unidadeRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		Unidade unidade = unidadeRepositorio.findOne(id);
		setValue(unidade);
	}
}
