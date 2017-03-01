package com.github.adambots.steamworks2017.auton;

import org.usfirst.frc.team245.robot.Actuators;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Gear {

	private static final double THRESHOLD_HORZ = 150;
	private static final double THRESHOLD_AREA = 15000;
	private static final double GOAL_X = 100; //change
	private static NetworkTable visionTable;
	
	
	public static void main(String[] args) {

		visionTable = NetworkTable.getTable("VisionTable");
		double baseSpeed = 0.5;
		double speed = 0;
		double errorX = THRESHOLD_HORZ - visionTable.getNumber('boxes_midpoint');
		double errorArea = THRESHOLD_AREA - visionTable.getNumber('area');
		void setPID(double p, double i, double d);
		
		if (errorX > 0 && Math.abs(errorX) > THRESHOLD_HORZ) {
			
			speed = baseSpeed + (getP() * errorX);
			Actuators.getLeftDriveMotor().set(speed);
			Actuators.getRightDriveMotor().set(-speed);
			
			
		} else if (errorX < 0 && Math.abs(errorX) > THRESHOLD_HORZ) {
			
			speed = baseSpeed + (getP() * errorX);
			Actuators.getLeftDriveMotor().set(-speed);
			Actuators.getRightDriveMotor().set(speed);
		}
		
		if (Math.abs(errorX) <= THRESHOLD_HORZ) {
			
			if(area < THRESHOLD_AREA) {
				
				speed = baseSpeed + (getP() * errorArea);
				Actuators.getLeftDriveMotor().set(speed);
				Actuators.getRightDriveMotor().set(speed);
				
			} else {
				
				// go forward for a couple feet to execute gear drop off;
				Actuators.getLeftDriveMotor().set(speed); // for specific amount of time
				Actuators.getRightDriveMotor().set(speed); // for specific amount of time
				Actuators.getDispenseGearPneumatic().set(true);
				
			}		
		}
	}
}
