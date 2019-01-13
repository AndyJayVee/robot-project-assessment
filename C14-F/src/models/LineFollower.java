package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Driving;
import models.Pilot;


public class LineFollower{ //implements Runnable {

//	private LineFollower lineFollower = new LineFollower();
//	static Brick brick;
	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

	public LineFollower() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void followLine() {
		// start with driving straight
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");
//		double radius = 50;
//		double angleRight = 7;
//		double angleLeft = 7;
		// while not pressed continue
		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .60) { // white
				drive.turn(-6);
			} else if (scannedColor[0] < .20) { // black
				drive.turn(6);
//				drive.straight(Distance);
			} else { // grey
				drive.straight(-10);
			}
		}
	}


//	@Override
//	public void run() {
//		try {
//			
//
//		} catch (Exception e) {
//			System.out.println("Oops, something went wrong with the linefollower");
//		}
//	}

}
