package models;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;

public class Menu {
	private String currentGame;
	private Brick brick = LocalEV3.get();
	private TextLCD display = brick.getTextLCD();
	private boolean repeat;

	public Menu() {
		super();
	}

	public String chooseGame() {
		repeat = true;
		while (repeat) {
			display.clear();
			display.drawString("  linefollower:   ", 0, 1);
			display.drawString("    Press up      ", 0, 2);
			display.drawString("  Beaconfinder:   ", 0, 3);
			display.drawString("  Press enter     ", 0, 4);
			display.drawString("   Beacongrab:    ", 0, 5);
			display.drawString("   Press down     ", 0, 6);

			int pressedButton = Button.waitForAnyEvent();
			if (pressedButton == Button.ID_UP) { // 1 = button up
				display.clear();
				display.drawString("  Line follower   ", 0, 3);
				gameStarted();
				currentGame = "linefollower";

			} else if (pressedButton == Button.ID_ENTER) {// 1 = button down
				display.clear();
//				display.drawString("123456789987654321", 0, 1); ruler comment
				display.drawString("  Beacon finder   ", 0, 1);
				gameStarted();
				currentGame = "beaconfinder";

			} else if (pressedButton == Button.ID_LEFT) {// 1 = button down
				Music song = new Music();
				song.playStarWars();
				display.clear();
//				display.drawString("123456789987654321", 0, 1); ruler comment
				display.drawString("    Star Wars     ", 0, 1);
				gameStarted();
				currentGame = "nogame";

			} else if (pressedButton == Button.ID_DOWN) {// 1 = button down
				display.clear();
				display.drawString("  Beacon grabber  ", 0, 1);
				gameStarted();
				currentGame = "movebeacon";

			} else if (pressedButton == Button.ID_LEFT || pressedButton == Button.ID_RIGHT) {
				display.clear();
//				display.drawString("123456789987654321", 0, 1); ruler comment
				display.drawString("   Wrong button   ", 0, 1);
				display.drawString(" Press any key to ", 0, 2);
				display.drawString("   Choose again   ", 0, 3);
				Delay.msDelay(3000);
			} else if (pressedButton == Button.ID_ESCAPE) {
				repeat = false;
				currentGame = "nogame";
			}
		}
		repeat = true;
		return currentGame;
	}

	private void gameStarted() {
		display.drawString("     started      ", 0, 2);
		Delay.msDelay(3000);
		display.clear();
		repeat = false;
	}

	public String gameStopped(String gameName) {
		String currentGame;
		display.clear();
		display.drawString("  " + gameName, 0, 1);
		display.drawString("     stopped      ", 0, 2);
		display.drawString("  Press any key   ", 0, 3);
		display.drawString("    continue      ", 0, 4);
		int pressedButton = Button.waitForAnyPress();
		if (pressedButton == Button.ID_ESCAPE) {
			currentGame = "nogame";
		} else
			currentGame = "";
		return currentGame;
	}
}
