package com.test.exercise.sender;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input thread size:");
		String line = sc.nextLine();
		
		int threadPoolSize = Integer.parseInt(line);
		
		WorkerContainer wc = WorkerContainer.getInstance();
		wc.init(threadPoolSize);
		wc.start();
		
		boolean stop = false;
		
		while(!stop) {
			System.out.println("Input a sequence of integers (format: [1,2,3,4,5,6,7]):");
			line = sc.nextLine();
			String[] input = line.split(",");
			for (int i = 0; i < input.length; i++) {
				try {
					wc.add(Integer.parseInt(input[i]));
				} catch (Exception e) {
					e.printStackTrace();
					wc.stop();
					stop = true;
				}
			}
		
			System.out.println("Input 'exit' to stop.");
			line = sc.nextLine();
			if (line.equals("exit")) {
				wc.stop();
				sc.close();
				stop = true;
			}
		}
	}
}
