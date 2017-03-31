
package com.github.adambots.steamworks2017.drive;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Drive {

	private static boolean toggleReleased = true;
	public static int crabState = 0;
	public static boolean goingLeft = false;
	static boolean hasReleased = false;
	static boolean firstRun = true;
	static int leftPosition = 50;
	static int rightPosition = 50;
	static boolean setEncoder = false;

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

	public static void crab(boolean left, boolean right) {
		if ((!left || !right) && hasReleased) {
			hasReleased = false;
			firstRun = true;
			crabState = 0;
		}
		if (left || right) {

			if (hasReleased) {
				// this only runs on the first iteration of the crabwalk
				if (firstRun) {
					firstRun = false;
					// reset encoder values for drive on the first run through
					// of crab walk
					Actuators.getLeftDriveMotor().setEncPosition(Constants.RESET);
					Actuators.getRightDriveMotor().setEncPosition(Constants.RESET);
					leftPosition = Constants.INITIAL_ENCODER;
					rightPosition = Constants.INITIAL_ENCODER;
				}
			}

			// logic to set motor encoders to values at the correct time
			if (crabState == 1 && setEncoder) {
				setEncoder = false;
				// backing up
				leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.INITIAL_BACKUP;
				rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.INITIAL_BACKUP;
			} else if (crabState == 2 && setEncoder) {
				setEncoder = false;
				// turning
				if (left) {
					leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.TURN_LEFT;
					rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.TURN_LEFT;
				} else if (right) {
					leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.TURN_RIGHT;
					rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.TURN_RIGHT;
				}
			} else if (crabState == 3 && setEncoder) {
				setEncoder = false;
				// backing up
				leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.BACKUP;
				rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.BACKUP;
			} else if (crabState == 4 && setEncoder) {
				setEncoder = false;
				// unturning itself
				if (left) {
					leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.TURN_LEFT;
					rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.TURN_LEFT;
				} else if (right) {
					leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.TURN_RIGHT;
					rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.TURN_RIGHT;
				}
			} else if (crabState == 5 && setEncoder) {
				setEncoder = false;
				// going forward to initial position
				leftPosition = Actuators.getLeftDriveMotor().getEncPosition() + Constants.DRIVE_FORWARD;
				rightPosition = Actuators.getRightDriveMotor().getEncPosition() + Constants.DRIVE_FORWARD;
			}

			// logic to run motors based off a set encoder value
			if (Actuators.getLeftDriveMotor().getEncPosition() < (leftPosition - Constants.ERROR)) {
				Actuators.getLeftDriveMotor().set(Constants.CRAB_MOTOR_SPEED_FORWARD);
			} else if (Actuators.getLeftDriveMotor().getEncPosition() > (leftPosition + Constants.ERROR)) {
				Actuators.getLeftDriveMotor().set(Constants.CRAB_MOTOR_SPEED_BACKWARD);
			}

			if (Actuators.getRightDriveMotor().getEncPosition() < (rightPosition - Constants.ERROR)) {
				Actuators.getRightDriveMotor().set(Constants.CRAB_MOTOR_SPEED_FORWARD);
			} else if (Actuators.getRightDriveMotor().getEncPosition() > (rightPosition + Constants.ERROR)) {
				Actuators.getRightDriveMotor().set(Constants.CRAB_MOTOR_SPEED_BACKWARD);
			}
			if (Actuators.getLeftDriveMotor().getEncPosition() < (leftPosition - Constants.ERROR)
					&& Actuators.getLeftDriveMotor().getEncPosition() > (leftPosition + Constants.ERROR)
					&& Actuators.getRightDriveMotor().getEncPosition() < (rightPosition - Constants.ERROR)
					&& Actuators.getRightDriveMotor().getEncPosition() > (rightPosition + Constants.ERROR)) {

				// iterates the crabState when the encoders are all at the
				// correct position
				crabState++;
				setEncoder = true;

			}
		}

		// crabState++;
		// if (!goingLeft) {
		//
		// if (crabState < 10)
		// drive(0, .8);
		// else if (crabState < 20)
		// drive(-.6, 0);
		// else if (crabState < 30)
		// drive(0, -.8);
		// else if (crabState < 40)
		// drive(.6, 0);
		// else
		// crabState = 0;
		// } else {
		//
		// if (crabState < 10)
		// drive(0, -.8);
		// else if (crabState < 20)
		// drive(.6, 0);
		// else if (crabState < 30)
		// drive(0, .8);
		// else if (crabState < 40)
		// drive(-.6, 0);
		// else
		// crabState = 0;
		//
		// }

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
