/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.*;
//import frc.robot.subsystems.DriveSubsystem;
/**
 * Add your docs here.
 */
public class DrivePIDSubsystem extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  public DrivePIDSubsystem() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", 0.5, 0, 1);
    setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);
    
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to 
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;

    return -Robot.driveSubsystem.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    double GyroAngle = Robot.driveSubsystem.getGyroValue();

    if (GyroAngle > 90.0)
      GyroAngle = 90.0;

    double errorPercentage = GyroAngle / 90.0;

    SmartDashboard.putNumber("Enocder Value: ", Robot.driveSubsystem.getDistance());
    SmartDashboard.putNumber("left enc: ", Robot.driveSubsystem.getLeftDriveEncoder());
    SmartDashboard.putNumber("right enc: ", Robot.driveSubsystem.getRightDriveEncoder());
    SmartDashboard.putNumber("Current PID Value: ", Robot.drivepidsubsystem.getPosition());

    if (errorPercentage < 2.0 && errorPercentage > -2.0) {
      Robot.driveSubsystem.tankDrive(output, output); 
    } else if (errorPercentage > 0) {
      Robot.driveSubsystem.tankDrive(output * errorPercentage, output); 
    } else {
      Robot.driveSubsystem.tankDrive(output, output * -errorPercentage); 
    }
  }
}
