package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.UnidadeTipo;
import br.com.stanchese.portaria.modelo.repositorios.UnidadeTipoRepositorio;

@Component
public class UnidadeTipoPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private UnidadeTipoRepositorio unidadeTipoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		UnidadeTipo unidadeTipo = unidadeTipoRepositorio.findOne(id);
		setValue(unidadeTipo);
	}
}
