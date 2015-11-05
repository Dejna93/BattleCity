package handlers;

import java.sql.*;
import java.util.ArrayList;

import Queries.QueryGetID;
import Queries.QueryScorePlayer;
import Queries.QueryTopScore;
import SqlPackage.SqlUsers;
import Utils.Score;
import Utils.TopScore;

public class DatebaseHandler {

	private final String url = "jdbc:mysql://151.80.147.193:3306/";
	private final String dbName = "battlecity";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String userName = "admin";
	private final String password = "battlecity11";

	private Connection connection;
	private SqlUsers user;

	public DatebaseHandler() {
		// TODO Auto-generated constructor stub

		connection();
	}

	private void connection() {

		try {
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url + dbName, userName,
					password);
			System.out.println("Connected to datebase ");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private boolean isConnection() {
		try {
			return connection.isValid(50);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean isContain(String username, String password) {
		try {
			if (username != "" && password != "") {
				Statement st = connection.createStatement();
				ResultSet result = st
						.executeQuery("SELECT * FROM users WHERE Users = '"
								+ username + "' AND Password = '" + password
								+ "'");

				if (result.next())
					return true;
				else
					return false;
				// System.out.println(result.getString("Users")+ " "+
				// result.getString("Password"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean registerSQl(String username, String password) {
		try {
			if (!isContain(username, password)) {
				String query = " insert into users (Users,Password,Date)"
						+ " values (?,?, ?)";
				PreparedStatement preparedStmt = connection
						.prepareStatement(query);

				preparedStmt.setString(1, username);
				preparedStmt.setString(2, password);
				preparedStmt.setString(3, "");

				// execute the preparedstatement
				preparedStmt.execute();

				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public void sendNewScore(int score, String nick) {

		QueryGetID query = new QueryGetID(connection);
		QueryScorePlayer scoreQuery = new QueryScorePlayer(connection);
		System.err.println("send ");
		if (query.getID(nick).equals("-1")) {
			System.err.println("nie ma go ");
			scoreQuery.setScore(query.getID(nick), score);
		} else {
			System.err.println("jest ");
			scoreQuery.updateScore(query.getID(nick), score);
		}

	}

	public TopScore [] getTopScore() {
		QueryTopScore query = new QueryTopScore(connection);
		return query.getTableScore();
	}

	public void sendNewScore(Score score) {
	
		QueryGetID query = new QueryGetID(connection);
		QueryScorePlayer scoreQuery = new QueryScorePlayer(connection);
		System.err.println("send ");
		if (query.getID(score.getNick()).equals("-1")) {
			System.err.println("nie ma go ");
			scoreQuery.setScore(query.getID(score.getNick()), score.getIScore());
		} else {
			System.err.println("jest ");
			scoreQuery.updateScore(query.getID(score.getNick()), score.getIScore());
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
