package models;

import lejos.hardware.Button;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.MarvinMover;
import models.Driving;
import models.Pilot;

public class LineFollower { //implements Runnable {

	static Brick brick;
	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private MarvinMover marvinMover = new MarvinMover();

	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

		public void followLine() {
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .60) { // white
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .20) { // black
				marvinMover.turnRightOnBlack();
			} else { // grey
		        marvinMover.driveStraightOnGrey();
			}
		}
	}
}
