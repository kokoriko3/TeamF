package bean;

import java.util.HashMap;
import java.util.Map;

public class TestListSubject {
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer,Integer> points = new HashMap<>();
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getPoints(int key) {
		return points.get(key).toString();
	}
	public void putPoints(int key, int value) {
		System.out.println("key:"+key+" vakue:"+value);
		this.points.put(key, value);
	}
}