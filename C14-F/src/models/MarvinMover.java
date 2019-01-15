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
    
    public void turn90DegreesLeft() { // turns vehicle
		motorB.setSpeed(200); // 90 degrees left
		motorB.rotate(500);
	}

	public void turn90DegreesRight() { // turns vehicle
		motorA.setSpeed(200); // 90 degrees right
		motorA.rotate(500);
	}

	public void turn45DegreesLeft() { // turns vehicle
		motorB.setSpeed(200); // 45 degrees left
		motorB.rotate(250);
	}
	
	public void turn45DegreesRight() { // turns vehicle
		motorA.setSpeed(200); // 45 degrees right
		motorA.rotate(250);
	}

	public void turn180Degrees() { // turns vehicle
		motorA.setSpeed(200); // 180 degrees right
		motorA.rotate(1000);
	}

	public void driveForward() { // drives vehicle forward
		motorA.setSpeed(300);
		motorA.forward();
		motorB.setSpeed(300);
		motorB.forward();
		Delay.msDelay(1500);
	}
}

