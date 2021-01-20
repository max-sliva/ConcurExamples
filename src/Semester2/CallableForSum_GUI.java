package Semester2;
import java.util.Arrays;
import java.util.concurrent.Callable;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CallableForSum_GUI implements Callable<Integer> { //потоковый класс с возвращаемым результатом
	private int arr[];  //частичный массив текущего потока
	private int sum;  //частичная сумма
	JProgressBar sumProgress;  //прогресс-бар из интерфейса
	JLabel prLabel;  //лейбл из интерфейса для отображения процентов
	
	public CallableForSum_GUI(int[] initArr) { //конструктор
		arr = initArr;
	}

	@Override
	public Integer call() throws Exception { //основной метод, в котором происходит вся работа потока
		//выводим частичный массив текущего потока	
		System.out.println(Thread.currentThread().getName()+" arr="+Arrays.toString(arr));
		System.out.println(Thread.currentThread().getName()+" is calculating...");
		for (int i = 0; i < arr.length; i++) { //цикл по частичному массиву текущего потока
			sum += arr[i]; 		//суммируем элементы массива
			Thread.sleep(1000);  //имитируем долгую работу
			//условие для первого потока, которому передали прогресс-бар и лейбл,
			if (sumProgress!=null) //чтобы узнать, что ему передали прогресс-бар
				if ((i+1)*100 / arr.length % 20 == 0 ) { //если достигли 20% от суммируемых элементов
					sumProgress.setValue(sumProgress.getValue()+20); //меняем значение прогресс-бара
					System.out.println(sumProgress.getValue()); //отладочный вывод
					prLabel.setText(sumProgress.getValue()+"%");	//выводим процент на лейбл
				}
		}	
		return sum; //возвращаем сумму в основную программу
	}
	
	public void setPrBar(JProgressBar prBar) { //метод для установки ссылки на прогресс-бар из интерфейса
		sumProgress = prBar;
	}
	
	public void setPrLabel(JLabel prLab) {  //метод для установки ссылки на лейбл из интерфейса
		prLabel = prLab;
	}
}