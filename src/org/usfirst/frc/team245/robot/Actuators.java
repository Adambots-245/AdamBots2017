package org.usfirst.frc.team245.robot;

import com.ctre.CANTalon;

public class Actuators {
	
	//CONSTANTS
	
	//Motor max and min constants
	public static final double MAX_MOTOR_SPEED = 1;
	public static final double MIN_MOTOR_SPEED = -1;
	
	//Motors
	private static CANTalon rightDriveMotor;
	private static CANTalon rightDriveMotorSlave;
	
	private static CANTalon leftDriveMotor;
	private static CANTalon leftDriveMotorSlave;
	
	/*
	 * Initializes all actuators
	 */
	public static void init(){
		rightDriveMotor = new CANTalon(0);
		rightDriveMotorSlave = new CANTalon(1);
		rightDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightDriveMotorSlave.set(rightDriveMotor.getDeviceID());
		rightDriveMotorSlave.reverseOutput(true);
		
		leftDriveMotor = new CANTalon(2);
		leftDriveMotorSlave = new CANTalon(3);
		leftDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftDriveMotorSlave.set(leftDriveMotor.getDeviceID());
		rightDriveMotorSlave.reverseOutput(true);
	}

	/*
	 * @return rightDriveMotor
	 * */
	public static CANTalon getRightDriveMotor() {
		return rightDriveMotor;
	}
	
	/*
	 * @return rightDriveMotorSlave
	 * */
	public static CANTalon getRightDriveMotorSlave() {
		return rightDriveMotorSlave;
	}

	/*
	 * @return leftDriveMotor
	 * */
	public static CANTalon getLeftDriveMotor() {
		return leftDriveMotor;
	}

	/*
	 * @return leftDriveMotorSlave
	 * */
	public static CANTalon getLeftDriveMotorSlave() {
		return leftDriveMotorSlave;
	}
	
}
