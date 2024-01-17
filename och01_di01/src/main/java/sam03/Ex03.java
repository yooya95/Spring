package sam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex03 {
	
	public static void main(String[] args) {
		//classic
		MessageBean cmb = new MessageBeanImpl("황인정", "전통적 안녕");
		cmb.sayHello();
		
		//DI 호출 객체 =ApplicationContext --> classpath = resource라고 생각 --> resulrces = beaon03.xml
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean03.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb3");
		mb.sayHello();
		
	}
	
}
