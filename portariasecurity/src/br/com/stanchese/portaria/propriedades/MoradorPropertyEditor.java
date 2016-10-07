package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Morador;
import br.com.stanchese.portaria.modelo.repositorios.MoradorRepositorio;

@Component
public class MoradorPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private MoradorRepositorio moradorRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		Morador morador = moradorRepositorio.findOne(id);
		setValue(morador);
	}
}
