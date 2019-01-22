package extraCodeFrank;

import extraCodeFrank.Racer;
import models.Stopwatch;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class LineFollowerUsingThread {
	
private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
private static float minimumValue;
private static float maximumValue;
private float[] scannedColor = new float[1];
private static final float BORDER_BRIGHT = 0.70f;
private static final float BORDER_DARK = 0.30f;

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
		System.out.println("calibration...");
		sensor.setCurrentMode("Red");
		
		boolean enoughSamples = false;
		int numberOfMeasurements = 0;
		minimumValue = 1;
		maximumValue = 0;
		
		Calibrator calibrator = new Calibrator(enoughSamples);
		Thread calibratorThread = new Thread(calibrator);
		calibratorThread.start();
		
		while (numberOfMeasurements<1000) {
			sensor.fetchSample(scannedColor, 0);
			Delay.msDelay(10);
			if (scannedColor[0] > maximumValue) {
				scannedColor[0] = maximumValue;
				numberOfMeasurements++;
			} else if (scannedColor[0] < minimumValue) {
				scannedColor[0] = minimumValue;
				numberOfMeasurements++;
			} else {
				numberOfMeasurements++;
			}
			System.out.println("min" + minimumValue + "max" + maximumValue);
		}
		enoughSamples = true;
		calibrator.setStopCalibrate(enoughSamples);
	} 
			

	public void followLine() {
		calibrateLinefollower();
		System.out.println("min: " + minimumValue);
		System.out.println("max: " + maximumValue);
		
		sensor.setCurrentMode("Red");
		sensor.fetchSample(scannedColor, 0);
		
		boolean race = true;
		Racer racer = new Racer(shadeOfGray(scannedColor[0]), race);
		Racer.setBorderBright(BORDER_BRIGHT);
		Racer.setBorderDark(BORDER_DARK);
		Stopwatch stopwatch = new Stopwatch(race);
		Thread moverThread = new Thread(racer);
		Thread stopwatchThread = new Thread(stopwatch);
		moverThread.start();
		stopwatchThread.start();
		while (Button.ESCAPE.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			while (shadeOfGray(scannedColor[0]) > BORDER_BRIGHT) {
				sensor.fetchSample(scannedColor, 0);
				System.out.println("te wit");
			}
			racer.setCurrentGray(shadeOfGray(scannedColor[0]));
			while (shadeOfGray(scannedColor[0]) < BORDER_DARK) {
				sensor.fetchSample(scannedColor, 0);
				System.out.println("te zwart");
			}
			racer.setCurrentGray(shadeOfGray(scannedColor[0]));
			while ((shadeOfGray(scannedColor[0]) <= BORDER_BRIGHT) && 
					(shadeOfGray(scannedColor[0]) >= BORDER_DARK)) {
				sensor.fetchSample(scannedColor, 0);
				System.out.println("kleur is goed!");
			}
			racer.setCurrentGray(shadeOfGray(scannedColor[0]));
		}
		stopwatch.setNotStopped(false);
		racer.setRacing(false);
		Delay.msDelay(5000);
	}
	
	public float shadeOfGray(float ScannedGray) {
		float grayValue = (ScannedGray-minimumValue)/maximumValue;
		return grayValue;
	}
}
