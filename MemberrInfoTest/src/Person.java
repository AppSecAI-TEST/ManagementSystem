import java.sql.Date;
// ��¥ ��� Ŭ���� �ƴ� test�� ���Ŭ����
public class Person {
	private String dept;
	private String rank;
	private String name;
	private Boolean checkbox;
	public Person(String dept, String rank, String name, Boolean checkbox) {
		super();
		this.dept = dept;
		this.rank = rank;
		this.name = name;
		this.checkbox = checkbox;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getCheckbox() {
		return checkbox;
	}
	public void setChk(Boolean checkbox) {
		this.checkbox = checkbox;
	}
	
	
	
	
}
