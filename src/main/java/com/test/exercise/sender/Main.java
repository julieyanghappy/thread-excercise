package com.test.exercise.sender;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input M (Thread),N(Queue Size):");
		
		String line = sc.nextLine();
		String[] input = line.split(",");
		int M = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);
		
		WorkerContainer wc = WorkerContainer.getInstance();
		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		
		for(int i = 0; i < N; i++) {
			queue.add(new Random().nextInt());
		}
		wc.init(queue, M);
		
		wc.start();
		
		System.out.println("Wait for threads finish");
		sc.nextLine();
		wc.stop();
		sc.close();
	}
}
