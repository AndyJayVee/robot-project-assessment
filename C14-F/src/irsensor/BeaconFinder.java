package irsensor;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;

/**
 * Requires a wheeled vehicle with two independently controlled motors connected
 * to motor ports B and C, and an EV3 IR sensor connected to port 4.
 * 
 * @author Lawrie Griffiths / Leo Snel
 * 
 *         * Sample providers use standard units.<br>
 *         <ul>
 *         <li>Length in meters</li>
 *         <li>Angle in degrees</li>
 * 
 *         * When there is no clear unit a sample provider use normalized values
 *         in the range between 0 and 1.</li>
 *         <li>Sample providers that measure spatial data use a right handed
 *         cartesian coordinate system with the X-axis pointing forwards, the
 *         Y-axis pointing to the left and the Z-axis pointing up. (The plug of
 *         a sensor is always on its back.)</li>
 *         <li>A positive rotation of a mobile robot is a counterclockwise
 *         rotation.</li>
 *         <li>If a sample provider measures spatial data over more than one
 *         axis, the order of the elements in a sample corresponds with the X,Y
 *         and Z axis.
 * 
 *         <b>EV3 Infra Red sensor, Seek mode</b><br>
 *         In seek mode the sensor locates up to four beacons and provides
 *         bearing and distance of each beacon.
 * 
 *         <b>Size and content of the sample</b><br>
 *         The sample contains four pairs of elements in a single sample. Each
 *         pair gives bearing of and distance to the beacon. The first pair of
 *         elements is associated with a beacon transmitting on channel 0, the
 *         second pair with a beacon transmitting on channel 1 etc.<br>
 *         The bearing values range from -25 to +25 (with values increasing
 *         clockwise when looking from behind the sensor). A bearing of 0
 *         indicates the beacon is directly in front of the sensor. <br>
 *         Distance values are not to scale. An increasing value indicate
 *         increasing distance. <br>
 *         If no beacon is detected both bearing is set to zero, and distance to
 *         positive infinity.
 * 
 *         <b>EV3 Infra Red sensor, Distance mode</b><br>
 *         Measures the distance to an object in front of the sensor.
 * 
 *         <b>Size and content of the sample</b><br>
 *         The sample contains one element giving the distance to an object in
 *         front of the sensor. The distance provided is very roughly equivalent
 *         to meters but needs conversion to give better distance. See product
 *         page for details. <br>
 *         The effective range of the sensor in Distance mode is about 5 to 50
 *         centimeters. Outside this range a zero is returned for low values and
 *         positive infinity for high values.
 */

public class BeaconFinder {

	protected static final int BEARING_ZERO = 0;

	public static void introMessage() {

		GraphicsLCD g = LocalEV3.get().getGraphicsLCD(); // generic initiation message
		g.drawString("Beacon Finder", 5, 0, 0);
		g.setFont(Font.getSmallFont());
		g.drawString("Team Foxtrot", 2, 20, 0);

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

		// Driving.setBearing(direction);
		// roomba feature
		// als in bereik van sensor
		// pas bearing aan tot 0 graden
		// recht op de meid

		while (seek.sampleSize() > 0) {
			seek.fetchSample(sample, 0);
			int direction = (int) sample[0];

			if (direction > 0) {
				turn(-5);
			} else if (direction < 0) {
				turn(5);
			} else {
				if (distance < Integer.MAX_VALUE) {
					straight();
				} else {
					stop(true);
				}
			}
			turn(seek.sampleSize());
		}

		straight(seek.fetchSample(sample, BEARING_ZERO));

		ir.close();
	}

	// voorbeeld code:
	// Get sample from infrared sensor set in <b>seek mode</b>.
	//
	// @param sensorPort on which the infrared sensor is connected
	// @return array of size 7
	//
	// public synchronized ArrayList<Float> getInfraredSensorSeek(SensorPort
	// sensorPort) {
//	    SampleProvider sampleProvider = this.deviceHandler.getProvider(sensorPort, InfraredSensorMode.SEEK.getValues()[0]);
//	    float[] sample = new float[sampleProvider.sampleSize()];
//	    sampleProvider.fetchSample(sample, 0);
//	    ArrayList<Float> result = new ArrayList<>();
//	    result.add((float) Math.round(sample[0]));
//	    result.add((float) Math.round(sample[1]));
//	    result.add((float) Math.round(sample[2]));
//	    result.add((float) Math.round(sample[3]));
//	    result.add((float) Math.round(sample[4]));
//	    result.add((float) Math.round(sample[5]));
//	    result.add((float) Math.round(sample[6]));
//	    result.add((float) Math.round(sample[7]));
//	    return result;
//}
}