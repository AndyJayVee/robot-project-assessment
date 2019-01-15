package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.MarvinMover;

public class LineFollower {

	private static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private MarvinMover marvinMover = new MarvinMover();
	private Stopwatch stopwatch = new Stopwatch(true);
	private Thread stopwatchThread = new Thread(stopwatch);
	private float[] scannedColor = new float[1];
	
	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */
		public void followLine() {
		sensor.setCurrentMode("Red");
		stopwatchThread.start();
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
		stopwatch.setNotStopped(false);
		while (Button.ESCAPE.isUp()) {
		}
	}
}
