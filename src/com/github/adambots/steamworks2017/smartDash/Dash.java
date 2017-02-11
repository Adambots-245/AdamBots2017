package com.github.adambots.steamworks2017.smartDash;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;
import org.usfirst.frc.team245.robot.Sensors;


public class Dash {
	public void driveMode(){
	/*
	 * SmartDash
	 */
	//TODO: Add more 
		SmartDashboard.putBoolean("Is Climbing:", TalonDio.climbEncodDio(Actuators.getClimbMotor()));
		SmartDashboard.putBoolean("Is Driving:", TalonDio.driveEncodDio(Actuators.getLeftDriveMotor(), Actuators.getRightDriveMotor()));
		SmartDashboard.putBoolean("Is Climbing Motor Stalling:", TalonDio.CIMStall(Actuators.getClimbMotor()));
		SmartDashboard.putNumber("Total Current Draw:", SensorsDio.PDPCurrent(Sensors.getPowerDistro()));
	
	//TODO: Add Gear Vibrations for both controllers
	//Vibration Feedback
		//Sets the Secondary to vibrate if climbing motor is going to stall
		Vibrations.climbStallVibrate(Constants.MAX_RUMBLE);	
		//If within the second of TIME_RUMBLE then both controllers are set to HALF_RUMBLE
		Vibrations.timeLeftVibrate(Constants.HALF_RUMBLE, Constants.TIME_RUMBLE);		
	}
}
