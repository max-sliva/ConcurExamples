package Semester1;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex2 {
	public static void main(String[] args) {
		String[] threads = {"A", "B"}; 
		Date currentDate=new Date();
		long time1=currentDate.getTime();
		ExecutorService executor = Executors.newFixedThreadPool(threads.length);
		for (String string : threads) {
			executor.execute(new SimpleRunnable(string));
		} 
		currentDate=new Date();
		long time2=currentDate.getTime();;
		while ((time2-time1)<2000) { //цикл, пока разница не составит 2 секунды
			currentDate=new Date();
			time2=currentDate.getTime();
		}
		executor.shutdownNow();
		System.out.println("2 seconds");
	}
}
