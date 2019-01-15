package models;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();
	private MarvinMover mover = new MarvinMover();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon, move it in an F-shape, and put it down
	public void grabBeacon() {
//	 Driving drive = new Driving();  TODO: graag testen en kijken wat deze methode doet.
//	 drive.driveRectangle(500);
//	 
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.
		arm.liftUp(); // lifts beacon up
		System.out.println("lifted up");

		mover.longLeg(); // leg 1. moves beacon
		System.out.println("drove forward");

		mover.turn90DegreesRight(); // leg 2. moves beacon
		System.out.println("turned right");

		mover.shortLeg(); // leg 2. moves beacon
		System.out.println("drove forward");

		mover.turn180Degrees(); // leg 3. moves beacon
		System.out.println("turned 180 degrees");

		mover.shortLeg(); // leg 3. moves beacon
		System.out.println("drove forward");

		mover.turn90DegreesLeft(); // leg 4. moves beacon
		System.out.println("turned left");

		mover.shortLeg(); // leg 4. moves beacon
		System.out.println("drove forward");

		mover.turn90DegreesLeft(); // leg 5. moves beacon
		System.out.println("turned left");

		mover.shortLeg(); // leg 5. moves beacon
		System.out.println("last leg");
		
		mover.turn180Degrees(); // leg 3. moves beacon
		System.out.println("turned 180 degrees");
		
		mover.shortLeg(); // leg 5. moves beacon
		System.out.println("last leg");
		
		mover.turn90DegreesLeft(); // leg 5. moves beacon
		System.out.println("turned left");

		mover.shortLeg(); // leg 5. moves beacon
		System.out.println("last leg");
	
		mover.stopMoving(); // stops motor

		Sound.beepSequence(); // finished grabBeacon method.

		arm.liftDown(); // puts beacon down
		System.out.println("put down");
	}
}