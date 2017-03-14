package com.github.adambots.steamworks2017.autonModes;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

import com.github.adambots.steamworks2017.drive.Drive;

import edu.wpi.first.wpilibj.command.Command;

import com.github.adambots.steamworks2017.drive.Drive;

public class BaselineCenter extends Command {
	static double distance = 95; // distance to baseline
	static boolean hasFinished = false;
	static double rampSpeed = .3;
	static boolean driveDone = false;

	public BaselineCenter() {
		System.out.println("I got here BaselineCenter");

	}

	public BaselineCenter(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public BaselineCenter(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}

	public BaselineCenter(String name, double timeout) {
		super(name, timeout);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Actuators.getLeftDriveMotor().setPosition(0);
		Actuators.getRightDriveMotor().setPosition(0);
		Drive.drive(0, 0);
	}

	@Override
	protected void execute() {
		//System.out.println("I got here execution (Baseline Center)");
		try {
			if (Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) < 750) {
				System.out.println("Ramp up");
				Actuators.getLeftDriveMotor().set(-rampSpeed);
				Actuators.getRightDriveMotor().set(rampSpeed);
				hasFinished = false;
				driveDone = false;
			} else if (Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) < 7500
					&& Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) >= 750) {
				System.out.println("Half speed");
				Actuators.getLeftDriveMotor().set(-Constants.HALF_MOTOR_SPEED);
				Actuators.getRightDriveMotor().set(Constants.HALF_MOTOR_SPEED);
				driveDone = false;
				hasFinished = false;
			} else if (Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) >= 7500
					&& Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) < 8300) {
				System.out.println("Ramp down");
				Actuators.getLeftDriveMotor().set(-rampSpeed);
				Actuators.getRightDriveMotor().set(rampSpeed);
				driveDone = false;
				hasFinished = false;
			} else if (Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) >= 8300) {
				System.out.println("Stop");
				Actuators.getLeftDriveMotor().set(Constants.MOTOR_STOP);
				Actuators.getRightDriveMotor().set(Constants.MOTOR_STOP);
				hasFinished = false;
				driveDone = true;

			}
			if (driveDone) {
				Actuators.getDispenseGearPneumatic().set(true);
				hasFinished = true;
			}
			// Drive.driveWithPID(distance, distance);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return hasFinished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
