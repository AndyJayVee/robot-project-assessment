/**
 * @author loek 
 * Main Class including followLine
 */

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
import models.LineFollower;
import models.Pilot;


public class Marvin {

	Brick brick;
//	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

	public Marvin() {
		super();
		brick = LocalEV3.get();
	}

	public static void main(String[] args) {
		// initialize a Marvin object
		Marvin marvin = new Marvin();

		// instantiate LineFollower
		LineFollower lineFollower = new LineFollower();

		// run method
		marvin.runEnter();



	}



	/** This method waits for Enter key
	 *  After Enter it will invoke followLine()
	 */

	private void runEnter() {
		TextLCD display = brick.getTextLCD();
		display.drawString(" Foxtrot ", 0, 3);
		display.drawString(" Press Enter for Line", 0, 4);
		waitForKey(Button.ENTER);
		// initialize LineFollower
		LineFollower lineFollower = new LineFollower();
		lineFollower.followLine();
	}

	
	public void waitForKey(Key key) {
		while (key.isUp()) {
			Delay.msDelay(100);
		}
		while (key.isDown()) {
			Delay.msDelay(100);
		}
	}
	
	/**
	 * TEST method to print EV3Sensor on Foxtrot display
	 */

		// private void readAndPrintSample() {
			// TODO Auto-generated method stub

//			TextLCD display = brick.getTextLCD();
//			display.drawString("Press Enter", 0, 3);
//			display.drawString("Team Foxtrot", 0, 4);
//			waitForKey(Button.ENTER);
//			// initialize array to fetch sample in
//			float[] scannedColor = new float[1];
//			sensor.setCurrentMode("Red");
	//
//			while (Button.DOWN.isUp()) {
//				// fetch sample
//				sensor.fetchSample(scannedColor, 0);
//			SensorAndFilterSample sensorAndFilterSample = new SensorAndFilterSample();
//			sensorAndFilterSample.getLatestSample();
//			SensorAndFilterSample sensorAndFilter = new SensorAndFilterSample();
//				if (scannedColor[0] < .20) {
//					display.drawString("Donker", 0, 4);
//				} else {
//					display.drawString("Licht", 0, 4);
	//
//				}
//			}
	//
//		}
}
