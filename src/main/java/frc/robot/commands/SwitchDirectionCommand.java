/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SwitchDirectionCommand extends Command {
  private boolean direction;
  private boolean amOverridingDirection;

  // allow for the direction to be toggled
  public SwitchDirectionCommand() {
    // Use requires() here to declare subsystem dependencies
    super("SwitchDirection<T>");
    // eg. requires(chassis);
    //requires(Robot.driveSubsystem);
    amOverridingDirection = false;
  }

  // allow for the direction to be manually set
  public SwitchDirectionCommand(boolean directionForward) {
    super("SwitchDirection<S>");
    direction = directionForward;
    amOverridingDirection = directionForward;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // set the direction that is forward, true is forward
    if(amOverridingDirection) {
      RobotMap.setRobotDirection(direction);
    } else {
      RobotMap.toggleRobotDirection();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
