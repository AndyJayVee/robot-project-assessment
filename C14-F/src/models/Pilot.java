package models;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot extends MovePilot{

	private static final double WHEEL_DIAMETER = 43.2; // Wheel diameter and offset (half track width) is set in mm.
	private static final double TRACK_WIDTH = 114.0;
		
	
	public Pilot(Chassis chassis) {
		super(chassis);
	}

	public Pilot() {	
		super(new WheeledChassis(
				new Wheel[] {WheeledChassis.modelWheel(Motor.A, WHEEL_DIAMETER).offset(-0.5 * TRACK_WIDTH), 
							WheeledChassis.modelWheel(Motor.D, WHEEL_DIAMETER).offset(0.5 * TRACK_WIDTH)}, 
				WheeledChassis.TYPE_DIFFERENTIAL));
	}	
}
	
	
    
    

