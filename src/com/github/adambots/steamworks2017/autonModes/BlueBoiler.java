package com.github.adambots.steamworks2017.autonModes;

import com.github.adambots.steamworks2017.ghostMode.Play;

import edu.wpi.first.wpilibj.command.Command;

public class BlueBoiler extends Command{
	static boolean hasFinished = false;
	
	public BlueBoiler() {

	}

	@Override
	protected void initialize() {
		Play.readRecording("/home/admin/blueBoiler.txt"); //TODO: Add actual path
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
