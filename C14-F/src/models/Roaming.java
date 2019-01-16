package models;

public class Roaming extends Driving implements Runnable{
	
	private boolean stopRoaming;


	public Roaming(boolean stopRoaming) {
		super();
		this.stopRoaming = stopRoaming;
	}

	public void setStopRoaming(boolean stopRoaming) {
		this.stopRoaming = stopRoaming;
	}


	public void run() {
		try {
			while (!stopRoaming) {
				for (int i = 0; i < 11; i++) {
					super.pilot.travel(1500); //diameter van de ster
					super.pilot.rotate(160);                                                            
				}
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, roaming crashed");
		}
	}

}
