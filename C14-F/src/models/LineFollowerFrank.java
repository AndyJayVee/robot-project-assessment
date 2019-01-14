package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Pilot;

public class LineFollowerFrank {

	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	Pilot pilot = new Pilot();
	float gray;
	float sumOfMeasurements = 0;

	public LineFollowerFrank() {
		super();
	}

	public void followLine() {
		while (Button.DOWN.isUp()) {
			pilot.getPilot().forward();
			gray = currentGray();
			while (gray >= .20 && gray <= .60) {
				pilot.getPilot().forward(); // Continuous driving if average measurement is between specified values
											// (0.2 and 0.6)
				gray = currentGray(); // updating gray value with current gray value.
			}
			pilot.getPilot().stop();
			if (gray < .20) {
				while (gray < .45) { // black (need to turn right).
					pilot.getPilot().rotateRight(); // turns robot right
					gray = currentGray(); // updates gray value
				}
			}
			if (gray > .60) {
				while (gray > .45) { // white (need to turn left).
					pilot.getPilot().rotateLeft(); // turns robot left
					gray = currentGray(); // updates gray value
				}
			}
		}
	}

	public float currentGray() {
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		float[] averagedScan = new float[5]; // Making new float array to put 5 values from scannedColor in.
		sensor.setCurrentMode("Red");
		sensor.fetchSample(scannedColor, 0);

		for (int i = 0; i < averagedScan.length; i++) { // filling the array.
			averagedScan[i] = scannedColor[0];
			i++;
		}
		for (float measurement : averagedScan) { // For each loop summing the 5 values from the array
			sumOfMeasurements += measurement;
		}
		float averageMeasurement = sumOfMeasurements / averagedScan.length; // calculating average
		return averageMeasurement;
	}
}
