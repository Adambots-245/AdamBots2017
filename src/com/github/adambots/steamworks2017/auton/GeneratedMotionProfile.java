package com.github.adambots.steamworks2017.auton;

public class GeneratedMotionProfile {			
	public static final int kNumPoints = 185;
	public static final int MAX_RPM = 240;
	public static int iterator = 0;
	public static int timesRun = 0;
	public static double [][]Points;
	
	public static double readProfile(double[][] Points){
		timesRun++;
		if(timesRun % 2 == 0){
			iterator++;
		}
		
		return Points[iterator][1] / MAX_RPM;
	}
	//Wheel diameter is 4"
	//Wheel Circumference is approx. 12.55"
	//Baseline is 93.3" from the end of the field (use 95 or higher for most calculations)
	
}
	
		
