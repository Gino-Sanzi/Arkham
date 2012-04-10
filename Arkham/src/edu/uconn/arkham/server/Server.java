package edu.uconn.arkham.server;

import java.io.IOException;
import java.util.ArrayList;

import edu.uconn.arkham.database.Database;

public class Server {
	
	private Connector connector;
	private Database database;
	
	public Server() {
		database = new Database();
		try {
			connector = new Connector();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.run();
	}
	
	public void run() {
		while(true) {
			try {
				connector.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ArrayList<String> message = database.getData();
			
			for(int i = 0; i < message.size(); i++) {
				connector.sendData(message.get(i));
				System.out.println(message.get(i));
			}
			
			connector.close();
		}
	}
	
}
