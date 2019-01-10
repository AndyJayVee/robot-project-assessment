package models;

import java.util.ArrayList;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class SensorAndFilter {

	private SampleProvider reflectedLight;
	int reflectSampleSize = 0;
	float[] sample = new float[reflectSampleSize];

	public SensorAndFilter() {
		/* Steps to initialize a sensor */
		Brick brick = BrickFinder.getDefault();
		Port s2 = brick.getPort("S2");
		EV3ColorSensor sensor = new EV3ColorSensor(s2);

		// Get the Red mode of the sensor.
		SampleProvider redMode = sensor.getRedMode();

		// Use a filter on the sample

		SampleProvider reflectedLight = new AutoAdjustFilter(redMode);
	}
	 
	public float getLatestSample() {
		// A sample represents a single measurement by the sensor. Some modes return

		/*
		 * Get a fresh sample from the filter. (The filter gets it from its source, the
		 * redMode)
		 */
		reflectedLight.fetchSample(sample, 0);
		
		return sample[reflectSampleSize];
	}

}


