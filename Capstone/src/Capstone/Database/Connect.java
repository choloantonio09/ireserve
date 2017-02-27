package Capstone.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public Connection con;
	public ResultSet rs;
	public Statement st;
	public PreparedStatement pst;
//	public String url = "jdbc:mysql://192.168.1.66:3306/capstone?noAccessToProcedureBodies=true";
	public String url = "jdbc:mysql://localhost:3306/capstone";
	public String user = "root";
	public String pass = "1234";
	
	public Connect() throws SQLException{
	   try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public void closeConnection() throws SQLException {
		rs.close();
		pst.close();
		con.close();
	}
}
