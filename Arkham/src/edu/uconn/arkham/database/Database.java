package edu.uconn.arkham.database;

import java.util.ArrayList;

public class Database {

	private DatabaseConnector connector;
	
	public Database() {
		connector = new DatabaseConnector();
	}
	
	public ArrayList<String> getData() {
		// return "Hiya Antonio!\n";
		return connector.requestTowers();
	}
	
}
