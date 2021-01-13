package Semester1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
 
public class Ex11
{
	public static void main(String[] args) throws InterruptedException{
		Exchanger<String> ex = new Exchanger<String>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		CountDownLatch latch = new CountDownLatch(3);
		executor.execute(new SimpleRunnable6("A", 2000, ex, latch));
		executor.execute(new SimpleRunnable6("B", 3000, ex, latch));
		executor.execute(new SimpleRunnable6("C", 3000, ex, latch));
		executor.execute(new SimpleRunnable6("D", 3000, ex, latch));
		latch.await();
		System.out.println("End main");
		executor.shutdownNow();
	}
}