package Semester2;

import java.util.Random;
import java.util.Scanner;


public class ExPP_Lab1_Sum_1 {

	public static void main(String[] args) throws InterruptedException{
		System.out.print("n = ");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int mas[] = new int[n];
		Random random = new Random();
		int s = 0;
		for (int i = 0; i < mas.length; i++) {
			mas[i] = random.nextInt(10);
			System.out.print(" "+mas[i]);
			s+=mas[i];
		} 
		System.out.println();
		System.out.println("sequense sum = "+s);
		System.out.print("thrCount = ");
		int thrCount = in.nextInt();
		int partArraySize = n / thrCount;
		int iend=partArraySize;  //нижняя граница для формирования частичных массивов 
		
		ThreadForSum threads[] = new ThreadForSum[thrCount];
		for (int i = 0; i<thrCount; i++) {
			int masPart[]=new int[partArraySize]; //создаем временный массив
			for (int j = 0; j < masPart.length; j++) {//записываем в него элементы из главного массива
				masPart[j]=mas[j+iend*i]; 
			}
			threads[i] = new ThreadForSum(masPart);
			threads[i].start();
		} 
		System.out.println("Waiting for result from callable threads...");
		while(threads[0].isAlive()) {
		    System.out.println("Calculating...");
		    Thread.sleep(300);
		}
		int sum = 0;
		for(int i = 0; i<thrCount; i++){
			System.out.println(threads[i].getSum());
			sum = sum + threads[i].getSum();
		}
		System.out.println("parallel sum = "+sum);
		in.close();
	}
}