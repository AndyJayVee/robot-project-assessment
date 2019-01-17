/**
 * @author Leo
 * @author Loek
 * @author Frank
 */

package models;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Pilot;
import models.Roamer;

public class BeaconFinder {

	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // use port S4 for IR Sensor
	private SensorMode seek = ir.getSeekMode(); // initiate seekmode in Sensor
	private float[] sample = new float[seek.sampleSize()]; // declare array to store samples form Sensor
	private boolean beaconFound;
	private int bearing;
	private int distance;
	private boolean stop = false;
	private Pilot pilot = new Pilot();

	

	public boolean isBeaconFound() {
		return beaconFound;
	}

	// no args cons
	public BeaconFinder() {
		super();
	}

	/**
	 * @author loek (+frankl for thread) When placed in area with beacon, it will
	 *         roam until sensor will measure some values. When inRange it should
	 *         turn & drive towards beacon. NOTE: sensor is slow. Works, but it's
	 *         not pretty
	 */
	public void findBeacon() {
//		Roamer roaming = new Roamer(beaconFound);
//		Thread roamThread = new Thread(roaming);
//		roamThread.start();
		distance = fetchDistance();
//		System.out.println("Distance = " + distance);
//		while (!stop || (distance<=0)) { 
//			distance = fetchDistance();
//			System.out.println("1st while. Distance: " + distance);;
//			while (distance >= 1500 || distance <= 0) {
//				bearing = fetchBearing();
//				distance = fetchDistance();
//				System.out.println("2. Roaming | Distance: " + distance);
//				System.out.println("2. Roaming | Bearing: " + bearing);
//			}
//			beaconFound = true;
//			roaming.setStopRoaming(beaconFound);
//			roamThread.interrupt();
		pilot.arc(5000, 45);
		pilot.arc(-5000, -45);
			while (distance < 1000 && distance > 5) { // inRange --> turn and drive to beacon
				bearing = fetchBearing();
				distance = fetchDistance();
				System.out.println("2. inRange | Distance: " + distance);
				System.out.println("2. inRange | Bearing: " + bearing); //these prints ARE printed.
				// turn with bearing
				pilot.rotate(-bearing);   //Pilot does not rotate with bearing. Possible due to thread interrupt.
				distance = fetchDistance();
				// drive distance to beacon
				pilot.travel(distance * 8); //Pilot does not travel to beacon. Possible due to thread interrupt.
				// fetch another sample to test if movement was sufficient
				distance = fetchDistance();
				bearing = fetchBearing();
				}
//			if (distance <= 5) {
//				stop = true;
//			}
//			beaconFound = false;
//			roaming.setStopRoaming(beaconFound);
//			if (stop = true) {
//				break;
//			}
			ir.close();	
	}

	/**
	 * initiates turn&drive towards beacon, fetches new measurement after each move
	 */
//	private void inRange() {
//		beaconFound = true;
//		bearing = fetchBearing();
//		distance = fetchDistance();
//		System.out.println("2. inRange | Distance: " + distance);
//		System.out.println("2. inRange | Bearing: " + bearing);
//		// turn with bearing, use Pilot
//		pilot.rotate(-bearing);
//		distance = fetchDistance();
//		// drive distance to beacon
//		pilot.travel(distance * 10);
//		// fetch another sample to test if movement was sufficient
//		distance = fetchDistance();
//		bearing = fetchBearing();
//	}
//
//	/**
//	 * initiates roamingMode from Pilot. Fetches new distance
//	 */
//	private void seekRange() {
//		beaconFound = false;
//		bearing = fetchBearing();
//		distance = fetchDistance();
//		System.out.println("2. Roaming | Distance: " + distance);
//		System.out.println("2. Roaming | Bearing: " + bearing);
//		distance = fetchDistance();
//	}

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
