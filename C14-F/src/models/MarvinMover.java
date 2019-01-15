/**
 * Has methods to set motorspeeds to move Marvin
 */

package models;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class MarvinMover {
	
	static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
    static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.D);

    public void turnRightOnBlack() {
		motorA.setSpeed(-48);
        motorB.setSpeed(0);
    }
    
    public void turnLeftOnWhite() {
		motorA.setSpeed(-4);
        motorB.setSpeed(-44);
    }
    
    public void driveStraightOnGrey() {
		motorA.setSpeed(-45);
        motorB.setSpeed(-45);
    }
}

