package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Named
@ViewScoped
public class ListaNotasFiscaisBean  implements Serializable{
	private List<NotaFiscal> lista = new ArrayList<NotaFiscal>();
	@Inject
	private DAO<NotaFiscal> dao;
	public List<NotaFiscal> getLista() {
		return dao.listaTodos();
	}

	public void setLista(List<NotaFiscal> lista) {
		this.lista = lista;
	}
	
}
