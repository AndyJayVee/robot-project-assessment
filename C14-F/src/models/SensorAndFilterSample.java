package models;

import java.util.ArrayList;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class SensorAndFilterSample {

  public SensorAndFilterSample() {
    /* Steps to initialize a sensor */
    Brick brick = BrickFinder.getDefault();
    Port s2 = brick.getPort("S2");
    EV3ColorSensor sensor = new EV3ColorSensor(s2);

    // Get the Red mode of the sensor.
    SampleProvider redMode = sensor.getRedMode();

    // Use a filter on the sample
    SampleProvider reflectedLight = new AutoAdjustFilter(redMode);

    // A sample represents a single measurement by the sensor. Some modes return
    int sampleSize = reflectedLight.sampleSize();
    float[] sample = new float[sampleSize];

    Key escape = brick.getKey("Escape");
    while (!escape.isDown()) {
      /*
       * Get a fresh sample from the filter. (The filter gets it from its
       * source, the redMode)
       */
      reflectedLight.fetchSample(sample, 0);
      /* Display individual values in the sample. */
 //     for (int i = 0; i < sampleSize; i++) {
 //       System.out.print(sample[0] + " ");
 //     }
      System.out.println();
      Delay.msDelay(50);
    }
    /* When ready close the sensor */
    sensor.close();

  }
 
  
  public float getLatestSample () {
	  float sample = (float) 0.1;
	  
	  return sample;
  }

}