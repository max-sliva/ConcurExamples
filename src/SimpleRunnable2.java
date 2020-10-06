import java.util.Date;

public class SimpleRunnable2 implements Runnable {
	private String s;
	private int timeout;

	public SimpleRunnable2(String str, int timeout) {
		s = str;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		Date currentDate=new Date();
		long time1=currentDate.getTime();
//		currentDate=new Date();
		long time2=currentDate.getTime();;
		while ((time2-time1) < timeout) { //цикл, пока разница не составит 2 секунды
			currentDate=new Date();
			time2=currentDate.getTime();
			System.out.println(s+" "+(time2-time1));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
