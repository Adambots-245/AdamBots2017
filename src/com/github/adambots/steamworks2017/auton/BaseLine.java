package com.github.adambots.steamworks2017.auton;

	 import com.ctre.CANTalon;
	 import edu.wpi.first.wpilibj.Notifier;
	 import com.ctre.CANTalon.TalonControlMode;
	 
	 public class BaseLine {
	 
	 	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
	 	
	 	private CANTalon _talon;
	 	
	 	private int _state = 0;
	 	
	 	private int _loopTimeout = -1;
	 	
	 	private boolean _bStart = false;
	 	
	 	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
	 	
	 	private static final int kMinPointsInTalon = 5;
	 	
	 	private static final int kNumLoopsTimeout = 10;
	 	
	 	class PeriodicRunnable implements java.lang.Runnable{
	 		public void run() { _talon.processMotionProfileBuffer();    }
	 	}
	 	
	 	Notifier _notifier = new Notifier(new PeriodicRunnable() );	
	 		
	 	
	 	
	 	
	 	public BaseLine(CANTalon talon) {
	 		_talon = talon; 
	 		_talon.changeMotionControlFramePeriod(5);
	 		_notifier.startPeriodic(0.005);
	 	}
	 	
	 	public void reset(){
	 		_talon.clearMotionProfileTrajectories();
	 		_setValue = CANTalon.SetValueMotionProfile.Disable;
	 		_state = 0;
	 		_loopTimeout = -1;
	 		_bStart = false;
	 	}
	 	
	 	public void control() {
			/* Get the motion profile status every loop */
			_talon.getMotionProfileStatus(_status);



			if (_loopTimeout < 0) {
				/* do nothing, timeout is disabled */
			} else {
				/* our timeout is nonzero */
				if (_loopTimeout == 0) {
					
					 // something is wrong. Talon is not present, unplugged, breaker
					 //tripped
					 
					instrumentation.OnNoProgress();
				} else {
					--_loopTimeout;
				}
			}

			/* first check if we are in MP mode */
			if (_talon.getControlMode() != TalonControlMode.MotionProfile) {
				
				_state = 0;
				_loopTimeout = -1;
			} else {
				
				switch (_state) {
					case 0: /* wait for application to tell us to start an MP */
						if (_bStart) {
							_bStart = false;
		
							_setValue = CANTalon.SetValueMotionProfile.Disable;
							startFilling();
							
							 // MP is being sent to CAN bus, wait a small amount of time
							 
							_state = 1;
							_loopTimeout = kNumLoopsTimeout;
						}
						break;
					case 1: 
//							  wait for MP to stream to Talon, really just the first few
//							  points
							 
						/* do we have a minimum number of points in Talon */
						if (_status.btmBufferCnt > kMinPointsInTalon) {
							/* start (once) the motion profile */
							_setValue = CANTalon.SetValueMotionProfile.Enable;
							/* MP will start once the control frame gets scheduled */
							_state = 2;
							_loopTimeout = kNumLoopsTimeout;
						}
						break;
					case 2: /* check the status of the MP */
						/*
						 * if talon is reporting things are good, keep adding to our
						 * timeout. Really this is so that you can unplug your talon in
						 * the middle of an MP and react to it.
						 */
						if (_status.isUnderrun == false) {
							_loopTimeout = kNumLoopsTimeout;
						}
						/*
						 * If we are executing an MP and the MP finished, start loading
						 * another. We will go into hold state so robot servo's
						 * position.
						 */
						if (_status.activePointValid && _status.activePoint.isLastPoint) {
							/*
							 * because we set the last point's isLast to true, we will
							 * get here when the MP is done
							 */
							_setValue = CANTalon.SetValueMotionProfile.Hold;
							_state = 0;
							_loopTimeout = -1;
						}
						break;
				}
			}
			/* printfs and/or logging */
			instrumentation.process(_status);
		}

		/** Start filling the MPs to all of the involved Talons. */
		private void startFilling() {
			/* since this example only has one talon, just update that one */
			startFilling(motionProfile.Points, motionProfile.kNumPoints);
		}

		private void startFilling(double[][] profile, int totalCnt) {

			/* create an empty point */
			CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

			/* did we get an underrun condition since last time we checked ? */
			if (_status.hasUnderrun) {
				/* better log it so we know about it */
				instrumentation.OnUnderrun();
				/*
				 * clear the error. This flag does not auto clear, this way 
				 * we never miss logging it.
				 */
				_talon.clearMotionProfileHasUnderrun();
			}
			/*
			 * just in case we are interrupting another MP and there is still buffer
			 * points in memory, clear it.
			 */
			_talon.clearMotionProfileTrajectories();

			/* This is fast since it's just into our TOP buffer */
			for (int i = 0; i < totalCnt; ++i) {
				/* for each point, fill our structure and pass it to API */
				point.position = profile[i][0];
				point.velocity = profile[i][1];
				point.timeDurMs = (int) profile[i][2];
				point.profileSlotSelect = 0; /* which set of gains would you like to use? */
				point.velocityOnly = false; /* set true to not do any position
											 * servo, just velocity feedforward
											 */
				point.zeroPos = false;
				if (i == 0)
					point.zeroPos = true; /* set this to true on the first point */

				point.isLastPoint = false;
				if ((i + 1) == totalCnt)
					point.isLastPoint = true; /* set this to true on the last point  */

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
