package org.usfirst.frc.team245.robot;

import com.github.adambots.steamworks2017.climb.Climb;
import com.github.adambots.steamworks2017.drive.Drive;
import com.github.adambots.steamworks2017.intake.Intake;
import com.github.adambots.steamworks2017.score.Score;

import edu.wpi.first.wpilibj.IterativeRobot;


import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
//		chooser.addDefault("Default Auto", defaultAuto);
//		chooser.addObject("My Auto", customAuto);
//		SmartDashboard.putData("Auto choices", chooser);
		try{
			Actuators.init();
			Drive.init();
		} catch(Exception e){
			System.out.println("Errors occurred during initialization.");
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
//		autoSelected = chooser.getSelected();
//		// autoSelected = SmartDashboard.getString("Auto Selector",
//		// defaultAuto);
//		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
//		switch (autoSelected) {
//		case customAuto:
//			// Put custom auto code here
//			break;
//		case defaultAuto:
//		default:
//			// Put default auto code here
//			break;
//		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/*
		 * Primary Controllers Controls
		 */
		//TODO: confirm right trigger forward, left trigger reverse
		//Drive controls
		Drive.drive(Gamepad.primary.getTriggers(), Gamepad.primary.getLeftX()); //driving with triggers for speed and left joy for turning
		Drive.shift(Gamepad.primary.getA(), Gamepad.primary.getY()); //shifting with A low gear and Y high gear		
		Drive.shiftToggle(Gamepad.primary.getLB());
		
		//Climb controls
		Climb.climbStop(Gamepad.primary.getDPadLeft()); //runs climbStop using left on the DPad - Primary
		
		//Gear controls
		Score.dispenseGear(Gamepad.primary.getBack());
		
		/*
		 * Secondary Controllers Controls
		 */
		//Intake controls
		Intake.intake(Gamepad.secondary.getRightButton()); //runs intake with B on second controller
		Intake.intakeSpeed(Gamepad.secondary.getRightY());
		Intake.intakeDirection(Gamepad.secondary.getRightX());
		
		//Climb controls
		Climb.climbStop(Gamepad.secondary.getDPadLeft()); //runs climbStop using left on the DPad - Secondary
		Climb.climbStart(Gamepad.secondary.getDPadRight()); //runs climbStart using right on the DPad - Secondary
		
		//Gear controls
		Score.dispenseGear(Gamepad.secondary.getB());
		Score.gearLock(Gamepad.secondary.getStart());
		
		//Outtake Controls
		Score.outtakeToggle(Gamepad.secondary.getLB());
		
		//Conveyor Controls
		Score.conveyor(Gamepad.secondary.getLeftButton());
		Score.conveyorSpeed(Gamepad.secondary.getLeftY());
		Score.conveyorDirection(Gamepad.secondary.getLeftX());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

