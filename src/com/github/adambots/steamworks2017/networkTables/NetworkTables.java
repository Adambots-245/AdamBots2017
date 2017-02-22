package com.github.adambots.steamworks2017.networkTables;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTables {
	private static NetworkTable controlsTable;
	private static NetworkTable visionTable;
	
	public static void init(){
		controlsTable = NetworkTable.getTable("Controls");
		visionTable = NetworkTable.getTable("VisionTable");
	}

	public static NetworkTable getControlsTable() {
		return controlsTable;
	}

	public static NetworkTable getVisionTable() {
		return visionTable;
	}
}
