package models;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon and put it down at a programmed location via LiftArm.java
	public void grabBeacon() {
        Button.LEDPattern(4);    // flash green led and 
        Sound.beepSequenceUp();  // make sound when ready.
		arm.liftUp(); // lifts beacon up
		System.out.println("lifted up");
		arm.driveWithBeacon(); // moves beacon (according to LiftArm.java specifications)
		System.out.println("moved beacon to other location");
		arm.liftDown(); // puts beacon down
		System.out.println("put down");
		Sound.beepSequence(); // finished grabBeacon method.
	}
}