/**
 * Has methods to set motorspeeds to move Marvin
 */

package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MarvinMover extends Driving {

	static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.D);

	
	public MarvinMover() {
		super();
	}
	
		public void turnRightOnBlack() {
		motorA.setSpeed(-300);
		motorB.setSpeed(5);
		motorA.backward();
		motorB.backward();
	}

	public void turnLeftOnWhite() {
		motorA.setSpeed(0);
		motorB.setSpeed(-270);
		motorA.forward();
		motorB.backward();
	}

	public void driveStraightOnGrey() {
		motorA.setSpeed(-280);
		motorB.setSpeed(-280);
		motorA.backward();
		motorB.backward();
	}

	public void turn90DegreesLeft() { // turns vehicle
		motorA.setSpeed(200); // 90 degrees left
		motorA.rotate(500);
	}

	public void turn90DegreesRight() { // turns vehicle
		motorB.setSpeed(200); // 90 degrees right
		motorB.rotate(500);
	}

	public void turn45DegreesLeft() { // turns vehicle
		motorA.setSpeed(200); // 45 degrees left
		motorA.rotate(250);
	}

	public void turn45DegreesRight() { // turns vehicle
		motorB.setSpeed(200); // 45 degrees right
		motorB.rotate(250);
	}

	public void turn180Degrees() { // turns vehicle
		motorB.setSpeed(200); // 180 degrees right
		motorB.rotate(1000);
	}

	public void driveForward() { // drives vehicle forward
		motorB.setSpeed(200);
		motorB.forward();
		motorA.setSpeed(200);
		motorA.forward();
		Delay.msDelay(8000);
	}

	/**
	 * cruises straight with low speed
	 */
	public void cruise() {
		motorA.setSpeed(-180);
		motorB.setSpeed(-180);
		motorA.backward();
		motorB.backward();		
	}
	
}
