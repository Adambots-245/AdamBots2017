package com.github.adambots.steamworks2017.score;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Score {
	
	static boolean gearButtonReleased;
	static boolean conveyorButtonReleased;
	static boolean outtakeButtonReleased;
	static boolean gearIsLocked = false;
	static double motorSpeed;
	static boolean conveyorInButtonReleased;
	
	
	/*
	 * Intake
	 */
	//TODO: Add Logic for when limit switches get tripped, the pneumatic stops.
	public static void dispenseGear(boolean gearButton){
		if(gearButton && !gearIsLocked){
			Actuators.getDispenseGearReturnPneumatic().set(false);
			Actuators.getDispenseGearAdvancePneumatic().set(true);
		}else if(!gearButton && !gearIsLocked){
			Actuators.getDispenseGearAdvancePneumatic().set(false);
			Actuators.getDispenseGearReturnPneumatic().set(true);
		}
	}
	
	//toggles if the gear system is locked in position or not
	public static void gearLock(boolean lock, boolean lock1){
		if(!lock && !lock1){
			gearButtonReleased = true;
		}
		if(lock && lock1 && gearButtonReleased){
			gearButtonReleased = false;
			gearIsLocked = !gearIsLocked;
		}
	}
	
	/*
	 * Conveyor
	 */
	public static void conveyor(boolean conveyorButton){
		
		if(!conveyorButton){
			//only runs if button is released
			conveyorButtonReleased = true;
		}
		
		if (Actuators.getFuelConveyorMotor().get() == Constants.MOTOR_STOP && conveyorButton && conveyorButtonReleased){
			Actuators.getFuelConveyorMotor().set(Constants.MOTOR_START_VALUE);
			conveyorButtonReleased = false;

		} else if(conveyorButton && conveyorButtonReleased){
			Actuators.getFuelConveyorMotor().set(Constants.MOTOR_STOP);
			conveyorButtonReleased = false;

		}
	}
	
	public static void conveyorSpeed(double speed){
		//increases motor speed
		if(speed <= Constants.STICK_PRESSED_UP &&
				Constants.MOTOR_STOP < Math.abs(Actuators.getFuelConveyorMotor().get()) &&
				Math.abs(Actuators.getFuelConveyorMotor().get()) < Constants.MAX_MOTOR_SPEED){
			
			//Increments motor speed by a set value while stick is more than 50% pressed
			motorSpeed = Actuators.getFuelConveyorMotor().get() + Constants.MOTOR_INCREMENT;
			Actuators.getFuelConveyorMotor().set(motorSpeed);
				
				
		}//decreases motor speed
		else if(speed >= Constants.STICK_PRESSED_DOWN &&
				Constants.MOTOR_STOP < Math.abs(Actuators.getFuelConveyorMotor().get()) &&
				Math.abs(Actuators.getFuelConveyorMotor().get()) < Constants.MAX_MOTOR_SPEED){
			
			//Increments motor speed by a set value while stick is more than 50% pressed
			motorSpeed = Actuators.getFuelConveyorMotor().get() - Constants.MOTOR_INCREMENT;
			Actuators.getFuelConveyorMotor().set(motorSpeed);
			}
	}
	public static void conveyorDirection(double direction){
		//reverses direction, if needed
		if(direction <= Constants.STICK_PRESSED_LEFT && !Actuators.getFuelIntakeMotor().getInverted()){
			Actuators.getFuelIntakeMotor().setInverted(true);
				
		}else if(direction >= Constants.STICK_PRESSED_RIGHT && Actuators.getFuelIntakeMotor().getInverted()){
			Actuators.getFuelIntakeMotor().setInverted(false);
		}
	}
	/*
	 * Outtake
	 */
	//TODO: turn internal rollers on towards outtake while outtake is running
	public static void outtakeToggle(boolean outtakeButton){
		
		if(!outtakeButton){
			//only runs if button is released
			outtakeButtonReleased = true;
		}
		if(outtakeButtonReleased){
			if(Actuators.getFuelOuttakeMotor().get() == Constants.MOTOR_STOP && outtakeButton){
				Actuators.getFuelOuttakeMotor().set(Constants.OUTTAKE_MOTOR_SPEED);
				outtakeButtonReleased = false;
			}else if(Actuators.getFuelOuttakeMotor().get() == Constants.OUTTAKE_MOTOR_SPEED && outtakeButton){
				Actuators.getFuelOuttakeMotor().set(Constants.MOTOR_STOP);
				outtakeButtonReleased = false;
			}
		}
	}
	public static void conveyorIn(boolean conveyorButton){
		if(!conveyorButton){
			conveyorInButtonReleased = true;
		}
		if(conveyorButton && conveyorInButtonReleased && Actuators.getFuelConveyorMotor().get() >= Constants.MOTOR_STOP){
			Actuators.getFuelConveyorMotor().set(Constants.MAX_MOTOR_SPEED);
		}else if(conveyorButton && conveyorInButtonReleased){
			Actuators.getFuelConveyorMotor().set(Constants.MOTOR_STOP);
		}
	}
}
