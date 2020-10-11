import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex5 {
	public static void main(String[] args) throws InterruptedException {
		String[] threads = {"A", "B"};
		int[] times = {4000, 5000};
		ExecutorService executor = Executors.newFixedThreadPool(threads.length);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable2(string, times[i++]));
		} 
		executor.awaitTermination(2100, TimeUnit.MILLISECONDS);
		executor.shutdownNow();
		System.out.println("End main");
	}
}
