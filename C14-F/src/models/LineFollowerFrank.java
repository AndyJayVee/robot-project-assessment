package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class LineFollowerFrank {

	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

	public LineFollowerFrank() {
		super();
	}

	public void followLine() {
		// start with driving straight
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		// while not pressed continue
		while (Button.DOWN.isUp()) {
			drive.pilot.forward();
			float gray = currentGray();

			while (gray >= .20 && gray <= .60) {
				drive.pilot.forward(); // Continuous driving if average measurement is between specified values (0.2
										// and 0.6)
				gray = currentGray(); // updating gray value with current gray value.
			}
			drive.pilot.stop();
			if (gray < .20) {
				while (gray < .45) { // black (need to turn right).
					drive.pilot.rotateRight(); // turns robot right
					gray = currentGray(); // updates gray value
				}
			}
			if (gray > .60) {
				while (gray > .45) { // white (need to turn left).
					drive.pilot.rotateLeft(); // turns robot left
					gray = currentGray(); // updates gray value
				}
			}
		}
	}

	public float currentGray() {
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		float[] averagedScan = new float[5]; // Making new float array to put 5 values from scannedColor in.
		sensor.fetchSample(scannedColor, 0);

		for (int i = 0; i < averagedScan.length; i++) { // filling the array.
			averagedScan[i] = scannedColor[1];
			i++;
		}

		float sumOfMeasurements = 0; // initializing float for making a summation of 3 values later.
		for (float measurement : averagedScan) { // For each loop summing the 5 values from the array
			sumOfMeasurements += measurement;
		}
		float averageMeasurement = sumOfMeasurements / averagedScan.length; // calculating average
		return averageMeasurement;
	}
}
