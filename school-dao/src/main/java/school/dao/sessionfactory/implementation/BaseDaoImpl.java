package school.dao.sessionfactory.implementation;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import school.dao.BaseDao;

public class BaseDaoImpl <E> implements BaseDao<E>{

	private Class<E> entityClass;
	
	public BaseDaoImpl(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public E findById(long id) {
		Session session = null;
		E newEntity = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession(); 
			Transaction transaction = session.beginTransaction();
			newEntity = (E) session.get(entityClass, id);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newEntity;
	}

	public void save(E entity) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession(); 
			Transaction transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public void remove(E entity) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession(); 
			Transaction transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public E update(E entity) {
		Session session = null;
		E newEntity = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession(); 
			Transaction transaction = session.beginTransaction();
			newEntity = (E) session.merge(entity);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newEntity;
	}

	@SuppressWarnings("unchecked")
	public Set<E> findAll() {
		Session session = null;
		Set<E> entities = new HashSet<E>();
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession(); 
			Transaction transaction = session.beginTransaction();
			entities = new HashSet<E>(session.createCriteria(entityClass).list());
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return entities;
	}

	
}
