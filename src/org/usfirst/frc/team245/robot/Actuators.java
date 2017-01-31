package org.usfirst.frc.team245.robot;

import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.Solenoid;

public class Actuators {
	
	//Motors
	private static CANTalon rightDriveMotor;
	private static CANTalon rightDriveMotorSlave;
	
	private static CANTalon leftDriveMotor;
	private static CANTalon leftDriveMotorSlave;
	
	private static CANTalon intakeMotor;
	
	//Pneumatics
	private static Solenoid driveShiftPneumatic;
	private static Solenoid dispenseGearPneumatic;
	
	/*
	 * Initializes all actuators
	 */
	//TODO: Set correct IDs, test motors individually and confirm correct directions
	public static void init(){
		rightDriveMotor = new CANTalon(0);
		rightDriveMotorSlave = new CANTalon(1);
		rightDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower); //setting right rear motor to follow right front motor
		rightDriveMotorSlave.set(rightDriveMotor.getDeviceID());
		rightDriveMotorSlave.reverseOutput(true); //reversing right slave motor because of gear design
		
		leftDriveMotor = new CANTalon(2);
		leftDriveMotorSlave = new CANTalon(3);
		leftDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower); //setting left rear motor to follow left front motor
		leftDriveMotorSlave.set(leftDriveMotor.getDeviceID());
		leftDriveMotorSlave.reverseOutput(true); //reversing left slave motor because of gear design
		
		intakeMotor = new CANTalon(4); // Temporary value, exact not yet known
		
		driveShiftPneumatic = new Solenoid(0);		
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
	
	/*
	 * @return intakeMotor
	 * */
	public static CANTalon getIntakeMotor() {
		return intakeMotor;
	}
	
	/*
	 * @return driveShiftPneumatic
	 * */
	public static Solenoid getDriveShiftPneumatic() {
		return driveShiftPneumatic;
	}
	
}
