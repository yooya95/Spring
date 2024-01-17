package DI06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass06 {

	public static void main(String[] args) {
		String configLocation1 = "classpath:applicationCTX610.xml";
		String configLocation2 = "classpath:applicationCTX611.xml";

		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1, configLocation2);
		Student student1 = ctx.getBean("student1", Student.class);
		System.out.println(student1.getName());
		System.out.println(student1.getHobbys());
		
		StudentInfo studentInfo = ctx.getBean("studentInfo1", StudentInfo.class);
		Student student2 = studentInfo.getStudent();
		System.out.println(student2.getName());
		System.out.println(student2.getHobbys());
	
		if(student1.equals(student2)) {
			System.out.println("student1 == stduent2");
		}
		
		Student student3 = ctx.getBean("student3", Student.class);
		Student student4 = ctx.getBean("student3", Student.class);
		System.out.println(student3.getName());
		System.out.println(student4.getName());
	
		if(student3.equals(student4)) {
			System.out.println("student3 == stduent4");
		} else {
			System.out.println("student3 != stduent4");
		}
		
	}
	
}
