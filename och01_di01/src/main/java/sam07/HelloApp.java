package sam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("/sam07/bean07.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb7");
		mb.sayHello();
	}

}
