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
			pilot.rotate(30);
			while (calibrate) {
				pilot.setAngularSpeed(360);
				pilot.setLinearSpeed(360);
				pilot.rotate(-120);
				pilot.rotate(120);
				pilot.travel(30);
			}
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
