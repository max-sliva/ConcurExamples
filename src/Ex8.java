import java.util.concurrent.*;
 
public class Ex8
{
	public static void main(String[] args){
		Semaphore available = new Semaphore(2);
		String[] threads = {"A", "B", "C", "D"};
		int[] times = {2000, 2000, 2000, 2000};
		ExecutorService executor = Executors.newFixedThreadPool(threads.length);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable3(string, times[i++], available));
		} 
		System.out.println("End main");
	}
}