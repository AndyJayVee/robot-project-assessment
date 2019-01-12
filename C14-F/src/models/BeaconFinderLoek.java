package models;

/**
 * @author loek
 * Adjustment: improve start/roam functionality && improve readbility in logic
 */

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class BeaconFinderLoek {

	private static final int MAXIMUM_RANGE_IR_SENSOR = 150;

	EV3IRSensor ir = new EV3IRSensor(SensorPort.S4);
	SensorMode seek = ir.getSeekMode(); // TODO small explanation seekmode?
	Driving drive = new Driving();
	Pilot pilot = new Pilot();

	float[] sample = new float[seek.sampleSize()];

	private int bearing;
	private int distance;

	private boolean beaconFound = false;

	public boolean isBeaconFound() {
		return beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	public BeaconFinderLoek() { // no args constructor
		super();
	}

	/**
	 * if invoked will start measuring + moving. Can be invoked from random position
	 * Tests whether in range (moves to beacon or not in range (roam)
	 */
	public void findBeacon() {
		// TODO not sure why exitkey is needed?
		while (Button.ESCAPE.isUp()) {
			// Fetch an initial measurement for distance and store this value
			seek.fetchSample(sample, 0);
			distance = (int) sample[1];
			System.out.println("1st while distance: " + distance);

			// if not in Range, fetch distance will give infinite
			// so: while (distance is inifite keep roaming), after distance becomes lower,
			// break out while, and start inRange()
			while (distance > 150) {
				// start roam
				drive.roam(beaconFound);
				// during roam keep fetching and updating local distance variable
				// this to break out of this while()
				distance = getDistance();
			}
			// TODO do we need a driving.stopRoam to prevent eternal roaming??
			
			// now status == beaconFound(true) as we're in range of sensor
			// while not at beacon yet keep doing inRange()
			while (distance > 0) {
				inRange();
				
				// If Marvin has arrived at beacon, it should break from this while
				// following line will ensure that it breaks from current while loop
				// at least when it measures distance smaller than the 0 
				distance = getDistance();
			}
			// TODO do we need a command to let Marvin stop? 
		}
			// TODO or needs te stop command come here, when Excape key is pressed?
	}
	/**
	 * Puts robot in mode to turn towards beacon and drive towards it
	 */
	private void inRange() {
		// this will let other classes read the latest status of whether
		// distance/bearing are known or not
		// by now bearing and distance are measured == known == found(true)
		setBeaconFound(true);

		System.out.println("inRange() bearing: " + bearing);
		drive.turn(bearing);

		// do another fetch (in a perfect world bearing would be 0 after latest
		// fetch&turn)
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		distance = (int) sample[1];

		// drive the distance as fetched
		drive.straight(distance);
		// fetch again and adjust bearing/distance if needed
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
	}

	public int getBearing() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		return bearing;
	}

	public int getDistance() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		return distance;
	}
}

/** After update redundant, keeping it for now
 * Sets robot in roam mode
 */

//public void roamMode() {
//	drive.roam(beaconFound); // start roam
//
//	// fetch measurement and store the value
//	seek.fetchSample(sample, 0);
//	distance = (int) sample[1];
//}
