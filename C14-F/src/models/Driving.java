package models;

import lejos.hardware.Sound;


public class Driving {
	
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
		
	
	//methode om een enneagram te rijden (9 puntige ster) voor het zoeken van het beacon.
		//Na iedere 500 mm rijden of na het keren wordt gecontroleerd of het beacon gevonden is.
		public void roam(boolean beaconFound) {
			for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 4; j++) {
					pilot.travel(100); //2000 is de hoogte van de ster, dus eventueel de lengte (of breedte) van het te zoeken gebied. 
					if (beaconFound) {
						break;
						}
					}
				pilot.rotate(160); //De ster heeft scherpe hoeken (punten) van 20 graden. Hiervoor moet de robot 160 graden draaien.
				if (beaconFound) {
					break;
				}
			}
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
