package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
    	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/learning", "root", "123456");
    	Statement statement=connection.createStatement();
    	ResultSet rs=statement.executeQuery("select * from t_user");
    	while(rs.next()){
    		System.out.println(rs.getString(1));
    	}
	}
}
