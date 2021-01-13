package Semester1;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex3 {
	public static void main(String[] args) throws InterruptedException {
		String[] threads = {"A", "B", "C", "D"};
		int[] times = {4000, 5000, 2000, 2000};
		ExecutorService executor = Executors.newFixedThreadPool(2);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable2(string, times[i++]));
		} 
//		executor.awaitTermination(2500, TimeUnit.MILLISECONDS);
//		executor.shutdownNow();
		System.out.println("End main");
	}
}
