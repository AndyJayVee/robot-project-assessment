package main;

import models.Menu;
import models.BeaconFinder;
import models.LineFollower;
import models.MoveBeacon;

public class GameLauncher {

	public GameLauncher() {
		super();
	}

	public static void main(String[] args) {

		GameLauncher gameLauncher = new GameLauncher();
		gameLauncher.launchGame();
	}

	private void launchGame() {

		String currentGame;
		Menu menu = new Menu();
		boolean repeatAfterfinish = true;
		while (repeatAfterfinish) {
			currentGame = menu.chooseGame();

			if (currentGame.equals("linefollower")) {
				LineFollower lineFollower = new LineFollower();
				lineFollower.followLine();
				currentGame = menu.gameStopped("Line follower");
			} else if (currentGame.equals("beaconfinder")) {
				BeaconFinder beaconFinder = new BeaconFinder();
				beaconFinder.findBeacon();
				currentGame = menu.gameStopped("Beacon finder");
			} else if (currentGame.equals("movebeacon")) {
				MoveBeacon moveBeacon = new MoveBeacon();
				moveBeacon.grabBeacon();
				currentGame = menu.gameStopped("Move beacon");
			} else if (currentGame.equals("nogame")) {
				currentGame = menu.gameStopped("All programs");
			} else
				repeatAfterfinish = false;

		}
	}
}
