package models;

public class Calibrator extends Driving implements Runnable {

	private boolean calibrate = true;
	
	public Calibrator(boolean calibrate) {
		super();
		this.calibrate = calibrate;
	}

	public void setCalibrate(boolean calibrate) {
		this.calibrate = calibrate;
	}

	@Override
	public void run() {
		try {
			while (calibrate = true) {
				pilot.rotate(360);
				pilot.travel(30);
			}
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
