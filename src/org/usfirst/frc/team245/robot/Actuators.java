package org.usfirst.frc.team245.robot;

import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc.team245.robot.Constants;
import edu.wpi.first.wpilibj.VictorSP;

public class Actuators {
	
	//CONSTANTS
	
	//Motor max and min constants
	public static final double MAX_MOTOR_SPEED = 1;
	public static final double MIN_MOTOR_SPEED = -1;
	public static final double STOP_MOTOR = 0;
	//Motors
	private static CANTalon rightDriveMotor;
	private static CANTalon rightDriveMotorSlave;
	
	private static CANTalon leftDriveMotor;
	private static CANTalon leftDriveMotorSlave;
	
	private static CANTalon climbingMotor;
	private static CANTalon intakeMotor;
	
	//Pneumatics
	private static Solenoid driveShiftPneumatic;
	
	private static Solenoid gearPneumatic;
	
	//VictorSP's ... Motor names need to be changed
	private static VictorSP fuelIntakeMotor;
	private static VictorSP hopperDispenseMotor;
	
	/*
	 * Initializes all actuators
	 */
	//TODO: Set correct IDs, test motors individually and confirm correct directions
	public static void init(){
		rightDriveMotor = new CANTalon(Constants.RIGHT_FRONT_DRIVE_MOTOR_PORT);
		rightDriveMotorSlave = new CANTalon(Constants.RIGHT_REAR_DRIVE_MOTOR_PORT);
		rightDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower); //setting right rear motor to follow right front motor
		rightDriveMotorSlave.set(rightDriveMotor.getDeviceID());
		rightDriveMotorSlave.reverseOutput(true); //reversing right slave motor because of gear design
		
		leftDriveMotor = new CANTalon(Constants.LEFT_FRONT_DRIVE_MOTOR_PORT);
		leftDriveMotorSlave = new CANTalon(Constants.LEFT_REAR_DRIVE_MOTOR_PORT);
		leftDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower); //setting left rear motor to follow left front motor
		leftDriveMotorSlave.set(leftDriveMotor.getDeviceID());
		leftDriveMotorSlave.reverseOutput(true); //reversing left slave motor because of gear design
		
		climbingMotor = new CANTalon(4);
		intakeMotor = new CANTalon(Constants.INTAKE_MOTOR_PORT);

		driveShiftPneumatic = new Solenoid(0);
		
		 fuelIntakeMotor = new VictorSP(0);
		driveShiftPneumatic = new Solenoid(Constants.DRIVE_SHIFT_PNEUMATIC_PORT);
		 hopperDispenseMotor = new VictorSP(0);
		 
		 gearPneumatic = new Solenoid(0);
		
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
	
	public static Solenoid getGearPneumatic () {
		return gearPneumatic;
	}
	public static CANTalon getClimbingMotor() {
		return climbingMotor;
	}
}
