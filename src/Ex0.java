import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex0 {

	public static void main(String[] args) {
		String[] threads = {"A", "B"};
		int[] times = {2000, 3000};
		ExecutorService executor = Executors.newFixedThreadPool(threads.length);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable2(string, times[i++]));
		} 

		System.out.println("End main");

	}
}
