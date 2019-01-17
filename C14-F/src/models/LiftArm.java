/**
 * @author Leo
 * Instantiates medium sized motor on port B.
 * Two methods to move the fork lift arm up and down.
 */

package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LiftArm {

	private static final int NUMBER_OF_ROTATIONS = 120;
	private static final int ROTATION_SPEED = 25;
	private EV3LargeRegulatedMotor liftArm = new EV3LargeRegulatedMotor(MotorPort.B);// instantiate the connection of
																						// the liftArm to port B


	// no args constructor
	public LiftArm() {
		super();
	}

	// methods for moving the LiftArm

	public void liftUp() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(ROTATION_SPEED); // initial speed set @25
		liftArm.rotate(NUMBER_OF_ROTATIONS); // initial degrees of rotations set @120
	}

	public void liftDown() { // rotates the motor at a set speed and number of rotations
		liftArm.setSpeed(ROTATION_SPEED); // initial speed set @25
		liftArm.rotate(-NUMBER_OF_ROTATIONS); // initial degrees of rotations set @-120
	}

}