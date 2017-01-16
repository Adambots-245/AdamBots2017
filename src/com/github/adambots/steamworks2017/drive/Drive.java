package com.github.adambots.steamworks2017.drive;

import org.usfirst.frc.team245.robot.Actuators;

public class Drive {
	
	/*
	 * Drives robot with turning ability
	 * @param speed
	 * @param turnSpeed
	 * */
	public static void drive(double speed, double turnSpeed){
		double leftSpeed = Math.min(speed + turnSpeed, Actuators.MAX_MOTOR_SPEED); //restrict leftSpeed between min and max values
		leftSpeed = Math.max(leftSpeed, Actuators.MIN_MOTOR_SPEED);
		double rightSpeed = Math.min(speed - turnSpeed, Actuators.MAX_MOTOR_SPEED); //restrict rightSpeed between min and max values
		rightSpeed = Math.max(rightSpeed, Actuators.MIN_MOTOR_SPEED);
		Actuators.getLeftDriveMotor().set(leftSpeed);
		Actuators.getRightDriveMotor().set(rightSpeed);
	}
}
