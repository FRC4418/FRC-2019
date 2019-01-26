/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.OI;

public class TeleopDriveCommand extends Command {
  public TeleopDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    super("TelopDrive");
    // eg. requires(chassis);
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    SmartDashboard.putNumber("Enocder Value: ", Robot.driveSubsystem.getDistance());
    SmartDashboard.putNumber("left enc: ", Robot.driveSubsystem.getLeftDriveEncoder());
    SmartDashboard.putNumber("right enc: ", Robot.driveSubsystem.getRightDriveEncoder());
    SmartDashboard.putNumber("Current PID Value: ", Robot.drivepidsubsystem.getPosition());
    SmartDashboard.putNumber("climb enc: ", Robot.climbsubsystem.getFrontEncoderDistanceValue());

    Robot.driveSubsystem.tankDrive(OI.getDriverJoystick().getRawAxis(1), OI.getDriverJoystick().getRawAxis(5));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSubsystem.stopDrive();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
