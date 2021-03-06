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
	
//	public ArrayList<Person> getAllData() throws Exception {
//		Connection con = this.getConnection();
//		Statement stat = con.createStatement();
//		ResultSet rs = stat.executeQuery("select * from person");
//
//		Vector<Person> result = new Vector<Person>();
//
//		while (rs.next()) {
//			String dept = rs.getString("dept");
//			String rank = rs.getString("rank");
//			String name = rs.getString("name");
//			String s = rs.getString("checkbox");
//			Boolean check;
//			if(s.equals("true")){
//				check = true;
//			}else
//				check = false;
//			result.add(new Person(dept,rank,name,check));
//		}
//		con.close();
//
//		return result;
//	}
	
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
	
	
	
	//부서에 따라 사람 가져오기
		public ArrayList<Person> selectData(String department) throws Exception {
			Connection con = this.getConnection();
			
			String sql = "select * from person where dept = ?";
			
			ArrayList<Person> result = new ArrayList<Person>();
			
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
				result.add(new Person(dept,rank,name,check)); 
				
			}
			con.close();
			return result;
		}
		
		//검색 
		public ArrayList<Person> searchData(String msg) throws Exception  {
				Connection con = this.getConnection();
				
				String sql = "select * from person where 1=1 ";
				
				ArrayList<Person> result = new ArrayList<Person>();
				System.out.println(msg);
				
				sql += msg;
				System.out.println(sql);
				PreparedStatement pstmt = con.prepareStatement(sql);
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
					result.add(new Person(dept,rank,name,check)); 
					
				}
				con.close();
				return result;
			
		}
	
	
}
