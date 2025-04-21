package bean;

import java.io.Serializable;
public class ClassNum implements Serializable{
	private String class_num;
	private School School;
	public String getClass_num() {
		return class_num;
	}
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	public School getSchool() {
		return School;
	}
	public void setSchool(School school) {
		School = school;
	}


}
