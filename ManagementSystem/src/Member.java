import java.sql.Date;

public class Member {
	private int MemberId;//ID
	private int MemberPw;//PW
	private String MemberName;//�̸�
	private String Email;//�̸���
	private Date MemberBirthday;//�������
	private int CompanyPhone;//������ȣ
	private int CellPhone;//�޴���ȭ��ȣ
	private String Address;//�ּ�
	private int Zipcode;//�����ȣ
	private String Department;//�μ�
	private String Position;//��å
	private String Rank;//����
	
	public Member(int MemberId,int MemberPw,String MemberName,String Email,Date MemberBirthday,int CompanyPhone,int CellPhone,String Address,int Zipcode,String Department,String Position, String Rank){
		this.MemberId = MemberId;
		this.MemberPw = MemberPw;
		this.MemberName = MemberName;
		this.Email = Email;
		this.MemberBirthday = MemberBirthday;
		this.CompanyPhone = CompanyPhone;
		this.CellPhone = CellPhone;
		this.Address = Address;
		this.Zipcode = Zipcode;
		this.Department = Department;
		this.Position = Position;
		this.Rank = Rank;
	}
	
	public int getMemberId() {
		return MemberId;
	}
	public void setMemberId(int memberId) {
		MemberId = memberId;
	}
	public int getMemberPw() {
		return MemberPw;
	}
	public void setMemberPw(int memberPw) {
		MemberPw = memberPw;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getMemberBirthday() {
		return MemberBirthday;
	}
	public void setMemberBirthday(Date memberBirthday) {
		MemberBirthday = memberBirthday;
	}
	public int getCompanyPhone() {
		return CompanyPhone;
	}
	public void setCompanyPhone(int companyPhone) {
		CompanyPhone = companyPhone;
	}
	public int getCellPhone() {
		return CellPhone;
	}
	public void setCellPhone(int cellPhone) {
		CellPhone = cellPhone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getZipcode() {
		return Zipcode;
	}
	public void setZipcode(int zipcode) {
		Zipcode = zipcode;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getRank() {
		return Rank;
	}
	public void setRank(String rank) {
		Rank = rank;
	}
	
}
