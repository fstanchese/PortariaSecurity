package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Marca;
import br.com.stanchese.portaria.modelo.repositorios.MarcaRepositorio;

@Component
public class MarcaPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private MarcaRepositorio marcaRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		Marca marca = marcaRepositorio.findOne(id);
		setValue(marca);
	}
}
