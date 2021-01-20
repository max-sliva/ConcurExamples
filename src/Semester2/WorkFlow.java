package Semester2;
public class WorkFlow implements Runnable {
	private int[] localArray; //локальный массив для сортировки
	private int localProgress; //локальный индикатор прогресса для текущей сортировки

	public int[] getLocalArray() {
		return localArray;
	}

	public int getLocalProgress() {
		return localProgress;
	}

	public WorkFlow(int[] localArray) {
		super();
		this.localArray = localArray;
		localProgress=localArray.length;
	}
	
	@Override
	public void run() {
		localArray=sort(localArray); //вызываем метод сортировки
		Thread.currentThread().interrupt();
	}

	public int[] sort(int[] a){ //сортировка выбором
		int min;
		int buf;
		for (int i = 0; i < a.length-1; i++){  
			min = i; 
			for (int j = i+1; j < a.length; j++) 
				if (a[j] < a[min]) min = j; 
			// поменяем местами a [min] и a[i] 
			buf = a[i]; 
			a[i] = a[min]; 
			a[min] = buf; 
			localProgress--;
		}
		return a;
	}
}
