package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class LineFollower { //implements Runnable {

//	static Brick brick;
	private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private MarvinMover marvinMover = new MarvinMover();

	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

		public void followLine() {
		System.out.println("followLine started");
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .60) { // white
				System.out.println("White");
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .20) { // black
				System.out.println("Black");
				marvinMover.turnRightOnBlack();
			} else { // grey
				System.out.println("Grey");
				marvinMover.driveStraightOnGrey();
			}
		}
	}
}
