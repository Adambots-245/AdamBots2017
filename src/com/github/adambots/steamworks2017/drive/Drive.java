package com.github.adambots.steamworks2017.drive;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;


public class Drive {
	/*
	 * Sets initial conditions for driving
	 * */
	public static void init(){
		Actuators.getDriveShiftPneumatic().set(false);
	}
	
	/*
	 * Drives robot with turning ability
	 * @param speed
	 * @param turnSpeed
	 * */
	//TODO: test drive code
	public static void drive(double speed, double turnSpeed){
		double leftSpeed = Math.min(speed + turnSpeed, Constants.MAX_MOTOR_SPEED); //restrict leftSpeed between min and max values
		leftSpeed = Math.max(leftSpeed, Constants.MIN_MOTOR_SPEED);
		double rightSpeed = Math.min(speed - turnSpeed, Constants.MAX_MOTOR_SPEED); //restrict rightSpeed between min and max values
		rightSpeed = Math.max(rightSpeed, Constants.MIN_MOTOR_SPEED);
		Actuators.getLeftDriveMotor().set(leftSpeed);
		Actuators.getRightDriveMotor().set(rightSpeed);
	}
	
	/*
	 * Shift gear
	 * @param lowGearButton
	 * @param highGearButton
	 * */
	//TODO: Test shift code, confirm fired is high gear not fired is low gear
	public static void shift(boolean lowGearButton, boolean highGearButton){
		if(lowGearButton && Actuators.getDriveShiftPneumatic().get()){
			Actuators.getDriveShiftPneumatic().set(false);
		} else if (highGearButton && !Actuators.getDriveShiftPneumatic().get()){
			Actuators.getDriveShiftPneumatic().set(true);
		}
	}
	/*
	 * Toggle shifting gears
	 * @param toggleButton
	 */
	//TODO: Test please
	public static void shiftToggle(boolean toggleButton){
		boolean leftBumperReleased = true;
		if(toggleButton && Actuators.getDriveShiftPneumatic().get() && leftBumperReleased){
			Actuators.getDriveShiftPneumatic().set(false);
			leftBumperReleased = false;
		} else if (toggleButton && !Actuators.getDriveShiftPneumatic().get() && leftBumperReleased){
			Actuators.getDriveShiftPneumatic().set(true);
			leftBumperReleased = false;
		}
	}
}
