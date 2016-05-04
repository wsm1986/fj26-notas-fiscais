package br.com.caelum.notasfiscais.interceptor;

import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.sun.istack.internal.logging.Logger;

import br.com.caelum.notasficais.annotation.Auditavel;
import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

@Interceptor
@Auditavel
public class AuditoriaInterceptor {

	private @Inject UsuarioLogadoBean usuarioLogadoBean;
	private static final Logger logger = Logger.getLogger(AuditoriaInterceptor.class);
	
	@AroundInvoke
	public Object audita(InvocationContext ctx) throws Exception {
		Object result = ctx.proceed();
		logger.info("O Metodo "+ctx.getMethod().getName()
				+ " foi executado pelo usuario "+usuarioLogadoBean.getUsuario().getLogin()
				+ " na data: "+new SimpleDateFormat("dd/MM/yyyy"));
		
		return result;
	}
}
