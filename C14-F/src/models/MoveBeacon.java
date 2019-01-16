package models;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import models.Music;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();
	private MarvinMover mover = new MarvinMover();
	private Music music = new Music();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon, move it in an F-shape, and put it down
	public void grabBeacon() {
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.
		
		arm.liftUp();
		System.out.println("lift beacon up");

		mover.longLeg();
		System.out.println("F-shape drive from point 1 to point 2");

		mover.turn90DegreesClockwise();
		System.out.println("F-shape turn from point 2 to point 3");

		mover.shortLeg();
		System.out.println("F-shape drive from point 2 to point 3");

		mover.turn180DegreesClockwise();
		System.out.println("F-shape turn from point 3 to point 2");

		mover.shortLeg();
		System.out.println("F-shape drive from point 3 to point 2");

		mover.turn90DegreesCounterClockwise();
		System.out.println("F-shape turn from point 2 to point 4");

		mover.shortLeg();
		System.out.println("F-shape drive from point 2 to point 4");

		mover.turn90DegreesCounterClockwise();
		System.out.println("F-shape turn from point 4 to point 5");

		mover.shortLeg();
		System.out.println("F-shape drive from point 4 to point 5");
		
		mover.turn180DegreesClockwise();
		System.out.println("F-shape turn from point 5 to point 4");
		
		mover.shortLeg();
		System.out.println("F-shape drive from point 5 to point 4");
		
		mover.turn90DegreesCounterClockwise();
		System.out.println("F-shape turn from point 4 to point 1");

		mover.shortLeg();
		System.out.println("F-shape drive from point 4 to point 1");
		
		mover.stopMoving();
		System.out.println("stop moving");

		music.playStarWars(); // finished grabBeacon method.

		arm.liftDown();
		System.out.println("put beacon down");
		
		mover.shortBack();
		System.out.println("backing up");
		
		System.exit(0);
	}
}