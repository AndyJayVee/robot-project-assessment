package models;

public class Calibrator extends Driving implements Runnable {

	private boolean stopCalibrating = true;
	
	public Calibrator(boolean stopCalibrating) {
		super();
		this.stopCalibrating = stopCalibrating;
	}

	public void setStopCalibrate(boolean stopCalibrating) {
		this.stopCalibrating = stopCalibrating;
	}

	@Override
	public void run() {
		try {
			pilot.rotate(45);
			while (!stopCalibrating) {
				pilot.setAngularSpeed(360);
				pilot.setLinearSpeed(360);
				pilot.rotate(-45);
				pilot.rotate(45);
				pilot.travel(20);
				System.out.println("calibreren");
			}
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
