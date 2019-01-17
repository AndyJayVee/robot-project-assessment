/**
 * @author Leo
 * Instantiates medium sized motor on port B.
 * Two methods to move the fork lift arm up and down.
 */

package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LiftArm {

	private EV3LargeRegulatedMotor liftArm = new EV3LargeRegulatedMotor(MotorPort.B);// instantiate the connection of
																						// the liftArm to port B


	// no args constructor
	public LiftArm() {
		super();
	}

	// methods for moving the LiftArm

	public void liftUp() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(25); // initial speed set @25
		liftArm.rotate(120); // initial degrees of rotations set @120
	}

	public void liftDown() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(25); // initial speed set @25
		liftArm.rotate(-120); // initial degrees of rotations set @-120
	}

}