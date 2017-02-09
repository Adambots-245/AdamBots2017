package org.usfirst.frc.team245.robot;

public class Constants {
	//Motor max and min constants
	public static final double MAX_MOTOR_SPEED = 1;
	public static final double MIN_MOTOR_SPEED = -1;
	
	//Stops motor
	public static final double MOTOR_STOP = 0;
	
	//Port map
	public static final int LEFT_FRONT_DRIVE_MOTOR_PORT = 0;
	public static final int RIGHT_FRONT_DRIVE_MOTOR_PORT = 1;
	public static final int LEFT_REAR_DRIVE_MOTOR_PORT = 2;
	public static final int RIGHT_REAR_DRIVE_MOTOR_PORT = 3;
	public static final int DRIVE_SHIFT_PNEUMATIC_PORT = 0;
	public static final int CLIMB_MOTOR_PORT = 4;
	public static final int DISPENSE_GEAR_ADVANCE_PNEUMATIC_PORT = 1;
	public static final int DISPENSE_GEAR_RETURN_PNEUMATIC_PORT = 2;
	public static final int GEAR_LIFT_PNEUMATIC_PORT = 3;
	public static final int SWEEPER_ADVANCE_PNEUMATIC_PORT = 4 ;
	public static final int SWEEPER_RETURN_PNEUMATIC_PORT = 5;
	public static final int POWER_DISTRIBUTION_PANEL_PORT = 5;//currently unknown
	//PWM Port
	public static final int FUEL_INTAKE_MOTOR_PWM_PORT = 0;
	public static final int FUEL_OUTTAKE_MOTOR_PWM_PORT = 1;
	public static final int FUEL_CONVEYOR_MOTOR_PWM_PORT = 2;
	
	//Sensor Ports
	public static final int SWEEPER_MAX_LIMIT_SWITCH_PORT = 0;
	public static final int SWEEPER_MIN_LIMIT_SWITCH_PORT = 1;
	public static final int GEAR_DISPENSED_LIMIT_SWITCH_PORT = 2;
	public static final int CLIMBING_COMPLETE_BUMP_PORT = 3;
}