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
import lejos.utility.Delay;
import models.BeaconFinder;
import models.Driving;
import models.LineFollower;
import models.LineFollowerFrank;
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
		
		// instantiate LineFollowerFrank
		LineFollowerFrank lineFolloweFrank = new LineFollowerFrank();
		
		// instantiate BeaconFinder
		BeaconFinder beaconFinder = new BeaconFinder();

		// run method to ask for Enter key (this will invoke lineFollower())
		// marvin.runEnter();

		// run method LineFollowerFrank()
		// lineFollowerFrank.followLine();
		
		// run method BeaconFinder()
		beaconFinder.findBeacon();

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
}
