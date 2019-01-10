
package irsensor;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import models.Driving;

/**
 * Requires a wheeled vehicle with two independently controlled motors connected
 * to motor ports B and C, and an EV3 IR sensor connected to port 4.
 * 
 * @author Lawrie Griffiths
 */
public class BeaconFinder {

	private static final int MAXIMUM_RANGE_IR_SENSOR = 75; // eventueel aanpassen
	private boolean beaconFound; // activeert het break point in de methode roam

	/**
	 * @return the beaconFound
	 */
	public boolean isBeaconFound() {
		return beaconFound;
	}

	/**
	 * @param beaconFound the beaconFound to set
	 */
	public void setBeaconFound(boolean beaconFound) {
		this.beaconFound = beaconFound;
	}

	public static void introMessage() {

		GraphicsLCD g = LocalEV3.get().getGraphicsLCD(); // generic initiation message
		g.drawString("Follow Beacon Demo", 5, 0, 0);
		g.setFont(Font.getSmallFont());
		g.drawString("Demonstration of IR Beacon", 2, 20, 0);
		g.drawString("seek mode. Requires", 2, 30, 0);
		g.drawString("a wheeled vehicle with two", 2, 40, 0);
		g.drawString("independently controlled", 2, 50, 0);
		g.drawString("motors connected to motor", 2, 60, 0);
		g.drawString("ports B and C, and an", 2, 70, 0);
		g.drawString("infrared sensor connected", 2, 80, 0);
		g.drawString("to port 4.", 2, 90, 0);

		// Quit GUI button:
		g.setFont(Font.getSmallFont()); // can also get specific size using Font.getFont()
		int y_quit = 100;
		int width_quit = 45;
		int height_quit = width_quit / 2;
		int arc_diam = 6;
		g.drawString("QUIT", 9, y_quit + 7, 0);
		g.drawLine(0, y_quit, 45, y_quit); // top line
		g.drawLine(0, y_quit, 0, y_quit + height_quit - arc_diam / 2); // left line
		g.drawLine(width_quit, y_quit, width_quit, y_quit + height_quit / 2); // right line
		g.drawLine(0 + arc_diam / 2, y_quit + height_quit, width_quit - 10, y_quit + height_quit); // bottom line
		g.drawLine(width_quit - 10, y_quit + height_quit, width_quit, y_quit + height_quit / 2); // diagonal
		g.drawArc(0, y_quit + height_quit - arc_diam, arc_diam, arc_diam, 180, 90);

		// Enter GUI button:
		g.fillRect(width_quit + 10, y_quit, height_quit, height_quit);
		g.drawString("GO", width_quit + 15, y_quit + 7, 0, true);

		Button.waitForAnyPress();
		if (Button.ESCAPE.isDown())
			System.exit(0);
		g.clear();
	}

	public static void main(String[] args) {

		introMessage();

		EV3IRSensor ir = new EV3IRSensor(SensorPort.S4); // activeert een nieuwe IR-sensor op poort S4
		SensorMode seek = ir.getSeekMode(); // activeert de Seek modus
		float[] sample = new float[seek.sampleSize()]; // maakt array met sample informatie

		while (Button.ESCAPE.isUp()) {
			seek.fetchSample(sample, 0);
			int bearing = (int) sample[0];
			System.out.println("Bearing: " + bearing);
			int distance = (int) sample[1];
			System.out.println("Distance: " + distance);

			if (distance > Integer.MAX_VALUE) { // als het beacon out of range is
				setBeaconFound(false); // beaconFound false
				Driving.roam; // begin met roam
			} else if (distance < MAXIMUM_RANGE_IR_SENSOR) { // maximum range IR sensor
				setBeaconFound(true); // beaconFound true
				Driving.straight; // begin met straight
			}

			if (bearing != 0) { // als het beacon niet recht voor de sensor is
				Driving.turn(bearing); // gaat de robot in de richting van het beacon roteren met "direction" graden
			} else if (bearing == 0) { // als het beacon recht voor de sensor is
				Driving.straight(distance); // gaat de robot rechtuit rijden gedurende afstand "distance"
			}
			ir.close();
		}

	}

}