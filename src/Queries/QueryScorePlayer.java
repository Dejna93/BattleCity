package Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryScorePlayer {

	private String query = new String();
	
	private Connection conn;
	public QueryScorePlayer(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn = conn;
		
	}
	public void setScore(String id,int score)
	{
		String query = "INSERT INTO Score VALUES (NULL,"+score+","+id+");";
		try {
			System.out.println(query);
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	public void updateScore(String id,int score)
	{
		String query =  "UPDATE `Score` SET  Score.score=score+"+score+" WHERE  idplayer='"+id+"' ;";
		System.out.println(query);
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
