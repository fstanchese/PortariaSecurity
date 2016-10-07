package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Condominio;
import br.com.stanchese.portaria.modelo.repositorios.CondominioRepositorio;

@Component
public class CondominioPropertyEditor extends PropertyEditorSupport {

	@Autowired private CondominioRepositorio condominioRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		Condominio condominio = condominioRepositorio.findOne(id);
		setValue(condominio);
	}
}
