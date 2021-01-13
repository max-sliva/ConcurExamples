package Semester1;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

public class SimpleRunnable6 implements Runnable {
	private String s;
	private int timeout;
	Exchanger<String> ex;
	CountDownLatch latch;
	
	public SimpleRunnable6(String str, int timeout, Exchanger<String> ex, CountDownLatch latch) {
		s = str;
		this.timeout = timeout;
		this.ex = ex;
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" initial data = "+s);
		System.out.println(Thread.currentThread().getName()+" exchange");
		try {
			Thread.sleep(timeout);
			s = ex.exchange(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" exchanged data = "+s);
		latch.countDown();
	}
}
