package Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import SqlPackage.SqlUsers;

public class testqueries {

	private final String url= "jdbc:mysql://151.80.147.193:3306/";
	private final String dbName = "battlecity";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String userName = "admin";
	private final String password = "battlecity11";
	
	public Connection connection;

	private SqlUsers user;
	public testqueries() {
		// TODO Auto-generated constructor stub
	
		connection();
	
	}
	private void connection()
	{
	
		try{
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url+dbName,userName,password);

			System.out.println("Connected to datebase ");
		}catch(Exception e)
		{
			e.printStackTrace();
	
		}
	}
	public void test() throws SQLException
	{
		//QueryGetID query = new QueryGetID(connection);
		//QueryScorePlayer scoreQuery = new QueryScorePlayer(connection);
		//scoreQuery.setScore(query.getID("Dejna"), 12);

		QueryTopScore top = new QueryTopScore(connection);
		
		top.getTableScore();
			
		 
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		testqueries ts = new testqueries();
		ts.test();
	//

	}

}
