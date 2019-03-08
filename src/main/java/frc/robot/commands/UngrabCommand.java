/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class UngrabCommand extends Command {
  private long time;

  public UngrabCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchManipulatorSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    time = System.currentTimeMillis() + 500;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(System.currentTimeMillis() < time && Robot.hatchManipulatorSubsystem.goodbool) {
      Robot.hatchManipulatorSubsystem.setHatchMotorValue(.1);
    } else {
      Robot.hatchManipulatorSubsystem.setHatchMotorValue(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hatchManipulatorSubsystem.setHatchMotorValue(0);
    Robot.hatchManipulatorSubsystem.goodbool=true;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
