package models;

import lejos.hardware.Sound;
import lejos.robotics.navigation.MovePilot;

public class Driving {

	private int travelDistance;
	private int rotation;
//	private static final int POSITIVE_INFINITY = 1/0;

	MovePilot pilot;

	public Driving() {
		super();
	}

	public Driving(MovePilot p) {
		pilot = p;
	}

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

	// Methode om de robot te laten rijden en een bochtje te laten maken zoals
	// bijvoorbeeld bij een vierkant.
	public void travelAndRotate() {
		pilot.travel(travelDistance);
		pilot.rotate(rotation);
	}

	// methode om bepaald stuk rechtuit te rijden.
	public void straight(int distance) {
		pilot.travel(distance);
	}

	// methode om rechtuit te rijden.
	public void drive() {
		pilot.forward();
	}

	// methode om te roteren.
	public void turn(int bearing) {
		pilot.rotate(-bearing);
	}

	// methode om te roteren tot een stopsignaal.
	public void turning(boolean lineFound) {
		pilot.rotate(-90, lineFound);
	}

	// methode om een enneagram te rijden (9 puntige ster) voor het zoeken van het
	// beacon.
	// Na iedere 500 mm rijden of na het keren wordt gecontroleerd of het beacon
	// gevonden is.
	public void roam(boolean beaconFound) {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 4; j++) {
				pilot.travel(100); // 2000 is de hoogte van de ster, dus eventueel de lengte (of breedte) van het
									// te zoeken gebied.
				if (beaconFound) {
					break;
				}
			}
			pilot.rotate(160); // De ster heeft scherpe hoeken (punten) van 20 graden. Hiervoor moet de robot
								// 160 graden draaien.
			if (beaconFound) {
				break;
			}
		}
	}

	// methode om een vierkant te rijden en dit daarna ook te vieren!
	public void driveRectangle(int distance) {
		for (int i = 0; i < 4; i++) {
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

	public void driveCurve(double radius, double angle) {
		// code die een bochtje draait
		pilot.arc(radius, angle);

		// TODO Auto-generated method stub

	}

}
