package models;

public class Forward extends Driving implements Runnable {

	private Driving drive = new Driving();
	private boolean continueDriving = true;
		
	public Forward(boolean continueDriving) {
		super();
		this.continueDriving = continueDriving;
	}

	
	public void setContinueDriving(boolean continueDriving) {
		this.continueDriving = continueDriving;
	}


	@Override
	public void run() {
		
		try {
			while (continueDriving)
				drive.getPilot().forward();
		
		} catch (Exception e) {
			System.out.printf("Oops, forward");
		}
		
		
	}
}
