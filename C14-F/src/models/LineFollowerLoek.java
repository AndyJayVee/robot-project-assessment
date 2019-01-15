package models;

import lejos.hardware.Button;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import models.Driving;
import models.Pilot;


public class LineFollowerLoek { //implements Runnable {

	static Brick brick;
	static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

	static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.D);
    
	public LineFollowerLoek() {
		super();
	}

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
				motorA.setPower(-4);
                motorB.setPower(-44);
			} else if (scannedColor[0] < .20) { // black
				motorA.setPower(-48);
                motorB.setPower(0);
			} else { // grey
		        motorA.setPower(-45);
		        motorB.setPower(-45);
			}
		}
	}
}
