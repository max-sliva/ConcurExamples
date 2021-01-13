package Semester2;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class ThreadForSum extends Thread {
	private int arr[];
	private int sum;

	public ThreadForSum(int[] initArr) {
		arr = initArr;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName()+" arr="+Arrays.toString(arr));
		System.out.println(this.getName()+" is calculating...");
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
//		interrupt();
	}
	
	public int getSum() {
		return sum;
	}
}