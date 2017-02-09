package com.github.adambots.steamworks2017.score;

import org.usfirst.frc.team245.robot.Actuators;

public class Score {
	
	static boolean buttonReleased;
	static boolean gearIsLocked = false;
	
	public static void dispenseGear(boolean gearButton){
		if(gearButton && !gearIsLocked){
			Actuators.getDispenseGearReturnPneumatic().set(false);
			Actuators.getDispenseGearAdvancePneumatic().set(true);
		}else if(!gearButton && !gearIsLocked){
			Actuators.getDispenseGearAdvancePneumatic().set(false);
			Actuators.getDispenseGearReturnPneumatic().set(true);
		}
	}
	
	//toggles if the gear system is locked in position or not
	public static void gearLock(boolean lock){
		if(!lock){
			buttonReleased = true;
		}
		if(lock && buttonReleased){
			buttonReleased = false;
			gearIsLocked = !gearIsLocked;
		}
	}
}
