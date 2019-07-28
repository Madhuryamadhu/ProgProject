package com.madhudevs.common;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	static{
		if (sessionFactory == null) {
			try {
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (standardServiceRegistry != null) {
					StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
				}
			}
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}




/*SessionFactory factory = HibernateUtil.getSessionFactory();
Session session = factory.getCurrentSession();
Transaction tr = session.beginTransaction();

CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<UserBean> cr = cb.createQuery(UserBean.class);

Root<UserBean> root = cr.from(UserBean.class);
cr.select(root);
 
Query<UserBean> query = session.createQuery(cr);
List<UserBean> results = query.getResultList();
for (UserBean userBean : results) {
	System.out.println(userBean.getSeqId()+" "+userBean.getUsername());
}


tr.commit();
*/