package Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class QueryGetID {

	private String query;
	private Connection conn;
	public QueryGetID(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn = conn;
		
	}
	public String getID(String username)
	{
		String query = "SELECT users.id FROM users WHERE users.Users = '"+username+"'";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			if(rs.next())
				return rs.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";
		
				
	}
	
}
