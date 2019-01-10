package models;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;
import models.Pilot;

public class BeaconFinder {
	boolean beaconFound = false;
	Driving drive;
	private static final int MAXIMUM_RANGE_IR_SENSOR = 75; // eventueel aanpassen

	// vanaf hier is de IR-sensorcode:

	EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
	SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
	float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie

	while(Button.ESCAPE.isUp())
	{
		seek.fetchSample(sample, 0);
		int bearing = (int) sample[0];
		System.out.println("Bearing: " + bearing);
		int distance = (int) sample[1];
		System.out.println("Distance: " + distance);

		if (distance > MAXIMUM_RANGE_IR_SENSOR) { // als het beacon out of range is
			drive.roam(beaconFound); // begin met roam
		} else if (distance < MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
			drive.straight(MAXIMUM_RANGE_IR_SENSOR); // begin met straight
		}

		if (bearing != 0) { // als het beacon niet recht voor de sensor is
			drive.turn(bearing); // gaat de robot in de richting van het beacon roteren met "direction" graden
		} else if (bearing == 0) { // als het beacon recht voor de sensor is
			drive.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
		}

		ir.close();
	}
}}
