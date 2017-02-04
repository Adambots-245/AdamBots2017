package com.github.adambots.steamworks2017.intake;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	
	//TODO: Set MAX_CURRENT to a number once we get an exact value
	public static double MAX_CURRENT;
	public static double SPEED = 0.8;
	
	/*
	 * Runs intakeMotor
	 * @Param intakeButon
	 * */
	public static void intake(boolean intakeButton){
		if (intakeButton){
			Actuators.getIntakeMotor().set(SPEED);
		} else {
			Actuators.getIntakeMotor().set(Constants.MOTOR_STOP);
		}
		currentCheck();
	}
	
	/*
	 * Checks current and stops motor if there is a current spike
	 * */
	public static void currentCheck(){
		if (Actuators.getIntakeMotor().getOutputCurrent() >= MAX_CURRENT){
			SmartDashboard.putBoolean("Current spike: ", true);
		} else {
			SmartDashboard.putBoolean("Current spike: ", false);
		}
	}
}