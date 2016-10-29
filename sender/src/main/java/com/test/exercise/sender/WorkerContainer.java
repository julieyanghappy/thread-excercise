package com.test.exercise.sender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WorkerContainer {
	private static WorkerContainer instance;
	
	private ConcurrentLinkedQueue<Integer> queue;
	
	private List<SenderWorker> senderWorkers = new ArrayList<SenderWorker>();
	
	private volatile boolean shouldStop = false;
	
	public static WorkerContainer getInstance() {
		if (instance == null)
			instance = new WorkerContainer();
		return instance;
	}
	
	private WorkerContainer() {
	}

	public void init(int workerCount) {
		queue = new ConcurrentLinkedQueue<Integer>();
		
		for (int i = 0; i < workerCount; i++) {
			SenderWorker sw = new SenderWorker(this, i);
			addSenderWorkers(sw);
		}
	}
	
	public Integer poll() {
		return queue.poll();
	}
	
	public void add(Integer number) {
		queue.offer(number);
	}
	
	public void rollback(Integer number) {
		add(number);
	}
	
	public void start() {
		for (SenderWorker sw : senderWorkers) {
			sw.start();
		}
	}
	
	public void stop() {
		shouldStop = true;
		System.out.println("Waiting for workers to finished");
		for (SenderWorker sw : senderWorkers) {
			try {
				sw.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("All workers finished.");
	}
	
	public boolean isStop() {
		return shouldStop;
	}
	
	private void addSenderWorkers(SenderWorker worker) {
		this.senderWorkers.add(worker);
	}
}
