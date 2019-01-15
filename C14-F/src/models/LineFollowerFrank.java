package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Pilot;

public class LineFollowerFrank implements Runnable {

	private static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private Pilot pilot = new Pilot();
	private Stopwatch stopwatch = new Stopwatch(true);
	private boolean driving = true;
	private boolean turning = true;
	private Forward drive = new Forward(driving);
	private Thread stopwatchThread = new Thread(stopwatch);
	private Thread turningThread = new Thread(pilot);
	private Thread forwardDriveThread = new Thread(drive);
	private float[] scannedColor = new float[1];
	private static final double ANGLE_LEFT = 135;
	private static final double ANGLE_RIGHT = -135;
	private static final double RADIUS = 50;
	
	
	public LineFollowerFrank() {
		super();
	}
 	
	
	@Override
	public void run() {
		try {
			sensor.setCurrentMode("Red");
			stopwatchThread.start();
			pilot.setLinearSpeed(50);
			pilot.setAngularSpeed(50);
			System.out.println("A");
			while (Button.ENTER.isUp()) {
				sensor.fetchSample(scannedColor, 0);
				System.out.println("B"+ scannedColor[0]);
				if (scannedColor[0] > .65) {	
				while (scannedColor[0] > .60) {
						pilot.setAngle(ANGLE_LEFT);
						pilot.setRadius(RADIUS);
						pilot.setTurning(turning);
						pilot.setAngularSpeed(50);
						turningThread.start();
						sensor.fetchSample(scannedColor, 0); // updates gray value
						System.out.println("C" + scannedColor[0]);
					}
					turning = false;
					pilot.setTurning(turning);
					
				}
				if (scannedColor[0] < .2) {
					while (scannedColor[0] < .35) {
						// black (need to turn right).
						pilot.setAngle(ANGLE_RIGHT);
						pilot.setRadius(RADIUS);
						pilot.setTurning(turning);
						pilot.setAngularSpeed(50);
						turningThread.start();
						sensor.fetchSample(scannedColor, 0); // updates gray value
						System.out.println("D" + scannedColor[0]);
					}
					turning = false;
					pilot.setTurning(turning);
					
					
				} else
					while (scannedColor[0] <= 0.6 && scannedColor[0] >= 0.2) {
						pilot.setLinearSpeed(50);
						forwardDriveThread.start(); // Continuous driving if average measurement is between specified
													// values (0.2 and 0.6).
						sensor.fetchSample(scannedColor, 0);
						System.out.println("E" + scannedColor[0]);
					}
				driving = false;
				drive.setContinueDriving(driving);
				
			}
			stopwatch.setNotStopped(false);
			Thread.sleep(10000);


		} catch (Exception e) {
			System.out.printf("Oops, line follower");
		}
	}
}