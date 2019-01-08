package movement;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class ForwardDrive {

	
	private int speed;
	private int duration;
	
	
	public ForwardDrive(int speed, int duration) {
		super();
		this.speed = speed;
		this.duration = duration;
	}

	
	/**
	 * Method to drive straight
	 * @param speed (percentage, postive == forward)
	 * @param duration (milisecxonds)
	 */
	public void ForwardDrive(int speed, int duration) {
	System.out.println("Drive Forward\nand Stop\n");
	System.out.println("Press any key to start");

	Button.LEDPattern(4); // flash green led and
	Sound.beepSequenceUp(); // make sound when ready.

	Button.waitForAnyPress();

	// create two motor objects to control the motors.
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	// set motors to 50% power.
	motorA.setPower(-speed);
	motorB.setPower(-speed);

	// wait 2 seconds.
	Delay.msDelay(duration);

	// stop motors with brakes on.
	motorA.stop();
	motorB.stop();

	// free up motor resources.
	motorA.close();
	motorB.close();

	Sound.beepSequence(); // we are done.
	}



}
