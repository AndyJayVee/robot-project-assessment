package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class LineFollowerUsingThread {
private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
private float[] scannedColor = new float[0];
static float minimumValue = 0;
static float maximumValue = 0;

	public LineFollowerUsingThread() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void calibrateLinefollower() {
		sensor.setCurrentMode("Red");
		sensor.fetchSample(scannedColor, 0);
		boolean enoughSamples = false;
		Calibrator calibrator = new Calibrator(enoughSamples);
		Thread calibratorThread = new Thread(calibrator);
		int numberOfMeasurements = 0;
		calibratorThread.start();
		while (numberOfMeasurements<1000) {
			sensor.fetchSample(scannedColor, 0);
			Delay.msDelay(20);
			if (scannedColor[0] > maximumValue) {
				scannedColor[0] = maximumValue;
				numberOfMeasurements++;
			} else if (scannedColor[0] < minimumValue) {
				scannedColor[0] = minimumValue;
				numberOfMeasurements++;
			} else {
				numberOfMeasurements++;
			}
		}
		enoughSamples = true;
		calibrator.setCalibrate(enoughSamples);
	} 
			

	public void followLine() {
		calibrateLinefollower();
		// initialize array to fetch sample in
		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);

		sensor.setCurrentMode("Red");
		sensor.fetchSample(scannedColor, 0);

		Mover mover = new Mover(calibratedGrayValue());
		System.out.println(calibratedGrayValue());
		Thread moverThread = new Thread(mover);

		moverThread.start();
		stopwatchThread.start();
		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			while (calibratedGrayValue() > .70) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setShadeOfGray(calibratedGrayValue());
			while (calibratedGrayValue() < .15) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setShadeOfGray(calibratedGrayValue());
			while ((calibratedGrayValue() <= 0.70) && (calibratedGrayValue() >= 0.15)) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setShadeOfGray(calibratedGrayValue());
		}
		stopwatch.setNotStopped(false);
		Delay.msDelay(5000);
	}
	
	public float calibratedGrayValue() {
		float grayValue = (scannedColor[0]-minimumValue)/maximumValue;
		return grayValue;
	}
	
}
