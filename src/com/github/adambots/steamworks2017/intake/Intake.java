package com.github.adambots.steamworks2017.intake;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Intake {
	
	//variables for this class
	static boolean intakeDisabled = true;
	
	/*
	 * Runs intakeMotor
	 * @Param intakeButon
	 * */
	public static void intake(boolean intakeButton, boolean buttonReleased){
		if (Actuators.getFuelIntakeMotor().get() == Constants.MOTOR_STOP &&intakeButton && buttonReleased){
			Actuators.getFuelIntakeMotor().set(Constants.INTAKE_START_VALUE);
			buttonReleased = false;
		} else if(intakeButton && buttonReleased){
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_STOP);
			buttonReleased = false;
		}
	}
	/*
	 * Changes Speed of Intake Motor
	 * @Param speed
	 */
	public static void intakeSpeed(double speed){
		if(!intakeDisabled && speed >= Constants.STICK_HALF_PRESSED &&
				Actuators.getFuelIntakeMotor().get() < Constants.MAX_MOTOR_SPEED){
			
			
		}
	}
	/*
	 * Changes direction of Intake motor
	 * @Param direction
	 */
	public static void intakeDirection(double direction){
		
	}
}