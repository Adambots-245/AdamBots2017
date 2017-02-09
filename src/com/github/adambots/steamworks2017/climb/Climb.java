package com.github.adambots.steamworks2017.climb;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;


public class Climb {
	
	//TODO: Add so it takes 2 seconds to enable the first time
	public static void climbStart(boolean climbButton){
		//if the button is pressed, then the motor will be set to a speed and will run
		if(climbButton){
			Actuators.getClimbMotor().set(Constants.CLIMB_MOTOR_SPEED);
		}
	}
	//TODO: Add so it takes primary 2 seconds to disable
	public static void climbStop(boolean climbButton){
		//if the button is pressed, then the motor will stop
		if(climbButton){
			Actuators.getClimbMotor().set(Constants.MOTOR_STOP);
		}
	}
}
