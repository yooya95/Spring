package sam07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	private String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void output(String msg) {
		System.out.println("fileName:" + fileName);
		try {
			FileWriter fw = new FileWriter(new File(fileName));
			fw.write(msg);
			fw.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("e.getMessage()->"+e.getMessage());
		}
	}

}
