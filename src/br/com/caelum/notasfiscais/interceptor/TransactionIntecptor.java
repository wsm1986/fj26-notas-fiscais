package br.com.caelum.notasfiscais.interceptor;
import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.caelum.notasficais.annotation.Transactional;

@Interceptor
@Transactional
public class TransactionIntecptor implements Serializable {

	@Inject
	private EntityManager em;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		System.out.println("Abrindo a Transação");
		em.getTransaction().begin();

		// Passando para o JSF Tratar a requisição
		Object resuntado = ctx.proceed();

		em.getTransaction().commit();
		System.out.println("Fechando a Transação");
		return resuntado;
	}
}
