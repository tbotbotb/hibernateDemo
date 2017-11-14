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


public class TestHibernate {
	private static  SessionFactory sessionFactory;
	private static  Session session;
	private static Transaction transaction;
	
	public static void init(){
		//创建配置对象
		Configuration config=new Configuration().configure();
		//创建服务注册对象
		StandardServiceRegistry serviceRegistry=
				new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建会话工厂对象
		sessionFactory=config.buildSessionFactory(serviceRegistry);
		//会话对象
		session=sessionFactory.openSession();
		//开启事务
		//transaction=session.beginTransaction();
	}
	
	public static void destory(){
		//transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	public static void main(String[] args) {
		init();
		student s=new student();
		s.setName("zzy");
		s.setNo(1);
		session.save(s);
		destory();
	}
	
	@Test
	public void autoCommit(){
		init();
		student s=new student();
		s.setNo(0);
		s.setName("zzy_autoCommit");
		session.doWork(new Work() {
			
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				connection.setAutoCommit(true);
			}
		});
		session.save(s);
		session.flush();
		destory();
	}
}
