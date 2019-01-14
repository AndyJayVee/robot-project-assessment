// BeaconFinder met verbeteringen
// zonder methods

package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();
	// private Driving drive = new Driving();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	public void grabAndMoveBeacon() {
		pickUpBeacon();
//		reposition();
		putDownBeacon();
	}

	// method to grab the beacon and put it down at the opposite side
	public void pickUpBeacon() {

		arm.liftUp(); // lifts beacon up
//		drive.setRotation(180); // rotates 180 degrees
//		drive.straight(25); // drives straight for a distance of 25	
		System.out.println("lifted up");
	}

	// method to grab the beacon and put it down at the opposite side
	public void reposition() {
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		drive.turn(-45);
		System.out.println("turned -45");

	}

	// method to grab the beacon and put it down at the opposite side
	public void putDownBeacon() {
		arm.liftDown(); // puts beacon down
		System.out.println("put down");

	}

}
