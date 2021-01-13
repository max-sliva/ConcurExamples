package Semester1;
import java.util.concurrent.Phaser;

public class SimpleRunnable7 implements Runnable {
	private Phaser phaser;
	int time;

	public SimpleRunnable7(int time, Phaser ph) {
		this.time = time; 
		phaser = ph;
		phaser.register();
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " выполняет фазу " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута
        try{
            Thread.sleep(time);
        }  catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
         
        System.out.println(Thread.currentThread().getName() + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута
        try{
            Thread.sleep(time);
        }  catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " выполняет фазу " + phaser.getPhase());

        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты 
	}
}
