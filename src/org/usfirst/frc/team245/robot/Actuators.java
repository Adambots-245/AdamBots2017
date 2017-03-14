package org.usfirst.frc.team245.robot;

import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team245.robot.Constants;

public class Actuators {
	
	//Motors
	private static CANTalon rightDriveMotor;
	private static CANTalon rightDriveMotorSlave;
	
	public static CANTalon leftDriveMotor;
	private static CANTalon leftDriveMotorSlave;
	
	private static CANTalon climbMotor;
	
	private static VictorSP fuelIntakeMotor;
	private static VictorSP fuelOuttakeMotor;
	private static VictorSP fuelConveyorMotor;


	
	//Pneumatics
	private static Solenoid driveShiftPneumatic;
	private static Solenoid dispenseGearPneumatic;
	private static Solenoid sweeperPneumatic;
	
	//Ring light
	private static Solenoid ringLight;
	private static Solenoid LEDs;
	/*
	 * Initializes all actuators
	 */
	//TODO: Set correct IDs, test motors individually and confirm correct directions
	public static void init(){
		rightDriveMotor = new CANTalon(Constants.RIGHT_FRONT_DRIVE_MOTOR_PORT);
		rightDriveMotorSlave = new CANTalon(Constants.RIGHT_REAR_DRIVE_MOTOR_PORT);
		rightDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower); //setting right rear motor to follow right front motor
		rightDriveMotorSlave.set(rightDriveMotor.getDeviceID());
		rightDriveMotorSlave.reverseOutput(false); //reversing right slave motor because of gear design
		rightDriveMotor.enableBrakeMode(true);
		rightDriveMotorSlave.enableBrakeMode(true);
		
		leftDriveMotor = new CANTalon(Constants.LEFT_FRONT_DRIVE_MOTOR_PORT);
		leftDriveMotorSlave = new CANTalon(Constants.LEFT_REAR_DRIVE_MOTOR_PORT);
		leftDriveMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower); //setting left rear motor to follow left front motor
		leftDriveMotorSlave.set(leftDriveMotor.getDeviceID());
		leftDriveMotorSlave.reverseOutput(false); //reversing left slave motor because of gear design
		leftDriveMotor.enableBrakeMode(true);
		leftDriveMotorSlave.enableBrakeMode(true);

		climbMotor = new CANTalon(Constants.CLIMB_MOTOR_PORT);
		climbMotor.set(Constants.MOTOR_STOP);
		climbMotor.enableBrakeMode(true);
		climbMotor.setInverted(true);
		climbMotor.set(Constants.MOTOR_STOP);
		
		fuelIntakeMotor = new VictorSP(Constants.FUEL_INTAKE_MOTOR_PWM_PORT);
		fuelIntakeMotor.set(Constants.MOTOR_STOP);
		fuelOuttakeMotor = new VictorSP(Constants.FUEL_OUTTAKE_MOTOR_PWM_PORT);
		fuelOuttakeMotor.set(Constants.MOTOR_STOP);
		fuelConveyorMotor = new VictorSP(Constants.FUEL_CONVEYOR_MOTOR_PWM_PORT);
		
		//Pneumatics
		driveShiftPneumatic = new Solenoid(Constants.DRIVE_SHIFT_PNEUMATIC_PORT);
		dispenseGearPneumatic = new Solenoid(Constants.DISPENSE_GEAR_PNEUMATIC_PORT);
		sweeperPneumatic = new Solenoid(Constants.SWEEPER_PNEUMATIC_PORT);
		driveShiftPneumatic.set(false);
		
		ringLight = new Solenoid(Constants.RING_LIGHT);
		ringLight.set(true);
		
		LEDs = new Solenoid(Constants.LED_LIGHT);
		LEDs.set(true);
		
		
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
	 * values can be off, forward, or reverse
	 */
	public static Solenoid getDispenseGearPneumatic() {
		return dispenseGearPneumatic;
	}

	/*
	 * @return sweeperPneumatic
	 *  values can be off, forward, or reverse
	 */
	public static Solenoid getSweeperPneumatic() {
		return sweeperPneumatic;
	}

}

