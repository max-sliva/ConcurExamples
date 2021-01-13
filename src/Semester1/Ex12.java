package Semester1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
 
public class Ex12
{
	public static void main(String[] args) throws InterruptedException{
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Phaser phaser = new Phaser(1);
		executor.execute(new SimpleRunnable7(1000, phaser));
		executor.execute(new SimpleRunnable7(2000, phaser));
		executor.execute(new SimpleRunnable7(1000, phaser));

		int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();// ждем завершения фазы 0
        System.out.println("Фаза " + phase + " завершена");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();// ждем завершения фазы 1
        System.out.println("Фаза " + phase + " завершена");
         
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance(); // ждем завершения фазы 2
        System.out.println("Фаза " + phase + " завершена");
         
        phaser.arriveAndDeregister();		
        System.out.println("End main");
		executor.shutdownNow();
	}
}