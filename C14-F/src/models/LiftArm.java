package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LiftArm {

	private EV3LargeRegulatedMotor liftArm = new EV3LargeRegulatedMotor(MotorPort.B);// instantiate the connection of
																						// the liftArm to port B

	// constructor
	public LiftArm(EV3LargeRegulatedMotor liftArm) {
		super();
		this.liftArm = liftArm;
	}

	// getter
	public EV3LargeRegulatedMotor getLiftArm() {
		return liftArm;
	}

	// setter
	public void setLiftArm(EV3LargeRegulatedMotor liftArm) {
		this.liftArm = liftArm;
	}

	// methods for moving the LiftArm

	public void liftUp() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(1000); // initial speed set at 1,000 degrees per second
		liftArm.rotate(3600); // initial number of rotations set at 10 (10 * 360)
	}

	public void liftDown() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(-1000); // initial speed set at -1,000 degrees per second
		liftArm.rotate(3600); // initial number of rotations set at 10 (10 * 360)
	}

}
