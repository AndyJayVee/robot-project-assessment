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

	private static final int ROAM_DISTANCE = 21474836;

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
	public BeaconFinder() {
		super();
	}

	/**
	 * If placed within range, will turn and drive towards
	 */
	public void findBeacon() {
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		while (Button.DOWN.isUp()) {
			// TODO seek.fetchSample(sample, 0); 
			
			//System.out.println("1st while. Bearing: " + bearing);
			distance = fetchDistance();
			System.out.println("1st while. Distance: " + distance);
			while (distance > 0) {
				while (distance >= ROAM_DISTANCE) {					
					bearing = fetchBearing();
					distance = fetchDistance();
					System.out.println("2. Roaming | Distance: " + distance);
					System.out.println("2. Roaming | Bearing: " + bearing);
					drive.roam(beaconFound);
					distance = fetchDistance();
				}
				while (distance < ROAM_DISTANCE) { // inRange --> turn and drive to beacon
					setBeaconFound(true);
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
			}
		}
		ir.close();
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
