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

	private static final int SPEED_200 = 200;
	private static final int SPEED_100 = 100;
	private static final int SPEED_140 = 140;
	private static final int DELAY_1400 = 1400;
	private static final int SPEED_400 = 400;
	private static final int DELAY_2600 = 2600;
	private static final int SPEED_300 = 300;
	private static final int SPEED_80 = 80;
	EV3LargeRegulatedMotor motorA;
	EV3LargeRegulatedMotor motorD;

	public MarvinMover() {
		super();
		this.motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		this.motorD = new EV3LargeRegulatedMotor(MotorPort.D);
	}

	public void turnRightOnBlack() {
		motorA.setSpeed(-SPEED_140);
		motorD.setSpeed(SPEED_100);
		motorA.backward();
		motorD.forward();
	}

	public void turnLeftOnWhite() {
		motorA.setSpeed(SPEED_100);
		motorD.setSpeed(-SPEED_200);
		motorA.forward();
		motorD.backward();
	}

	public void driveStraightOnGrey() {
		motorA.setSpeed(-SPEED_200);
		motorD.setSpeed(-SPEED_200);
		motorA.backward();
		motorD.backward();
	}

	public void turn90DegreesClockwise() { // turns vehicle 90 degrees right
		motorD.setSpeed(SPEED_80);
		motorA.setSpeed(-SPEED_300);
		motorD.forward();
		motorA.backward();
		Delay.msDelay(DELAY_1400);
	}

	public void turn90DegreesCounterClockwise() { // turns vehicle 90 degrees left
		motorA.setSpeed(SPEED_80);
		motorD.setSpeed(-SPEED_300);
		motorA.forward();
		motorD.backward();
		Delay.msDelay(DELAY_1400);
	}

	public void turn180DegreesCounterClockwise() { // turns vehicle 180 degrees counter clockwise
		motorA.setSpeed(SPEED_80);
		motorD.setSpeed(-SPEED_300);
		motorA.forward();
		motorD.backward();
		Delay.msDelay(DELAY_2600);
	}
	
	public void turn180DegreesClockwise() { // turns vehicle 180 degrees clockwise
		motorD.setSpeed(SPEED_80);
		motorA.setSpeed(-SPEED_300);
		motorD.forward();
		motorA.backward();
		Delay.msDelay(DELAY_2600);
	}

	public void shortLeg() { // drives vehicle forward by approximately 20 cm
		motorA.setSpeed(SPEED_400);
		motorA.forward();
		motorD.setSpeed(SPEED_400);
		motorD.forward();
		Delay.msDelay(DELAY_1400);
	}
	
	public void shortBack() { // drives vehicle backward by approximately 20 cm
		motorA.setSpeed(-SPEED_400);
		motorA.forward();
		motorD.setSpeed(-SPEED_400);
		motorD.forward();
		Delay.msDelay(700);
	}

	public void longLeg() { // drives vehicle forward by approximately 40 cm
		motorA.setSpeed(SPEED_400);
		motorA.forward();
		motorD.setSpeed(SPEED_400);
		motorD.forward();
		Delay.msDelay(DELAY_2600);
	}
	
	public void stopMoving() { // stops both motors
		motorA.stop();
		motorD.stop();
	}

}
