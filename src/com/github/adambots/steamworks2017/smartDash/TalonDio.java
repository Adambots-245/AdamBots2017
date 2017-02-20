package com.github.adambots.steamworks2017.smartDash;
import org.usfirst.frc.team245.robot.Constants;

import com.ctre.CANTalon;


public class TalonDio {
	//Will return if current is going to drive motors
		public static boolean driveEncodDio(CANTalon CANTalon1, CANTalon CANTalon2){
			double outputCurrent1 = CANTalon1.getOutputCurrent();
			double outputCurrent2 = CANTalon2.getOutputCurrent();
			if(outputCurrent1 != 0 || outputCurrent2 != 0){
				return true;
			}else{
				return false;
			}
			
		}
		
		//Will return if current is going to Climbing motor
		public static boolean climbEncodDio(CANTalon CANTalon){
			double outputCurrent = CANTalon.getOutputCurrent();
			if (outputCurrent > 0){
				return true;
			}else{
				return false;
			}
		}
		//Will return true if motor is about to stall
		public static boolean CIMStall(CANTalon CANTalon3){
			double outputCurrent = CANTalon3.getOutputCurrent();
			if (outputCurrent >= Constants.MOTOR_CIM_STALL_CURRENT){
				return true;
			}else{
				return false;
			}
		}
}
