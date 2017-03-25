package org.usfirst.frc.team245.robot;

import java.io.IOException;
import java.io.PrintWriter;

public class GhostModeWrite {
	private static long startTime;
	private static double[][] arrayContainer = new double[750][3];
	//private double[] myIntArray = new double[3];
	private static int index;
	private static boolean recordingGhost = false;
	double triggerValue;
	double joystickValue;
	long timeStamp;

	public static void Recording() { 
		if (index <= 0){
			index = 0;
			startTime = System.nanoTime();
		}
		if (index >= 750){
			writingArray();
			recordingGhost = false;
		}
		if (recordingGhost == true){
			System.out.println("Starting Recording");
			double triggerValue = Gamepad.primary.getTriggers();
			double joystickValue = Gamepad.primary.getLeftX();
			long timeStamp = startTime - System.nanoTime();
			double[] myIntArray = { triggerValue, joystickValue, (double) timeStamp };
			arrayContainer[index] = myIntArray;
			index++;
		}
		else {

		}
	} 
	public static void ghostModeInit() {
		startTime = System.nanoTime();
		index = 0;
		arrayContainer = new double[750][3];
		
	}
	public static void writingArray() {
		System.out.println("Starting Write");
		try{
			PrintWriter writer = new PrintWriter("/tmp/autonGhost.txt", "UTF-8");	
			if (recordingGhost == true && arrayContainer.length >= 750){
				for (index = 0;index < 750; index++){
					writer.print(index);
					for(int i = 0; i<3; i++){
						writer.print(" ");
						writer.print(arrayContainer[index][i]);
					}
					writer.println();
				}

				writer.println(arrayContainer);
				writer.close();

			}
			else{

			}

		}
		catch (IOException e) {

		}


	}
}

