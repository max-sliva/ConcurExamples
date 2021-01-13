package Semester1;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SimpleRunnable3 implements Runnable {
	private String s;
	private int timeout;
	Semaphore available;
	
	public SimpleRunnable3(String str, int timeout, Semaphore sem) {
		s = str;
		this.timeout = timeout;
		available = sem;
	}

	@Override
	public void run() {
		try {
			available.acquire();
			Date currentDate=new Date();
			long time1=currentDate.getTime();
			long time2=currentDate.getTime();
			while ((time2-time1) < timeout) {
				currentDate=new Date();
				time2=currentDate.getTime();
				System.out.println(s+" "+(time2-time1));
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					return;
				}
			}
			available.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
