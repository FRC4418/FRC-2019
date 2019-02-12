/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class TurnToAnglePIDSubsystem extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  public TurnToAnglePIDSubsystem(double setpoint) {
    // Intert a subsystem name and PID values here
    super("TurnToAngle", 2, 0, 0);
    setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.

    setSetpoint(setpoint);
    Robot.driveSubsystem.resetGyro();
  }

  public void changeSetpoint(double setpoint){
    setSetpoint(setpoint);
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
    return Robot.driveSubsystem.getGyroValue();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    Robot.driveSubsystem.tankDrive(output, output);
  }
}