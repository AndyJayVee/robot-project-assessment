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
			int distance = (int) sample[1];
			System.out.println("1st while distance: " + distance);

			// Here's where the magic happens
			// if not in Range, fetch distance will give infinite
			// so while distance is inifite keep roaming
			if (distance > 500) {
				roamMode();
			} else {// when
				inRange();
			}
		}
	}
	// TODO decide if we need this (or on another spot??)
	// ir.close();

	/**
	 * Puts the robot in roamingmode. Roaming is defined in Driving
	 */
	private void roamMode() {
		// roam
		drive.roam(beaconFound); // begin met roam

		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		System.out.println("Roaming: distance: " + distance);

		// TODO if latest distance fetch == within range, stop roaming
		// and start phase2
		if (distance < 70) {
			// proceed with inRange actions
			inRange();
		}
	}

	/**
	 * Puts robot in mode to turn towards beacon and drive towards it
	 */
	private void inRange() {
		// this will let other classes read the latest boolean value
		setBeaconFound(true);

		System.out.println("inRange, bearing: " + bearing);
		drive.turn(bearing);

		// do another fetch (in a perfect world would be 0 after latest fetch&turn)
		seek.fetchSample(sample, 0);

		bearing = (int) sample[0];

		drive.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		if (distance == 0) {
			// TODO stop when it hits something || last distance fetch == 0??
		}
	}
}