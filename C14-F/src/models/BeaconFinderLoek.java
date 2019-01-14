// BeaconFinder met verbeteringen
// zonder methods

package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class BeaconFinderLoek {

	private static final int MAXIMUM_RANGE_IR_SENSOR = 150; // de maximum range is tussen de 100~200 centimeter
	// afhankelijk van de bron

	EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
	SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
	float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie
	// edit Loek
	// initialize the Pilot and Driving in a correct manner
	Driving drive = new Driving();
	Pilot pilot = new Pilot();
	private boolean beaconFound = false;
	
	private int bearing;
	private int distance;

	public boolean isBeaconFound() {
		return beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	public BeaconFinderLoek() { // no args constructor
		super();
	}

	// vanaf hier is de IR-sensorcode:

	public void findBeacon() {
		// @aut Loek: edit: instantiate Pilot and Driving
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		while (Button.ESCAPE.isUp()) {
			seek.fetchSample(sample, 0);
			
			//System.out.println("1st while. Bearing: " + bearing);
			distance = (int) sample[1];
			System.out.println("1st while. Distance: " + distance);
			while (distance < 3) { // IRSensor is not perfect, so assuming distance < 3 means Marvin reached beacon
				if (distance > 21474836) {
					setBeaconFound(false);
					seek.fetchSample(sample, 0);
					bearing = (int) sample[0];
					distance = (int) sample[1];
					// drive.roam(beaconFound); // start roam
					System.out.println("2. Roam | Distance: " + distance);
					System.out.println("2. Roam | Bearing: " + bearing);
					drive.straight(30);
					seek.fetchSample(sample, 0);
					distance = (int) sample[1];
				} else { // inRange --> turn and drive to beacon
					setBeaconFound(true);
					seek.fetchSample(sample, 0);
					bearing = (int) sample[0];
					distance = (int) sample[1];
					System.out.println("2. inRange | Distance: " + distance);
					System.out.println("2. inRange | Bearing: " + bearing);
					// turn with bearing
					drive.turn(bearing); 
					seek.fetchSample(sample, 0);
					bearing = (int) sample[0];
					// drive distance to beacon
					drive.straight(distance);
					// fetch another sample to test if movement was sufficient
					seek.fetchSample(sample, 0);
					distance = (int) sample[1];
				}
			}
		}
		// ir.close();
	}
	
	/**
	 * @return latest fetch of bearing measurement from Sensor (int)
	 */
	public int fetchBearing() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		return bearing;
	}
	/**
	 * @return latest fetch of distance measurement from Sensor (int)
	 */
	public int fetchDistance() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		return distance;
	}
}
