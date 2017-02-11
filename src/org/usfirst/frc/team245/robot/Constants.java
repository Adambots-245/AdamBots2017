package org.usfirst.frc.team245.robot;

public class Constants {
	//Motor max and min constants
	public static final double MAX_MOTOR_SPEED = 1;
	public static final double MIN_MOTOR_SPEED = -1;
	
	//Motor values
	public static final double MOTOR_STOP = 0;
	public static final double CLIMB_MOTOR_SPEED = 0.8; //TODO: Find Correct Value for Motor speed to climb
	public static final double OUTTAKE_MOTOR_SPEED = 0.8; //TODO: Find Correct Value for Motor
	public static final double MOTOR_START_VALUE = 0.5;
	public static final double MOTOR_INCREMENT = 0.05; //TODO: Find correct value for the increment value
	public static final double MOTOR_CIM_STALL_CURRENT = 20; //TODO: Find correct current value for motor stall
	
	//Port map
	public static final int LEFT_FRONT_DRIVE_MOTOR_PORT = 0;
	public static final int RIGHT_FRONT_DRIVE_MOTOR_PORT = 1;
	public static final int LEFT_REAR_DRIVE_MOTOR_PORT = 2;
	public static final int RIGHT_REAR_DRIVE_MOTOR_PORT = 3;
	public static final int INTAKE_MOTOR_PORT = 5;
	public static final int DRIVE_SHIFT_PNEUMATIC_PORT = 0;
	public static final int CLIMB_MOTOR_PORT = 4;
	public static final int DISPENSE_GEAR_ADVANCE_PNEUMATIC_PORT = 1;
	public static final int DISPENSE_GEAR_RETURN_PNEUMATIC_PORT = 2;
	public static final int GEAR_LIFT_PNEUMATIC_PORT = 3;
	public static final int SWEEPER_ADVANCE_PNEUMATIC_PORT = 4 ;
	public static final int SWEEPER_RETURN_PNEUMATIC_PORT = 5;
	public static final int POWER_DISTRIBUTION_PANEL_PORT = 5;//TODO: Find correct port for PDP
	//PWM Port
	public static final int FUEL_INTAKE_MOTOR_PWM_PORT = 0;
	public static final int FUEL_OUTTAKE_MOTOR_PWM_PORT = 1;
	public static final int FUEL_CONVEYOR_MOTOR_PWM_PORT = 2;
	
	//Sensor Ports
	public static final int SWEEPER_MAX_LIMIT_SWITCH_PORT = 0;
	public static final int SWEEPER_MIN_LIMIT_SWITCH_PORT = 1;
	public static final int GEAR_DISPENSED_LIMIT_SWITCH_PORT = 2;
	public static final int CLIMBING_COMPLETE_BUMP_PORT = 3;
	
	//Controller Constants
	public static final double STICK_PRESSED_UP = -0.5;		//TODO: Test Values to make sure they feel natural
	public static final double STICK_PRESSED_DOWN = 0.5;	//TODO: Test Values to make sure they feel natural
	public static final double STICK_PRESSED_LEFT = -0.5;	//TODO: Test Values to make sure they feel natural
	public static final double STICK_PRESSED_RIGHT = 0.5;	//TODO: Test Values to make sure they feel natural
	public static final double TRIGGER_PRESSED_LEFT = -0.5;	//TODO: Test Values to make sure they feel natural
	public static final double TRIGGER_PRESSED_RIGHT = 0.5;	//TODO: Test Values to make sure they feel natural
	
	//Rumble Constants
	public static final double MAX_RUMBLE = 1;
	public static final double HALF_RUMBLE = 0.5;
	public static final double NO_RUMBLE = 0;
	public static final double TIME_RUMBLE = 45;
	
	//Safety Constants
	public static final int COUNTER_END = 3;	//If the counter for button presses is at this amount, then the safety will be disabled
	public static final int COUNTER_START = 0;
}
