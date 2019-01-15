package models;

import lejos.hardware.Brick;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;

public class Stopwatch implements Runnable {

	private long startTime;
	private long currentTime;
	private long elapsedTime = 0;
	private int tenthOfSeconds;
	private int seconds;
	private int minutes;
	private int hours;
	private boolean running = true;
	private Brick brick = LocalEV3.get();
	private TextLCD display = brick.getTextLCD();
	private String output1;
	private String output2;

	public Stopwatch(boolean running) {
		super();
		this.running = running;
	}

	public void setNotStopped(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		try {
			display.clear();
			while (running) {
				startTime = System.currentTimeMillis();
				while (elapsedTime < 100) {
					currentTime = System.currentTimeMillis();
					elapsedTime = currentTime - startTime;
				}
				tenthOfSeconds++;
				elapsedTime = 0;
				if (tenthOfSeconds > 9) {
					tenthOfSeconds = 0;
					seconds++;
				}
				if (seconds > 59) {
					seconds = 0;
					minutes++;
				}
				if (minutes > 59) {
					minutes = 0;
					hours++;
				}
				output1 = String.format("\n%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);
				display.drawString((output1),3, 3);
			}
			output2 = String.format("%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);
			display.drawString(("Final time:"),4, 2);
			display.drawString((output2),3, 3);
			
		} catch (Exception e) {
			System.out.println("Oops, something went wrong with the stopwatch");
		}
	}
}
