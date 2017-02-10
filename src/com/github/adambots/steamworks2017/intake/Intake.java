package com.github.adambots.steamworks2017.intake;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Intake {
	
	//variables for this class
	static boolean intakeDisabled = true;
	static boolean buttonReleased;
	/*
	 * Runs intakeMotor
	 * @Param intakeButon
	 * */
	public static void intake(boolean intakeButton){
		
		if(!intakeButton){
			//only runs if button is released
			buttonReleased = true;
		}
		
		if (Actuators.getFuelIntakeMotor().get() == Constants.MOTOR_STOP && intakeButton && buttonReleased){
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_START_VALUE);
			buttonReleased = false;
			intakeDisabled = false;
		} else if(intakeButton && buttonReleased){
			Actuators.getFuelIntakeMotor().set(Constants.MOTOR_STOP);
			buttonReleased = false;
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
				
			}else if(direction >= Constants.STICK_HALF_PRESSED_RIGHT && Actuators.getFuelIntakeMotor().getInverted()){
				Actuators.getFuelIntakeMotor().setInverted(false);
			}
		}
	}
	
	public static void intakeJam(boolean intakeJamButton){
		
	}
}