package br.com.conoslp.dao.impl.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.conoslp.dao.api.generic.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager em;
	private Class<T> clazz;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		clazz = ((Class) pt.getActualTypeArguments()[0]);	
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public void deletar(final Integer id) {
		try {
			getEntityManager().remove(em.getReference(clazz, id));
		} catch (Exception e) {
			System.out.print("Erro ao excluir!");
		}
	}

	public List<T> buscaTodos() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cQuery = builder.createQuery(getTypeClass());
		Root<T> from = cQuery.from(getTypeClass());
		CriteriaQuery<T> select = cQuery.select(from);
		List<Predicate> predicates = new ArrayList<Predicate>();
		select.where(predicates.toArray(new Predicate[] {}));
		//select.orderBy(builder.asc(from.get("codigo")));
		TypedQuery<T> listQuery = em.createQuery(select);
		return listQuery.getResultList();
	}

	private Class<T> getTypeClass() {
		return clazz;
	}

	@TransactionAttribute
	public T salvar(final T t) {
		try {
			getEntityManager().persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public void alterar(final T t) {
		getEntityManager().merge(t);
	}

	public T pesquisarPorCodigo(Integer id) {
		return (T) getEntityManager().find(clazz, id);
	}
}
