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
import models.GameLauncher;
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

		// run method to ask for Enter key (comment/uncomment the lines in method to
		// run)
		marvin.runEnter();
	}

	/**
	 * This method waits for Enter key
	 */

	private void runEnter() {
		TextLCD display = brick.getTextLCD();
//		display.drawString("123456789987654321", 0, 1); ruler string
		display.drawString("      Foxtrot     ", 0, 3);
		display.drawString("    Press Enter   ", 0, 4);
		waitForKey(Button.ENTER);

		display.clear();

		GameLauncher newGame = new GameLauncher();
		newGame.launchGame();
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
