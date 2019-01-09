package models;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot {
	MovePilot pilot;
	Driving drive;
	
	//Wheel diameter and offset (half track with) is set in mm.
	private static final double WHEEL_DIAMETER = 43.2;
	private static final double TRACK_WIDTH = 148.0;
	private double linearSpeed;
	private double angularSpeed;
	
	public void setLinearSpeed(double linearSpeed) {
		this.linearSpeed = linearSpeed;
	}

	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
	
	//Pilot constructor
	public Pilot() {
	Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, WHEEL_DIAMETER).offset(-0.5 * TRACK_WIDTH);
	Wheel rightWheel = WheeledChassis.modelWheel(Motor.D, WHEEL_DIAMETER).offset(0.5 * TRACK_WIDTH);
	Chassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	pilot = new MovePilot(chassis);
	
	drive = new Driving(pilot);
	drive.travelAndRotate();
	
	pilot.setLinearSpeed(linearSpeed);
	pilot.setAngularSpeed(angularSpeed);
	
	//while (Hier de voorwaarden){
	// Hier de bewegingen om de auto aan te sturen.
	//
	//}
	
	pilot.stop();
	}
}
    
    

