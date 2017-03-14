package org.usfirst.frc.team245.robot;

public class Constants {
	//Motor max and min constants
	public static final double MAX_MOTOR_SPEED = 1;
	public static final double MIN_MOTOR_SPEED = -1;
	public static final double HALF_MOTOR_SPEED = .5;
	public static final double INCHES_PER_REV = 12.556;
	
	//Motor values
	public static final double MOTOR_STOP = 0;
	public static final double CLIMB_MOTOR_SPEED = 1; 
	public static final double OUTTAKE_MOTOR_SPEED = 0.8; //TODO: Find Correct Value for Motor
	public static final double MOTOR_START_VALUE = - 0.5;
	public static final double MOTOR_INCREMENT = 0.05; 
	public static final double MOTOR_CIM_STALL_CURRENT = 15; //TODO: Find correct current value for motor stall
	public static final double MOTOR_REVERSE = -0.6;	
	public static final double MOTOR_ACCEL = 0.05;		
	
	//Port map
	public static final int LEFT_FRONT_DRIVE_MOTOR_PORT = 0;
	public static final int RIGHT_FRONT_DRIVE_MOTOR_PORT = 2; //switched with climbing motor to have same build model for all drive controllers
	public static final int LEFT_REAR_DRIVE_MOTOR_PORT = 1;
	public static final int RIGHT_REAR_DRIVE_MOTOR_PORT = 3;
	public static final int INTAKE_MOTOR_PORT = 5;
	public static final int DRIVE_SHIFT_PNEUMATIC_PORT = 0;
	public static final int CLIMB_MOTOR_PORT = 4;	//switched with right front drive motor to have same build model for all drive controllers
	public static final int DISPENSE_GEAR_PNEUMATIC_PORT = 1;
	public static final int GEAR_LIFT_PNEUMATIC_PORT = 3;
	public static final int SWEEPER_PNEUMATIC_PORT = 4 ;
	public static final int POWER_DISTRIBUTION_PANEL_PORT = 0;
	
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
	public static final double TIME_RUMBLE_VALUE = 0.75;
	public static final double NO_RUMBLE = 0;
	public static final double TIME_RUMBLE = 45;
	
	//Safety Constants
	public static final int COUNTER_END = 3;	//If the counter for button presses is at this amount, then the safety will be disabled
	public static final int COUNTER_START = 0;
	
	//Ring Light
	public static final int RING_LIGHT = 3;
	public static final int LED_LIGHT = 5;
	
	//Vision Constants
	public static final boolean VISION_WORKING = true;
	public static final boolean VISION_FAIL = false;
}
