package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.junit.Test;

public class SessionTest {
	@Test
	public void testOpenSession() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.openSession();
		Session session2=sessionFactory.openSession();
		System.out.println(session1.equals(session2));
//		if (session != null) {
//			System.out.println("Session创建成功");
//		} else {
//			System.out.println("Session创建失败");
//		}
	}

	@Test
	public void testGetCurrentSession() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.getCurrentSession();
		Session session2=sessionFactory.getCurrentSession();
		System.out.println(session1.equals(session2));
//		if (session != null) {
//			System.out.println("Session创建成功");
//		} else {
//			System.out.println("Session创建失败");
//		}
	}
	
	@Test
	public void testSavesStudentsWithOpenSession(){
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session1=sessionFactory.openSession();
		Transaction transaction=session1.beginTransaction();
		student s=new student(3, "Tom");
		session1.doWork(new Work() {
			
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection.hashCode());
				
			}
		});
		session1.save(s);
		transaction.commit();
		
		Session session2=sessionFactory.openSession();
		transaction=session2.beginTransaction();
		s=new student(4,"Jerry");
		session2.doWork(new Work() {
			
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection.hashCode());
				
			}
		});
		session2.save(s);
		transaction.commit();
	}
}
