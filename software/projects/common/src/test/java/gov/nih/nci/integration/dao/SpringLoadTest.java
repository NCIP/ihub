package gov.nih.nci.integration.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoadTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"classpath*:applicationContext-common2.xml");
		
		System.out.println(ctx.getBean("jpaVendorAdapter"));
	}
}