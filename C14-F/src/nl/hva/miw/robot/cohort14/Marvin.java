/**
 * @author loek 
 * Main Class. runEnter needs to be adjusted to let Marvin choose Game
 */

package nl.hva.miw.robot.cohort14;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
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

	private Brick brick;

	public Marvin() {
		super();
		brick = LocalEV3.get();
	}

	public static void main(String[] args) {
		// initialize a Marvin object
		Marvin marvin = new Marvin();

		// run method to start the robot
		marvin.runEnter();
	
	}

	/** This method waits for Enter key
	 * After Enter it will invoke the method from Class 
	 */
	
	private void runEnter() {

		GameLauncher newGame = new GameLauncher();
		newGame.launchGame();
	}
	

}
