package Semester2;
import java.util.Arrays;
import java.util.concurrent.Callable;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CallableForSum_GUI2_alt implements Callable<Integer> { //потоковый класс с возвращаемым результатом
	private int arr[];  //частичный массив текущего потока
	private int sum;  //частичная сумма
	int i = 0;  //индекс элемента массива, нужен для отслеживания прогресса потока
	public CallableForSum_GUI2_alt(int[] initArr) { //конструктор
		arr = initArr;
	}

	@Override
	public Integer call() throws Exception { //основной метод, в котором происходит вся работа потока
		//выводим частичный массив текущего потока	
		System.out.println(Thread.currentThread().getName()+" arr="+Arrays.toString(arr));
		System.out.println(Thread.currentThread().getName()+" is calculating...");
		for (i = 0; i < arr.length; i++) { //цикл по частичному массиву текущего потока
			sum += arr[i]; 		//суммируем элементы массива
			Thread.sleep(1000);  //имитируем долгую работу
		}	
		return sum; //возвращаем сумму в основную программу
	}
		
	public int getI() { //метод для возвращения переменной i
		return i;
	}
}