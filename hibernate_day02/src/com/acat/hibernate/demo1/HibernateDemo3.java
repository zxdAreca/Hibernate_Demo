package com.acat.hibernate.demo1;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.acat.hibernate.utils.HibernateUtils;

/**
 * Hibernate的一级缓存的测试
 * @author Administrator
 *
 */
public class HibernateDemo3 {
	@Test
	//证明一级缓存的存在
	public void demo1(){
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		/*
		Customer customer = session.get(Customer.class, 1l);
		System.out.println(customer);
		
		Customer customer2 = session.get(Customer.class, 1l);
		System.out.println(customer2);
		
		System.out.println(customer == customer2);*/
		
		Customer customer = new Customer();
		customer.setCust_name("玩glow");
		Serializable serializable = session.save(customer);
		
		Customer customer2 = session.get(Customer.class, serializable);
		System.out.println(customer2);
		transaction.commit();
		session.close();
	}
	
	
	@Test
	//一级缓存的快照区
	public void demo2(){
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer = session.get(Customer.class, 1l);//发送SQL语句，同时加入到一级缓存中
		customer.setCust_name("张三");
		
		transaction.commit();
		session.close();
	}
	
	@Test
	//一级缓存的清空
	public void demo3(){
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer1 = session.get(Customer.class, 1l);
		customer1.setCust_name("王东");
	}
}
