package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class LineFollowerUsingThread {
	
private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
private float[] scannedColor = new float[0];
private static float minimumValue;
private static float maximumValue;
private static final double BORDER_BRIGHT = 0.70;
private static final double BORDER_DARK = 0.30;

	public LineFollowerUsingThread() {
		super();
	}

	
	public static double getBorderbright() {
		return BORDER_BRIGHT;
	}


	public static double getBorderdark() {
		return BORDER_DARK;
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
			Delay.msDelay(20);
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
		Stopwatch stopwatch = new Stopwatch(true);
		Thread stopwatchThread = new Thread(stopwatch);
		
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");
		sensor.fetchSample(scannedColor, 0);
		
		boolean stopMoving = false;
		Mover mover = new Mover(calibratedGrayValue(), stopMoving);
		Mover.setBorderBright(BORDER_BRIGHT);
		Mover.setBorderDark(BORDER_DARK);
		System.out.println(calibratedGrayValue());
		Thread moverThread = new Thread(mover);
		moverThread.start();
		stopwatchThread.start();
		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			while (calibratedGrayValue() > BORDER_BRIGHT) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setShadeOfGray(calibratedGrayValue());
			while (calibratedGrayValue() < BORDER_DARK) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setShadeOfGray(calibratedGrayValue());
			while ((calibratedGrayValue() <= BORDER_BRIGHT) && 
					(calibratedGrayValue() >= BORDER_DARK)) {
				sensor.fetchSample(scannedColor, 0);
			}
			mover.setShadeOfGray(calibratedGrayValue());
		}
		stopwatch.setNotStopped(false);
		mover.setStopMoving(true);
		Delay.msDelay(5000);
	}
	
	public float calibratedGrayValue() {
		float grayValue = (scannedColor[0]-minimumValue)/maximumValue;
		return grayValue;
	}
	
}
