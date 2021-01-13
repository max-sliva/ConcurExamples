package Semester1;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class SimpleRunnable4 implements Runnable {
	private String s;
	private int timeout;
	CountDownLatch latch;
	
	public SimpleRunnable4(String str, int timeout, CountDownLatch lat) {
		s = str;
		this.timeout = timeout;
		latch = lat;
	}

	@Override
	public void run() {
		Date currentDate=new Date();
		long time1=currentDate.getTime();
		long time2=currentDate.getTime();
		while ((time2-time1) < timeout) {
			currentDate=new Date();
			time2=currentDate.getTime();
			System.out.println(s+" "+(time2-time1));
			latch.countDown();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
