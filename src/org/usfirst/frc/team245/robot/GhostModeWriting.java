package org.usfirst.frc.team245.robot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class GhostModeWriting {
	private static long startTime;
	private static double[][] arrayContainer = new double[750][4];
	// private double[] myIntArray = new double[3];
	private static int index;
	private static boolean recordingGhost = true;
	int x = 0;
	static boolean writing = false;

	public static void Recording() {
		if (index <= 0) {
			index = 0;
			startTime = System.nanoTime();
		}
		if (index >= 750) {
			writingArray();
			recordingGhost = false;
		}
		if (recordingGhost == true) {
			System.out.println("Starting Recording");
			double triggerValue = Gamepad.primary.getTriggers();
			double joystickValue = Gamepad.primary.getLeftX();
			boolean rightBumper = Gamepad.secondary.getRightButton();
			double rightBumperValue;

			if (rightBumper == true) {
				rightBumperValue = 1;
			} else {
				rightBumperValue = 0;
			}

			long timeStamp = System.nanoTime() - startTime;
			double[] myIntArray = { triggerValue, joystickValue, rightBumperValue, (double) timeStamp };
			arrayContainer[index] = myIntArray;
			index++;

		}
	}

	public static void ghostModeInit() {
		startTime = System.nanoTime();
		index = 0;
		arrayContainer = new double[750][3];

	}

	public static void writingArray() {
		if (writing) {
			return;

		}
		System.out.println("Starting Write");
		try {
			PrintWriter writer = new PrintWriter("/home/root/ghostMode.txt", "UTF-8");
			if (recordingGhost == true && arrayContainer.length >= 749) {
				for (index = 0; index <= 749; index++) {
					writer.print(index);
					for (int i = 0; i < 4; i++) {
						writer.print(" ");
						writer.print(arrayContainer[index][i]);
					}
					writer.println();
				}

				Arrays.toString(arrayContainer);
				writer.close();
				writing = true;

			} else {

			}

		} catch (IOException e) {

		}

	}

	public static void main(String[] args) throws InterruptedException {
		ghostModeInit();
		while (writing == false) {
			Recording();
			// writingArray();
			Thread.sleep(20);
		}
	}
}
