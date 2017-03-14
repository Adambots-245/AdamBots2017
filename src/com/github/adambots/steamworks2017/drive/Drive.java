package com.github.adambots.steamworks2017.drive;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Drive {

	private static boolean toggleReleased = true;
	public static int crabState = 0;
	public static boolean goingLeft = false;

	/*
	 * Sets initial conditions for driving
	 */
	public static void init() {
		Actuators.getDriveShiftPneumatic().set(false);
	}

	/*
	 * Drives robot with turning ability
	 * 
	 * @param speed
	 * 
	 * @param turnSpeed
	 */
	// TODO: test drive code
	public static void drive(double speed, double turnSpeed) {
		double leftSpeed = Math.min(speed + turnSpeed, Constants.MAX_MOTOR_SPEED); // restrict
																					// leftSpeed
																					// between
																					// min
																					// and
																					// max
																					// values
		leftSpeed = Math.max(leftSpeed, Constants.MIN_MOTOR_SPEED);
		double rightSpeed = Math.min(speed - turnSpeed, Constants.MAX_MOTOR_SPEED); // restrict
																					// rightSpeed
																					// between
																					// min
																					// and
																					// max
																					// values
		rightSpeed = Math.max(rightSpeed, Constants.MIN_MOTOR_SPEED);
		Actuators.getLeftDriveMotor().set(leftSpeed);
		Actuators.getRightDriveMotor().set(rightSpeed);
	}

	/*
	 * Shift gear
	 * 
	 * @param lowGearButton
	 * 
	 * @param highGearButton
	 */
	// TODO: Test shift code, confirm fired is high gear not fired is low gear
	public static void shift(boolean lowGearButton, boolean highGearButton) {
		if (lowGearButton && Actuators.getDriveShiftPneumatic().get()) {
			Actuators.getDriveShiftPneumatic().set(false);
		} else if (highGearButton && !Actuators.getDriveShiftPneumatic().get()) {
			Actuators.getDriveShiftPneumatic().set(true);
		}
	}

	public static void crab() {
		crabState++;
		if (!goingLeft) {

			if (crabState < 10)
				drive(0, .8);
			else if (crabState < 20)
				drive(-.6, 0);
			else if (crabState < 30)
				drive(0, -.8);
			else if (crabState < 40)
				drive(.6, 0);
			else
				crabState = 0;
		} else {

			if (crabState < 10)
				drive(0, -.8);
			else if (crabState < 20)
				drive(.6, 0);
			else if (crabState < 30)
				drive(0, .8);
			else if (crabState < 40)
				drive(-.6, 0);
			else
				crabState = 0;

		}

	}

	/*
	 * Toggle shifting gears
	 * 
	 * @param toggleButton
	 * 
	 * @param leftBumperReleased
	 */
	/*
	 * shiftToggle takes in leftBumperReleased, which should be set to true only
	 * when this is called again by the button being released and repressed. The
	 * first time gears is shifted, leftBumperReleased will be set to false once
	 * leftBumperReleased is set to false, shifting won't be possible until set
	 * to true again
	 */
	// TODO: Test please, someone else check logic too
	public static void shiftToggle(boolean toggleButton) {

		if (!toggleButton) {
			// this only runs if button is released
			toggleReleased = true;
		}
		if (toggleButton && Actuators.getDriveShiftPneumatic().get() && toggleReleased) {
			Actuators.getDriveShiftPneumatic().set(false);
			toggleReleased = false;
		} else if (toggleButton && !Actuators.getDriveShiftPneumatic().get() && toggleReleased) {
			Actuators.getDriveShiftPneumatic().set(true);
			toggleReleased = false;
		}
	}
	// public static void driveWithPID(double leftDistance, double
	// rightDistance){
	// leftDistance /= Constants.INCHES_PER_REV;
	// rightDistance /= Constants.INCHES_PER_REV;
	//
	// Actuators.getLeftDriveMotor().changeControlMode(TalonControlMode.Position);
	// Actuators.getLeftDriveMotor().set(leftDistance);
	//
	// Actuators.getRightDriveMotor().changeControlMode(TalonControlMode.Position);
	// Actuators.getRightDriveMotor().set(rightDistance);
	//
	// Actuators.getRightDriveMotor().enable();
	// Actuators.getLeftDriveMotor().enable();
	// System.out.println("I got here driveWithPID end");
	// }
}
