package models;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Pilot;

public class BeaconFinder {
 
	private static final int DISTANCE_TRESHOLD_ROAM = 21474836;
	
	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // use port S4 for IR Sensor
	private SensorMode seek = ir.getSeekMode(); // initiate seekmode
	private float[] sample = new float[seek.sampleSize()]; // declare array to store samples form Sensor
	private boolean beaconFound = false;
	private int bearing;
	private int distance;

	public boolean isBeaconFound() {
		return beaconFound;
	}

	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	// no args cons
	public BeaconFinder() {
		super();
	}

	/**
	 * When placed in area with beacon, it will roam until sensor will measure it (inRange)
	 * When inRsange it should switch to: turn&drive towards beacon.
	 * NOTE: sensor is slow. Works, but it's not pretty
	 */
		public void findBeacon() {
		Pilot pilot = new Pilot();
		while (Button.DOWN.isUp()) {
			// TODO seek.fetchSample(sample, 0); 
			
			//System.out.println("1st while. Bearing: " + bearing);
			distance = fetchDistance();
			System.out.println("1st while. Distance: " + distance);
			Roaming roaming = new Roaming(beaconFound);
			Thread roamThread = new Thread(roaming);
			while (distance > 0) {			//TODO loop never ends.
				if (distance >= DISTANCE_TRESHOLD_ROAM) {
					roamThread.start();
				}
				while (distance >= DISTANCE_TRESHOLD_ROAM) {					
					bearing = fetchBearing();
					distance = fetchDistance();
					System.out.println("2. Roaming | Distance: " + distance);
					System.out.println("2. Roaming | Bearing: " + bearing);
					distance = fetchDistance();
				}
				roaming.setStopRoaming(true);
				while (distance < DISTANCE_TRESHOLD_ROAM) { // inRange --> turn and drive to beacon
					setBeaconFound(true);
					bearing = fetchBearing();	
					distance = fetchDistance();
					System.out.println("2. inRange | Distance: " + distance);
					System.out.println("2. inRange | Bearing: " + bearing);
					// turn with bearing
					pilot.rotate(-bearing); 	
					distance = fetchDistance();	
					// drive distance to beacon
					pilot.travel(distance*10);
					// fetch another sample to test if movement was sufficient
					distance = fetchDistance();
					bearing = fetchBearing();
				} 
			}
		}
		ir.close();
	} 
	/*
	 * @return Fetch bearing measurement from Sensor
	 */
	public int fetchBearing() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		bearing = (int) sample[0];
		return bearing;
	}
	/**
	 * @return Fetch distance measurement from Sensor
	 */
	public int fetchDistance() {
		// fetch measurement and store the value
		seek.fetchSample(sample, 0);
		distance = (int) sample[1];
		return distance;
	}
}
