package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Pilot;

public class LineFollower {

	private static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private Stopwatch stopwatch = new Stopwatch(true);
	private Thread stopwatchThread = new Thread(stopwatch);
	private float[] scannedColor = new float[1];
	private Pilot pilot = new Pilot();

	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void followLine() {
		pilot.setAngularSpeed(150);
		pilot.setLinearSpeed(150);
		System.out.println("A");
		stopwatchThread.start();
		System.out.println("B");
		sensor.setCurrentMode("Red");
		// while not pressed continue
		while (Button.ESCAPE.isUp()) {
			 sensor.fetchSample(scannedColor, 0);
				if (scannedColor[0] > .60) { // white
					System.out.println("C");
					pilot.rotateRight();
				} else if (scannedColor[0] < .20) { // black
					pilot.rotateLeft();
					System.out.println("D");
				} else { // grey
					pilot.backward();
					System.out.println("E");
				}	
			}
		stopwatch.setNotStopped(false);
	}
}
