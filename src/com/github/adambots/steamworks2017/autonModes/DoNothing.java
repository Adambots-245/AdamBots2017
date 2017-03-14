package com.github.adambots.steamworks2017.autonModes;

import org.usfirst.frc.team245.robot.Actuators;

import com.github.adambots.steamworks2017.drive.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothing extends Command {
	public DoNothing(){
		//FINISHED!
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
		// TODO Auto-generated method stub
		
		System.out.println("DO NOTHING IS RUNNING");
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
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
