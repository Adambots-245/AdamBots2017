package com.github.adambots.steamworks2017.autonModes;

import com.github.adambots.steamworks2017.ghostMode.Play;

import edu.wpi.first.wpilibj.command.Command;

public class Dummy3 extends Command{
	static boolean hasFinished = false;
	
	public Dummy3() {

	}

	@Override
	protected void initialize() {
		Play.readRecording("Dummy3.txt"); //TODO: Add actual path
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
