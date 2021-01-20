package Semester2;
import java.util.Arrays;
import java.util.concurrent.Callable;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CallableForSort implements Callable<int[]> { //потоковый класс с возвращаемым результатом
	private int arr[];  //частичный массив текущего потока

	int i = 0;  //индекс элемента массива, нужен для отслеживания прогресса потока
	public CallableForSort(int[] initArr) { //конструктор
		arr = initArr;
	}

	@Override
	public int[] call() throws Exception { //основной метод, в котором происходит вся работа потока
		//выводим частичный массив текущего потока	
		System.out.println(Thread.currentThread().getName()+"init arr="+Arrays.toString(arr));
		System.out.println(Thread.currentThread().getName()+" is calculating...");
		arr=sort(arr); //вызываем метод сортировки
		System.out.println(Thread.currentThread().getName()+"sorted arr="+Arrays.toString(arr));

		return arr; //возвращаем массив в основную программу
	}
		
	public int getI() { //метод для возвращения переменной i
		return i;
	}
	public int[] sort(int[] a){ //сортировка выбором
		int min;
		int buf;
		for (i = 0; i < a.length-1; i++){  
			min = i; 
			for (int j = i+1; j < a.length; j++) 
				if (a[j] < a[min]) min = j; 
			// поменяем местами a[min] и a[i] 
			buf = a[i]; 
			a[i] = a[min]; 
			a[min] = buf; 
		}
		return a;
	}

}