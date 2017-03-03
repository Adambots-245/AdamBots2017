package com.github.adambots.steamworks2017.score;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Score {
	
	static boolean gearButtonReleased = false;
	static boolean conveyorButtonReleased = false;
	static boolean outtakeButtonReleased = false;
	static boolean gearIsLocked = false;
	public static double conveyorMotorSpeed = Constants.MOTOR_STOP;
	static boolean conveyorInButtonReleased = false;
	public static boolean conveyorDisabled = true;
	static double oldMotorSpeed = Constants.MOTOR_STOP;
	static double newMotorSpeed = Constants.MOTOR_STOP;
	
	/*
	 * Intake
	 */
	//TODO: Add Logic for when limit switches get tripped, the pneumatic stops.
	public static void dispenseGear(boolean gearButton){
		if(gearButton){
			Actuators.getDispenseGearPneumatic().set(true);

		}else if(!gearButton){
			Actuators.getDispenseGearPneumatic().set(false);
		}
	}
	
	//toggles if the gear system is locked in position or not
//	public static void gearLock(boolean lock, boolean lock1){
//		if(!lock && !lock1){
//			gearButtonReleased = true;
//		}
//		if(lock && lock1 && gearButtonReleased){
//			gearButtonReleased = false;
//			gearIsLocked = !gearIsLocked;
//		}
//	}
	
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
				conveyorDisabled = false;
			} else if(conveyorButton && conveyorButtonReleased){
				Actuators.getFuelConveyorMotor().set(Constants.MOTOR_STOP);
				conveyorButtonReleased = false;
				conveyorDisabled = true;
			
		}
	}
	
	public static void conveyorSpeed(double speed){
		
			if(!conveyorDisabled){	
				//increases motor speed
			
				if(speed <= Constants.STICK_PRESSED_UP && Math.abs(Actuators.getFuelConveyorMotor().get()) < Constants.MIN_MOTOR_SPEED){
					//Increments motor speed by a set value while stick is more than 50% pressed
					conveyorMotorSpeed = Actuators.getFuelConveyorMotor().get() + Constants.MOTOR_INCREMENT;
					Actuators.getFuelConveyorMotor().set(conveyorMotorSpeed);
					oldMotorSpeed = Actuators.getFuelConveyorMotor().get();
				}//decreases motor speed
				else if(speed >= Constants.STICK_PRESSED_DOWN && Constants.MOTOR_STOP < Actuators.getFuelConveyorMotor().get()){
					//Increments motor speed by a set value while stick is more than 50% pressed
					conveyorMotorSpeed = Actuators.getFuelConveyorMotor().get() - Constants.MOTOR_INCREMENT;
					Actuators.getFuelConveyorMotor().set(conveyorMotorSpeed);
					oldMotorSpeed = Actuators.getFuelConveyorMotor().get();
				
			}

		}
	}
	
	public static void conveyorDirection(double direction){
		
			//reverses direction, if needed
			if(!conveyorDisabled){
				if(direction <= Constants.STICK_PRESSED_LEFT){
					if(Actuators.getFuelConveyorMotor().get() > Constants.MOTOR_REVERSE){
						newMotorSpeed = Actuators.getFuelConveyorMotor().get() - Constants.MOTOR_ACCEL;
						Actuators.getFuelConveyorMotor().set(newMotorSpeed);
					}	
				}else if(direction >= Constants.STICK_PRESSED_LEFT){	//runs this if the left stick is no longer held
					if(Actuators.getFuelConveyorMotor().get() < oldMotorSpeed){
						newMotorSpeed = Actuators.getFuelConveyorMotor().get();
						Actuators.getFuelConveyorMotor().set(newMotorSpeed);
					
				}
			}
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
				}else if(/*Actuators.getFuelOuttakeMotor().get() == Constants.OUTTAKE_MOTOR_SPEED && */outtakeButton){
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
				Actuators.getFuelConveyorMotor().set(Constants.MIN_MOTOR_SPEED);
				conveyorInButtonReleased = false;
			}else if(conveyorButton && conveyorInButtonReleased){
				Actuators.getFuelConveyorMotor().set(Constants.MOTOR_STOP);
				conveyorInButtonReleased = false;
			
		}
	}
}
