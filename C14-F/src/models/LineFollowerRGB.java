package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class LineFollowerRGB {

	private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private MarvinMover marvinMover = new MarvinMover();

	public LineFollowerRGB() {
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

		// calculate avg of samples
		float total = 0;
		float avgSamples;
		for (int i = 0; i < scannedColor.length; i++) {
			total = total + scannedColor[i];
		}
		avgSamples = total / scannedColor.length;

		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);
		stopwatchThread.start();
		// set sensor to 3 colors mode
		sensor.setCurrentMode("RGB");
		while (Button.DOWN.isUp()) {
			sensor.getColorID();
			if (scannedColor[0] > 17 && scannedColor[1] > .17 && scannedColor[2] > .17) { // white
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .10 && scannedColor[1] < .10 && scannedColor[2] < .10) { // black
				marvinMover.turnRightOnBlack();
			} else if ((scannedColor[0] <= .17 && scannedColor[0] > .11)
					&& (scannedColor[1] <= .17 && scannedColor[1] > .11)
					&& (scannedColor[2] <= .17 && scannedColor[2] > .11)) // grey
			{ // grey
				marvinMover.driveStraightOnGrey();
			}
			// stopwatch.setNotStopped(false);
			// Delay.msDelay(10000);
		}
	}
	

	
	// TODO
				// if (sensor measures red finish line && lapCount == 0) {
				//	start.timer
				//	lapCount++; 
				// } else if (sensor measures red finish line && lapCount > 0) {
				// 	stop.timer();
				// 	lapTime[lapCount] = getLapTime();
				// 	reset.timer();
				//	lapCount++;
				// }
				// 	print ("Lap "lapCount + " " + lapTime[lapCount - 1]) ??

}
