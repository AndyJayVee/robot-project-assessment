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

		try {
			while (repeatAfterfinish) {
				currentGame = menu.chooseGame();
				if (currentGame.equals("linefollower")) {
					LineFollower lineFollower = new LineFollower();
					lineFollower.followLine();
//				                               "123456789987654321" ruler comment
					currentGame = menu.gameStopped("   Line follower  ");
				} else if (currentGame.equals("beaconfinder")) {
					BeaconFinder beaconFinder = new BeaconFinder();
					beaconFinder.findBeacon();
//                                             "123456789987654321" ruler comment
					currentGame = menu.gameStopped("   Beacon finder  ");
				} else if (currentGame.equals("movebeacon")) {
					MoveBeacon moveBeacon = new MoveBeacon();
					moveBeacon.grabBeacon();
//                                             "123456789987654321" ruler comment
					currentGame = menu.gameStopped("    Move beacon   ");
				} else if (currentGame.equals("nogame")) {
					currentGame = menu.gameStopped("All programs");
				}
				if (currentGame.equals("repeat")) {
					repeatAfterfinish = true;
				} else
					repeatAfterfinish = false;
			}
		} catch (Exception e) {
			menu.gameStopped("   Error occured, ");
		}

	}
}
