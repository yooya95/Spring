package sam04;

public class MessageImpl implements MessageBean {
	private String name;
	private String greet;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreet() {
		return greet;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}

	public void sayHello() {
		System.out.println(name+"님"+greet+" !!");

	}

}
