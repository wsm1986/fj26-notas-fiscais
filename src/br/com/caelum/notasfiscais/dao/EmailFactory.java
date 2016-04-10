package br.com.caelum.notasfiscais.dao;

import javax.enterprise.inject.Produces;

import br.com.caelum.notasficais.annotation.EmailComercial;
import br.com.caelum.notasficais.annotation.EmailFinanceiro;

public class EmailFactory {

	@Produces @EmailComercial
	private String emailComercial = "comercial@email.com";
	
	@Produces @EmailFinanceiro
	private String emailFinanceiro = "financeiro@email.com";
}
