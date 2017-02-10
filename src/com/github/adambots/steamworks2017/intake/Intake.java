package com.github.adambots.steamworks2017.intake;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants; 

public class Intake {
	
	//variables for this class
	static boolean intakeDisabled = true;		//checks if intake has been enabled yet
	static boolean intakeButtonReleased;		//checks to see if the button for enabling intake has been released
	static boolean intakeSafetyButtonReleased;	//checks to see if the safety has been released to prevent double counts	
	static boolean intakeSafety = true; 		//prevents running intake out of robot
	static int counter = Constants.COUNTER_START;//to check if the button has been pressed enough times to release safety
	
	public static void intakeSafety(boolean intakeSafetyButton){

		if(intakeSafetyButton){
			counter++;
			intakeSafetyButtonReleased = false;
		}else{
			intakeSafetyButtonReleased = true;
		}
		if(counter == Constants.COUNTER_END){
			counter = Constants.COUNTER_START;
			intakeSafety = !intakeSafety;
		}
	}
	/*
	 * Runs intakeMotor
	 * @Param intakeButon
	 * */
	public static void intake(boolean intakeButton){
		
		if(!intakeButton){
			//only runs if button is released
			intakeButtonReleased = true;
		}
		
		if (Actuators.getFuelIntakeMotor().get() == Constants.MOTOR_STOP && intakeButton && intakeButtonReleased){
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_START_VALUE);
			intakeButtonReleased = false;
			intakeDisabled = false;
		} else if(intakeButton && intakeButtonReleased){
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_STOP);
			intakeButtonReleased = false;
			intakeDisabled = true;
		}
	}
	/*
	 * Changes Speed of Intake Motor
	 * @Param speed
	 */
	//TODO: Check Direction of motor
	public static void intakeSpeed(double speed){
		//increases motor speed
		double motorSpeed;
		if(!intakeDisabled){
			if(speed <= Constants.STICK_HALF_PRESSED_UP &&
					Constants.MOTOR_STOP < Math.abs(Actuators.getFuelIntakeMotor().get()) &&
					Actuators.getFuelIntakeMotor().get() < Constants.MAX_MOTOR_SPEED){
				//Increments motor speed by a set value while stick is more than 50% pressed
				motorSpeed = Actuators.getFuelIntakeMotor().get() + Constants.MOTOR_INCREMENT;
				Actuators.getFuelIntakeMotor().set(motorSpeed);
				
				
			}//decreases motor speed
			else if(speed >= Constants.STICK_HALF_PRESSED_DOWN &&
					Constants.MOTOR_STOP < Math.abs(Actuators.getFuelIntakeMotor().get()) &&
					Actuators.getFuelIntakeMotor().get() < Constants.MAX_MOTOR_SPEED){
				//Increments motor speed by a set value while stick is more than 50% pressed
				motorSpeed = Actuators.getFuelIntakeMotor().get() - Constants.MOTOR_INCREMENT;
				Actuators.getFuelIntakeMotor().set(motorSpeed);
			}
		}
	}
	/*
	 * Changes direction of Intake motor
	 * @Param direction
	 */
	//TODO: Check direction of motor, switch the true and false if needed
	public static void intakeDirection(double direction){
		if(!intakeDisabled){
			//reverses direction, if needed
			if(direction <= Constants.STICK_HALF_PRESSED_LEFT && !Actuators.getFuelIntakeMotor().getInverted()){
				Actuators.getFuelIntakeMotor().setInverted(true);
				
			}else if(direction >= Constants.STICK_HALF_PRESSED_RIGHT && Actuators.getFuelIntakeMotor().getInverted()
					&& !intakeSafety ){
				//won't allow running the motor to eject balls unless safety has been released
				Actuators.getFuelIntakeMotor().setInverted(false);
			}
		}
	}
	
	public static void intakeJam(boolean intakeJamButton){
		if(!intakeDisabled){
			
		}
	}
}