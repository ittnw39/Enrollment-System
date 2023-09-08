package valueObject;

import java.util.Scanner;

public class VLecture {
	private int credit;
	private int code;
	private String name;
	private String professor;
	private String time;
	
	public int getCredit() {return credit;}
	public void setCredit(int credit) {this.credit = credit;}
	public String getProfessor() {return professor;}
	public void setProfessor(String professor) {this.professor = professor;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public void load(Scanner file) {
		this.setCode(file.nextInt());
		this.setName(file.next());
		this.setProfessor(file.next());
		this.setCredit(file.nextInt());
		this.setTime(file.next());
		
	}
	public void show() {
		System.out.println(this.getCode()
				+ " " + this.getName()
				+ " " + this.getProfessor()
				+ " " + this.getCredit()
				+ " " + this.getTime()
				);
		
	}
	
	public String toString() {
        return code
            + " " + name
            + " " + professor
            + " " + credit
            + " " + time;
    }
	
	

}
