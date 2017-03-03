package com.github.adambots.steamworks2017.smartDash;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;
import org.usfirst.frc.team245.robot.Sensors;


public class Dash {
	
	
	public static void driveMode(){
		
		
	/*
	 * SmartDash
	 */
	//TODO: Add more 

		SmartDashboard.putBoolean("High/Low Gear:", Actuators.getDriveShiftPneumatic().get());
		SmartDashboard.putBoolean("Climb motor running:", TalonDio.climbEncodDio(Actuators.getClimbMotor()));
		SmartDashboard.putBoolean("Is Climbing Motor Stalling:", TalonDio.CIMStall(Actuators.getClimbMotor()));
		SmartDashboard.putBoolean("Is Climbing:", TalonDio.climbEncodDio(Actuators.getClimbMotor()));
		SmartDashboard.putNumber("Total Current Draw:", SensorsDio.PDPCurrent(Sensors.getPowerDistro()));
		SmartDashboard.putNumber("Time remaining:", DriverStation.getInstance().getMatchTime());
//		SmartDashboard.putBoolean("Is Driving:", TalonDio.driveEncodDio(Actuators.getLeftDriveMotor(), Actuators.getRightDriveMotor()));
//		SmartDashboard.putBoolean("Is Intake Disabled:", Intake.intakeDisabled);
//		SmartDashboard.putNumber("motorSpeed", Intake.intakeMotorSpeed);
//		SmartDashboard.putNumber("conveyorMotorSpeed", Score.conveyorMotorSpeed);
//		SmartDashboard.putBoolean("Intake Motor Inverted?:", Actuators.getFuelIntakeMotor().getInverted());

	
	
	//Vibration Feedback
		//Sets the Secondary to vibrate if climbing motor is going to stall
		Vibrations.climbStallVibrate(Constants.MAX_RUMBLE);	
		//If within the second of TIME_RUMBLE then both controllers are set to HALF_RUMBLE
		Vibrations.timeLeftVibrate(Constants.TIME_RUMBLE_VALUE, Constants.TIME_RUMBLE);	
		
	}
}
