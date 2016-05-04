package br.com.caelum.notasfiscais.listener;

import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;

import org.jboss.seam.faces.event.qualifier.After;
import org.jboss.seam.faces.event.qualifier.RestoreView;

import br.com.caelum.notasfiscais.mb.LoginBean;
import javax.faces.application.NavigationHandler;

public class AutorizadorCDI {
	private @Inject FacesContext ctx;
	private @Inject LoginBean loginBean;
	private @Inject NavigationHandler navigationHandler;
	
	public void autoriza(@Observes @After @RestoreView PhaseEvent event){
		/*if("login.xhtml".equals(ctx.getViewRoot().getViewId())){
			return;
		}
		if(!loginBean.isLogado()){
			navigationHandler.handleNavigation(ctx, null, "login?faces-redirect=true");
			ctx.renderResponse();
		}*/
	}
}
