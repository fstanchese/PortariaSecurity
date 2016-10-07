package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.VisitanteTipo;
import br.com.stanchese.portaria.modelo.repositorios.VisitanteTipoRepositorio;

@Component
public class VisitanteTipoPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private VisitanteTipoRepositorio visitanteTipoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		VisitanteTipo visitanteTipo = visitanteTipoRepositorio.findOne(id);
		setValue(visitanteTipo);
	}
}
