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

	private static final int MAXIMUM_RANGE_IR_SENSOR = 150; // de maximum range is tussen de 100~200 centimeter
	// edit Loek
	// initialize the Pilot and Driving in a correct manner
	private Driving drive = new Driving();
	private static final int ROAM_DISTANCE = 21474836;

	private static final int DISTANCE_TRESHOLD_ROAM = 21474836;

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

	public BeaconFinder() {
		super();
	}

	// vanaf hier is de IR-sensorcode:

	/**
	 * When placed in area with beacon, it will roam until sensor will measure it
	 * (inRange) When inRsange it should switch to: turn&drive towards beacon. NOTE:
	 * sensor is slow. Works, but it's not pretty
	 */
	public void findBeacon() {
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		while (Button.DOWN.isUp()) {
			// TODO seek.fetchSample(sample, 0);

			// System.out.println("1st while. Bearing: " + bearing);
			distance = fetchDistance();
			System.out.println("1st while. Distance: " + distance);
			while (distance > 0) {
				while (distance >= DISTANCE_TRESHOLD_ROAM) {
					bearing = fetchBearing();
					distance = fetchDistance();
					System.out.println("2. Roaming | Distance: " + distance);
					System.out.println("2. Roaming | Bearing: " + bearing);
					drive.roam(beaconFound);
					distance = fetchDistance();
				}
				while (distance < DISTANCE_TRESHOLD_ROAM) { // inRange --> turn and drive to beacon
					setBeaconFound(true);
					System.out.println("Beacon found, going to beacon");
					drive.turn(bearing); // gaat de robot in de richting van het beacon roteren met "bearing" graden
					seek.fetchSample(sample, 0);
					bearing = (int) sample[0];
					drive.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
					seek.fetchSample(sample, 0);
					distance = (int) sample[1];
				}

			}
		}
		ir.close();
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
