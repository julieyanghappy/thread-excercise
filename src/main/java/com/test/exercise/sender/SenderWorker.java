package com.test.exercise.sender;

public class SenderWorker extends Thread {
	private WorkerContainer workerContainer;
	private int id;
	
	public SenderWorker(WorkerContainer workerContainer, int id) {
		this.workerContainer = workerContainer;
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("SenderWorker " + id + " starts running.");
		while (!workerContainer.isStop()) {
			Integer number = workerContainer.poll();
			try {
				if (number != null) {
					doWork(number);
				} else {
					System.out.println("SenderWorker " + id + " has nothing to be processed.");
					Thread.sleep(10 * 1000);
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage() + " \nSenderWorker " + id + " has to roll back.");
				workerContainer.rollback(number);
			}
		}
	}

	private void doWork(Integer number) {
		// Send the number here
		System.out.println("Worker " + id + " is processing " + number);
	}
}
