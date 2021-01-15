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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ExPP_Lab2_GUI {
	static JTextArea centerText = new JTextArea("Ready!");
	static int n = 0;
	static int mas[];
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		JFrame window = new JFrame("PPWindow");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setNorth(window);
		Font newFont = centerText.getFont();
		newFont=newFont.deriveFont(20.0f);
		centerText.setFont(newFont);
		centerText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		window.add(new JScrollPane(centerText), BorderLayout.CENTER);
		JLabel stateLabel = new JLabel("0%");
		window.add(stateLabel, BorderLayout.SOUTH);
		
		window.setVisible(true);	
		window.setLocationRelativeTo(null);
		window.setSize(500, 300);
		window.setMinimumSize(window.getSize());
		window.setLocation(window.getX()-window.getWidth()/2, window.getY()-window.getHeight()/2); 
	}

	private static void setNorth(JFrame window) {
		Box vBox = new Box(BoxLayout.Y_AXIS);
		Box hBox1 = new Box(BoxLayout.X_AXIS);
		JLabel arrSizeLabel = new JLabel("Array size:  ");
		Font newFont = arrSizeLabel.getFont();
		newFont=newFont.deriveFont(20.0f);
		arrSizeLabel.setFont(newFont);
		hBox1.add(arrSizeLabel);
		
		JTextField arrSize = new JTextField(5);
		arrSize.setFont(newFont);
		hBox1.add(arrSize);
		arrSize.setMaximumSize(new Dimension(60, arrSize.getMinimumSize().height));
		hBox1.add(Box.createHorizontalGlue());
		JLabel thrCountLabel = new JLabel("Threads count:   ");
		thrCountLabel.setFont(newFont);
		hBox1.add(thrCountLabel);
		JSpinner thrCount = new JSpinner(new SpinnerNumberModel(2, 2, 16, 2));
		thrCount.setMaximumSize(new Dimension(50, thrCount.getMinimumSize().height+10));
		thrCount.setFont(newFont);
		hBox1.add(thrCount);
		vBox.add(hBox1);
		
		Box hBox2 = new Box(BoxLayout.X_AXIS);
		JButton norSumButton = new JButton("Normal sum");
		norSumButton.addActionListener(l->{
			centerText.append("\n");
			n = Integer.parseInt(arrSize.getText());
			mas = new int[Integer.parseInt(arrSize.getText())];
			Random random = new Random();
			int s = 0;
			for (int i = 0; i < mas.length; i++) {
				mas[i] = random.nextInt(10);
				centerText.append(" "+mas[i]);
				s+=mas[i];
			} 
			centerText.append("\nsequense sum = "+s);
		});
		norSumButton.setFont(newFont);
		hBox2.add(norSumButton);
		hBox2.add(Box.createHorizontalGlue());
		JButton parSumButton = new JButton("Parallel sum");
		parSumButton.addActionListener(l->{
			int partArraySize = n / (Integer)thrCount.getValue();
			int iend=partArraySize;  //нижняя граница для формирования частичных массивов 
			
			ExecutorService executor = Executors.newFixedThreadPool((Integer)thrCount.getValue());
			List<Future<Integer>> list = new ArrayList<Future<Integer>>();
			for (int i = 0; i<(Integer)thrCount.getValue(); i++) {
				int masPart[]=new int[partArraySize]; //создаем временный массив
				for (int j = 0; j < masPart.length; j++) {//записываем в него элементы из главного массива
					masPart[j]=mas[j+iend*i]; 
				}
				Future<Integer> fut = executor.submit(new CallableForSum(masPart));
				list.add(fut);
			} 
			centerText.append("\nWaiting for result from callable threads...");
			while(!list.get(0).isDone()) {
				centerText.append("\nCalculating...");
			    try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int sum = 0;
			for(Future<Integer> fut : list){
				try {
					centerText.append("\n"+fut.get());
					sum = sum + fut.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			centerText.append("\nparallel sum = "+sum);
			executor.shutdownNow();

		});
		parSumButton.setFont(newFont);
		hBox2.add(parSumButton);

		vBox.add(hBox2);
		vBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		window.add(vBox, BorderLayout.NORTH);
	}
}