package com.github.adambots.steamworks2017.auton;

public class SendableChooserValue {
	private static int number;
	private static int backupNumber;
	
	public SendableChooserValue(int number, boolean visionWorking){
		if(visionWorking){
			SendableChooserValue.number = number;
		}else if(!visionWorking){
			SendableChooserValue.backupNumber = number;
		}
		
		SendableChooserValue.number = number;
	}
	public static int getNumber(){
		return number;
	}
	public static int getBackupNumber(){
		return backupNumber;
	}
}
