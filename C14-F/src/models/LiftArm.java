package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LiftArm {

	private EV3LargeRegulatedMotor liftArm = new EV3LargeRegulatedMotor(MotorPort.B);// instantiate the connection of
																						// the liftArm to port B
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);// instantiate the left motor

	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);// instantiate the right motor

	// no args constructor
	public LiftArm() {
		super();
	}

	// methods for moving the LiftArm

	public void liftUp() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(25); // initial speed set @25
		liftArm.rotate(90); // initial degrees of rotations set @45
	}

	public void moveBeacon() { // moves vehicle
		for (int i = 0; i < 6; i++) { // drive a right-hand circle (left motor rotates twice as much and twice as fast as right motor)
			leftMotor.setSpeed(200);
			leftMotor.rotate(60);
			rightMotor.setSpeed(100);
			rightMotor.rotate(30);
		}
	}

	public void liftDown() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(25); // initial speed set @25
		liftArm.rotate(-90); // initial degrees of rotations set @-45
	}
}