package Semester1;
import java.util.*;
import java.util.concurrent.*;
 
public class Ex7
{
    public static void main(String[] args) {
        Runnable limitedCall = new Runnable() {
            final Random rand = new Random();
            final Semaphore available = new Semaphore(1);
            int count = 0;
            public void run() {
                int time = rand.nextInt(5);
                int num = count++;
                try {
                    available.acquire();
                    System.out.println("Executing long-running action for " + time + " seconds... #" + num);
                    Thread.sleep(time * 1000);
                    System.out.println("Done with #" + num + "!");
                    available.release();
                }
                catch (InterruptedException intEx) {
                    intEx.printStackTrace();
                }
            }
        };
         
        for (int i=0; i<10; i++)
            new Thread(limitedCall).start();

    }
}