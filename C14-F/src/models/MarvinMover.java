/**
 * @author Loek
 * @author Leo 
 * Instantiates two motors: motorA and motorB.
 * Has methods to set individual motor speeds and directions to move the vehicle.
 * The Delay.msDelay can be adjusted to tweak the exact angles.
 */

package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MarvinMover { // provides movement to BeaconFinder and MoveBeacon classes

	EV3LargeRegulatedMotor motorA;
	EV3LargeRegulatedMotor motorD;

	public MarvinMover() {
		super();
		this.motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		this.motorD = new EV3LargeRegulatedMotor(MotorPort.D);
	}

	public void turnRightOnBlack() {
		motorA.setSpeed(-140);
		motorD.setSpeed(100);
		motorA.backward();
		motorD.forward();
	}

	public void turnLeftOnWhite() {
		motorA.setSpeed(100);
		motorD.setSpeed(-200);
		motorA.forward();
		motorD.backward();
	}

	public void driveStraightOnGrey() {
		motorA.setSpeed(-200);
		motorD.setSpeed(-200);
		motorA.backward();
		motorD.backward();
	}

	public void turn90DegreesClockwise() { // turns vehicle 90 degrees right
		motorD.setSpeed(80);
		motorA.setSpeed(-300);
		motorD.forward();
		motorA.backward();
		Delay.msDelay(1400);
	}

	public void turn90DegreesCounterClockwise() { // turns vehicle 90 degrees left
		motorA.setSpeed(80);
		motorD.setSpeed(-300);
		motorA.forward();
		motorD.backward();
		Delay.msDelay(1400);
	}

	public void turn180DegreesCounterClockwise() { // turns vehicle 180 degrees counter clockwise
		motorA.setSpeed(80);
		motorD.setSpeed(-300);
		motorA.forward();
		motorD.backward();
		Delay.msDelay(2600);
	}
	
	public void turn180DegreesClockwise() { // turns vehicle 180 degrees clockwise
		motorD.setSpeed(80);
		motorA.setSpeed(-300);
		motorD.forward();
		motorA.backward();
		Delay.msDelay(2600);
	}

	public void shortLeg() { // drives vehicle forward by approximately 20 cm
		motorA.setSpeed(400);
		motorA.forward();
		motorD.setSpeed(400);
		motorD.forward();
		Delay.msDelay(1400);
	}
	
	public void shortBack() { // drives vehicle backward by approximately 20 cm
		motorA.setSpeed(-400);
		motorA.forward();
		motorD.setSpeed(-400);
		motorD.forward();
		Delay.msDelay(700);
	}

	public void longLeg() { // drives vehicle forward by approximately 40 cm
		motorA.setSpeed(400);
		motorA.forward();
		motorD.setSpeed(400);
		motorD.forward();
		Delay.msDelay(2600);
	}
	
	public void stopMoving() { // stops both motors
		motorA.stop();
		motorD.stop();
	}

}
