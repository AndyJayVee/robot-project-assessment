package models;

import lejos.hardware.Sound;

/**@author Frank
 * The driver tells a pilot to drive more complex movements like the star used for finding the beacon (Roamer) 
 */

public class Navigator {
	
	private int travelDistance;
	private int rotation;
	protected Pilot pilot = new Pilot();
	

	public void setTravelDistance(int travelDistance) {
		this.travelDistance = travelDistance;
	}

	public void setRotation(int rotate) {
		this.rotation = rotate;
	}
	
	//Methode om de robot te laten rijden en een bochtje te laten maken zoals
	//bijvoorbeeld bij een vierkant.
		public void travelAndRotate() {
			pilot.travel(travelDistance);
			pilot.rotate(rotation);
		}
		
	

	//methode om een vierkant te rijden en dit daarna ook te vieren met geluid en beweging!
		public void driveRectangle(int distance) {
			for (int i = 0; i<4; i++) {
				pilot.rotate(90);
				pilot.travel(distance);
				Sound.beep();
			} 
			Sound.beepSequenceUp();
			pilot.rotate(-360);
			Sound.beepSequence();
			pilot.rotate(360);
			Sound.beepSequenceUp();
			
		}
	}
