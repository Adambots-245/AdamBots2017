package org.usfirst.frc.team245.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Gamepad {
	private Joystick joy;
	//// CONSTANTS -------------------------------------------------------------
	/**
	 * Primary Driver Controller Port Number.
	 */
	private static final int PRIMARY_DRIVER = 1;
	/**
	 * Secondary Driver Controller Port Number.
	 */
	private static final int SECONDARY_DRIVER = 2;
	/**
	 * XBOX 360 South Face Button
	 */
	private static final int BUTTON_A = 1;
	/**
	 * XBOX 360 East Face Button
	 */
	private static final int BUTTON_B = 2;
	/**
	 * XBOX 360 West Face Button
	 */
	private static final int BUTTON_X = 3;
	/**
	 * XBOX 360 North Face Button
	 */
	private static final int BUTTON_Y = 4;
	/**
	 * XBOX 360 Left Bumper (Top)
	 */
	private static final int BUTTON_LB = 5;
	/**
	 * XBOX 360 Right Bumper (Top)
	 */
	private static final int BUTTON_RB = 6;
	/**
	 * XBOX 360 Back Button
	 */
	private static final int BUTTON_BACK = 7;
	/**
	 * XBOX 360 Start Button
	 */
	private static final int BUTTON_START = 8;
	/**
	 * XBOX 360 Left Stick Button
	 */
	private static final int BUTTON_LEFT_STICK = 9;
	/**
	 * XBOX 360 Right Stick Button
	 */
	private static final int BUTTON_RIGHT_STICK = 10;
	/**
	 * XBOX 360 Left Horizontal Axis (Left=-1, Right=1)
	 */
	private static final int AXIS_LEFT_X = 0;
	/**
	 * XBOX 360 Left Vertical Axis (Up=-1, Down=1)
	 */
	private static final int AXIS_LEFT_Y = 1;
	/**
	 * XBOX 360 Trigger Axis (LEFT)
	 */
	public static final int LEFT_AXIS_TRIGGERS = 2;
	/**
	 * XBOX 360 Trigger Axis (RIGHT)
	 */
	public static final int RIGHT_AXIS_TRIGGERS = 3;
	/**
	 * XBOX 360 Right Horizontal Axis (Left=-1, Right=1)
	 */
	private static final int AXIS_RIGHT_X = 4;
	/**
	 * XBOX 360 Right Vertical Axis (Up=-1, Down=1)
	 */
	private static final int AXIS_RIGHT_Y = 5;
	/**
	 * XBOX 360 DPAD POV Port(Up=0, Right=90, Down = 180, Left = 270, Not pressed=-1)
	 */
	private static final int AXIS_DPAD = 0;
	private static final int AXIS_DPAD_UP = 0;
	private static final int AXIS_DPAD_RIGHT = 90;
	private static final int AXIS_DPAD_DOWN = 180;
	private static final int AXIS_DPAD_LEFT = 270;
	//// Control Instances
	public static Gamepad primary = new Gamepad(PRIMARY_DRIVER);
	public static Gamepad secondary = new Gamepad(SECONDARY_DRIVER);
	//// CONSTRUCTOR -----------------------------------------------------------

	/**
	 * Creates a new Joystick instance on the correct driver port.
	 *
	 * @param port 
	 * 				The joystick port number.
	 */
	private Gamepad(int port) {
		joy = new Joystick(port);
	}

	private double deaden(double u) {
		return Math.abs(u) < .1 ? 0 : u;
	}

	public double getTriggers() {
		return deaden(joy.getRawAxis(LEFT_AXIS_TRIGGERS)-joy.getRawAxis(RIGHT_AXIS_TRIGGERS));
	}

	/**
	 * Corresponds to RIGHT input on the DPad.
	 *
	 * @return Is the DPad pressed Right?
	 */
	public boolean getDPadRight() {
		if( joy.getPOV(AXIS_DPAD) == AXIS_DPAD_RIGHT){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Corresponds to UP input on the DPad.
	 *
	 * @return Is the DPad pressed Up?
	 */
	public boolean getDPadUp() {
		if(joy.getPOV(AXIS_DPAD) == AXIS_DPAD_UP){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Corresponds to Down input on the DPad.
	 *
	 * @return Is the DPad pressed Down?
	 */
	public boolean getDPadDown(){
		if(joy.getPOV(AXIS_DPAD) == AXIS_DPAD_DOWN){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Corresponds to Left input on the DPad.
	 *
	 * @return Is the DPad pressed Left?
	 */
	public boolean getDPadLeft(){
		if(joy.getPOV(AXIS_DPAD) == AXIS_DPAD_LEFT){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Corresponds to HORIZONTAL input on the LEFT joystick.
	 *
	 * @return The X coordinate of the left joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getLeftX() {
		return deaden(joy.getRawAxis(AXIS_LEFT_X));
	}

	/**
	 * Corresponds to VERTICAL input on the LEFT joystick.
	 *
	 * @return The Y coordinate of the LEFT joystick (-1 is UP, 1 is DOWN)
	 */
	public double getLeftY() {
		return deaden(joy.getRawAxis(AXIS_LEFT_Y));
	}

	/**
	 * Corresponds to HORIZONTAL input on the RIGHT joystick
	 *
	 * @return The X coordinate of the RIGHT joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getRightX() {
		return deaden(joy.getRawAxis(AXIS_RIGHT_X));
	}

	/**
	 * Corresponds to VERTICAL input on the RIGHT joystick
	 *
	 * @return The Y coordinate of the RIGHT joystick (-1 is UP, 1 is DOWN)
	 */
	public double getRightY() {
		return deaden(joy.getRawAxis(AXIS_RIGHT_Y));
	}

	/**
	 *
	 * @return Is the left bumper pressed? [top one]
	 */
	public boolean getLB() {
		return joy.getRawButton(BUTTON_LB);
	}

	/**
	 *
	 * @return Is the right bumper pressed? [top one]
	 */
	public boolean getRB() {
		return joy.getRawButton(BUTTON_RB);
	}
	/**
	 *
	 * @return Is the A button pressed?
	 */
	public boolean getA() {
		return joy.getRawButton(BUTTON_A);
	}
	/**
	 *
	 * @return Is the B button pressed?
	 */
	public boolean getB() {
		return joy.getRawButton(BUTTON_B);
	}
	/**
	 *
	 * @return Is the X button pressed?
	 */
	public boolean getX() {
		return joy.getRawButton(BUTTON_X);
	}
	/**
	 *
	 * @return Is the Y button pressed?
	 */
	public boolean getY() {
		return joy.getRawButton(BUTTON_Y);
	}
	/**
	 *
	 * @return Is the Start button pressed?
	 */
	public boolean getStart() {
		return joy.getRawButton(BUTTON_START);
	}
	/**
	 *
	 * @return Is the Back button pressed?
	 */
	public boolean getBack() {
		return joy.getRawButton(BUTTON_BACK);
	}
	/**
	 * Corresponds to the Left Joystick being pressed in
	 * 
	 * @return Is the Left Joystick pressed?
	 */
	public boolean getLeftButton(){
		return joy.getRawButton(BUTTON_LEFT_STICK);
	}
	/**
	 * Corresponds to the Right Joystick being pressed in
	 * 
	 * @return Is the Right Joystick pressed?
	 */
	public boolean getRightButton(){
		return joy.getRawButton(BUTTON_RIGHT_STICK);
	}
	
	
	/*
	 * Set Methods
	 */
	
	/**
	 * Make the controller vibrate on the left side
	 * 
	 * @param intensity How strong the rumble is
	 */
	public void setRumbleLeft(double intensity){
		joy.setRumble(RumbleType.kLeftRumble, intensity);
	}
	/**
	 * Make the controller vibrate on the right side
	 * 
	 * @param intensity How strong the rumble is
	 */
	public void setRumbleRight(double intensity){
		joy.setRumble(RumbleType.kRightRumble, intensity);
	}
	/**
	 * Make the controller vibrate on both sides
	 * 
	 * @param intensity How strong the rumble is
	 */
	public void setRumbleBoth(double intensity){
		joy.setRumble(RumbleType.kLeftRumble, intensity);
		joy.setRumble(RumbleType.kRightRumble, intensity);
	}
}
