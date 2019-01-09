package models;

import lejos.robotics.navigation.MovePilot;

public class Driving {
	
	private int travelDistance;
	private int rotation;
	
	MovePilot pilot;
	public Driving (MovePilot p) {
		pilot = p;
	}
	
	public int getTravelDistance() {
		return travelDistance;
	}
	public void setTravelDistance(int travelDistance) {
		this.travelDistance = travelDistance;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotate) {
		this.rotation = rotate;
	}
	
	//Methode om de robot te laten rijden en een bochtje te laten maken zoals
	//bijvoorbeeld bij een vierkant.
		public void travelAndRotate() {
			pilot.travel(travelDistance);
			pilot.rotate(rotation);
			pilot.arc(10, 14);
		}
		
	
		
}
