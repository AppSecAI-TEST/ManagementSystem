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
	
}
