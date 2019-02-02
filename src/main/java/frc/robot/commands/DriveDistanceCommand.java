/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Arrays;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveDistanceCommand extends Command {
  public DriveDistanceCommand(double distance) {
    Robot.drivepidsubsystem.setSetpoint(distance);
    Robot.driveSubsystem.resetEncoders();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSubsystem.resetEncoders(); 
    Robot.driveSubsystem.resetGyro();
    Robot.drivepidsubsystem.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.drivepidsubsystem.getPosition() - Robot.drivepidsubsystem.getSetpoint()) < 5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    DriverStation.reportWarning("ended pid", false);

    System.out.println(Arrays.toString(Robot.drivepidsubsystem.getOutputs()));

    SmartDashboard.putNumberArray("outputs", Robot.drivepidsubsystem.getOutputs());

    Robot.drivepidsubsystem.disable();
    Robot.driveSubsystem.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
