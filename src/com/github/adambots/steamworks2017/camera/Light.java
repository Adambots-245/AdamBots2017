package com.github.adambots.steamworks2017.camera;

import org.usfirst.frc.team245.robot.Actuators;

public class Light {
	private static boolean toggleReleased = true;
	
	public static void toggleLight(boolean toggleButton){
		if (!toggleButton) {
			// this only runs if button is released
			toggleReleased = true;
		}
		if (toggleButton && Actuators.getSpotlight().get() && toggleReleased) {
			Actuators.getSpotlight().set(false); 
			toggleReleased = false;
		} else if (toggleButton && !Actuators.getSpotlight().get() && toggleReleased) {
			Actuators.getSpotlight().set(true);
			toggleReleased = false;
		}
	}
}