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

	EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
	SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
	float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie
	// edit Loek
	// initialize the Pilot and Driving in a correct manner
	Driving drive = new Driving();
	Pilot pilot = new Pilot();
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

	// vanaf hier is de IR-sensorcode:

	public void findBeacon() {
		// @aut Loek: edit: instantiate Pilot and Driving
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

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
					System.out.println("in de tweede while loop");
					drive.roam(beaconFound); // begin met roam
					System.out.println("Start roaming");
				} else if (distance <= MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
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
	//	ir.close();
	}
}