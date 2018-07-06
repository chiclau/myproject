package com.liu.terminate;

public class Hello implements Runnable {
	int i=0;
	private boolean timeToQuit=false;
	@Override    
	public void run() {
		//标志没有被设置前，将每隔10ms输出两行hello
		while(!timeToQuit) {
			System.out.println("hello "+i++);
			try {
				if(i%2==0) Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	
	public void stopRunning() {
		timeToQuit=true;
	}
}
