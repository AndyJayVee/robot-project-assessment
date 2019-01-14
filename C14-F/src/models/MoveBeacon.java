package models;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();
	//private Driving drive = new Driving();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon and put it down at the opposite side
	public void grabBeacon() {
		arm.liftUp(); // lifts beacon up
//		drive.setRotation(180); // rotates 180 degrees
//		drive.straight(25); // drives straight for a distance of 25
		System.out.println("lifted up");
		arm.liftDown(); // puts beacon down
		System.out.println("put down");
	}
}
