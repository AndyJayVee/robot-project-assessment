package models;

public class Roaming extends Driving implements Runnable{
	
	private boolean BeaconIsFound;
	
	public Roaming(boolean BeaconIsFound) {
		super();
		this.BeaconIsFound = BeaconIsFound;
	}
	
	public void setBeaconIsFound(boolean BeaconIsFound) {
		this.BeaconIsFound = BeaconIsFound;
	}

	public void run() {
		try {
			while (!BeaconIsFound) {
				for (int i = 0; i < 11; i++) {
					getPilot().travel(100);
					getPilot().rotate(160);
				}
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, something went\n wrong with roaming");
		}
	}

}
