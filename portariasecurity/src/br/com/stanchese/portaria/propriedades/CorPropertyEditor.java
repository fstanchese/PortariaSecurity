package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Cor;
import br.com.stanchese.portaria.modelo.repositorios.CorRepositorio;

@Component
public class CorPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private CorRepositorio corRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id;
		Cor cor;
		try {
			id = Long.parseLong(text);
			cor = corRepositorio.findOne(id);
		} catch (Exception e) {
			cor = null;
		}
		setValue(cor);
	}
}
