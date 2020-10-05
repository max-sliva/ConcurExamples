import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex3 {
	public static void main(String[] args) {
		String[] threads = {"A", "B"};
		int[] times = {2000, 3000};
		ExecutorService executor = Executors.newFixedThreadPool(2);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable2(string, times[i++]));
		} 
		executor.shutdown();
		System.out.println("End main");
	}
}
