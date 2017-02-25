package com.github.adambots.steamworks2017.auton;

public class SendableChooserValue {
	private int number;
	private int backupNumber;
	
	public SendableChooserValue(int number, boolean visionWorking){
		if(visionWorking){
			this.number = number;
		}else if(!visionWorking){
			this.backupNumber = number;
		}
		
		this.number = number;
	}
	public int getNumber(){
		return number;
	}
	public int getBackupNumber(){
		return backupNumber;
	}
}
