package models;

public class Mover extends Driving implements Runnable{
	
	private float currentGray;
	private boolean stopMoving;
	private static float borderBright;
	private static float borderDark;

	
	
	
	public Mover(float currentGray) {
		super();
		this.currentGray = currentGray;
	}



	public Mover(float currentGray, boolean stopMoving) {
		super();
		this.currentGray = currentGray;
		this.stopMoving = stopMoving;
	}

	

	public static void setBorderBright(float borderBright) {
		Mover.borderBright = borderBright;
	}



	public static void setBorderDark(float borderDark) {
		Mover.borderDark = borderDark;
	}



	public void setCurrentGray(float currentGray) {
		this.currentGray = currentGray;
	}


	public void setStopMoving(boolean stopMoving) {
		this.stopMoving = stopMoving;
	}


	@Override
	public void run() {
		try {
			while (!stopMoving) {
			if (currentGray> borderBright) {
				pilot.rotateRight();
			}
			else if (currentGray< borderDark) {
				pilot.rotateLeft();
			} else
				pilot.backward();
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
