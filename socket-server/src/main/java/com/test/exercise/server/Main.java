package com.test.exercise.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {
	public static void main(String[] args) {
		int portNumber = 9999;
		System.out.println("Listen on port: " + portNumber);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);

			while(true) {
			    System.out.println("Waiting for socket connection...");
			    Socket clientSocket = serverSocket.accept();
			    System.out.println("Got connection: " + clientSocket.getInetAddress().getHostAddress() + clientSocket.getPort());;
			    
			    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			    
		    	String line = in.readLine();
		    	if (line != null) {
		    		System.out.println("Received: " + line);
		    		clientSocket.close();
		    	}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null)
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
