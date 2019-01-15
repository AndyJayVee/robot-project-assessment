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
			startTime = System.currentTimeMillis();
			while (running) {
				output1 = String.format("\n%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);
				display.drawString((output1), 3, 3);
				Thread.sleep(100);
				display.clear();
				currentTime = System.currentTimeMillis();
				elapsedTime = currentTime - startTime;
				hours = (int) (elapsedTime / (1000 * 60 * 60));
				minutes = (int) (elapsedTime / (1000 * 60) % 60);
				seconds = (int) (elapsedTime / 1000 % 60);
				tenthOfSeconds = (int) (elapsedTime / 100 % 10);
			}
<<<<<<< HEAD
			output1 = String.format("\n%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);
			display.drawString((output1), 3, 3);

			display.drawString(("Final time:"), 3, 2);
=======
			display.drawString((output1), 3, 3);
			display.drawString(("Final time:"), 2, 2);
>>>>>>> 9e2e9884232eca8f141f9f6f355e6e35279a0df8
	
		} catch (Exception e) {
			System.out.println("Oops, something went wrong with the stopwatch");
		}
	}
}
