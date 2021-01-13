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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ExPP_Lab2_GUI {

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		JFrame window = new JFrame("PPWindow");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setNorth(window);
		JTextArea centerText = new JTextArea("Ready!");
		Font newFont = centerText.getFont();
		newFont=newFont.deriveFont(20.0f);
		centerText.setFont(newFont);
		centerText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		window.add(centerText, BorderLayout.CENTER);
		JLabel stateLabel = new JLabel("0%");
		window.add(stateLabel, BorderLayout.SOUTH);
		
		window.setVisible(true);	
		window.setLocationRelativeTo(null);
		window.setSize(500, 300);
		window.setMinimumSize(window.getSize());
		window.setLocation(window.getX()-window.getWidth()/2, window.getY()-window.getHeight()/2); 
//		int n = 0;
//		int mas[] = new int[n];
//		Random random = new Random();
//		int s = 0;
//		for (int i = 0; i < mas.length; i++) {
//			mas[i] = random.nextInt(10);
//			System.out.print(" "+mas[i]);
//			s+=mas[i];
//		} 
//		System.out.println();
//		System.out.println("sequense sum = "+s);
//		System.out.print("thrCount = ");
//		int thrCount = in.nextInt();
//		int partArraySize = n / thrCount;
//		int iend=partArraySize;  //нижняя граница для формирования частичных массивов 
//		
//		ExecutorService executor = Executors.newFixedThreadPool(thrCount);
//		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
//		for (int i = 0; i<thrCount; i++) {
//			int masPart[]=new int[partArraySize]; //создаем временный массив
//			for (int j = 0; j < masPart.length; j++) {//записываем в него элементы из главного массива
//				masPart[j]=mas[j+iend*i]; 
//			}
//			Future<Integer> fut = executor.submit(new CallableForSum(masPart));
//			list.add(fut);
//		} 
//		System.out.println("Waiting for result from callable threads...");
//		while(!list.get(0).isDone()) {
//		    System.out.println("Calculating...");
//		    Thread.sleep(300);
//		}
//		int sum = 0;
//		for(Future<Integer> fut : list){
//			System.out.println(fut.get());
//			sum = sum + fut.get();
//		}
//		System.out.println("parallel sum = "+sum);
//		executor.shutdownNow();
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
		norSumButton.setFont(newFont);
		hBox2.add(norSumButton);
		hBox2.add(Box.createHorizontalGlue());
		JButton parSumButton = new JButton("Parallel sum");
		parSumButton.setFont(newFont);
		hBox2.add(parSumButton);

		vBox.add(hBox2);
		vBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		window.add(vBox, BorderLayout.NORTH);
	}
}