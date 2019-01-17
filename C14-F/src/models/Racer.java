package models;

import models.Driving;
import models.Racer;

public class Racer extends Driving implements Runnable{
	
	private float schadeOfGray;
	private boolean race;
	private static float borderBright;
	private static float borderDark;

	
	public Racer(float shadeOfGray) {
		super();
		this.schadeOfGray = shadeOfGray;
	}


	public Racer(float shadeOfGray, boolean race) {
		super();
		this.schadeOfGray = shadeOfGray;
		this.race = race;
	}

	

	public static void setBorderBright(float borderBright) {
		Racer.borderBright = borderBright;
	}



	public static void setBorderDark(float borderDark) {
		Racer.borderDark = borderDark;
	}



	public void setShadeOfGray(float shadeOfGray) {
		this.schadeOfGray = shadeOfGray;
	}


	public void setRacing(boolean race) {
		this.race = race;
	}


	@Override
	public void run() {
		try {
			while (race) {
			if (schadeOfGray > borderBright) {
				pilot.rotateRight();
			}
			else if (schadeOfGray < borderDark) {
				pilot.rotateLeft();
			} else
				pilot.backward();
			}
				
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
