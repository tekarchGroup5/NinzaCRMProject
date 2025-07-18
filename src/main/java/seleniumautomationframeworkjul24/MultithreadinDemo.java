package seleniumautomationframeworkjul24;

import java.io.IOException;

public class MultithreadinDemo implements Runnable {
	private int threadNo;  
	
	MultithreadinDemo(int threadNumber){
		this.threadNo = threadNumber;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(i+" Thread Number: "+this.threadNo);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
			MultithreadinDemo mt = new MultithreadinDemo(1);
			Thread t1 = new Thread(mt);
			MultithreadinDemo mt2 = new MultithreadinDemo(2);
			Thread t2 = new Thread(mt2);
			t1.start();
			t1.join();
			t2.start();
		
		
	}

}
