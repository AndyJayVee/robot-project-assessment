package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class BeaconFinderFrank implements Runnable {

	private static final int MAXIMUM_RANGE_IR_SENSOR = 150; // de maximum range is tussen de 100~200 centimeter
															// afhankelijk van de bron
	EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); 		// activeert een nieuwe IR-sensor op poort S4
	SensorMode seek = ir.getSeekMode(); 					// activeert de Seek modus
	float[] sample = new float[seek.sampleSize()]; 			// maakt array met sample informatie
	Pilot pilot = new Pilot(); 								// edit Loek and Frank
	private boolean lookingForBeacon = true;
	Driving drive = new Driving(pilot.getPilot(), lookingForBeacon); // initialize the Pilot and Driving in a correct
																		// manner
	Thread roamThread = new Thread(drive);

	public BeaconFinderFrank() { // no args constructor
		super();
	}

	@Override
	public void run() {

		try {
			roamThread.start();
			while (Button.ESCAPE.isUp()) {
				seek.fetchSample(sample, 0);
				int bearing = (int) sample[0];
				System.out.println("Bearing: " + bearing);
				int distance = (int) sample[1];
				System.out.println("Distance: " + distance);

				while (lookingForBeacon) {
					if (distance <= MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
						lookingForBeacon = false;
						drive.setBeaconIsFound(lookingForBeacon);
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

		} catch (Exception e) {
			System.out.println("Oops, something went wrong with the BeaconFinder!");
		}

//		ir.close();
	}
}
