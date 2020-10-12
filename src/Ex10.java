import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
 
public class Ex10
{
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		for (int i = 0; i<4; i++) {
			Future<Integer> fut = executor.submit(new SimpleCallable(i, i+2));
			list.add(fut);
		} 
		System.out.println("Waiting for result from callable threads...");
		while(!list.get(0).isDone()) {
		    System.out.println("Calculating...");
		    Thread.sleep(300);
		}
		int sum = 0;
		for(Future<Integer> fut : list){
			System.out.println(fut.get());
			sum = sum + fut.get();
		}
		System.out.println("End main, sum = "+sum);
		executor.shutdownNow();
	}
}