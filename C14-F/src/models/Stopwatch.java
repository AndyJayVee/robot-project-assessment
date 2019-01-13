package models;

public class Stopwatch implements Runnable {

	private long startTime;
	private long currentTime;
	private long elapsedTime = 0;
	private int tenthOfSeconds;
	private int seconds;
	private int minutes;
	private int hours;
	private boolean running = true;

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
				System.out.printf("\n%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);
			}
			System.out.printf("\n\nFinal time:\n%d : %d : %d.%d", hours, minutes, seconds, tenthOfSeconds);

		} catch (Exception e) {
			System.out.println("Oops, something went wrong with the stopwatch");
		}
	}
}
