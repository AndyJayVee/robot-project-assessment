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
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .20 && scannedColor[1] > .20 && scannedColor[2] > .20  ) { // white
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .10 && scannedColor[1] < .10 && scannedColor[2] < .10   ) { // black
				marvinMover.turnRightOnBlack();
			} else if (
					( scannedColor[0] <= .20 && scannedColor[0] > .11 ) && 
					( scannedColor[1] <= .20 && scannedColor[1] > .11 ) &&
					( scannedColor[2] <= .20 && scannedColor[2] > .11) ) // grey
			{ // grey
				marvinMover.driveStraightOnGrey();
			} else { 
				marvinMover.cruise();
			}
		}
	//	stopwatch.setNotStopped(false);
	//	Delay.msDelay(10000);
	}
}
