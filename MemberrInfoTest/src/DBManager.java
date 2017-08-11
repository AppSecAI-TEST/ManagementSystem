import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
//Test
//Test2
public class DBManager {
	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java07", "java07");
	}
	
	public Vector<Member> getAllData() throws Exception {
		Connection con = this.getConnection();
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery("select * from person");

		Vector<Member> result = new Vector<Member>();

		while (rs.next()) {
			String dept = rs.getString("dept");
			String rank = rs.getString("rank");
			String name = rs.getString("name");
			String s = rs.getString("checkbox");
			Boolean check;
			if(s.equals("true")){
				check = true;
			}else
				check = false;
			result.add(new Member(dept,rank,name,check));
		}
		con.close();

		return result;
	}
	
	//부서당 사원수 가져오기
			public int selectDeptPerson(String department) throws Exception {
				Connection con = this.getConnection();
				
				String sql = "select count(*) from person where dept = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1,department);
				ResultSet rs = pstmt.executeQuery();
				int count=0;
				if(rs.next()){
					count = rs.getInt(1);
				}
				con.close();
				return count;
			}
	
	
	
	//부서가져오기
		public ArrayList<Member> selectData(String department) throws Exception {
			Connection con = this.getConnection();
			
			String sql = "select * from person where dept = ?";
			
			ArrayList<Member> result = new ArrayList<Member>();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,department);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String dept = rs.getString("dept");
				String rank = rs.getString("rank");
				String name = rs.getString("name");
				String s = rs.getString("checkbox");
				Boolean check;
				if(s.equals("true")){
					check = true;
				}else
					check = false;
				result.add(new Member(dept,rank,name,check)); 
				
			}
			con.close();
			return result;
		}
	
	

//	public int insertData(Student param) throws Exception { 
//
//			Connection con = this.getConnection();
//			
//			String sql = "insert into student values(?, ?)"; 
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, param.getId());
//			pstmt.setString(2, param.getName());
//			int result =  pstmt.executeUpdate();
//
//			con.commit();
//			con.close();
//			return result;
//	}
//	
//	public int deleteData(int id) throws Exception {
//		Connection con = this.getConnection();
//		String sql = "delete from student where id = ?"; 
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(1, id);
//		int result =  pstmt.executeUpdate();
//		con.commit();
//		con.close();
//		return result;
//	}
}
