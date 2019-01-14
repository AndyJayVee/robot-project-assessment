package models;

public class MoveBeacon {

	private LiftArm arm = new LiftArm();

	// no args constructor
	public MoveBeacon() {
		super();
	}

	// method to grab the beacon and put it down at the opposite side
	public void grabBeacon() {
		
		arm.liftUp(); // lifts beacon up
		System.out.println("lifted up");
		arm.moveBeacon(); // moves beacon according to LiftArm.java specifications
		System.out.println("moved beacon");
		arm.liftDown(); // puts beacon down
		System.out.println("put down");
	}
}