package school.dao.sessionfactory.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;

import school.dao.UserDao;
import school.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	public User findByEmail(String email) {
		Session session = null;
		User newEntity = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			newEntity = (User) session.createQuery(User.FIND_BY_EMAIL_QUERY)
					.setString("email", email).uniqueResult();
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newEntity;
	}

	public User findByEmailAndPassword(String email, String password) {
		Session session = null;
		User newEntity = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			newEntity = (User) session.createQuery(User.FIND_BY_EMAIL_AND_PASSWORD_QUERY)
					.setString("email", email).setString("password", password).uniqueResult();
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newEntity;
	}
}
