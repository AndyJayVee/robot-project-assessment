/**
 * Has methods to set motorspeeds to move Marvin
 */

package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MarvinMover {

	static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.D);

	public MarvinMover() {
		super();
	}

	public void turnRightOnBlack() {
		motorA.setSpeed(-140);
		motorB.setSpeed(50);
		motorA.backward();
		motorB.forward();
	}

	public void turnLeftOnWhite() {
		motorA.setSpeed(80);
		motorB.setSpeed(-300);
		motorA.forward();
		motorB.backward();
	}

	public void driveStraightOnGrey() {
		motorA.setSpeed(-350);
		motorB.setSpeed(-350);
		motorA.backward();
		motorB.backward();
	}

	public void turn180DegreesCounterClockwise() { // turns vehicle 90 degrees clockwise
		motorB.setSpeed(80);
		motorA.setSpeed(-300);
		motorB.forward();
		motorA.backward();
		Delay.msDelay(1400);
	}

	public void turn90DegreesCounterClockwise() { // turns vehicle 90 degrees counter clockwise
		motorA.setSpeed(80);
		motorB.setSpeed(-300);
		motorA.forward();
		motorB.backward();
		Delay.msDelay(1400);
	}

	public void turn180DegreesCounterClockwise() { // turns vehicle 180 degrees counter clockwise
		motorA.setSpeed(80);
		motorB.setSpeed(-300);
		motorA.forward();
		motorB.backward();
		Delay.msDelay(2600);
	}
	
	public void turn180DegreesClockwise() { // turns vehicle 180 degrees clockwise
		motorB.setSpeed(80);
		motorA.setSpeed(-300);
		motorB.forward();
		motorA.backward();
		Delay.msDelay(2600);
	}

	public void shortLeg() { // drives vehicle forward by approximately 20 cm
		motorA.setSpeed(400);
		motorA.forward();
		motorB.setSpeed(400);
		motorB.forward();
		Delay.msDelay(1400);
	}

	public void longLeg() { // drives vehicle forward by approximately 40 cm
		motorA.setSpeed(400);
		motorA.forward();
		motorB.setSpeed(400);
		motorB.forward();
		Delay.msDelay(2600);
	}
	
	public void stopMoving() {
		motorA.stop();
		motorB.stop();
	}

}
