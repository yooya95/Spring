package sam07;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greet;
	private Outputter outPutter;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setGreet(String greet) {
		this.greet = greet;
	}
	public void setOutPutter(Outputter outPutter) {
		this.outPutter = outPutter;
	}

	public void sayHello() {
		String msg = name+"님 !! "+greet;
		System.out.println(msg);
		if(outPutter!=null) outPutter.output(msg);

	}

}
