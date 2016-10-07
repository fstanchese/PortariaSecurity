package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Modelo;
import br.com.stanchese.portaria.modelo.repositorios.ModeloRepositorio;

@Component
public class ModeloPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private ModeloRepositorio modeloRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id;
		Modelo modelo;
		try {
			id = Long.parseLong(text);
			modelo = modeloRepositorio.findOne(id);
		} catch (Exception e) {
			modelo = null;
		}
		setValue(modelo);
	}
}
