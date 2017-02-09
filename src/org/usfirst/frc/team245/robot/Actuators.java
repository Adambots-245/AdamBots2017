package org.usfirst.frc.team245.robot;

import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team245.robot.Constants;
public class Actuators {
	
	//Motors
	private static CANTalon rightDriveMotor;
	private static CANTalon rightDriveMotorSlave;
	
	private static CANTalon leftDriveMotor;
	private static CANTalon leftDriveMotorSlave;
	
	private static CANTalon climbMotor;
	
	private static VictorSP fuelIntakeMotor;
	private static VictorSP fuelOuttakeMotor;
	private static VictorSP fuelConveyorMotor;


	
	//Pneumatics
	private static Solenoid driveShiftPneumatic;
	private static Solenoid dispenseGearAdvancePneumatic;
	private static Solenoid dispenseGearReturnPneumatic;
	private static Solenoid gearLiftPneumatic;
	private static Solenoid sweeperAdvancePneumatic;
	private static Solenoid sweeperReturnPneumatic;
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
		

		climbMotor = new CANTalon(Constants.CLIMB_MOTOR_PORT);
		
		fuelIntakeMotor = new VictorSP(Constants.FUEL_INTAKE_MOTOR_PWM_PORT);
		fuelOuttakeMotor = new VictorSP(Constants.FUEL_OUTTAKE_MOTOR_PWM_PORT);
		fuelConveyorMotor = new VictorSP(Constants.FUEL_CONVEYOR_MOTOR_PWM_PORT);
		
		//Pneumatics
		driveShiftPneumatic = new Solenoid(Constants.DRIVE_SHIFT_PNEUMATIC_PORT);
		dispenseGearAdvancePneumatic = new Solenoid(Constants.DISPENSE_GEAR_ADVANCE_PNEUMATIC_PORT);
		dispenseGearReturnPneumatic = new Solenoid(Constants.DISPENSE_GEAR_RETURN_PNEUMATIC_PORT);
		gearLiftPneumatic = new Solenoid(Constants.GEAR_LIFT_PNEUMATIC_PORT);
		sweeperAdvancePneumatic = new Solenoid(Constants.SWEEPER_ADVANCE_PNEUMATIC_PORT);
		sweeperReturnPneumatic = new Solenoid(Constants.SWEEPER_RETURN_PNEUMATIC_PORT);
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
	 * @return climbMotor
	 */
	public static CANTalon getClimbMotor() {
		return climbMotor;
	}
	
	/*
	 * @return driveShiftPneumatic
	 * */
	public static Solenoid getDriveShiftPneumatic() {
		return driveShiftPneumatic;
	}
	/*
	 * @return fuelIntakeMotor
	 */
	public static VictorSP getFuelIntakeMotor() {
		return fuelIntakeMotor;
	}
	/*
	 * @return fuelOuttakeMotor
	 */
	public static VictorSP getFuelOuttakeMotor() {
		return fuelOuttakeMotor;
	}
	/*
	 * @return fuelConveyorMotor
	 */
	public static VictorSP getFuelConveyorMotor() {
		return fuelConveyorMotor;
	}
	/*
	 * @return dispenseGearAdvancePneumatic
	 */
	public static Solenoid getDispenseGearAdvancePneumatic() {
		return dispenseGearAdvancePneumatic;
	}
	/*
	 * @return dispenseGearReturnPneumatic
	 */
	public static Solenoid getDispenseGearReturnPneumatic() {
		return dispenseGearReturnPneumatic;
	}
	/*
	 * @return gearLiftPneumatic
	 */
	public static Solenoid getGearLiftPneumatic() {
		return gearLiftPneumatic;
	}
	/*
	 * @return sweeperAdvancePneumatic
	 */
	public static Solenoid getSweeperAdvancePneumatic() {
		return sweeperAdvancePneumatic;
	}
	/*
	 * @return sweeperReturnPneumatic
	 */
	public static Solenoid getSweeperReturnPneumatic() {
		return sweeperReturnPneumatic;
	}
}

