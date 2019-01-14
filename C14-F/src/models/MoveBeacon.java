package models;
import models.Driving;
//import models.Pilot;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();
	
	private static int driveStraight = -100;

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon and put it down at the opposite side
	public void grabBeacon() {
		Driving drive = new Driving();
//		Pilot pilot = new Pilot();
		
		arm.liftUp(); // lifts beacon up
		System.out.println("lifted up");
		drive.turn(40); // rotates 180 degrees
		System.out.println("rotated 180 degrees");
		drive.straight(driveStraight); // drives straight for a distance of 100 mm
		//System.out.println("drove 100");
		arm.liftDown(); // puts beacon down
		System.out.println("put down");
	}
}