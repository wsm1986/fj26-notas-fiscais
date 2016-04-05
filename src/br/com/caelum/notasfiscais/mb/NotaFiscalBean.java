package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasficais.annotation.Transactional;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import org.primefaces.component.datatable.DataTable;;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable {
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	private org.primefaces.component.datatable.DataTable  tabela;
	
	@Inject
	DAO<NotaFiscal> dao;

	@Transactional
	public void gravar() {
		dao.adiciona(notaFiscal);
		notaFiscal = new NotaFiscal();
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void guardarItem() {
		notaFiscal.getItens().add(item);
		item.setValorUnitario(item.getProduto().getPreco());
		item.setNotaFiscal(notaFiscal);
		item = new Item();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	public void removeItem(){
		Item item = (Item) tabela.getRowData();
		notaFiscal.getItens().remove(item);
	}

	public org.primefaces.component.datatable.DataTable getTabela() {
		return tabela;
	}

	public void setTabela(org.primefaces.component.datatable.DataTable tabela) {
		this.tabela = tabela;
	}

}
