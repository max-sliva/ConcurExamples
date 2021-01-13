package Semester1;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex1 {

	public static void main(String[] args) {
		Date currentDate=new Date();
		long time1=currentDate.getTime();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(() -> {
			String threadName = Thread.currentThread().getName();
			while (Thread.currentThread().getState() == Thread.State.RUNNABLE) {
				System.out.println("Hello " + threadName);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
//					e.printStackTrace();
					return;
				}
			}
		});
		executor.execute(() -> {
			String threadName = Thread.currentThread().getName();
			while (Thread.currentThread().getState() == Thread.State.RUNNABLE) {
				System.out.println("Hello " + threadName);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
//					e.printStackTrace();
					return;
				}
			}
		});
		currentDate=new Date();
		long time2=currentDate.getTime();;
		while ((time2-time1)<2000) { //цикл, пока разница не составит 2 секунды
			currentDate=new Date();
			time2=currentDate.getTime();
		}
		executor.shutdownNow();
	}

}
