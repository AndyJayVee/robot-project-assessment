package nl.hva.miw.robot.cohort14;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import models.Driving;
import models.Pilot;

public class Marvin {
	
	Brick brick;
	
	public Marvin() {
		super();
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin();
		marvin.run();
		
		 
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Foxtrot!", 0, 4);
		waitForKey(Button.ENTER);
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());
		float distance = -500;
		drive.driveRectangle(distance);
	}
	
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
}
