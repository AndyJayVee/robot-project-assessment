package extraCodeFrank;


import models.Navigator;

public class Calibrator extends Navigator implements Runnable {

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
			pilot.rotate(30);
			while (!stopCalibrating) {
				pilot.setAngularSpeed(360);
				pilot.setLinearSpeed(360);
				pilot.rotate(-120);
				pilot.rotate(120);
				pilot.travel(50);
			}
		} catch (Exception e) {
			System.out.printf("Oops, moving crashed");
		}
	}
}
