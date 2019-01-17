package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Pilot;

public class BeaconFinder {

	private static final int DISTANCE_TRESHOLD_ROAM = 21474836;

	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // use port S4 for IR Sensor
	private SensorMode seek = ir.getSeekMode(); // initiate seekmode in Sensor
	private float[] sample = new float[seek.sampleSize()]; // declare array to store samples form Sensor
	private boolean beaconFound;
	private int bearing;
	private int distance;

	private static Pilot pilot = new Pilot();

	public boolean isBeaconFound() {
		return beaconFound;
	}

	// no args cons
	public BeaconFinder() {
		super();
	}

	/** @author loek (+frankl for thread)
	 * When placed in area with beacon, it will roam until sensor will measure some
	 * values. When inRange it should turn & drive towards beacon. 
	 * NOTE: sensor is slow. Works, but it's not pretty
	 */
	public void findBeacon() {
		while (Button.DOWN.isUp()) {
			distance = fetchDistance();
			System.out.println("1st while. Distance: " + distance);
			Roamer roaming = new Roamer(beaconFound);
			Thread roamThread = new Thread(roaming);
			while (distance > 0) {
				if (distance >= DISTANCE_TRESHOLD_ROAM) {
					roamThread.start();
				}
				while (distance >= DISTANCE_TRESHOLD_ROAM) {
					seekRange();
				}
				 // TODO this needs to be the first action in inRange() method
				 // TODO setStopRoaming should not get 'true' but beaconFound as argument (or it can be, right?)
				 // TODO maybe also setStopRoaming in first line of seekRange() , as this needs to invoke start roaming??
				roaming.setStopRoaming(true);
				while (distance < DISTANCE_TRESHOLD_ROAM) { // inRange --> turn and drive to beacon
					inRange();
				}
			}
		}
		ir.close();
		System.exit(0);
	}

/**
 * initiates turn&drive towards beacon, fetches new measurement after each move
 */
	private void inRange() {
		beaconFound = true;
		bearing = fetchBearing();
		distance = fetchDistance();
		System.out.println("2. inRange | Distance: " + distance);
		System.out.println("2. inRange | Bearing: " + bearing);
		// turn with bearing, use Pilot
		pilot.rotate(-bearing);
		distance = fetchDistance();
		// drive distance to beacon
		pilot.travel(distance * 10);
		// fetch another sample to test if movement was sufficient
		distance = fetchDistance();
		bearing = fetchBearing();
	}

	/**
	 * initiates roamingMode from Pilot. Fetches new distance 
	 */
	private void seekRange() {
		beaconFound = false;
		bearing = fetchBearing();
		distance = fetchDistance();
		System.out.println("2. Roaming | Distance: " + distance);
		System.out.println("2. Roaming | Bearing: " + bearing);
		distance = fetchDistance();
	}

	/*
	 * @return bearing measurement from Sensor
	 */
	public int fetchBearing() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		return bearing;
	}

	/**
	 * @return distance measurement from Sensor
	 */
	public int fetchDistance() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		return distance;
		
	}
	
}
