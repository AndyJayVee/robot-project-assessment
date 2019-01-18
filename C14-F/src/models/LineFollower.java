package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;


public class LineFollower {

	private EV3ColorSensor sensor;
	private MarvinMover marvinMover;

	public LineFollower() {
		super();
		this.sensor = new EV3ColorSensor(SensorPort.S2);
		this.marvinMover = new MarvinMover();
	}
	/** @author loek (+frank for thread Stopwatch).
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

		public void followLine() {
		System.out.println("followLine started");
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);
		stopwatchThread.start();
		sensor.setCurrentMode("Red");
		while (Button.ESCAPE.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .60) { // white
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .20) { // black
				marvinMover.turnRightOnBlack();
			} else { // grey
				marvinMover.driveStraightOnGrey();
			}
		}
		marvinMover.stopMoving();
		stopwatch.setNotStopped(false);
		//Delay.msDelay(3000);
	}
}
