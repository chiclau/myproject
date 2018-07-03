package com.liu.chuli;

public class LostMessage {
	void w() throws ImportantException {
		throw new ImportantException();
	}
	
	void dispose() throws NormalException {
		throw new NormalException();
	}
	
	public static void main(String[] args) throws Exception {
		LostMessage lm=new LostMessage();
		try {
			lm.w();
		} finally {
			lm.dispose();
		}
	}
}
