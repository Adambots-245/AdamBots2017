package com.github.adambots.steamworks2017.autonModes;

import com.github.adambots.steamworks2017.ghostMode.Play;

import edu.wpi.first.wpilibj.command.Command;

public class RedBoiler extends Command{
	static boolean hasFinished = false;
	
	public RedBoiler() {

	}

	@Override
	protected void initialize() {
		Play.readRecording("/home/admin/redBoiler.txt"); //TODO: Add actual path
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
