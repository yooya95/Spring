package DI02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass02 {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX02.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		myInfo.getInfo();
		ctx.close();

	}

}
