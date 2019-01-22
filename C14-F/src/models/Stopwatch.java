package models;

import lejos.hardware.Brick;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;

public class Stopwatch implements Runnable {

	/**@author Frank
	 * The stopwatch starts a stopwatch when the run method is called.
	 * The implementation of runnable enables this method to run in a thread.
	 * In turn, this enables the simultaneous actions (e.g. following a line using LineFollower 
	 * AND running the stopwatch.
	 */
	
	private long startTime;
	private long currentTime;
	private long elapsedTime;
	private int tenthOfSeconds;
	private int seconds;
	private int minutes;
	private int hours;
	private static final int MS_IN_HOURS = 3600000;
	private static final int MS_IN_MINUTES = 60000;
	private static final int MS_IN_SECONDS = 1000;
	private boolean running = true;
	private Brick brick = LocalEV3.get();
	private TextLCD display = brick.getTextLCD();
	private String output;
	
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
				output = String.format("\n%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);
				display.drawString((output), 3, 3);
				Thread.sleep(100);
				display.clear();
				currentTime = System.currentTimeMillis();
				elapsedTime = currentTime - startTime;
				hours = (int) (elapsedTime / (MS_IN_HOURS));
				minutes = (int) (elapsedTime / (MS_IN_MINUTES) % 60);
				seconds = (int) (elapsedTime / MS_IN_SECONDS % 60);
				tenthOfSeconds = (int) (elapsedTime / 100 % 10);
			}
			display.drawString((output), 3, 3);
			display.drawString(("Final time:"), 2, 2);
	
		} catch (Exception e) {
			System.out.println("Oops, the stopwatch crashed");
		}
	}
}
