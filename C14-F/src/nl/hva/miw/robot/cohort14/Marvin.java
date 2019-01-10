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
		marvin.followLine();
//		marvin.readAndPrintSample();
//		marvin.run();
//		marvin.moveDriving();
//		marvin.simpleTurn();

	}
	
	private void simpleTurn() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Foxtrot!", 0, 4);
		waitForKey(Button.ENTER);
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());
		float distance = -500;
		drive.turn(-3);	
		drive.turn(-3);	
		drive.turn(-3);	
		drive.straight(10);
		drive.turn(8);

		
	}

	private void followLine() {
		// intro
		TextLCD display = brick.getTextLCD();
		display.drawString("Press Enter", 0, 3);
		display.drawString("Follow Line", 0, 4);
		waitForKey(Button.ENTER);

		// start with driving straight
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		// while not pressed continue
		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] < .10) { // white
				drive.turn(10); 
			} else if (scannedColor[0] > .70)  { // black
				drive.turn(-10);
			} else { // grey
				drive.straight(-50);
				// ga een kant op draaien
			}
		}

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



	private void moveDriving() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Foxtrot!", 0, 4);
		waitForKey(Button.ENTER);
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());
		float distance = -500;
		drive.driveRectangle(distance);
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Foxtrot!", 0, 4);
		waitForKey(Button.ENTER);
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
