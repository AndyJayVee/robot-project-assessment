package models;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import models.BeaconFinder;
import models.LineFollower;

public class GameLauncher {

	private Brick brick;
	

	public GameLauncher() {
		super();
	}

	public void launchGame() {
		brick = LocalEV3.get();
		TextLCD display = brick.getTextLCD();

		display.drawString("  linefollower:   ", 0, 1);
		display.drawString("    Press up      ", 0, 2);
		display.drawString("  Beaconfinder:   ", 0, 3);
		display.drawString("   Press down     ", 0, 4);
		int pressedButton = Button.waitForAnyEvent();
		if (pressedButton == Button.ID_UP) { // 1 = button up
			display.clear();
			display.drawString("   Linefollower   ", 0, 1);
			display.drawString("     started      ", 0, 2);
			LineFollower lineFollower = new LineFollower();
			lineFollower.followLine();

		} else if (pressedButton == Button.ID_DOWN) {// 1 = button down
			display.clear();
//			display.drawString("123456789987654321", 0, 1); string to compare
			display.drawString("   Beaconfinder   ", 0, 1);
			display.drawString("     started      ", 0, 2);
			BeaconFinder beaconFinder = new BeaconFinder();
			beaconFinder.findBeacon();
		}
	}
}
