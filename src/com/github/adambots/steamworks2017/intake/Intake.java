package com.github.adambots.steamworks2017.intake;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	

	
	/*
	 * Runs intakeMotor
	 * @Param intakeButon
	 * */
	public static void intake(boolean intakeButton){
		if (intakeButton){
			Actuators.getFuelIntakeMotor().set(Constants.CLIMB_MOTOR_SPEED);
		} else {
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_STOP);
		}
	}
}