package org.usfirst.frc.team245.robot;

import com.github.adambots.steamworks2017.auton.*;
import com.github.adambots.steamworks2017.auton.SendableChooserValue;
import com.github.adambots.steamworks2017.climb.Climb;
import com.github.adambots.steamworks2017.drive.Drive;
import com.github.adambots.steamworks2017.intake.Intake;
import com.github.adambots.steamworks2017.networkTables.NetworkTables;
import com.github.adambots.steamworks2017.score.Score;
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
	Command autonomousCommand;
	SendableChooser autoChooser;
	Command backupCommand;
	SendableChooser backupChooser;


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

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do nothing", new DoNothing());
		autoChooser.addObject("Cross baseline", new Baseline());
		autoChooser.addObject("Baseline Center", new BaselineCenter());
		autoChooser.addObject("Left gear lift", new DoNothing());
		autoChooser.addObject("Right gear lift", new DoNothing());
		autoChooser.addObject("Front Gear lift", new DoNothing());
		autoChooser.addObject("Left Hopper", new DoNothing());
		autoChooser.addObject("Right Hopper", new DoNothing());
		autoChooser.addObject("Score then Gear Left", new DoNothing());
		autoChooser.addObject("Score then Gear Right", new DoNothing());
		SmartDashboard.putData("Autonomous paths", autoChooser);


		backupChooser = new SendableChooser();
		backupChooser.addDefault("Do nothing", new DoNothing());
		backupChooser.addObject("Cross baseline", new DoNothing());
		backupChooser.addObject("Baseline Center", new DoNothing());
		backupChooser.addObject("Front gear lift", new DoNothing());
		backupChooser.addObject("left Hopper", new DoNothing());
		backupChooser.addObject("right Hopper", new DoNothing());
		SmartDashboard.putData("Camera is not working", backupChooser);
		SmartDashboard.putData(Scheduler.getInstance());
		
		state = "disabled";
		lastState = "disabled";
		try {
			Actuators.init();
			Sensors.init();
			NetworkTables.init();
		} catch (Exception e) {
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
		autonomousCommand = (Command) autoChooser.getSelected();
		backupCommand = (Command) backupChooser.getSelected();
		if (NetworkTables.getControlsTable().getBoolean("camera0", false)) {//Auto for working camera
			autonomousCommand.start();
			
		}
		else{
			backupCommand.start();

		}
		

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
		//TEST CODE FOR autoChooser
		System.out.println(autonomousCommand);
		System.out.println(backupCommand);
		
		
		Scheduler.getInstance().run();
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
		Drive.drive(-Gamepad.primary.getLeftX(), Gamepad.primary.getTriggers()); 
		Drive.shift(Gamepad.primary.getA(), Gamepad.primary.getY()); // shifting with A low gear and Y high gear
		Drive.shiftToggle(Gamepad.primary.getLB());

		// Climb controls
		Climb.climbStopPrimary(Gamepad.primary.getDPadLeft()); // runs climbStop using left on the DPad - Primary
		// Climb.climbSafetyTogglePrimary(Gamepad.primary.getBack()); //toggles safety if pressed 3 times

		// Gear controls
		Score.dispenseGear(Gamepad.primary.getB() || Gamepad.secondary.getDPadUp());

		/*
		 * Secondary Controllers Controls
		 */
		// Intake controls
		Intake.intake(Gamepad.secondary.getRightButton()); // runs intake with clicking in the Right Joystick on second controller
		Intake.intakeSpeed(Gamepad.secondary.getRightY()); // Override Y Button
		Intake.intakeDirection(Gamepad.secondary.getRightX()); // Override Y Button
		Intake.intakeJam(Gamepad.secondary.getLB()); // Runs the unjamming procedure for a max of 3 seconds per press
		// Intake.intakeSafety(Gamepad.secondary.getStart()); //Have to press 3 times to toggle the safety
		Intake.intakeIn(Gamepad.secondary.getA()); // Toggles Intake running into the robot at full speed
		Intake.intakeRun(Gamepad.secondary.getRB()); // Runs all stuff for intake in(conveyor and intake motor)
		Intake.intakeOut(Gamepad.secondary.getB());
		// Climb controls
		Climb.climbStopSecondary(Gamepad.secondary.getDPadRight()); // runs climbStop using left on the DPad - Secondary
		Climb.climbStartSecondary(Gamepad.secondary.getDPadLeft()); // runs climbStart using right on the DPad Secondary
		Climb.climbSafetyToggleSecondary(Gamepad.secondary.getBack()); // Have to press 3 times to toggle the safety

		// Gear controls
		// Score.gearLock(Gamepad.secondary.getStart(),
		// Gamepad.secondary.getBack());

		// Outtake Controls
		// Score.outtakeToggle(Gamepad.secondary.getLB());

		// Conveyor Controls

		Score.conveyor(Gamepad.secondary.getLeftButton()); // runs conveyor with clicking in the Left Joystick on second controller
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
