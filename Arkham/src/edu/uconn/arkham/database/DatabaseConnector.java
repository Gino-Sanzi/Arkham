package edu.uconn.arkham.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class DatabaseConnector {
	
	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;
	
	private ResultSet execute(String query) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/marea?" +
					"user=root&password=root");
			
			statement = connect.createStatement();
			
			resultSet = statement.executeQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public ArrayList<String> requestTowers() {
		// Execute query and get a ResultSet, representing all towers in DB
		ResultSet rs = execute(
									"SELECT * FROM tower_locations;"
								);
		
		// Convert ResultSet into a printable array list of strings
		ArrayList<String> towers = new ArrayList<String>();
		
		try {
			
			String network;
			
			while(rs.next()) {
				
				if(rs.getBoolean(4) == true) {
					network = "GSM";
				} else {
					network = "CDMA";
				}
				
				towers.add("ID: " + rs.getInt(1) + "\tLatitude: " + rs.getDouble(2) + "\tLongitude: " + 
						rs.getDouble(3) + "\t" + network + "\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception occurred in requestTowers() in DatabaseConnector.java");
		}
		
		return towers;
	}
	
	public void addTower(double latitude, double longitude, boolean gsm) {
		execute(
					"INSERT INTO tower_locations (lat, lon, gsm)" +
					"VALUES (" + latitude + ", " + longitude + ", " + gsm +
					");"
				);
	}
	
}
