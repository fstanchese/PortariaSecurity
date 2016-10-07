package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Bloco;
import br.com.stanchese.portaria.modelo.repositorios.BlocoRepositorio;

@Component
public class BlocoPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private BlocoRepositorio blocoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id;
		Bloco bloco;
		try {
			id = Long.parseLong(text);
			bloco = blocoRepositorio.findOne(id);
		} catch (Exception e) {
			bloco = null;
		}
		setValue(bloco);
	}
}
