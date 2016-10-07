package br.com.stanchese.portaria.propriedades;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.stanchese.portaria.modelo.entidades.Permissao;
import br.com.stanchese.portaria.modelo.repositorios.PermissaoRepositorio;

@Component
public class PermissaoPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private PermissaoRepositorio permissaoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long id = Long.parseLong(text);
		Permissao permissao = permissaoRepositorio.findOne(id);
		setValue(permissao);
	}
}
