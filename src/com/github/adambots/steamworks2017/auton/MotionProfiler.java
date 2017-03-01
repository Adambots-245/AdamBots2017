package com.github.adambots.steamworks2017.auton;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Notifier;
import com.ctre.CANTalon.TalonControlMode;

public class MotionProfiler {
	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
	
	private CANTalon _talon;
	// For state machine
	private int _state = 0;
	// Timeout for loop, at -1 to disable
	private int _loopTimeout = -1;
	// True when start() is called
	private boolean _bStart = false;
	
	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
	
	// Wait this many trajectory points before firing Motion Profile
	private static final int kMinPointsInTalon = 5;
	// State timeout (each loop is 20 ms)
	private static final int kNumLoopsTimeout = 10;
	
	// Call twice as many times as the loop
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {
	    	_talon.processMotionProfileBuffer();
	    	
	    }
	}
	
	Notifier _notifer = new Notifier(new PeriodicRunnable());

	public MotionProfiler(CANTalon talon) {
		_talon = talon;
		// Since the Motion Profiler runs at 10ms per point, control framerate and notifier will be half
		_talon.changeMotionControlFramePeriod(5);
		_notifer.startPeriodic(0.005);
	}

	// Resets when inactive/disabled
	public void reset() {
		_talon.clearMotionProfileTrajectories();
		_setValue = CANTalon.SetValueMotionProfile.Disable;
		_state = 0;
		_bStart = false;
	}

	// Called every loop
	public void control() {
		_talon.getMotionProfileStatus(_status);

		if (_loopTimeout < 0) {
			// Do nothing
		} else if (_loopTimeout == 0) {
			// Something is wrong
			Instrumentation.OnNoProgress();
		} else {
			--_loopTimeout;
		}

		// Check if in Motion Profile mode
		if (_talon.getControlMode() != TalonControlMode.MotionProfile) {
			_state = 0;
			_loopTimeout = -1;
		} else {
			// Currently in Motion Profile mode
			switch (_state) {
				case 0:
					if (_bStart) {
						_bStart = false;
	
						_setValue = CANTalon.SetValueMotionProfile.Disable;
						startFilling();
						_state = 1;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1:
					// Wait for Motion Profile to be sent to CAN 
					if (_status.btmBufferCnt > kMinPointsInTalon) {
						_setValue = CANTalon.SetValueMotionProfile.Enable;
						
						// Will start again once the control frame is scheduled
						_state = 2;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 2:
					// Keep adding to timeout if talon says things are good
					if (_status.isUnderrun == false) {
						_loopTimeout = kNumLoopsTimeout;
					}
					// If Motion Profile finished, load another
					if (_status.activePointValid && _status.activePoint.isLastPoint) {
						_setValue = CANTalon.SetValueMotionProfile.Hold;
						_state = 0;
						_loopTimeout = -1;
					}
					break;
			}
		}
		// Prints/logs
		Instrumentation.process(_status);
	}
	
	private void startFilling() {
		startFilling(GeneratedMotionProfile.Points, GeneratedMotionProfile.kNumPoints);
	}
	
	private void startFilling(double[][] profile, int totalCnt) {
		// Creates empty point
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		// Is the condition underrun?
		if (_status.hasUnderrun) {
			// Log the condition
			Instrumentation.OnUnderrun();
			// Clear error
			_talon.clearMotionProfileHasUnderrun();
		}
		// Clear in case interrupting another Motion Profile
		_talon.clearMotionProfileTrajectories();

		for (int i = 0; i < totalCnt; ++i) {
			// Fill structure and pass to API
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0;
			// Set to true if only using velocity feedforward
			point.velocityOnly = false;
			
			point.zeroPos = false;
			if (i == 0)
				// True only on first point
				point.zeroPos = true;

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				// True only on last point
				point.isLastPoint = true;
			_talon.pushMotionProfileTrajectory(point);

		}
	}

	// Called by application to start buffered Motion Profile
	public void startMotionProfile() {
		_bStart = true;
	}

	CANTalon.SetValueMotionProfile getSetValue() {
		return _setValue;
	}
}