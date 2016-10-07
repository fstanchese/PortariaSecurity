package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Andar;
import br.com.stanchese.portaria.modelo.repositorios.AndarRepositorio;

@Component
public class AndarPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private AndarRepositorio andarRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id;
		Andar andar;
		try {
			id = Long.parseLong(text);
			andar = andarRepositorio.findOne(id);
		} catch (Exception e) {
			andar = null;
		}
		setValue(andar);
	}
}
