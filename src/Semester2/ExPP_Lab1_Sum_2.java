package Semester2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExPP_Lab1_Sum_2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException{
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
		
		ExecutorService executor = Executors.newFixedThreadPool(thrCount);
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		for (int i = 0; i<thrCount; i++) {
			int masPart[]=new int[partArraySize]; //создаем временный массив
			for (int j = 0; j < masPart.length; j++) {//записываем в него элементы из главного массива
				masPart[j]=mas[j+iend*i]; 
			}
			Future<Integer> fut = executor.submit(new CallableForSum(masPart));
			list.add(fut);
		} 
		System.out.println("Waiting for result from callable threads...");
		while(!list.get(0).isDone()) {
		    System.out.println("Calculating...");
		    Thread.sleep(300);
		}
		int sum = 0;
		for(Future<Integer> fut : list){
			System.out.println(fut.get());
			sum = sum + fut.get();
		}
		System.out.println("parallel sum = "+sum);
		executor.shutdownNow();
		in.close();
	}
}