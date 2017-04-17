package com.github.adambots.steamworks2017.autonModes;

import org.usfirst.frc.team245.robot.Actuators;

import com.github.adambots.steamworks2017.ghostMode.Play;

import edu.wpi.first.wpilibj.command.Command;

public class RedBoilerPeg extends Command{
	static boolean hasFinished = false;
	
	public RedBoilerPeg() {

	}

	@Override
	protected void initialize() {
		Play.readRecording("/home/admin/redBoilerThu.txt"); //TODO: Add actual path
		Actuators.getDriveShiftPneumatic().set(false);
	}

	@Override
	protected void execute() {
		Play.playRecording();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
