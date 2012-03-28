package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoadTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					//"classpath*:applicationContext-common.xml");
					"classpath*:applicationContext-mirth-deploy.xml");
	}
}
