/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PIDController;
import frc.robot.Robot;

public class DriveDistanceCommand extends Command {

  private PIDController pid;

  public DriveDistanceCommand(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    pid = new PIDController(setpoint, 1,0,0);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSubsystem.resetEncoders();
    pid.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute(){
    double output = pid.getOutput(Robot.driveSubsystem.getLeftDriveEncoder());
    Robot.driveSubsystem.tankDrive(output, output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pid.isFinished(5);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pid.disable();
    System.out.print("ENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDEDENDED");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
