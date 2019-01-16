package models;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot extends MovePilot{

	//Wheel diameter and offset (half track with) is set in mm.
	private static final double WHEEL_DIAMETER = 43.2;
	private static final double TRACK_WIDTH = 114.0;
	private static Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, WHEEL_DIAMETER).offset(-0.5 * TRACK_WIDTH);
	private static Wheel rightWheel = WheeledChassis.modelWheel(Motor.D, WHEEL_DIAMETER).offset(0.5 * TRACK_WIDTH);
	private static WheeledChassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	
	//Pilot constructor
	public Pilot() {
		super(chassis);
	}
}
    
    

