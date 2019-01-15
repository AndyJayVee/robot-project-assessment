package models;

import lejos.hardware.Button;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Driving;
import models.Pilot;

public class LineFollower implements Runnable {

//	private LineFollower lineFollower = new LineFollower();
//	static Brick brick;

	private static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private Stopwatch stopwatch = new Stopwatch(true);
	private Thread stopwatchThread = new Thread(stopwatch);
	private Pilot pilot = new Pilot();
	private Driving drive = new Driving(pilot.getPilot());

	// initialize array to fetch sample in
	float[] scannedColor = new float[1];

	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void run() {
		try {
			// start with driving straight
			sensor.setCurrentMode("Red");
			// while not pressed continue
				stopwatchThread.start();
				while (Button.ENTER.isUp()) {
					sensor.fetchSample(scannedColor, 0);
					System.out.println(scannedColor[0]);
					if (scannedColor[0] > .60) { // white
						drive.turn(-6);
					} else if (scannedColor[0] < .20) { // black
						drive.turn(6);
					} else { // grey
						drive.straight(-10);
					}
				}
				stopwatch.setNotStopped(false);
				Thread.sleep(1000);
				
		} catch (Exception e) {
			System.out.printf("Oops, something went\n wrong with roaming");
		}
	}
}
