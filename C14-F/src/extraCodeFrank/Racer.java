package extraCodeFrank;

import models.Navigator;
import extraCodeFrank.Racer;

public class Racer extends Navigator implements Runnable{
	
	private float currentGray;
	private boolean race;
	private static float borderBright;
	private static float borderDark;

	
	
	
	public Racer(float currentGray) {
		super();
		this.currentGray = currentGray;
	}


	public Racer(float currentGray, boolean race) {
		super();
		this.currentGray = currentGray;
		this.race = race;
	}

	

	public static void setBorderBright(float borderBright) {
		Racer.borderBright = borderBright;
	}



	public static void setBorderDark(float borderDark) {
		Racer.borderDark = borderDark;
	}



	public void setCurrentGray(float currentGray) {
		this.currentGray = currentGray;
	}


	public void setRacing(boolean race) {
		this.race = race;
	}


	@Override
	public void run() {
		try {
			while (race) {
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
