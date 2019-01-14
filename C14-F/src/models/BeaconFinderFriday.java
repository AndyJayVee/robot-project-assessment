package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class BeaconFinderFriday {

	private static final int MAXIMUM_RANGE_IR_SENSOR = 150; // based on max range Sensor

	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // use port S4 for IR Sensor
	private SensorMode seek = ir.getSeekMode(); // initiate seekmode
	private float[] sample = new float[seek.sampleSize()]; // declare array to store samples form Sensor
	private boolean beaconFound = false;

	private int bearing;
	private int distance;

	public boolean isBeaconFound() {
		return beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	// no args cons
	public BeaconFinderFriday() {
		super();
	}

	/**
	 * If placed within range, will turn and drive towards
	 */
	public void findBeacon() {
		// Instantiate Pilot and Driving
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		while (Button.ESCAPE.isUp()) {
			bearing = fetchBearing();
			distance = fetchDistance();
			System.out.println("1. Bearing/Distance: " + bearing + " " + distance);
			while (distance > 0) {

				if (distance > MAXIMUM_RANGE_IR_SENSOR) {

					System.out.println("2. Roam");
					drive.roam(beaconFound); // begin met roam
					System.out.println("2. R | Bearing/Distance: " + bearing + " " + distance);
				} else if (distance <= MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
					setBeaconFound(true);
					System.out.println("2. IR | before turn bearing: " + bearing);
					drive.turn(bearing); // gaat de robot in de richting van het beacon roteren met "bearing" graden
					bearing = fetchBearing();
					distance = fetchDistance();
					System.out.println("2. IR | after turn drive distance: " + distance);
					drive.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
					distance = fetchDistance();
					System.out.println("2. IR | after drive bearing: " + bearing);
					System.out.println("2. IR | after drive distance: " + distance);
				}
			}
		}
		// ir.close();
	}

	/*
	 * @return Fetch of bearing measurement from Sensor
	 */
	public int fetchBearing() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		return bearing;
	}

	/**
	 * @return Fetch of distance measurement from Senso
	 */
	public int fetchDistance() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		return distance;
	}
}
