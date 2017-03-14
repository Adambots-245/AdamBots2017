package com.github.adambots.steamworks2017.smartDash;

import org.usfirst.frc.team245.robot.Gamepad;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team245.robot.Actuators;
import org.usfirst.frc.team245.robot.Constants;

public class Vibrations {
	static double time = 47;
	static boolean buttonPressed = true;
	
	//Will make the secondary controller Rumble if
	public static void climbStallVibrate(double intensity){
		//Will only set the Rumble if CIMStall is returning true(current draw is over Constants.MOTOR_CIM_STALL_CURRENT
		if(TalonDio.CIMStall(Actuators.getClimbMotor())){

			Gamepad.secondary.setRumbleBoth(intensity);

			Gamepad.secondary.setRumbleBoth(intensity);

		}else{
			Gamepad.secondary.setRumbleBoth(Constants.NO_RUMBLE);
		}
	}
	
	public static void timeLeftVibrate(double intensity, double timeLeft){
		//TODO: Check if rumbles for one second
		
		//will vibrate controllers if the timeLeft is a certain value
		//commented out for testing
		double time = DriverStation.getInstance().getMatchTime();
//		if(Gamepad.primary.getX() && buttonPressed){
//			time = time-.2;
//			buttonPressed = false;
//		}else if(!Gamepad.secondary.getX()){
//			buttonPressed = true;
//		}
		SmartDashboard.putNumber("time", time);

		//TEST CODE TO MAKE SURE IT RUMBLES
//		if(Gamepad.secondary.getX()){
//		Gamepad.primary.setRumbleLeft(intensity);
//		}else{
//			Gamepad.primary.setRumbleLeft(Constants.NO_RUMBLE);
//		}
//		if(Gamepad.primary.getX()){
		//Uncomment below for regular
		if(time + 2 > timeLeft && time < timeLeft){
			Gamepad.primary.setRumbleLeft(intensity);
			Gamepad.secondary.setRumbleLeft(intensity);
		}else if(time < timeLeft){
			Gamepad.primary.setRumbleLeft(Constants.NO_RUMBLE);
			Gamepad.secondary.setRumbleLeft(Constants.NO_RUMBLE);
		}
	}
}
