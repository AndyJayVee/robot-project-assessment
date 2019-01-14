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

	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
	private SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
	private float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie
	private boolean beaconFound = false;

	public boolean isBeaconFound() {
		return beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	public BeaconFinder() { // no args constructor
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
			seek.fetchSample(sample, 0);
			int bearing = (int) sample[0];
			int distance = (int) sample[1];
			System.out.println("1. Bearing/Distance: " + bearing + " " + distance);
			while (bearing != 0 && distance != 0) {
				// while (bearing != 0 && distance != 0) will never be the condition to roam.
				// Roaming should happen
				// while (bearing = 0 && distance != 0) OR while (distance > 100000)
				if (distance > MAXIMUM_RANGE_IR_SENSOR) {

					System.out.println("2. Roam");
					drive.roam(beaconFound); // begin met roam
					System.out.println("2. R | Bearing/Distance: " + bearing + " " + distance);
				} else if (distance <= MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
					setBeaconFound(true);
					System.out.println("2. IR | before turn bearing: " + bearing);
					drive.turn(bearing); // gaat de robot in de richting van het beacon roteren met "bearing" graden
					seek.fetchSample(sample, 0);
					bearing = (int) sample[0];
					System.out.println("2. IR | after turn drive distance: " + distance);
					drive.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
					seek.fetchSample(sample, 0);
					distance = (int) sample[1];
					System.out.println("2. IR | after drive bearing: " + bearing);
					System.out.println("2. IR | after drive distance: " + distance);
				}
			}
		}
		// ir.close();
	}
}
