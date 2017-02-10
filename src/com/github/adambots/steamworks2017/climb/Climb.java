package com.github.adambots.steamworks2017.climb;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;


public class Climb {
	static boolean climbSafetyPrimary = true;
	static boolean climbSafetySecondary = true;
	static boolean climbSafetyPrimaryReleased;
	static boolean climbSafetySecondaryReleased;
	
	//Will only allow Primary to toggle the motor off if two button safety is pressed to toggle safety off
	public static void climbSafetyTogglePrimary(boolean climbSafetyButton1, boolean climbSafetyButton2){
		if(!climbSafetyButton1 && !climbSafetyButton2){
			climbSafetyPrimaryReleased = true;
		}
		if(climbSafetyButton1 && climbSafetyButton2 && climbSafetyPrimaryReleased){
			climbSafetyPrimary = !climbSafetyPrimary;
			climbSafetyPrimaryReleased = false;
		}
		
	}
	
	public static void climbSafetyToggleSecondary(boolean climbSafetyButton1, boolean climbSafetyButton2){
		if(!climbSafetyButton1 && !climbSafetyButton2){
			climbSafetySecondaryReleased = true;
		}
		if(climbSafetyButton1 && climbSafetyButton2 && climbSafetySecondaryReleased){
			climbSafetySecondary = !climbSafetySecondary;
			climbSafetySecondaryReleased = false;
		}
	}
	
	public static void climbStartSecondary(boolean climbButton){
		//if the button is pressed, then the motor will be set to a speed and will run
		//will only run if climbSafetySecondary is toggled to false
		if(climbButton && !climbSafetySecondary && !climbSafetySecondary){
			Actuators.getClimbMotor().set(Constants.CLIMB_MOTOR_SPEED);
		}
	}
	
	//TODO: Make motor stop automatically if limit switch gets tripped
	public static void climbStopPrimary(boolean climbButton){
		//if the button is pressed, then the motor will stop
		if(climbButton && !climbSafetyPrimary){
			Actuators.getClimbMotor().set(Constants.MOTOR_STOP);
		}
	}
	public static void climbStopSecondary(boolean climbButton){
		
		if(climbButton && !climbSafetySecondary){
			Actuators.getClimbMotor().set(Constants.MOTOR_STOP);
		}
	}
}
