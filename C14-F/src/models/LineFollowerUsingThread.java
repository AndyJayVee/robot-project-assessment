package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class LineFollowerUsingThread {
	
private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
private float[] scannedColor = new float[0];
public float minimumValue;
public float maximumValue;
public float borderBright = (float) 0.7;
public float borderDark = (float) 0.3;
private float currentGray = (scannedColor[0]-minimumValue/maximumValue);

	public LineFollowerUsingThread() {
		super();
	}

	
	public float getBorderbright() {
		return borderBright;
	}


	public float getBorderdark() {
		return borderDark;
	}


	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void calibrateLinefollower() {
		System.out.println("calibration started");
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");
		boolean enoughSamples = false;
		minimumValue = 0;
		maximumValue = 1;
		int numberOfMeasurements = 0;
		Calibrator calibrator = new Calibrator(enoughSamples);
		Thread calibratorThread = new Thread(calibrator);
		calibratorThread.start();
		while (numberOfMeasurements<1000) {
			sensor.fetchSample(scannedColor, 0);
			Delay.msDelay(10);
			if (scannedColor[0] < maximumValue) {
				scannedColor[0] = maximumValue;
				numberOfMeasurements++;
			} else if (scannedColor[0] > minimumValue) {
				scannedColor[0] = minimumValue;
				numberOfMeasurements++;
			} else {
				numberOfMeasurements++;
			}
		}
		enoughSamples = true;
		calibrator.setStopCalibrate(enoughSamples);
	} 
			

	public void followLine() {
		calibrateLinefollower();
		System.out.println("A");
		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);
		System.out.println("B");
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");
		sensor.fetchSample(scannedColor, 0);
		boolean stopMoving = false;
		System.out.println("C");
		Mover mover = new Mover(currentGray, stopMoving);
		System.out.println("D");
		Mover.setBorderBright(borderBright);
		System.out.println("E");
		Mover.setBorderDark(borderDark);
		System.out.println(currentGray);
		Thread moverThread = new Thread(mover);
		moverThread.start();
		stopwatchThread.start();
		System.out.println("F");
		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			while (currentGray > borderBright) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setCurrentGray(currentGray);
			while (currentGray < borderDark) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setCurrentGray(currentGray);
			while (currentGray <= borderBright && 
					currentGray >= borderDark) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setCurrentGray(currentGray);
		}
		stopwatch.setNotStopped(false);
		mover.setStopMoving(true);
		Delay.msDelay(5000);
	}

	
}
