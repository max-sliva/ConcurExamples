import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ex4 {
	public static void main(String[] args) {
		String[] threads = {"A", "B"};
		int[] times = {4000, 5000};
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		int i = 0;
		for (String string : threads) {
			executor.scheduleAtFixedRate(new SimpleRunnable2(string, times[i++]),1,2,TimeUnit.SECONDS);
		} 

		System.out.println("End main");
	}
}
