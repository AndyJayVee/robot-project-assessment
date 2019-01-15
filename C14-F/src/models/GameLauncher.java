package models;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import models.BeaconFinder;
import models.LineFollower;

public class GameLauncher {
	private Brick brick = LocalEV3.get();
	private TextLCD display = brick.getTextLCD();

	public GameLauncher() {
		super();
	}

	public void launchGame() {
		boolean repeat = true;
		while (repeat) {
			display.drawString("  linefollower:   ", 0, 1);
			display.drawString("    Press up      ", 0, 2);
			display.drawString("  Beaconfinder:   ", 0, 3);
			display.drawString("  Press enter     ", 0, 4);
			display.drawString("   Beacongrab:    ", 0, 5);
			display.drawString("   Press down     ", 0, 6);

			int pressedButton = Button.waitForAnyEvent();
			if (pressedButton == Button.ID_UP) { // 1 = button up
				display.clear();
				display.drawString("   Linefollower   ", 0, 3);
				gameStarted();
				LineFollower lineFollower = new LineFollower();
				lineFollower.followLine();
				display.clear();
				display.drawString("   Linefollower   ", 0, 1);
				gameStopped();

			} else if (pressedButton == Button.ID_ENTER) {// 1 = button down
				display.clear();
//				display.drawString("123456789987654321", 0, 1); ruler comment
				display.drawString("   Beaconfinder   ", 0, 1);
				gameStarted();
				BeaconFinder beaconFinder = new BeaconFinder();
				beaconFinder.findBeacon();
				display.drawString("   Beaconfinder   ", 0, 1);
				gameStopped();

			} else if (pressedButton == Button.ID_DOWN) {// 1 = button down
				display.clear();
				display.drawString("   Beacongrabber  ", 0, 1);
				gameStarted();
				BeaconFinder beaconFinder = new BeaconFinder();
				beaconFinder.findBeacon();
				display.clear();
				display.drawString("   Beacongrabber  ", 0, 1);
				gameStopped();
			} else if (pressedButton == Button.ID_LEFT || pressedButton == Button.ID_RIGHT) {
				display.clear();
//		display.drawString("123456789987654321", 0, 1); ruler comment
			display.drawString("   Wrong button   ", 0, 1);
			display.drawString(" Press any key to ", 0, 2);
			display.drawString("   Choose again   ", 0, 3);
			Delay.msDelay(3000);
			} else if (pressedButton == Button.ID_ESCAPE) {
				repeat = false;
			}
		}
	}

	private void gameStarted() {
		display.drawString("     started      ", 0, 2);
		Delay.msDelay(3000);
		display.clear();
	}

	private void gameStopped() {
		display.drawString("     stopped      ", 0, 2);
		display.drawString("  Press any key   ", 0, 3);
		display.drawString("    continue      ", 0, 4);
	}
}
