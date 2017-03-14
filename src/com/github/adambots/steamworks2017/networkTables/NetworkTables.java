package com.github.adambots.steamworks2017.networkTables;


import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTables {
	private static NetworkTable controlsTable;
	private static NetworkTable visionTable;
	private static boolean toggleReleased = true;
	
	public static void init(){
		controlsTable = NetworkTable.getTable("Controls");
		controlsTable.putBoolean("auton", false);
		controlsTable.putString("stream", "front");
		visionTable = NetworkTable.getTable("VisionTable");
	}
	
	public static void putStream(boolean toggleButton){
		if(!toggleButton){
			//this only runs if button is released
			toggleReleased = true;
		}
		if(toggleButton && controlsTable.getString("stream", "broken").equals("front") && toggleReleased){	
			controlsTable.putString("stream", "back");
			toggleReleased = false;
		} else if (toggleButton && controlsTable.getString("stream", "broken").equals("back") && toggleReleased){
			controlsTable.putString("stream", "front"); 
			toggleReleased = false;
		}
	}

	public static NetworkTable getControlsTable() {
		return controlsTable;
	}

	public static NetworkTable getVisionTable() {
		return visionTable;
	}
}
