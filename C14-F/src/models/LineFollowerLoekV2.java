package models;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Driving;
import models.Pilot;

public class LineFollowerLoekV2 {

	static Brick brick;
	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

	public LineFollowerLoekV2() {
		super();
	}

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void followLine() {
		// start with driving straight
		Pilot pilot = new Pilot();
		Driving drive = new Driving(pilot.getPilot());

		// set Sensor mode
		sensor.setCurrentMode("Red");

		// initialize array to fetch sample in
		float[] lastFetchedValue = new float[1];
		// Declare String that describes color of last-fetched Sensor float
		String lastFetchedColor;
		// Counter for #laps. Lap1 means 'now driving in lap 1, 0 completed'.
		int lapCount = 0;
		// declare array to store lapTime
		// TODO ??? int[] lapTime = n
		// while not pressed continue
		while (Button.DOWN.isUp()) {
			// start fetching. Assumed it keeps fetching and updating so it will run correct while()
			sensor.fetchSample(lastFetchedValue, 0);
			while (lastFetchedValue[0] >= .20 && lastFetchedValue[0] <= .60) {	// while grey
				drive.drive(); 													// drive straight
				lastFetchedColor = "Grey";
				System.out.println(lastFetchedColor);
			}
			while (lastFetchedValue[0] > .60) { // while white
				drive.turn(-5);					// turn left
				lastFetchedColor = "White";
				System.out.println(lastFetchedColor);
			}
			while (lastFetchedValue[0] < .20) { // while black
				drive.turn(5); 					// turn right
				lastFetchedColor = "Black";
				System.out.println(lastFetchedColor);
			}
			// TODO
			// if (sensor measures red finish line && lapCount == 0) {
			//	start.timer
			//	lapCount++; 
			// } else if (sensor measures red finish line && lapCount > 0) {
			// 	stop.timer();
			// 	lapTime[lapCount] = getLapTime();
			// 	reset.timer();
			//	lapCount++;
			// }
			// 	print ("Lap "lapCount + " " + lapTime[lapCount - 1]) ??

		}
	}
}
