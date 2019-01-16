package models;

public class Mover extends Driving implements Runnable{
	
	private float shadeOfGray;

	public void setShadeOfGray(float shadeOfGray) {
		this.shadeOfGray = shadeOfGray;
	}

	public Mover(float shadeOfGray) {
		super();
		this.shadeOfGray = shadeOfGray;
	}

	@Override
	public void run() {
		try {
			if (shadeOfGray> .60) {
				pilot.rotateRight();
			}
			else if (shadeOfGray< .30) {
				pilot.rotateLeft();
			} else
				pilot.backward();
				
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
