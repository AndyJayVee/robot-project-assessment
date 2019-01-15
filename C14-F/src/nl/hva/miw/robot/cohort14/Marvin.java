/**
 * @author loek 
 * Main Class. runEnter needs to be adjusted to let Marvin choose Game
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
import models.GameLauncher;
//import models.GameLauncher;
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
		display.drawString("     Foxtrot ", 0, 3);
		display.drawString("   Press Enter", 0, 4);
		waitForKey(Button.ENTER);
		LineFollower lineFollower = new LineFollower();
		lineFollower.followLine();
//		display.clear();
//
//		GameLauncher newGame = new GameLauncher();
//		newGame.launchGame();
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
