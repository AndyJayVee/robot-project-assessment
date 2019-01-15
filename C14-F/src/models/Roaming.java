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

	//methode om een enneagram te rijden (9 puntige ster) voor het zoeken van het beacon.
	//Na iedere 500 mm rijden of na het keren wordt gecontroleerd of het beacon gevonden is.
	public void run() {
		try {
			while (!BeaconFound) {
				for (int i = 0; i < 11; i++) {
					super.pilot.travel(500); //diameter van de ster
					super.pilot.rotate(160);                                                            
				}
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, roaming crashed");
		}
	}

}
