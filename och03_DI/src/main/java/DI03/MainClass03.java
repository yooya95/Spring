package DI03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass03 {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX03.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		System.out.println("AbstractApplicationContexT after...");
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		System.out.println("studentInfo getBean after...");
		studentInfo.getSutdentInfo();
		System.out.println("tudentInfo.getStudentInfo() after...");
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println("getBean Student2 after...");
		studentInfo.setStudent(student2);
		System.out.println("studentInfo setSTUDENT after...");
		studentInfo.getSutdentInfo();
		
		System.out.println("stduent2.getaName()-->"+student2.getName());
		System.out.println("student2.getAage()-->"+student2.getAge());
		System.out.println(student2.getAge()+5);
		
		ctx.close();
	}

}
