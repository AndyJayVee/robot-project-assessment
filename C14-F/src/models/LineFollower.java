package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class LineFollower {

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
			}
		}
		stopwatch.setNotStopped(false);
		//Delay.msDelay(3000);
		return;

	}
//		public boolean stopRacing() {
//			boolean stop = false;
//			EV3IRSensor ir = new EV3IRSensor(SensorPort.S4);
//			SensorMode seek = ir.getSeekMode();
//			float[] sample = new float[seek.sampleSize()];
//			seek.fetchSample(sample, 0);
//			int distance = (int) sample[1];
//			ir.close();
//			if (distance <1000) {
//				stop = true;
//			}
//			return stop;
//		}	
}
