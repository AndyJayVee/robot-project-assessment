package nl.hva.miw.robot.cohort14;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;
import models.Driving;
import models.Pilot;
import models.SensorAndFilter;
import models.SensorAndFilterSample;

public class Marvin {

	Brick brick;
	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

	public Marvin() {
		super();
		brick = LocalEV3.get();
	}

	public static void main(String[] args) {
		Marvin marvin = new Marvin();
//		marvin.run();
//		marvin.readSensor();
		marvin.followLine();
//		marvin.readAndPrintSample();
//		marvin.run();

	}

	private void readAndPrintSample() {
		// TODO Auto-generated method stub

		TextLCD display = brick.getTextLCD();
		display.drawString("Press Enter", 0, 3);
		display.drawString("Team Foxtrot", 0, 4);
		waitForKey(Button.ENTER);
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		while (Button.DOWN.isUp()) {
			// fetch sample
			sensor.fetchSample(scannedColor, 0);
//		SensorAndFilterSample sensorAndFilterSample = new SensorAndFilterSample();
//		sensorAndFilterSample.getLatestSample();
//		SensorAndFilterSample sensorAndFilter = new SensorAndFilterSample();
			if (scannedColor[0] < .20) {
				display.drawString("Donker", 0, 4);
			} else {
				display.drawString("Licht", 0, 4);

			}
		}

	}

	private void followLine() {
		// drive straight


		TextLCD display = brick.getTextLCD();
		display.drawString("Press Enter", 0, 3);
		display.drawString("Team Foxtrot", 0, 4);
		waitForKey(Button.ENTER);
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		while (Button.DOWN.isUp()) {
			// fetch sample
			sensor.fetchSample(scannedColor, 0);

			if (scannedColor[0] < .40) {
				Pilot pilot = new Pilot();
				Driving drive = new Driving(pilot.getPilot());
				float distance = -500;
				drive.straight(distance);
			} else {
				// ga een kant op draaien
			}
		}

	}

	private void readSensor() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Foxtrot!", 0, 4);
		waitForKey(Button.ENTER);
		// initialize Driving and Pilot
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());
		// TODO magic number ; set distance
		float distance = -250;
		// start with driving the distance in a straight line by invoking Driving method
		drive.straight(distance);
		// after drive, read sensordata
		SensorAndFilterSample sensorAndFilterSample = new SensorAndFilterSample();
		sensorAndFilterSample.getLatestSample();

		// TODO Auto-generated method stub

	}

	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Foxtrot!", 0, 4);
		waitForKey(Button.ENTER);
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());
		float distance = -500;
		drive.driveRectangle(distance);
	}

	public void waitForKey(Key key) {
		while (key.isUp()) {
			Delay.msDelay(100);
		}
		while (key.isDown()) {
			Delay.msDelay(100);
		}
	}
}
