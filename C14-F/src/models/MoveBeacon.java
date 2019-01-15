package models;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon, move it in an F-shape, and put it down
	public void grabBeacon() {
//        Button.LEDPattern(4);    // flash green led and 
//        Sound.beepSequenceUp();  // make sound when ready.
		arm.liftUp(); // lifts beacon up
		System.out.println("lifted up");

		arm.driveForward(); // leg 1a. moves beacon (according to LiftArm.java specifications)
		System.out.println("drove forward");

		arm.driveForward(); // leg 1b. moves beacon (according to LiftArm.java specifications)
		System.out.println("drove forward");

		arm.turn90DegreesRight(); // leg 2. moves beacon (according to LiftArm.java specifications)
		System.out.println("turned right");

		arm.driveForward(); // leg 2. moves beacon (according to LiftArm.java specifications)
		System.out.println("drove forward");

		arm.turn180Degrees(); // leg 3. moves beacon (according to LiftArm.java specifications)
		System.out.println("turned 180 degrees");

		arm.driveForward(); // leg 3. moves beacon (according to LiftArm.java specifications)
		System.out.println("drove forward");

		arm.turn90DegreesLeft(); // leg 4. moves beacon (according to LiftArm.java specifications)
		System.out.println("turned left");

		arm.driveForward(); // leg 4. moves beacon (according to LiftArm.java specifications)
		System.out.println("drove forward");

		arm.turn90DegreesLeft(); // leg 5. moves beacon (according to LiftArm.java specifications)
		System.out.println("turned left");

		arm.driveForward(); // leg 5. moves beacon (according to LiftArm.java specifications)
		System.out.println("drove forward");

		arm.liftDown(); // puts beacon down
		System.out.println("put down");

//		Sound.beepSequence(); // finished grabBeacon method.
	}
}