package models;

import lejos.hardware.*;
import lejos.hardware.sensor.*;
import lejos.hardware.port.SensorPort;

public class BeaconFinderFrank implements Runnable {

	private double bearing = 0;
	private double distance;
	private Pilot pilot = new Pilot();
	private boolean beaconIsFound = false;

	private EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
	private SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
	private float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie

	private Roaming roam = new Roaming(beaconIsFound); // initializes roaming.
	private Thread roamThread = new Thread(roam); // puts the roam in a thread.

	public BeaconFinderFrank() {
		super();
	}

	public void run() {
		try {
			roamThread.start();
			while (bearing == 0) {
				fetchingBearingAndDistance();
				Thread.sleep(1000);
				System.out.printf("Bearing: %d\nDistance: %d\n", bearing, distance);
				Sound.buzz();
			}
			beaconIsFound();
			driveToBeacon();
			ir.close();

		} catch (Exception e) {
			System.out.printf("Oops, something went wrong\n with the BeaconFinder!");
		}
	}

	private void driveToBeacon() throws InterruptedException { // TODO: Deze methode werkt misschien niet.
		while (distance > 5) {
			fetchingBearingAndDistance();
			pilot.getPilot().rotate(-bearing);
			pilot.getPilot().travel(distance);
			Thread.sleep(250);
		}
	}

	public void fetchingBearingAndDistance() {
		seek.fetchSample(sample, 0);
		bearing = (double) sample[0];
		distance = (double) sample[1];
	}

	public void beaconIsFound() {
		beaconIsFound = true;
		Sound.beepSequenceUp();
		roam.setBeaconIsFound(beaconIsFound);
		System.out.println("Beacon found, going to beacon");
	}
}
