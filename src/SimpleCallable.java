import java.util.concurrent.Callable;

public class SimpleCallable implements Callable<Integer> {
	private int beg;
	private int end;

	public SimpleCallable(Integer beg, Integer end) {
		this.beg = beg;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName()+" is calculating...");
		Thread.sleep(2000);
		return beg+end;
	}
}
