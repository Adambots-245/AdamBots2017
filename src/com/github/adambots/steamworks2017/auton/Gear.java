package com.github.adambots.steamworks2017.auton;

import org.usfirst.frc.team245.robot.Actuators;

import edu.wpi.first.wpilibj.Solenoid;

public class Gear {
	

public static void gear(){
	if(Actuators.getGearPneumatic().get()){
		Actuators.getGearPneumatic().set(false);
	}else{
		Actuators.getGearPneumatic().set(true);
	}
	}

}

