package Semester1;
import java.awt.BorderLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Ex4 {
	static ScheduledExecutorService executor = null;
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("ScheduledExecutorService example");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Box northBox = new Box(BoxLayout.X_AXIS);
		JButton startThreads = new JButton("Start threads!");
		northBox.add(startThreads);
		northBox.add(Box.createHorizontalGlue());
		JButton stopThreads = new JButton("Stop threads!");
		northBox.add(stopThreads);
		
		myFrame.add(northBox, BorderLayout.NORTH);
		myFrame.pack();
		myFrame.setMinimumSize(myFrame.getSize());
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		String[] threads = {"A", "B"};
		int[] times = {1000, 1000};
		
		startThreads.addActionListener(arg0->{
			int i = 0;
			executor = Executors.newScheduledThreadPool(2);
			for (String string : threads) {
				executor.scheduleAtFixedRate(new SimpleRunnable2(string, times[i++]),1,2,TimeUnit.SECONDS);
			} 
		});

		stopThreads.addActionListener(l->{
			executor.shutdownNow();
		});	
		System.out.println("End main");
	}
}
