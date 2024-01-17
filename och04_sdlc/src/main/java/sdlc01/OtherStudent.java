package sdlc01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class OtherStudent {
	private String name;
	private int age;
	
	//생성자 생성이후
	@PostConstruct
	// 의존하는 객체를 설정한 이후에 초기화 작업을 수행할 메서드에 적용
	public void initMethod() {
		System.out.println("OtherStudent의 initMethod() 생성자 생성이후");
	}
	
	//소멸자 소멸전
	@PreDestroy
	// 컨테이너에 객체를 제거하기 전에 호출될 메서드에 적용
	public void destroyMethod( ) {
		System.out.println("OtherStudent의 destroyMethod() 소멸자가 소멸되기전..");
	}
	
	public OtherStudent(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("OtherStudent 생성자...");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
