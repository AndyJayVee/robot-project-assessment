package models;

<<<<<<< HEAD
import lejos.hardware.Button;
import lejos.hardware.Brick;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

import models.MarvinMover;

=======
import lejos.hardware.Button;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Driving;
import models.Pilot;
import models.MarvinMover;

>>>>>>> c863642dcbf953f83eb2a39123ee4593f3113c6c
public class LineFollowerLoek { //implements Runnable {

	static Brick brick;
	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
<<<<<<< HEAD
    private MarvinMover marvinMover = new MarvinMover();
	
=======

	static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
    static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.D);
    
    static MarvinMover marvinMover;
    
>>>>>>> c863642dcbf953f83eb2a39123ee4593f3113c6c
	public LineFollowerLoek() {
		super();
	}
	
	LineFollowerLoek lineFollowerLoek = new LineFollowerLoek();

	/**
	 * method to follow a line / this works best if the marvin is placed on the
	 * border of the black line / with the line on the left of marvin
	 */

	public void followLine() {
		// initialize array to fetch sample in
		float[] scannedColor = new float[1];
		sensor.setCurrentMode("Red");

		while (Button.DOWN.isUp()) {
			sensor.fetchSample(scannedColor, 0);
			if (scannedColor[0] > .60) { // white
				marvinMover.turnLeftOnWhite();
			} else if (scannedColor[0] < .20) { // black
				marvinMover.turnRightOnBlack();
			} else { // grey
		        marvinMover.driveStraightOnGrey();
			}
		}
	}
}
