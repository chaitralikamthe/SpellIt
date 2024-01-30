package spell;

import java.sql.*;

public class storage {
	
	int num;;
	String ds;
	String loc;
	
	 storage(int num) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");

		this.num=num;
		String dbURL="jdbc:mysql://localhost:3306/db1";
		String userName="root";
		String password="saurav@55";
		
		Connection con= DriverManager.getConnection(dbURL,userName,password);
	
		System.out.println("Connection created");
		
		Statement st=con.createStatement();
//		st.setInt(1,num);
		ResultSet rs= st.executeQuery("select * from alphabets where num="+num);
		
		while(rs.next()){
			num=rs.getInt("num");
			ds=rs.getString("whatFor");
			loc=rs.getString("icon");
			
			System.out.print(num+" "+ds);
			System.out.println();
		
		}
		
		//ds=rs.getString("whatFor");
		
		//System.out.println(ds);
		
	};

}
