package models;

public class Stopwatch implements Runnable {

	private long startTime;
	private long currentTime;
	private long elapsedTime = 0;
	private int thenthOfSeconds;
	private int seconds;
	private int minutes;
	private int hours;
	private boolean stopwatchRuns = true;

	@Override
	public void run() {
		try {
			startTime = System.currentTimeMillis();
			while (stopwatchRuns) {
				while (elapsedTime < 100) {
					currentTime = System.currentTimeMillis();
					elapsedTime = currentTime - startTime;
				}
				thenthOfSeconds++;
				if (thenthOfSeconds > 9) {
					thenthOfSeconds = 0;
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
				System.out.println(seconds);
			}

		} catch (Exception e) {
			System.out.println("Oops, something went wrong with the stopwatch");
		}
	}
}
