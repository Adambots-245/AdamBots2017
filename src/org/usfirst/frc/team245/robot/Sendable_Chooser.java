package org.usfirst.frc.team245.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Sendable_Chooser {

	Command autonomousCommand;
	SendableChooser autoChooser; 
	
	autoChooser = new SendableChooser();
	autoChooser.addobject("Straight path", new path1());
	autoChooser.addobject("Cross baseline", new path());
	Smartdashboard.putdata("Autonomous paths", autoChooser);
	
	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();
		Scheduler.getInstance().run();
		}
	}
}
