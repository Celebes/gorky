package pl.edu.wat.wcy.tim.gorky.runnables;

public class MyRunnable implements Runnable {

	private boolean zakonczonoPolaczenieHTTP = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public boolean isZakonczonoPolaczenieHTTP() {
		return zakonczonoPolaczenieHTTP;
	}

	public void setZakonczonoPolaczenieHTTP(boolean zakonczonoPolaczenieHTTP) {
		this.zakonczonoPolaczenieHTTP = zakonczonoPolaczenieHTTP;
	}
	
	
}
