package Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Utils.TopScore;

public class QueryTopScore {
	
	private String query;
	private Connection conn;
	//private String[] scoreTable = new String [10];
	private TopScore [] scoreTable= new TopScore[10];
	public QueryTopScore(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn = conn;
		for(int i=0 ; i < 10 ; i++)
		{
			//scoreTable[i]="Nr "+String.valueOf(i+1) + " " ;
			scoreTable[i] = new TopScore(String.valueOf(i+1));
		}
		
	}
	public TopScore[] getTableScore()
	{
		String query = "SELECT score,idplayer FROM `Score` ORDER BY score DESC LIMIT 10";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
			int i=0;
			while(rs.next())
			{
				System.out.println(rs.getString("score") + " " + getUsername(rs.getString("idplayer")));
				//scoreTable[i] 
			//	scoreTable.set(i, scoreTable.get(i)+" " + getUsername(rs.getString("idplayer")) +" " +rs.getString("score")) ;
			//	scoreTable.set(i, new TopScore(getUsername(rs.getString("idplayer")), rs.getString("score")));
				scoreTable[i].setNick(getUsername(rs.getString("idplayer")));
				scoreTable[i].setScore(rs.getString("score"));
			//	getUsername(rs.getString("idplayer")) +" " +rs.getString("score")  ;
				 
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return scoreTable;
	}
	
	private String getUsername(String id)
	{
		String query = "SELECT Users FROM users WHERE id='"+id+"';";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			if(rs.next())
				return rs.getString("Users");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
				
	}
	
}
