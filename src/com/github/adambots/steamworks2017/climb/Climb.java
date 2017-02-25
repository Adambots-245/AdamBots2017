package com.github.adambots.steamworks2017.climb;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;


public class Climb {
	public static boolean climbSafetyPrimary = true;
	public static boolean climbSafetySecondary = true;
	public static boolean climbEnabled = true;
	private static boolean climbSafetyPrimaryReleased = true;
	private static boolean climbSafetySecondaryReleased = true;
	private static int counterPrimary = Constants.COUNTER_START;
	private static int counterSecondary = Constants.COUNTER_START;
	
	//Will only allow Primary to toggle the motor off if two button safety is pressed to toggle safety off
	public static void climbSafetyTogglePrimary(boolean climbSafetyButton1){
		if(climbSafetyButton1 && climbSafetyPrimaryReleased){
			counterPrimary++;
			climbSafetyPrimaryReleased = false;
		}else if(!climbSafetyButton1){
			climbSafetyPrimaryReleased = true;
		}
		if(counterPrimary == Constants.COUNTER_END){
			counterPrimary = Constants.COUNTER_START;
			climbSafetyPrimary = !climbSafetyPrimary;
		}
		
	}
	
	public static void climbSafetyToggleSecondary(boolean climbSafetyButton){
		if(climbSafetyButton && climbSafetySecondaryReleased){
			counterSecondary++;
			climbSafetySecondaryReleased = false;
		}else if(!climbSafetyButton){
			climbSafetySecondaryReleased = true;
		}
		if(counterSecondary == Constants.COUNTER_END){
			counterSecondary = Constants.COUNTER_START;
			climbSafetySecondary = !climbSafetySecondary;
		}
	}
	
	public static void climbStartSecondary(boolean climbButton){
		//if the button is pressed, then the motor will be set to a speed and will run
		//will only run if climbSafetySecondary is toggled to false
		//Disables all internal motors when climb button is pressed
		if(climbButton && !climbSafetySecondary){
			Actuators.getClimbMotor().set(Constants.CLIMB_MOTOR_SPEED);
			climbEnabled = false;
			Actuators.getFuelConveyorMotor().set(Constants.MOTOR_STOP);
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_STOP);
			Actuators.getFuelOuttakeMotor().set(Constants.MOTOR_STOP);
		}
		if(Actuators.getClimbMotor().get() > Constants.MOTOR_STOP){
			Actuators.getFuelConveyorMotor().set(Constants.MOTOR_STOP);
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_STOP);
			Actuators.getFuelOuttakeMotor().set(Constants.MOTOR_STOP);
		}
	}
	
	//TODO: Make motor stop automatically if limit switch gets tripped
	public static void climbStopPrimary(boolean climbButton){
		//if the button is pressed, then the motor will stop
		if(climbButton){
			Actuators.getClimbMotor().set(Constants.MOTOR_STOP);
			climbEnabled = true;
		}
	}
	
	public static void climbStopSecondary(boolean climbButton){
		if(climbButton){
			Actuators.getClimbMotor().set(Constants.MOTOR_STOP);
			climbEnabled = true;
			
	
		}
	}
}
