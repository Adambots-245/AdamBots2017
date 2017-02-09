package com.github.adambots.steamworks2017.smartDash;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Sensors;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Dash {
	public void driveMode(){
	/*
	 * SmartDash
	 */
	SmartDashboard.putBoolean("Is Climbing:", TalonDio.climbEncodDio(Actuators.getClimbMotor()));
	SmartDashboard.putBoolean("Is Driving:", TalonDio.driveEncodDio(Actuators.getLeftDriveMotor(), Actuators.getRightDriveMotor()));
	SmartDashboard.putBoolean("Is Climbing Motor Stalling:", TalonDio.CIMStall(Actuators.getClimbMotor()));
	SmartDashboard.putNumber("Total Current Draw:", SensorsDio.PDPCurrent(Sensors.getPowerDistro()));
	
	
	//TODO: Put in rumble code for robot
	
	}
}
