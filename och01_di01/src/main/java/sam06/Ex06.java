package sam06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex06 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean06.xml");
		Vehicle vh = (Vehicle) ac.getBean("vh6");
		vh.ride();
		
	}

}
