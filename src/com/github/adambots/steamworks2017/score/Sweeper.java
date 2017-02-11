package com.github.adambots.steamworks2017.score;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Sweeper {
	
	public static void sweeperMotion(double trigger){
		if(trigger >= Constants.TRIGGER_PRESSED_RIGHT){
			Actuators.getSweeperReturnPneumatic().set(false);
			Actuators.getSweeperAdvancePneumatic().set(true);
		}else if(trigger <= Constants.TRIGGER_PRESSED_LEFT){
			Actuators.getSweeperAdvancePneumatic().set(false);
			Actuators.getSweeperReturnPneumatic().set(true);
		}
	}
}
