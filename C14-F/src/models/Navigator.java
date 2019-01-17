package models;

import lejos.hardware.Sound;


public class Navigator {
	
	private int travelDistance;
	private int rotation;
	
	Pilot pilot = new Pilot();
	
	public int getTravelDistance() {
		return travelDistance;
	}
	public void setTravelDistance(int travelDistance) {
		this.travelDistance = travelDistance;
	}
	public int getRotation() {
		return rotation;
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
		
	

	//methode om een vierkant te rijden en dit daarna ook te vieren!
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
