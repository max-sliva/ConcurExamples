import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex6 {
	public static void main(String[] args){
		String[] threads = {"A", "B", "C", "D"};
		int[] times = {2000, 2000, 2000, 2000};
		ExecutorService executor = Executors.newFixedThreadPool(2);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable2(string, times[i++]));
		} 
		System.out.println("End main");
	}
}
