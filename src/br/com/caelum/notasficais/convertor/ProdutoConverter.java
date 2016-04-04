package br.com.caelum.notasficais.convertor;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.notasfiscais.modelo.Produto;

@FacesConverter(forClass=Produto.class)
@RequestScoped
public class ProdutoConverter implements Converter{
	
	@Inject
	private EntityManager manager;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String value) {
		Long id = Long.valueOf(value);
		return manager.find(Produto.class, id);
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object value) {
		Produto produto = (Produto)value;
		return String.valueOf(produto.getId());
	}

	
}
