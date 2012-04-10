package edu.uconn.arkham.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
	
	private ServerSocket server;
	private int port;
	private PrintWriter output;
	
	public Connector() throws IOException {
		port = 1047;
		System.out.println("Starting server listener!");
		start();
	}
	
	public void start() {
		try {
			server = new ServerSocket(port);
			System.out.println("Listening on port " + port);
		} catch(IOException e) {
			System.out.println("Could not start server process on port " + port);
			e.printStackTrace();
		}
	}
	
	public void accept() throws IOException {
		Socket client = server.accept();
		String addr = client.getInetAddress().getHostAddress();
		int clientPort = client.getPort();
		
		System.out.println("Received request from " + addr + ":" + clientPort);
		
		output = new PrintWriter(client.getOutputStream(), true);
	}
	
	public void sendData(String data) {
		System.out.println("Data is being sent!");
		output.print(data);
	}
	
	public void close() {
		System.out.println("Output stream closing...");
		output.close();
		System.out.println("Output stream closed.");
	}
}
