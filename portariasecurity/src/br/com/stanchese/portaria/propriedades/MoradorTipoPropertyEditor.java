package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.MoradorTipo;
import br.com.stanchese.portaria.modelo.repositorios.MoradorTipoRepositorio;

@Component
public class MoradorTipoPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private MoradorTipoRepositorio moradorTipoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		MoradorTipo moradorTipo = moradorTipoRepositorio.findOne(id);
		setValue(moradorTipo);
	}
}
