package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LiftArm {

	EV3LargeRegulatedMotor liftArm = new EV3LargeRegulatedMotor(MotorPort.B);// instantiate the connection of the
																				// liftArm to port B

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

	public void liftUp() { // WIP

	}

	public void liftDown() { // WIP

	}

}
