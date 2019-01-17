package models;

public class Roamer extends Navigator implements Runnable{
	
	/**@author Frank
	 * The Roamer drives a 11 pointed star in order to enable the BeaconFinder to find the beacon in a 
	 * certain area.
	 * The implementation of runnable enables this method to run in a thread.
	 * In turn, this enables the simultaneous actions (e.g. Roam and look out for the beacon at the same time).
	 */
	
	private boolean stopRoaming;


	public Roamer(boolean stopRoaming) {
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
					pilot.travel(1500); //diameter van de ster
					pilot.rotate(160);
				}
				Thread.sleep(100);
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, roaming crashed");
		}
	}

}
