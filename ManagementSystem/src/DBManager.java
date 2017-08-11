import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {

	ManagementUtil ut = new ManagementUtil();

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@192.168.53.14:1521:XE", "java07", "java07");
	}

	public void getData() throws Exception {
		Connection con = this.getConnection();
		Statement stat = con.createStatement();

		String sql = "select sysdate from dual";
		ResultSet rs = stat.executeQuery(sql);

		System.out.println(rs);

		con.close();
	}

	public int insertData(Member param) throws Exception {

		Connection con = this.getConnection();

		String sql = "insert into tb_member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, param.getMemberId());
		pstat.setString(2, param.getMemberPw());
		pstat.setString(3, param.getMemberName());
		pstat.setString(4, param.getEmail());
		pstat.setString(5, ut.data2string(param.getMemberBirthday()));
		pstat.setInt(6, param.getCompanyPhone());
		pstat.setInt(7, param.getCellPhone());
		pstat.setString(8, param.getAddress());
		pstat.setInt(9, param.getZipcode());
		pstat.setString(10, param.getDepartment());
		pstat.setString(11, param.getPosition());
		pstat.setString(12, param.getRank());

		int result = pstat.executeUpdate();

		con.commit();

		con.close();

		return result;

	}
//////////////////////////////////////////////////////////////////////////////////////////
	// 회원정보에서 사용하는 db
	
	// 부서당 사원수 가져오기
	public int selectDeptPerson(String department) throws Exception {
		Connection con = this.getConnection();

		String sql = "select count(*) from person where dept = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, department);
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		con.close();
		return count;
	}

	// 부서에 따라 사람 가져오기
	public ArrayList<Person> selectData(String department) throws Exception {
		Connection con = this.getConnection();

		String sql = "select * from person where dept = ?";

		ArrayList<Person> result = new ArrayList<Person>();

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, department);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String dept = rs.getString("dept");
			String rank = rs.getString("rank");
			String name = rs.getString("name");
			String s = rs.getString("checkbox");
			Boolean check;
			if (s.equals("true")) {
				check = true;
			} else
				check = false;
			result.add(new Person(dept, rank, name, check));

		}
		con.close();
		return result;
	}

	// 검색
	public ArrayList<Person> searchData(String msg) throws Exception {
		Connection con = this.getConnection();

		String sql = "select * from person where 1=1 ";

		ArrayList<Person> result = new ArrayList<Person>();
		System.out.println(msg);

		sql += msg;
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String dept = rs.getString("dept");
			String rank = rs.getString("rank");
			String name = rs.getString("name");
			String s = rs.getString("checkbox");
			Boolean check;
			if (s.equals("true")) {
				check = true;
			} else
				check = false;
			result.add(new Person(dept, rank, name, check));

		}
		con.close();
		return result;

	}
//////////////////////////////////////////////////////////////////////////////////////////
}
