package env03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
@Configuration //환경작업할때
public class ApplicationConfig {
	@Value("${admin.id}") //값을 셋팅할때
	private String adminId;
	@Value("${admin.pw}")	
	private String adminPw;
	@Value("${sub_admin.id}")
	private String sub_adminId;
	@Value("${sub_admin.pw}")
	private String sub_adminPw;

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		// 환경변수에 개발자 환경변수를 Append한다. 환경변수랑 관련된 것은 그 객체가 실행되었을 때제일먼저 실행됨 
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		System.out.println("2. Properties Run...");
		
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("admin3.properties");
		locations[1]=new ClassPathResource("sub_admin3.properties");
		configurer.setLocations(locations);
		return configurer;
	}
	@Bean
	public AdminConnection adminConnection() {
		AdminConnection adminConnection = new AdminConnection();
		System.out.println("3.adminConfig Run");
		adminConnection.setAdminId(adminId);
		adminConnection.setAdminPw(adminPw);
		adminConnection.setSub_adminId(sub_adminId);
		adminConnection.setSub_adminPw(sub_adminPw);
	
		return adminConnection;
	}
	
}
