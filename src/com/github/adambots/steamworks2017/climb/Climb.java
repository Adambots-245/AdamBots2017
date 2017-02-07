package com.github.adambots.steamworks2017.climb;

import org.usfirst.frc.team245.robot.Actuators;

public class Climb {
	
	
public static void climb(){
	if(Actuators.getClimbingMotor().get() == Actuators.MAX_MOTOR_SPEED){
		Actuators.getClimbingMotor().set(Actuators.STOP_MOTOR);
	}else{
		Actuators.getClimbingMotor().set(Actuators.MAX_MOTOR_SPEED);
	}
	}
}
