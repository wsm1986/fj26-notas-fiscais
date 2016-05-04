package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasficais.annotation.Auditavel;
import br.com.caelum.notasficais.annotation.EmailComercial;
import br.com.caelum.notasficais.annotation.Transactional;
import br.com.caelum.notasficais.annotation.ViewBean;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named @RequestScoped
public class ProdutoBean implements Serializable {
	private Produto produto = new Produto();
	@Inject
	DAO<Produto> dao;
	
	@Inject @EmailComercial
	private String emailComercial;
	
	private List<Produto> produtos;

	private Double total = new Double(0);

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void cancelar() {
		produto = new Produto();
	}
	@Transactional
	@Auditavel
	public String grava() {
		System.out.println(emailComercial);
		if (produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
	
		return "produto?faces-redirect=true";
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando Produtos");
			produtos = dao.listaTodos();
			AtualizaProduto();
		}
		return produtos;
	}
	@Transactional
	public void remover(Produto produto) {
		dao.remove(produto);
		produtos = dao.listaTodos();
		AtualizaProduto();
	}

	public Double AtualizaProduto() {
		if (produtos.size() > 0) {
			this.setTotal(0d);
			for (Produto produto : produtos) {
				this.setTotal(this.getTotal() + produto.getPreco());
			}
		}
		return total;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
