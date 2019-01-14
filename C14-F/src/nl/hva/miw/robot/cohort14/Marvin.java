
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
import models.BeaconFinderLoek;
import models.Driving;
//import models.GameLauncher;
import models.LineFollower;
import models.LineFollowerLoek;
// import models.LineFollowerFrank;
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

//		GameLauncher newGame = new GameLauncher();
//		newGame.welcomeMenu();
//		newGame.launchGame();


		// run method to ask for Enter key (this will invoke lineFollower())
		marvin.runEnter();
	
	}

	/** This method waits for Enter key
	 * After Enter it will invoke the method from Class which is UNCOMMENTED
	 * NOTE: comment other methods to refrain Marvin to run other Classes
	 */

	private void runEnter() {
		TextLCD display = brick.getTextLCD();
		display.drawString(" Foxtrot ", 0, 3);
		display.drawString(" Press Enter", 0, 4);
		waitForKey(Button.ENTER);

		// instantiate object from Class that needs to run
		LineFollower lineFollower = new LineFollower();
		// Run method
		lineFollower.followLine();
		
		// instantiate object from Class that needs to run
//		LineFollowerLoek lineFollowerLoek = new LineFollowerLoek();
		// Run method
//		lineFollowerLoek.followLine();
		
		// instantiate object from Class that needs to run
//		LineFollowerFrank lineFollowerFrank = new LineFollowerLoek();
		// Run method
//		lineFollowerFrank.followLine();
		
		// instantiate object from Class that needs to run
//		BeaconFinder beaconFinder = new BeaconFinder();
		// Run method
//		beaconFinder.findBeacon();
		
		// instantiate object from Class that needs to run
//		BeaconFinderLoek beaconFinderLoek = new BeaconFinderLoek();
		// Run method
//		beaconFinderLoek.findBeacon();
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
