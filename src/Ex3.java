import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex3 {
	public static void main(String[] args) {
		String[] threads = {"A", "B"};
		int[] times = {4000, 5000};
		ExecutorService executor = Executors.newFixedThreadPool(2);
		int i = 0;
		for (String string : threads) {
			executor.execute(new SimpleRunnable2(string, times[i++]));
		} 
		Date currentDate=new Date();
		long time1=currentDate.getTime();
		long time2=currentDate.getTime();
		while ((time2-time1) <= 2000) { //цикл, пока разница не составит 2 секунды
			currentDate=new Date();
			time2=currentDate.getTime();
		}
		executor.shutdownNow();
		System.out.println("End main");
	}
}
