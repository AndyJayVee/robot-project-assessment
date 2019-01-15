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
															// afhankelijk van de bron
	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); 		// activeert een nieuwe IR-sensor op poort S4
	private SensorMode seek = ir.getSeekMode(); 					// activeert de Seek modus
	private float[] sample = new float[seek.sampleSize()]; 			// maakt array met sample informatie
	// edit Loek
	// initialize the Pilot and Driving in a correct manner
	private Driving drive = new Driving();
	private static final int ROAM_DISTANCE = 21474836;
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
	 * When placed in area with beacon, it will roam untill sensor will measure it (inRange)
	 * When inRsange it should switch to: turn&drive towards beacon.
	 * NOTE: sensor is slow. Works, but it's not pretty
	 */
	public void findBeacon() {
		// @aut Loek: edit: instantiate Pilot and Driving
		while (Button.ESCAPE.isUp()) {
			seek.fetchSample(sample, 0);
			int bearing = (int) sample[0];
			System.out.println("Bearing: " + bearing);
			int distance = (int) sample[1];
			System.out.println("Distance: " + distance);

			while (bearing != 0 && distance != 0) {

				if (distance > MAXIMUM_RANGE_IR_SENSOR) {
					// dit werkt niet omdat roam alleen begint als bearing !=0
					// echter if (niet-in-range) dan geldt bearing ==0;
					// dus roam start nooit omdat bearing altijd 0 is.
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

					System.out.println("in de tweede while loop");
					drive.roam(beaconFound); // begin met roam
					System.out.println("Start roaming");
				} else if (distance <= MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
		while (Button.ESCAPE.isUp()) {
			distance = fetchDistance();
			System.out.println("1st while. Distance: " + distance);
			while (distance > 0) {
				while (distance >= ROAM_DISTANCE) {
					setBeaconFound(false);
					
					bearing = fetchBearing();
					distance = fetchDistance();
					System.out.println("2. Roaming | Distance: " + distance);
					System.out.println("2. Roaming | Bearing: " + bearing);
					drive.roam(beaconFound);
					distance = fetchDistance();
				}
				while (distance < ROAM_DISTANCE) { // inRange --> turn and drive to beacon
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
		// ir.close();
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
		// TODO is this needed?
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
