package com.tecule.starlings;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tecule.starlings.service.CustomerService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"META-INF/spring/app-context.xml");
			CustomerService customerService = applicationContext.getBean("customerService", CustomerService.class);
			customerService.create("a", "1");
			System.out.println("finish");
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}
