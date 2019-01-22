/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OutputAllDataCommand extends Command {
  public OutputAllDataCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Hatch Encoder", Robot.hatchSubsystem.getHatchEncoderValue());
    SmartDashboard.putNumber("Climb Back Encoder", Robot.climbsubsystem.getBackEncoderDistanceValue());
    SmartDashboard.putNumber("Climb Front Encoder", Robot.climbsubsystem.getFrontEncoderDistanceValue());
    SmartDashboard.putNumber("Drive Left Drive Value", Robot.drivesubsystem.getLeftDriveValue());
    SmartDashboard.putNumber("Drive Right Drive Value", Robot.drivesubsystem.getRightDriveValue());
    SmartDashboard.putNumber("Drive Gyro Value", Robot.drivesubsystem.getGyroValue());
    SmartDashboard.putNumber("Drive Left Encoder", Robot.drivesubsystem.getLeftDriveEncoder());
    SmartDashboard.putNumber("Drive Right Encoder", Robot.drivesubsystem.getRightDriveEncoder());
    SmartDashboard.putNumber("Drive Accel X", Robot.drivesubsystem.getDriveAccelX());
    SmartDashboard.putNumber("Drive Accel Y", Robot.drivesubsystem.getDriveAccelY());
    SmartDashboard.putNumber("Drive Accel Z", Robot.drivesubsystem.getDriveAccelZ());
    SmartDashboard.putNumber("Drive Front Distance",Robot.drivesubsystem.getFrontDriveDistance());
    SmartDashboard.putNumber("Drive Back Distance", Robot.drivesubsystem.getBackDriveDistance());
    SmartDashboard.putBoolean("Drive Front", Robot.drivesubsystem.isFrontSide());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
