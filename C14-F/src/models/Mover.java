package models;

public class Mover extends Driving implements Runnable{
	
	private float shadeOfGray;
	private boolean stopMoving;
	private static double borderBright;
	private static double borderDark;

	
	public Mover(float shadeOfGray, boolean stopMoving) {
		super();
		this.shadeOfGray = shadeOfGray;
		this.stopMoving = stopMoving;
	}

	

	public static void setBorderBright(double borderBright) {
		Mover.borderBright = borderBright;
	}



	public static void setBorderDark(double borderDark) {
		Mover.borderDark = borderDark;
	}



	public void setShadeOfGray(float shadeOfGray) {
		this.shadeOfGray = shadeOfGray;
	}


	public void setStopMoving(boolean stopMoving) {
		this.stopMoving = stopMoving;
	}


	@Override
	public void run() {
		try {
			while (!stopMoving) {
			if (shadeOfGray> borderBright) {
				pilot.rotateRight();
			}
			else if (shadeOfGray< borderDark) {
				pilot.rotateLeft();
			} else
				pilot.backward();
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
