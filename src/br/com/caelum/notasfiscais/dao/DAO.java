package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {
	private final Class<T> classe;
	private EntityManager em;
	public DAO(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(T t) {
		//consegue a entity manager
		//EntityManager em = new JPAUtil().getEntityManager();
		//abre transacao
		//em.getTransaction().begin();

		//persiste o objeto
		em.persist(t);

		//commita a transacao
		//em.getTransaction().commit();

		//fecha a entity manager
		//em.close();
	}

	public void remove(T t) {
		//EntityManager em = new JPAUtil().getEntityManager();
		//em.getTransaction().begin();

		em.remove(em.merge(t));

		//em.getTransaction().commit();
		//em.close();
	}

	public void atualiza(T t) {
		//EntityManager em = new JPAUtil().getEntityManager();
		//em.getTransaction().begin();

		em.merge(t);

		//em.getTransaction().commit();
		//em.close();
	}

	public List<T> listaTodos() {
		//EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		//em.close();
		return lista;
	}
	
	public T buscaPorId(Long id) {
		//EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		//em.close();
		return instancia;
	}
	
	public int contaTodos() {
		//EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from " + classe.getName() + " n").getSingleResult();
		//em.close();
		
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		//EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		//em.close();
		return lista;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


}
