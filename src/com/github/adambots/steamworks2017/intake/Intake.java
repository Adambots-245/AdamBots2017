package com.github.adambots.steamworks2017.intake;

import org.usfirst.frc.team245.robot.Actuators;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Intake {
	
	//TODO: Set MAX_CURRENT to a number once we get an exact value
	public static double MAX_CURRENT;
	
	/*
	 * Runs intakeMotor
	 * @Param intakeButon
	 * */
	public static void intake(boolean intakeButton){
		if (intakeButton == true){
			Actuators.getIntakeMotor().set(1);
		} else {
			Actuators.getIntakeMotor().set(0);
		}
		currentCheck();
	}
	
	/*
	 * Checks current and stops motor if there is a current spike
	 * */
	public static void currentCheck(){
		if (Actuators.getIntakeMotor().getOutputCurrent() >= MAX_CURRENT){
			Actuators.getIntakeMotor().set(0);
			
		}
	}
}
