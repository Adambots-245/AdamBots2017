package com.github.adambots.steamworks2017.smartDash;

import org.usfirst.frc.team245.robot.Sensors;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class SensorsDio {
	public static double PDPCurrent(PowerDistributionPanel powerDistro){
		return powerDistro.getTotalCurrent();
	}
	public static boolean getLimitSwitchMax() {
		return Sensors.getSweeperMaxLimitSwitch().get();
	}
	public static boolean getLimitSwitchMin() {
		return Sensors.getSweeperMinLimitSwitch().get();
	}
	public static boolean getClimbingLimitSwitch() {
		return Sensors.getClimbingCompleteSensor().get();
	}
	
}
