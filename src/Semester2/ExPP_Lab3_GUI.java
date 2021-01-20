package Semester2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ExPP_Lab3_GUI {
	static JTextArea centerText = new JTextArea("Ready!"); //центральная текстовая область
	static int n = 0; //кол-во эл-ов в массиве 
	static int mas[];  //сам массив
	static JProgressBar sumProgress;  //прогресс-бар
	static JLabel stateLabel;  //лейбл с процентом прогресса
	static CallableForSort firstCall; //один из объектов типа Callable для вычисления
	static int sumPar = 0; //для итоговой параллельной суммы 
	static int partArraySize = 0;
	static private int[] mainArray;
	static private ArrayList<int[]> arrayParts;
	public static void main(String[] args) {
		JFrame window = new JFrame("PPWindow"); //основное окно
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setNorth(window); //вызываем метод для заполнения верхней части окна (описан ниже)
		Font newFont = centerText.getFont();  //объект для изменения шрифта 
		newFont=newFont.deriveFont(20.0f); //меняем размер шрифта 
		centerText.setFont(newFont);  //устанавливаем новый шрифт
		centerText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5)); //делаем границу
		window.add(new JScrollPane(centerText), BorderLayout.CENTER); //вставляем текстовую область в панель со прокруткой и в центр окна
		
		stateLabel = new JLabel("0%"); //делаем текст по умолчанию для лейбла с процентом прогресса
		JPanel southPane = new JPanel(); //нижняя панель
		southPane.add(stateLabel);  //вставляем туда лейбл с процентом прогресса
		sumProgress=new JProgressBar(0,100); //создаем прогресс-бар
		southPane.add(sumProgress); //добавляем его на нижнюю панель
		window.add(southPane, BorderLayout.SOUTH); //вставляем нижнюю панель в окно
		
		window.setVisible(true);	//делаем видимым окно
		window.setLocationRelativeTo(null); //положение окна в центре (его верхний угол)
		window.setSize(500, 400);  //размер окна
		window.setMinimumSize(window.getSize()); //делаем текущий размер окна минимальным
		//немного сдвигаем окно, чтоб оно было более центральным
		window.setLocation(window.getX()-window.getWidth()/2, window.getY()-window.getHeight()/2); 
	}

	private static void setNorth(JFrame window) { //метод для заполнения верхней части окна
		Box vBox = new Box(BoxLayout.Y_AXIS);  //создаем коробку с горизонтальным размещением 
		Box hBox1 = new Box(BoxLayout.X_AXIS);  //создаем коробку с вертикальным размещением
		JLabel arrSizeLabel = new JLabel("Array size:  ");  //лейбл с надписью для размера массива
		Font newFont = arrSizeLabel.getFont();  //объект для изменения шрифта 
		newFont=newFont.deriveFont(20.0f);  //меняем размер шрифта
		arrSizeLabel.setFont(newFont);  //устанавливаем новый шрифт
		hBox1.add(arrSizeLabel);  //вставляем лейбл в коробку
		
		JTextField arrSize = new JTextField(5);  //текстовое поле для размера массива
		arrSize.setFont(newFont);  //меняем его шрифт
		arrSize.setText("10");  //размер массива по умолчанию
		hBox1.add(arrSize);  //вставляем текстовое поле в коробку
		arrSize.setMaximumSize(new Dimension(60, arrSize.getMinimumSize().height)); //и задаем ему размеры для лучшего отображения
		hBox1.add(Box.createHorizontalGlue()); //вставляем в коробку пружину
		
		JLabel thrCountLabel = new JLabel("Threads count:   ");  //лейбл с надписью для кол-ва потоков 
		thrCountLabel.setFont(newFont); //меняем его шрифт 
		hBox1.add(thrCountLabel);  //вставляем лейбл в коробку
		JSpinner thrCount = new JSpinner(new SpinnerNumberModel(2, 2, 16, 2)); //поле со стрелками для ввода кол-ва потоков
		thrCount.setMaximumSize(new Dimension(50, thrCount.getMinimumSize().height+10)); //задаем ему размеры для лучшего отображения
		thrCount.setFont(newFont);  //меняем его шрифт 
		hBox1.add(thrCount);   //вставляем в коробку
		vBox.add(hBox1);  //горизонтальную коробку вставляем в вертикальную 
		
		Box hBox2 = new Box(BoxLayout.X_AXIS);  //еще коробка с горизонтальным размещением 
		JButton norSortButton = new JButton("Normal sort"); //кнопка для обычного суммирования массива
		norSortButton.addActionListener(l->{  //обработчик нажатия на кнопку
			centerText.append("\n");  //переход на новую строку
			n = Integer.parseInt(arrSize.getText()); //размер массива
			mas = new int[n];  //выделяем память под массив
			Random random = new Random();
			int s = 0;  //для хранения суммы массива
			for (int i = 0; i < mas.length; i++) { //цикл по всему массиву
				mas[i] = random.nextInt(5)+1;  //рандомное значение элемента массива
			} 
			
			centerText.append("\nsequense sort = "+s); //выводим сумму массива в текстовую область 
		});
		norSortButton.setFont(newFont);   //меняем шрифт кнопки
		hBox2.add(norSortButton);  //вставляем кнопку в коробку 
		hBox2.add(Box.createHorizontalGlue());   //вставляем в коробку пружину
		JButton parSortButton = new JButton("Parallel sort");  //кнопка для параллельного суммирования массива
		parSortButton.addActionListener(l->{  
			arrayParts=new ArrayList<int[]>();
			partArraySize = n / (Integer)thrCount.getValue();  //размер частичного массива для каждого потока
			int iend=partArraySize;  //нижняя граница для формирования частичных массивов 
			//создаем исполнитель для запуска n потоков
			ExecutorService executor = Executors.newFixedThreadPool((Integer)thrCount.getValue());
			List<Future<int[]>> list = new ArrayList<Future<int[]>>(); //создаем список из будущих результатов
			for (int i = 0; i<(Integer)thrCount.getValue(); i++) { //цикл по кол-ву потоков
				int masPart[]=new int[partArraySize]; //создаем частичный массив
				for (int j = 0; j < masPart.length; j++) {//записываем в него элементы из главного массива
					masPart[j]=mas[j+iend*i]; 
				}
				if (i==0) { //для первого потока
					firstCall = new CallableForSort(masPart);  //создаем первый поток (отдельный класс, описан ниже)
					Future<int[]> fut = executor.submit(firstCall); //создаем объект для будущего результата созданного потока
					list.add(fut); //добавляем его в список
				}
				else { //для всех остальных потоков
					Future<int[]> fut = executor.submit(new CallableForSort(masPart));
					list.add(fut);
				}
			} 
			centerText.append("\nWaiting for result from callable threads...");
			centerText.append("\nCalculating...");
			//создаем исполнитель для запуска отдельного потока-диспетчера
			ExecutorService executor2 = Executors.newSingleThreadExecutor();
			executor2.execute(() -> { //запускаем анонимный поток 
				int i = 0;
				int pred_i = 0;
				while(!list.get(0).isDone()) { //для апдейта прогресс-бара из этого потока 
					i = firstCall.getI();
					if ((i+1)*100 / partArraySize % 20 == 0 && i!=pred_i) { //если достигли 20% от суммируемых элементов
						sumProgress.setValue(sumProgress.getValue()+20); //меняем значение прогресс-бара
						stateLabel.setText(sumProgress.getValue()+"%");	//выводим процент на лейбл
						System.out.println("% = "+ sumProgress.getValue());
						pred_i = i;
					}
				    try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(Future<int[]> fut : list){ //цикл по объектам с будущими результатами потоков
					try {
						centerText.append("\n"+fut.get());  //выводим результат потока
						arrayParts.add(fut.get()); //добавляем его в общую сумму
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
				mainArray=splitArrayParts();
				centerText.append("\nparallel sort: "+Arrays.toString(mainArray)); //выводим сумму массива в текстовую область
				sumProgress.setValue(100); //меняем значение прогресс-бара
				stateLabel.setText("100%");	//выводим процент на лейбл
			});
		});
		parSortButton.setFont(newFont); //меняем шрифт кнопки 
		hBox2.add(parSortButton);   //вставляем кнопку в коробку 

		vBox.add(hBox2);  //горизонтальную коробку вставляем в вертикальную 
		vBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5)); //делаем границу коробки
		window.add(vBox, BorderLayout.NORTH);  //вставляем коробку в верхнюю часть окна
	}
	public static int[] splitArrayParts(){ //метод для объединения отсортированных частей массива в один массив
		System.out.println();
		int[] resultArray = new int[mas.length]; //массив с объединенными частями
		int i=0, j=0, k=0;
		int a[]=arrayParts.get(0); //массив с первой отсортированной частью
		for (int n = 1; n < arrayParts.size(); n++) { 
			k=0; j=0; i=0;
			int b[]=arrayParts.get(n); //массив со следующей отсортированной частью
			while (k<(a.length+b.length)){ //цикл до конца обоих массивов
				if ((j>=a.length) || ((i<b.length) && (b[i]<=a[j]))) { // если массив b еще не закончился и b[i]<=a[j]
					resultArray[k]=b[i]; //в результирующий массив записываем элемент массива b
					k++;
					i++;
				}  
				else {  //иначе в результирующий массив записываем элемент массива a
					resultArray[k]=a[j];
					k++;
					j++;
				}
			}
			a=new int[k]; //пересоздаем массив а и увеличиваем его размер до k
			if (n!=arrayParts.size()-1)	//если не последняя итерация цикла
				for (int m=0; m<k; m++){ //переносим все элементы результирующего массива в массив а,
					a[m]=resultArray[m];  //чтобы продолжить слияние
				}
		}
//		System.out.println();
//		for (int l = 0; l < resultArray.length; l++) {
//			System.out.print(resultArray[l]+" "); 
//		}
//		System.out.println();
		return resultArray; //возвращаем в качестве результата общий массив с объединенными частями
	}

}