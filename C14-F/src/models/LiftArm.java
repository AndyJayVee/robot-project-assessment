package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

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
		liftArm.rotate(90); // initial degrees of rotations set @90
	}

	public void liftDown() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(25); // initial speed set @25
		liftArm.rotate(-90); // initial degrees of rotations set @-90
	}
	
	// methods for moving the vehicle

	public void turn90DegreesLeft() { // turns vehicle
		leftMotor.setSpeed(200); // 90 degrees left
		leftMotor.rotate(500);
	}

	public void turn90DegreesRight() { // turns vehicle
		rightMotor.setSpeed(200); // 90 degrees right
		rightMotor.rotate(500);
	}

	public void turn45DegreesLeft() { // turns vehicle
		leftMotor.setSpeed(200); // 45 degrees left
		leftMotor.rotate(250);
	}
	
	public void turn45DegreesRight() { // turns vehicle
		rightMotor.setSpeed(200); // 45 degrees right
		rightMotor.rotate(250);
	}

	public void turn180Degrees() { // turns vehicle
		rightMotor.setSpeed(200); // 180 degrees right
		rightMotor.rotate(1000);
	}

	public void driveForward() { // drives vehicle forward
		rightMotor.setSpeed(200);
		rightMotor.forward();
		leftMotor.setSpeed(200);
		leftMotor.forward();
		Delay.msDelay(8000);
	}

}