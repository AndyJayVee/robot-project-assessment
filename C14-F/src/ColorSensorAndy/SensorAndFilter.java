package ColorSensorAndy;

// import java.util.ArrayList;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
// import lejos.hardware.Key;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
// import lejos.utility.Delay;

public class SensorAndFilter {

	private SampleProvider reflectedLight;
	private int reflectSampleSize = reflectedLight.sampleSize();
	private int redSample = 0;
	private float[] sample = new float[reflectSampleSize];

	
	
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

		reflectedLight.fetchSample(sample, 0);
		
		return sample[redSample];
	}

}
