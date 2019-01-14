package models;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot {

	private MovePilot pilot;
	private Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, WHEEL_DIAMETER).offset(-0.5 * TRACK_WIDTH);
	private Wheel rightWheel = WheeledChassis.modelWheel(Motor.D, WHEEL_DIAMETER).offset(0.5 * TRACK_WIDTH);
	private Chassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	private static final double MAX_LINEAR_SPEED = 350;
//	private static final double MAX_ANGULAR_SPEED = 0;
	private static final double WHEEL_DIAMETER = 43.2; // Wheel diameter and offset (half track width) is set in mm.
	private static final double TRACK_WIDTH = 114.0;
	private double linearSpeed = MAX_LINEAR_SPEED;
	private double angularSpeed = 350;
	
	//Pilot constructor
	public Pilot() {	
	pilot = new MovePilot(chassis);
	pilot.setLinearSpeed(linearSpeed);
	pilot.setAngularSpeed(angularSpeed);
	pilot.stop();
	pilot.forward();
	}
	
	public void setLinearSpeed(double linearSpeed) {
		this.linearSpeed = linearSpeed;
	}

	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
	
	public MovePilot getPilot() {
		return pilot;
	}
	

}
    
    

