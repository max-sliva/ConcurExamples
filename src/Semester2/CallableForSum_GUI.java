package Semester2;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class CallableForSum_GUI implements Callable<Integer> {
	private int arr[];
	private int sum;

	public CallableForSum_GUI(int[] initArr) {
		arr = initArr;
	}
	
	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName()+" arr="+Arrays.toString(arr));
		System.out.println(Thread.currentThread().getName()+" is calculating...");
		Thread.sleep(2000);
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}	
		return sum;
	}
}