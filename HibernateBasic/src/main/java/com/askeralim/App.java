package com.askeralim;

import org.hibernate.Session;

import com.askeralim.user.User;
import com.askeralim.util.HibernateUtil;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + MySQL");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		User user = new User();

		user.setUserId(100);
		user.setUsername("superman");

		session.save(user);
		session.getTransaction().commit();
	}
}
