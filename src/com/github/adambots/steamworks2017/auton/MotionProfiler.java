package com.github.adambots.steamworks2017.auton;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Notifier;
import com.ctre.CANTalon.TalonControlMode;

public class MotionProfiler {
	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
	private CANTalon _talon;
	private int _state = 0;
	private int _loopTimeout = -1;
	private boolean _bStart = false;
	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
	private static final int kMinPointsInTalon = 5;
	private static final int kNumLoopsTimeout = 10;
	
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {
	    	_talon.processMotionProfileBuffer();
	    }
	}
	
	Notifier _notifer = new Notifier(new PeriodicRunnable());

	public MotionProfiler(CANTalon talon) {
		_talon = talon;
		_talon.changeMotionControlFramePeriod(5);
		_notifer.startPeriodic(0.005);
	}

	public void reset() {
		_talon.clearMotionProfileTrajectories();
		_setValue = CANTalon.SetValueMotionProfile.Disable;
		_state = 0;
		_bStart = false;
	}

	public void control() {
		_talon.getMotionProfileStatus(_status);

		if (_loopTimeout < 0) {
			// Do nothing
		} else {
			if (_loopTimeout == 0) {
				instrumentation.OnNoProgress();
			} else {
				--_loopTimeout;
			}
		}

		if (_talon.getControlMode() != TalonControlMode.MotionProfile) {
			_state = 0;
			_loopTimeout = -1;
		} else {
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
					if (_status.btmBufferCnt > kMinPointsInTalon) {
						_setValue = CANTalon.SetValueMotionProfile.Enable;
						_state = 2;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 2:
					if (_status.isUnderrun == false) {
						_loopTimeout = kNumLoopsTimeout;
					}
					if (_status.activePointValid && _status.activePoint.isLastPoint) {
						_setValue = CANTalon.SetValueMotionProfile.Hold;
						_state = 0;
						_loopTimeout = -1;
					}
					break;
			}
		}
		instrumentation.process(_status);
	}
	
	private void startFilling() {
		startFilling(GeneratedMotionProfile.Points, GeneratedMotionProfile.kNumPoints);
	}
	
	private void startFilling(double[][] profile, int totalCnt) {
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		if (_status.hasUnderrun) {
			instrumentation.OnUnderrun();
			_talon.clearMotionProfileHasUnderrun();
		}
		_talon.clearMotionProfileTrajectories();

		for (int i = 0; i < totalCnt; ++i) {
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; 
			point.velocityOnly = false;
			point.zeroPos = false;
			
			if (i == 0)
				point.zeroPos = true;

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true;
			_talon.pushMotionProfileTrajectory(point);
		}
	}

	void startMotionProfile() {
		_bStart = true;
	}

	CANTalon.SetValueMotionProfile getSetValue() {
		return _setValue;
	}
}
