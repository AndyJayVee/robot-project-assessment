/**
 * MONDAY Working version, checks if needs to roam or turn/drive
 */
package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class BeaconFinder {

	private static final int DISTANCE_TRESHOLD_ROAM = 21474836;

	private EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S4); // use port S4 for IR Sensor
	private SensorMode seek = irSensor.getSeekMode(); // initiate seekmode
	private float[] sample = new float[seek.sampleSize()]; // declare array to store samples form Sensor
	private boolean beaconFound;
	private Pilot pilot;
	private Driver drive;

	private int bearing;
	private int distance;

	// no args cons
	public BeaconFinder() {
		super();
		this.irSensor = new EV3IRSensor(SensorPort.S4); // use port S4 for IR Sensor
		this.seek = irSensor.getSeekMode(); // initiate seekmode
		this.sample = new float[seek.sampleSize()];
		this.beaconFound = beaconFound;
		this.pilot = new Pilot();
		this.drive = new Driver.

	}

	public boolean isBeaconFound() {
		return beaconFound;
	}

	/**
	 * When placed in area with beacon, it will roam until sensor will measure it
	 * (inRange) When inRsange it should switch to: turn&drive towards beacon. NOTE:
	 * sensor is slow. Works, but it's not pretty
	 */
	public void findBeacon() {

		while (Button.ESCAPE.isUp()) {
			distance = fetchDistance();
			System.out.println("1st while. Distance: " + distance);
			while (distance > 0) {
				while (distance >= ROAM_DISTANCE) {
					setBeaconFound(false);
					seekRange();
				}
				while (distance < ROAM_DISTANCE) { // inRange --> turn and drive to beacon
					setBeaconFound(true);
					inRange();
				}
			}
			irSensor.close();
		}

	}

	private void seekRange() {
		bearing = fetchBearing();
		distance = fetchDistance();
		System.out.println("2. Roaming | Distance: " + distance);
		System.out.println("2. Roaming | Bearing: " + bearing);
		drive.roam(beaconFound);
		distance = fetchDistance();
	}

	private void inRange() {
		bearing = fetchBearing();
		distance = fetchDistance();
		System.out.println("2. inRange | Distance: " + distance);
		System.out.println("2. inRange | Bearing: " + bearing);
		// turn with bearing
		drive.turn(bearing);
		distance = fetchDistance();
		// drive distance to beacon
		drive.straight(distance);
		// fetch another sample to test if movement was sufficient
		distance = fetchDistance();
		bearing = fetchBearing();
	}

	/*
	 * @return Fetch bearing measurement from Sensor
	 */
	public int fetchBearing() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		return bearing;
	}

	/**
	 * @return Fetch distance measurement from Sensor
	 */
	public int fetchDistance() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		return distance;
	}
}
