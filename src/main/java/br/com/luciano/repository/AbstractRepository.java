package br.com.luciano.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.luciano.model.AbstractEntity;

public abstract class AbstractRepository<T extends AbstractEntity<?>, I> {

	private EntityManagerFactory emf;
	private EntityManager em;

	private Class<T> classType;

	public AbstractRepository(Class<T> clazz) {
		super();
		classType = clazz;
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	public void save(T entity) {
		boolean abriuTransacao = this.iniciaTransacao();
		if (entity.getId() == null) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		if (abriuTransacao) {
			em.getTransaction().commit();
		}
	}

	public void delete(T entity) {
		boolean abriuTransacao = this.iniciaTransacao();
		em.remove(entity);
		if (abriuTransacao) {
			em.getTransaction().commit();
		}
	}

	public void saveList(List<T> list) {
		for (T entity : list) {
			this.save(entity);
		}
	}

	public T findById(I id) {
		T entity;
		boolean abriuTransacao = this.iniciaTransacao();
		entity = em.find(classType, id);
		if (abriuTransacao) {
			em.getTransaction().commit();
		}
		return entity;
	}

	protected Query createQuery(String hql) {
		return this.em.createQuery(hql);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	private boolean iniciaTransacao() {
		boolean abriuTransacao = false;
		if (!em.getTransaction().isActive()) {
			abriuTransacao = true;
			em.getTransaction().begin();
		}
		return abriuTransacao;
	}

}
