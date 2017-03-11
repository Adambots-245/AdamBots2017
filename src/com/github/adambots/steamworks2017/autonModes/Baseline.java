package com.github.adambots.steamworks2017.autonModes;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

import com.github.adambots.steamworks2017.drive.Drive;

import edu.wpi.first.wpilibj.command.Command;


public class Baseline extends Command{
	static double distance = 95; //distance to baseline
	static boolean hasFinished = false;
	
	public Baseline(){
		System.out.println("I got here Baseline");
		

	}
	public Baseline(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Baseline(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}

	public Baseline(String name, double timeout) {
		super(name, timeout);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Actuators.getLeftDriveMotor().setPosition(0);
		Actuators.getRightDriveMotor().setPosition(0);
		Drive.drive(0,0);
	}
	@Override
	protected void execute() {
		System.out.println("I got here execution (Baseline)");
		try{
			if(Math.abs(Actuators.getLeftDriveMotor().getEncPosition()) <= 7835){
			Actuators.getLeftDriveMotor().set(- Constants.HALF_MOTOR_SPEED);
			Actuators.getRightDriveMotor().set(Constants.HALF_MOTOR_SPEED);
			hasFinished = false;
			}else{
				Actuators.getLeftDriveMotor().set(Constants.MOTOR_STOP);
				Actuators.getRightDriveMotor().set(Constants.MOTOR_STOP);
				hasFinished = true;
			}
//			Drive.driveWithPID(distance, distance);
		}catch(Exception e){
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
	