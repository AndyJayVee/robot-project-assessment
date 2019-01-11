package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;

public class BeaconFinder {
	private boolean beaconFound = false;

	public boolean isBeaconFound() {
		return beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	Driving drive;
	private static final int MAXIMUM_RANGE_IR_SENSOR = 150; // de maximum range is tussen de 100~200 centimeter
	// afhankelijk van de bron

	// vanaf hier is de IR-sensorcode:

	EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
	SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
	float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie

	public void findBeacon() {
		while (Button.ESCAPE.isUp()) {
			seek.fetchSample(sample, 0);
			int bearing = (int) sample[0];
			System.out.println("Bearing: " + bearing);
			int distance = (int) sample[1];
			System.out.println("Distance: " + distance);

			while (bearing != 0 && distance != 0) {
				if (distance > MAXIMUM_RANGE_IR_SENSOR) {
					drive.roam(beaconFound); // begin met roam
				} else if (distance <= MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
					setBeaconFound(beaconFound = true); // CHECKEN MET FRANK
					drive.straight((MAXIMUM_RANGE_IR_SENSOR) / 10); // begin met klein stukje straight
				}

				if (bearing != 0) { // als het beacon niet recht voor de sensor is
					drive.turn(bearing); // gaat de robot in de richting van het beacon roteren met "bearing" graden
				} else if (bearing == 0) { // als het beacon recht voor de sensor is
					drive.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
				} 

				ir.close();
			}
		}
	}
}
