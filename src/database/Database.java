package database;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import application.Score;

public class Database
{
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/simennj_spill_prosjekt";
	
	static final String USER = "simennj_spill";
	static final String PASS = "esie";
	
	private Connection connection = null;
	
	private boolean connect() {
		try {
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Connecting to database");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to DB successfully");
			return true;
		} 
		catch(SQLException sqle) {
			//connection.rollback();
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	private boolean close() {
		try {
			if(connection != null) {
				connection.close();
			}
			return true;
		} 
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Score> getHighscoreList(int end) {
		ArrayList<Score> scores = new ArrayList<>();
		if(connect()) {
			try
			{
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM highscore ORDER BY score DESC LIMIT "+ end;
				ResultSet rs = statement.executeQuery(query);
				while(rs.next()) {
					scores.add(new Score(rs.getInt("score"), rs.getString("name"), rs.getDate("date_time")));
				}
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		close();
		return scores;
	}

	/**
	 * 
	 * @param name String, navn på spiller
	 * @param score int
	 * @return 1 on success, 2 on SQL failure, 3 on other, 4 hvis navn for langt
	 */
	public int insertHighscore(String name, int score) {
		if(name.length() > 20) {
			return 4;
		}
		if(connect()) {
			//lager først et date object fra java.util.date og bruker getTime() i konstruktøren java.sql.Timestamp
			java.util.Date utilDate = new java.util.Date();
			System.out.println(utilDate.getTime());
			Timestamp date = new Timestamp(utilDate.getTime());
			try {
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				String query = "INSERT INTO highscore(name, score, date_time) VALUES('"+ name +"', "+ score +", '"+ date +"')";
				statement.executeUpdate(query);
				close();
				return 1;
			}
			catch (SQLException e) {
				e.printStackTrace();
				close();
				return 2;
			}
		}
		return 3;
	}
	
	/***
	 * 
	 * @param id int, DB id
	 * @return 1 on success, 2 on failure
	 */
	public int deleteEntry(int id) {
		if(connect()) {
			try {
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				statement.executeUpdate("DELETE FROM highscore WHERE id = "+id);
				close();
				return 1;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				close();
			}
		}
		return 2;
	}
	
	public static void main(String[] args)
	{
		Database db = new Database();
		db.insertHighscore("simen", 500);
		ArrayList<Score> scores = db.getHighscoreList(10);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		for(Score s: scores) {
			String date = df.format(s.getDate_time());
			System.out.println(s.getName() + " - " + s.getScore() + " - " + date);
		}
	}
}