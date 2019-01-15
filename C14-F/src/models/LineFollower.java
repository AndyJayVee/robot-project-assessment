package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
<<<<<<< HEAD
import models.Pilot;

public class LineFollower {

	private static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private Stopwatch stopwatch = new Stopwatch(true);
	private Thread stopwatchThread = new Thread(stopwatch);
	private float[] scannedColor = new float[1];
	private Pilot pilot = new Pilot();
=======
import lejos.utility.Delay;

public class LineFollower {

	private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private MarvinMover marvinMover = new MarvinMover();
>>>>>>> 9e2e9884232eca8f141f9f6f355e6e35279a0df8

	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

<<<<<<< HEAD
	public void followLine() {
		pilot.setAngularSpeed(90000);
		pilot.setLinearSpeed(150);
		System.out.println("A");
		stopwatchThread.start();
		System.out.println("B");
		sensor.setCurrentMode("Red");
		// while not pressed continue
		while (Button.ESCAPE.isUp()) {
			while (Button.ENTER.isUp()) {
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
=======
		public void followLine() {
		System.out.println("followLine started");
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);
		stopwatchThread.start();
		sensor.setCurrentMode("Red");
		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .60) { // white
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .20) { // black
				marvinMover.turnRightOnBlack();
			} else { // grey
				marvinMover.driveStraightOnGrey();
>>>>>>> 9e2e9884232eca8f141f9f6f355e6e35279a0df8
			}
			stopwatch.setNotStopped(false);
		}
		stopwatch.setNotStopped(false);
		Delay.msDelay(10000);
	}
}
