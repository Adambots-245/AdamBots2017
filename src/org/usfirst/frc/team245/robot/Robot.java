package org.usfirst.frc.team245.robot;


import com.github.adambots.steamworks2017.auton.Gear;

import com.github.adambots.steamworks2017.auton.SendableChooserValue;

import com.github.adambots.steamworks2017.climb.Climb;
import com.github.adambots.steamworks2017.drive.Drive;
import com.github.adambots.steamworks2017.intake.Intake;
import com.github.adambots.steamworks2017.networkTables.NetworkTables;
import com.github.adambots.steamworks2017.score.Score;
//import com.github.adambots.steamworks2017.score.Sweeper;
import com.github.adambots.steamworks2017.smartDash.Dash;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
//	final String defaultAuto = "Default";
//	final String customAuto = "My Auto";
//	String autoSelected;
//	SendableChooser<String> chooser = new SendableChooser<>();

	
	private int autonomousNumber;
	private int backupNumber;
	
	Command autonomousCommand;
	SendableChooser autoChooser;
	SendableChooser backup;

	/*
	 * creation of sendable chooser and
	 */


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	private String state;
	private String lastState;

	public void robotInit() {
		


		
//		chooser.addDefault("Default Auto", defaultAuto);
//		chooser.addObject("My Auto", customAuto);
//		SmartDashboard.putData("Auto choices", chooser);

			autoChooser = new SendableChooser();
			autoChooser.addDefault("Do nothing", new SendableChooserValue(1, Constants.VISION_WORKING));
			autoChooser.addObject("Cross baseline", new SendableChooserValue(2, Constants.VISION_WORKING));
			autoChooser.addObject("Baseline Center", new SendableChooserValue(3, Constants.VISION_WORKING));
			autoChooser.addObject("Left gear lift", new SendableChooserValue(4, Constants.VISION_WORKING));
			autoChooser.addObject("Right gear lift", new SendableChooserValue(5, Constants.VISION_WORKING));
			autoChooser.addObject("Front Gear lift", new SendableChooserValue(6, Constants.VISION_WORKING));
			autoChooser.addObject("Left Hopper", new SendableChooserValue(7, Constants.VISION_WORKING));
			autoChooser.addObject("Right Hopper", new SendableChooserValue(8, Constants.VISION_WORKING));
			autoChooser.addObject("Score then Gear Left", new SendableChooserValue(9, Constants.VISION_WORKING));
			autoChooser.addObject("Score then Gear Right", new SendableChooserValue(10, Constants.VISION_WORKING));
			
			
			SmartDashboard.putData("Autonomous paths", autoChooser);
		
		
			backup = new SendableChooser();
			backup.addDefault("Do nothing", new SendableChooserValue(1, Constants.VISION_FAIL));
			backup.addObject("Cross baseline", new SendableChooserValue(2, Constants.VISION_FAIL));
			backup.addObject("Baseline Center", new SendableChooserValue(3, Constants.VISION_FAIL));
			backup.addObject("Front gear lift", new SendableChooserValue(6, Constants.VISION_FAIL));
			backup.addObject("left Hopper", new SendableChooserValue (7, Constants.VISION_FAIL));
			backup.addObject("right Hopper", new SendableChooserValue (8, Constants.VISION_FAIL));
			SmartDashboard.putData("Camera is not working", backup);
		

		state = "disabled";
		lastState = "disabled";
		try{
			Actuators.init();
			Drive.init();
			Sensors.init();


		} catch(Exception e){
			System.out.println("Errors occurred during initialization.");
			System.out.println(e.getMessage());
		}
		System.out.println("Initialization is complete.");
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override

	public void autonomousInit() {
		// runs the autonomous smartdashboard display for auton
		
		if (NetworkTables.getControlsTable().getBoolean("camera0", false)){
			autonomousNumber = ((SendableChooserValue) autoChooser.getSelected()).getNumber();
		} else if(!NetworkTables.getControlsTable().getBoolean("camera0", false)){
			backupNumber = ((SendableChooserValue) autoChooser.getSelected()).getBackupNumber();
		}
			
			//TODO: Add the methods for the code
			switch(autonomousNumber){
				
			default:
			case 1:				//do nothing
				break;
			
			case 2: 			//cross baseline
				break;
			
			case 3:				//baseline Center 
				break;
			
			case 4:				//left gear lift
				break;
			
			case 5: 			//right gear lift
				break;
			
			case 6:				//front gear lift
				break;
			
			case 7:				//left hopper
				break;
			
			case 8:				//right Hopper
				break;
			
			case 9:				//score then gear left
				break;

			case 10:			//score then gear right
				break;
				
			}
				
		
		autonomousCommand = (Command) autoChooser.getSelected();
		Scheduler.getInstance().run();
		
		

		state = "auton";
		// autoSelected = chooser.getSelected();
		// // autoSelected = SmartDashboard.getString("Auto Selector",
		// // defaultAuto);
		// System.out.println("Auto selected: " + autoSelected);
		NetworkTables.getControlsTable().putBoolean("auton", true);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		if (state == "auton") {
			lastState = "auton";
		}
		// switch (autoSelected) {
		// case customAuto:
		// // Put custom auto code here
		// break;
		// case defaultAuto:
		// default:
		// // Put default auto code here
		// break;
		// }
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if (state.equals("teleop")) {
			lastState = "teleop";
		}
		state = "teleop";

		if (lastState.equals("auton")) {
			NetworkTables.getControlsTable().putBoolean("auton", false);
		}

		NetworkTables.putStream(Gamepad.primary.getX() || Gamepad.secondary.getX());
		/*
		 * Primary Controllers Controls
		 */
		// TODO: confirm right trigger forward, left trigger reverse
		// Drive controls
		Drive.drive(-Gamepad.primary.getLeftX(), Gamepad.primary.getTriggers()); // TODO:
																					// FIGURE
																					// OUT
																					// WHY
																					// WE
																					// NEED
																					// TO
																					// FLIP
																					// THESE
																					// //negative
																					// because
																					// of
																					// motor
																					// polarity,
																					// driving
																					// with
																					// triggers
																					// for
																					// speed
																					// and
																					// left
																					// joy
																					// for
																					// turning
		Drive.shift(Gamepad.primary.getA(), Gamepad.primary.getY()); // shifting
																		// with
																		// A low
																		// gear
																		// and Y
																		// high
																		// gear
		Drive.shiftToggle(Gamepad.primary.getLB());

		// Climb controls
		Climb.climbStopPrimary(Gamepad.primary.getDPadLeft()); // runs climbStop
																// using left on
																// the DPad -
																// Primary
		// Climb.climbSafetyTogglePrimary(Gamepad.primary.getBack()); //toggles
		// safety if pressed 3 times

		// Gear controls
		Score.dispenseGear(Gamepad.primary.getB() || Gamepad.secondary.getDPadUp());

		/*
		 * Secondary Controllers Controls
		 */
		// Intake controls
		Intake.intake(Gamepad.secondary.getRightButton()); // runs intake with
															// Clicking in the
															// Right Joystick on
															// second controller
		Intake.intakeSpeed(Gamepad.secondary.getRightY()); // Override Y Button
		Intake.intakeDirection(Gamepad.secondary.getRightX()); // Override Y
																// Button
		Intake.intakeJam(Gamepad.secondary.getLB()); // Runs the unjamming
														// procedure for a max
														// of 3 seconds per
														// press
		// Intake.intakeSafety(Gamepad.secondary.getStart()); //Have to press 3
		// times to toggle the safety
		Intake.intakeIn(Gamepad.secondary.getA()); // Toggles Intake running
													// into the robot at full
													// speed
		Intake.intakeRun(Gamepad.secondary.getRB()); // Runs all stuff for
														// intake in(conveyor
														// and intake motor)
		Intake.intakeOut(Gamepad.secondary.getB());
		// Climb controls
		Climb.climbStopSecondary(Gamepad.secondary.getDPadRight()); // runs
																	// climbStop
																	// using
																	// left on
																	// the DPad
																	// -
																	// Secondary
		Climb.climbStartSecondary(Gamepad.secondary.getDPadLeft()); // runs
																	// climbStart
																	// using
																	// right on
																	// the DPad
																	// -
																	// Secondary
		Climb.climbSafetyToggleSecondary(Gamepad.secondary.getBack()); // Have
																		// to
																		// press
																		// 3
																		// times
																		// to
																		// toggle
																		// the
																		// safety

		// Gear controls
		// Score.gearLock(Gamepad.secondary.getStart(),
		// Gamepad.secondary.getBack());

		// Outtake Controls
		// Score.outtakeToggle(Gamepad.secondary.getLB());

		// Conveyor Controls

		Score.conveyor(Gamepad.secondary.getLeftButton()); // runs conveyor with
															// Clicking in the
															// Left Joystick on
															// second controller
		Score.conveyorSpeed(Gamepad.secondary.getLeftY());
		Score.conveyorDirection(Gamepad.secondary.getLeftX());
		Score.conveyorIn(Gamepad.secondary.getY());

		// Sweeper
		// Sweeper.sweeperMotion(Gamepad.secondary.getTriggers());

		Dash.driveMode();

		SmartDashboard.putString("Controls Table", NetworkTables.getControlsTable().getKeys().toString());
		SmartDashboard.putString("Stream", NetworkTables.getControlsTable().getString("stream", "nothing"));

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
