package models;

public class Roaming extends Driving implements Runnable{
	
	private boolean BeaconFound;
	
	public Roaming(boolean beaconFound) {
		super();
		BeaconFound = beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		BeaconFound = beaconFound;
	}

	public void run() {
		try {
			while (!BeaconFound) {
				for (int i = 0; i < 11; i++) {
					super.pilot.travel(500);
					super.pilot.rotate(160);
				}
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, roaming crashed");
		}
	}

}
