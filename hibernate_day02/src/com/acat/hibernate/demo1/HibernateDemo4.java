package com.acat.hibernate.demo1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.acat.hibernate.utils.HibernateUtils;

/**
 * 测试当前线程绑定的Session
 * @author Administrator
 *
 */
public class HibernateDemo4 {
	@Test
	public void demo1(){
		//保证使用的session都是同一个
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("王小兵");
		session.save(customer);
		
		transaction.commit();
		//线程关闭了再关闭session会报错 
		//session.close();
	}
}
