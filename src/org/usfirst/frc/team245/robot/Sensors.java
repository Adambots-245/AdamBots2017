package org.usfirst.frc.team245.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;


public class Sensors {
	//Digital
	private static PowerDistributionPanel powerDistro;
	private static DigitalInput sweeperMinLimitSwitch;
	private static DigitalInput sweeperMaxLimitSwitch;
	private static DigitalInput climbingCompleteSensor;
	
	//Analog
	private static AnalogGyro robotGyro;
	
	public static void init(){
		
		powerDistro = new PowerDistributionPanel(Constants.POWER_DISTRIBUTION_PANEL_PORT);
		sweeperMinLimitSwitch = new DigitalInput(Constants.SWEEPER_MIN_LIMIT_SWITCH_PORT);
		sweeperMaxLimitSwitch = new DigitalInput(Constants.SWEEPER_MAX_LIMIT_SWITCH_PORT);
		climbingCompleteSensor = new DigitalInput(Constants.CLIMBING_COMPLETE_BUMP_PORT);
	}
	
	/*
	 * @return powerDistro
	 */
	public static PowerDistributionPanel getPowerDistro(){
		return powerDistro;
	}
	/*
	 * @return sweeperMinLimitSwitch
	 */
	public static DigitalInput getSweeperMinLimitSwitch() {
		return sweeperMinLimitSwitch;
	}
	/*
	 * @return sweeperMaxLimitSwitch
	 */
	public static DigitalInput getSweeperMaxLimitSwitch() {
		return sweeperMaxLimitSwitch;
	}
	/*
	 * @return climbingCompleteSensor
	 */
	public static DigitalInput getClimbingCompleteSensor(){
		return climbingCompleteSensor;
	}
}
