package com.github.adambots.steamworks2017.smartDash;

import org.usfirst.frc.team245.robot.Gamepad;
import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Vibrations {
	
	//Will make the secondary controller Rumble if
	public static void climbStallVibrate(double intensity){
		//Will only set the Rumble if CIMStall is returning true(current draw is over Constants.MOTOR_CIM_STALL_CURRENT
		if(TalonDio.CIMStall(Actuators.getClimbMotor())){
			Gamepad.secondary.setRumbleBoth(intensity);
		}else{
			Gamepad.secondary.setRumbleBoth(Constants.NO_RUMBLE);
		}
	}
	
	public static void timeLeftVibrate(double intensity, double timeLeft){
		//TODO: Check if rumbles for one second
		//will vibrate controllers if the timeLeft is a certain value
		double time = DriverStation.getInstance().getMatchTime();
		//TO
		//for 45.5seconds left
		//			45			45			45			46
		//for 44.6 seconds left
		//			44			45			45			45
		//for 46.5 seconds left
		//			46			45			45			47
		if(Math.floor(time) >= timeLeft && timeLeft <= Math.ceil(time)){
			Gamepad.primary.setRumbleLeft(intensity);
			Gamepad.secondary.setRumbleLeft(intensity);
		}else{
			Gamepad.primary.setRumbleLeft(Constants.NO_RUMBLE);
			Gamepad.secondary.setRumbleLeft(Constants.NO_RUMBLE);
		}
	}
}
