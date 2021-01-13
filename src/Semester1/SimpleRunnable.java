package Semester1;

public class SimpleRunnable implements Runnable {
	private String s;

	public SimpleRunnable(String str) {
		s = str;
	}

	@Override
	public void run() {
		while (Thread.currentThread().getState() == Thread.State.RUNNABLE) {
			System.out.println(s);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
