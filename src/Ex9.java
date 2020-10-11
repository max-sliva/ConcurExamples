import java.util.concurrent.*;
 
public class Ex9
{
	public static void main(String[] args) throws InterruptedException{
		String[] threads = {"A", "B", "C", "D"};
		int[] times = {1000, 1000, 1000, 2000};
		ExecutorService executor = Executors.newFixedThreadPool(threads.length);
		CountDownLatch latch = new CountDownLatch(4);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable4(string, times[i++], latch));
		} 
		latch.await();
		System.out.println("4 threads finished");
		System.out.println("End main");
		executor.shutdownNow();
	}
}