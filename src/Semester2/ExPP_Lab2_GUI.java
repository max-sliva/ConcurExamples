package Semester2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
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

public class ExPP_Lab2_GUI {
	static JTextArea centerText = new JTextArea("Ready!"); //центральная текстовая область
	static int n = 0; //кол-во эл-ов в массиве 
	static int mas[];  //сам массив
	static JProgressBar sumProgress;  //прогресс-бар
	static JLabel stateLabel;  //лейбл с процентом прогресса
	static CallableForSum_GUI firstCall; //один из объектов типа Callable для вычисления
	static int sumPar = 0; //для итоговой параллельной суммы 
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
		JButton norSumButton = new JButton("Normal sum"); //кнопка для обычного суммирования массива
		norSumButton.addActionListener(l->{  //обработчик нажатия на кнопку
			centerText.append("\n");  //переход на новую строку
			n = Integer.parseInt(arrSize.getText()); //размер массива
			mas = new int[n];  //выделяем память под массив
			Random random = new Random();
			int s = 0;  //для хранения суммы массива
			for (int i = 0; i < mas.length; i++) { //цикл по всему массиву
				mas[i] = random.nextInt(5)+1;  //рандомное значение элемента массива
				centerText.append(" "+mas[i]); //выводим элемент массива в текстовую область
				s+=mas[i];  //суммируем элементы массива
			} 
			centerText.append("\nsequense sum = "+s); //выводим сумму массива в текстовую область 
		});
		norSumButton.setFont(newFont);   //меняем шрифт кнопки
		hBox2.add(norSumButton);  //вставляем кнопку в коробку 
		hBox2.add(Box.createHorizontalGlue());   //вставляем в коробку пружину
		JButton parSumButton = new JButton("Parallel sum");  //кнопка для параллельного суммирования массива
		parSumButton.addActionListener(l->{  
			int partArraySize = n / (Integer)thrCount.getValue();  //размер частичного массива для каждого потока
			int iend=partArraySize;  //нижняя граница для формирования частичных массивов 
			//создаем исполнитель для запуска n потоков
			ExecutorService executor = Executors.newFixedThreadPool((Integer)thrCount.getValue());
			List<Future<Integer>> list = new ArrayList<Future<Integer>>(); //создаем список из будущих результатов
			for (int i = 0; i<(Integer)thrCount.getValue(); i++) { //цикл по кол-ву потоков
				int masPart[]=new int[partArraySize]; //создаем частичный массив
				for (int j = 0; j < masPart.length; j++) {//записываем в него элементы из главного массива
					masPart[j]=mas[j+iend*i]; 
				}
				if (i==0) { //для первого потока
					firstCall = new CallableForSum_GUI(masPart);  //создаем первый поток (отдельный класс, описан ниже)
					firstCall.setPrBar(sumProgress);  //передаем ему прогресс-бар
					firstCall.setPrLabel(stateLabel);  //и лейбл для вывода кол-ва процентов 
					Future<Integer> fut = executor.submit(firstCall); //создаем объект для будущего результата созданного потока
					list.add(fut); //добавляем его в список
				}
				else { //для всех остальных потоков
					Future<Integer> fut = executor.submit(new CallableForSum_GUI(masPart));
					list.add(fut);
				}
			} 
			centerText.append("\nWaiting for result from callable threads...");
			centerText.append("\nCalculating...");
			//создаем исполнитель для запуска отдельного потока-диспетчера
			ExecutorService executor2 = Executors.newSingleThreadExecutor();
			executor2.execute(() -> { //запускаем анонимный поток
				for(Future<Integer> fut : list){ //цикл по объектам с будущими результатами потоков
					try {
						centerText.append("\n"+fut.get());  //выводим результат потока
						sumPar = sumPar + fut.get(); //добавляем его в общую сумму
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
				centerText.append("\nparallel sum = "+sumPar); //выводим сумму массива в текстовую область
			});
		});
		parSumButton.setFont(newFont); //меняем шрифт кнопки 
		hBox2.add(parSumButton);   //вставляем кнопку в коробку 

		vBox.add(hBox2);  //горизонтальную коробку вставляем в вертикальную 
		vBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5)); //делаем границу коробки
		window.add(vBox, BorderLayout.NORTH);  //вставляем коробку в верхнюю часть окна
	}
}