package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.notasfiscais.mb.LoginBean;

public class Autorizador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext ctx = event.getFacesContext();
		if("/login.xhtml".equals(ctx.getViewRoot().getViewId())){
			return;
		}
		
		//Recupera os Dados Do Bean
		LoginBean login = ctx.getApplication().evaluateExpressionGet(ctx, "#{loginBean}", LoginBean.class);
		if(!login.isLogado()){
			NavigationHandler handler = ctx.getApplication().getNavigationHandler();
			handler.handleNavigation(ctx, null, "login?faces-redirect=true");
			ctx.renderResponse();
		}

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("Antes da Fase: " + arg0.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
