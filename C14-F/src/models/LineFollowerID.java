package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class LineFollowerID {

	private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private MarvinMover marvinMover = new MarvinMover();
	
	// calibrated/tested with ColorSensor ID mode
	private final static int black = 7;
//  private final static int grey;
	private final static int red = 0;
	private final static int white = 6;

	public LineFollowerID() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void followLine() {
		System.out.println("followLine started");
		// initialize array to fetch sample in
		float[] scannedColor = new float[3];
		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);
		stopwatchThread.start();
		// set sensor to 3 colors mode, gives int as color
		sensor.setCurrentMode("ColorID");
		int currentColor = sensor.getColorID();

		while (Button.DOWN.isUp()) {
	
			int lapCount = 0; // initialize on 0
			if (currentColor == red && lapCount == 0) {
				marvinMover.driveStraightOnGrey();
//				start.timer
//					lapCount++;
//				 } else if (currentColor == red && lapCount > 0) {
//				 	stop.timer();
//				 	lapTime[lapCount] = getLapTime();
//				 	reset.timer();
//					lapCount++;
//				 }
//				 	print ("Lap "lapCount + " " + lapTime[lapCount - 1]) ??
			} else if (currentColor == red && lapCount != 0) {
//				 stop.timer();
//				 lapTime[lapCount] = getLapTime();
//				 reset.timer();
//				 lapCount++;
//				 start.timer();
			} else if (currentColor == white) {
				marvinMover.turnLeftOnWhite();
			} else if (currentColor == black) { // black
				marvinMover.turnRightOnBlack();
			} else { // all other values are considered grey
				marvinMover.driveStraightOnGrey();
			}
//			 stopwatch.setNotStopped(false);
//			 Delay.msDelay(10000);
		}
	}
}
