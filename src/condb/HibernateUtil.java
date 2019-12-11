/**
 * @Autor WCJ
 * @Date 2019年9月2日下午3:33:37
 */
package condb;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

/**
 * @author WCJ
 * @Date 2019年9月2日 下午3:33:37
 */
public class HibernateUtil {
	static Session session;
	static SessionFactory fac;
	static {
		AnnotationConfiguration cfg = new AnnotationConfiguration().configure();
		fac = cfg.buildSessionFactory();
	}

	public Session getSession() {
		session = fac.openSession();
		return session;
	}

	public void close() {
		if (session != null) {
			session.close();
		}

	}

	public void executeUpdate(Object obj) {
		session = getSession();
		try {
			session.beginTransaction().begin();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	public void executeDelete(Object obj) {
		session = getSession();
		try {
			session.beginTransaction().begin();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public List executeSelect(String hql, Object obs[]) {
		session = getSession();
		List list = null;
		try {
			Query query = session.createQuery(hql).setCacheable(true);
			for (int i = 0; i < obs.length; i++) {
				Object ob = obs[i];
				query.setParameter(i, ob);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List executeSelect(String hql, Object obs) {
		session = getSession();
		List list = null;
		try {
			list = session.createQuery(hql).setProperties(obs).setCacheable(true).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List executeSelectLimit(String hql, Object obs, int page, int pcount) {
		session = getSession();
		List list = null;
		try {
			Query query = session.createQuery(hql).setProperties(obs).setCacheable(true).setFirstResult(pcount * (page - 1)).setMaxResults(pcount);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List executeSQLSelect(String sql, Object obs) {
		session = getSession();
		List list = null;
		try {
			Query query = session.createSQLQuery(sql).addEntity(obs.getClass()).setProperties(obs).setCacheable(true);
			list = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public int executeSQLUpdate(String sql, Object obs) {
		session = getSession();
		int n = 0;
		try {
			session.beginTransaction().begin();
			Query query = session.createSQLQuery(sql).setProperties(obs);
			n = query.executeUpdate();
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return n;
	}
	
	public int executeSQLUpdateNoCommit(String sql, Object obs) {
		int n = 0;
		try {
			Query query = session.createSQLQuery(sql).setProperties(obs);
			n = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return n;
	}
}
