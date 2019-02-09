/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbControlCommand extends Command {
  public ClimbControlCommand() {
    // Use requires() here to declare subsystem dependencies
    super ("ClimbControl");
    // eg. requires(chassis);
    requires(Robot.climbsubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climbsubsystem.setClimbLegsBack(OI.getFrontClimberAxis());
    Robot.climbsubsystem.setClimbLegsFront(-OI.getBackClimberAxis());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climbsubsystem.setClimbLegsFront(0.0);
    Robot.climbsubsystem.setClimbLegsBack(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
