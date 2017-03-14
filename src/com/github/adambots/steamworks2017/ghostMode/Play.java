package com.github.adambots.steamworks2017.ghostMode;

import java.io.BufferedReader;
import java.io.FileReader;

import org.usfirst.frc.team245.robot.Constants;

import com.github.adambots.steamworks2017.drive.Drive;

public class Play {
	static String[][] recording = new String[1000][5];
	static int index = 0;
	static int snapshotNum = 0;
	static double startTime;
	static double time;

	public static void readRecording(String autonMode) {
		BufferedReader buffer;
		String line;

		try {
			buffer = new BufferedReader(new FileReader(autonMode));
			while ((line = buffer.readLine()) != null) {
				String[] snapshot = line.split(", ");
				recording[index] = snapshot;
				index++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void init() {
		startTime = System.nanoTime();
	}

	public static double interpolate(double val1, double val2, double time1, double time2, double time) {
		double slope = (val2 - val1) / (time2 - time1);
		double value = slope * (time - time1) + val1;
		return value;
	}

	public static void playRecording() {
		int snapshotBoundNum = Math.max(0, Math.min(snapshotNum, index - 1));
		time = System.nanoTime() - startTime;
		double speed = 0;
		double turn = 0;

		double timePast = Double.parseDouble(recording[snapshotBoundNum - 1][Constants.timeIndex]);
		double timeCurrent = Double.parseDouble(recording[snapshotBoundNum][Constants.timeIndex]);
		double timeFuture = Double.parseDouble(recording[snapshotBoundNum + 1][Constants.timeIndex]);

		double speedPast = Double.parseDouble(recording[snapshotBoundNum - 1][Constants.triggerIndex]);
		double speedCurrent = Double.parseDouble(recording[snapshotBoundNum][Constants.triggerIndex]);
		double speedFuture = Double.parseDouble(recording[snapshotBoundNum + 1][Constants.triggerIndex]);

		double turnPast = Double.parseDouble(recording[snapshotBoundNum - 1][Constants.joyIndex]);
		double turnCurrent = Double.parseDouble(recording[snapshotBoundNum][Constants.joyIndex]);
		double turnFuture = Double.parseDouble(recording[snapshotBoundNum + 1][Constants.joyIndex]);

		if (time == timeCurrent) {
			speed = speedCurrent;
			turn = turnCurrent;
		} else if (time < timeCurrent) {
			speed = interpolate(speedPast, speedCurrent, timePast, timeCurrent, time);
			turn = interpolate(turnPast, turnCurrent, timePast, timeCurrent, time);
		} else if (time > timeCurrent) {
			speed = interpolate(speedCurrent, speedFuture, timeCurrent, timeFuture, time);
			turn = interpolate(turnCurrent, turnFuture, timeCurrent, timeFuture, time);
		}

		Drive.drive(speed, turn);

		snapshotNum++;
	}
}
