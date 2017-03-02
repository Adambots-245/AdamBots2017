package com.github.adambots.steamworks2017.autonModes;

import org.usfirst.frc.team245.robot.Actuators;
import com.github.adambots.steamworks2017.drive.Drive;

import edu.wpi.first.wpilibj.command.Command;


public class Baseline extends Command{
	static double distance = 95; //distance to baseline
	
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
		System.out.println("I got here execution");
		Drive.driveWithPID(distance, distance);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
	