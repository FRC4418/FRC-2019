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

import java.util.ArrayList;

import edu.wpi.first.wpilibj.*;
//import frc.robot.subsystems.DriveSubsystem;
/**
 * Add your docs here
 */
public class DrivePIDSubsystem extends PIDSubsystem {
  /**
   * Add your docs here.
   */

   public ArrayList<Double> outputs = new ArrayList<Double>();

  public DrivePIDSubsystem() {
    // Intert a subsystem name and PID values here
    super("DrivePIDSubsystem", 0.5,0,0);
    setAbsoluteTolerance(5);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to 
    // enable() - Enables the PID controller.
  }

  public double[] getOutputs(){
    double[] out = new double[outputs.size()];
    for(int i = 0; i < out.length; i++){
      out[i] = outputs.get(i);
    }
    return out;
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

    return Robot.driveSubsystem.getLeftDriveEncoder();
  }

  @Override
  protected void usePIDOutput(double output) {
   /* double GyroAngle = Robot.driveSubsystem.getGyroValue();

    if (GyroAngle > 90.0)
      GyroAngle = 90.0;

    double errorPercentage = GyroAngle / 90.0;
    */

    outputs.add(output);

    SmartDashboard.putNumber("Enocder Value: ", Robot.driveSubsystem.getDistance());
    SmartDashboard.putNumber("left enc: ", Robot.driveSubsystem.getLeftDriveEncoder());
    SmartDashboard.putNumber("right enc: ", Robot.driveSubsystem.getRightDriveEncoder());
    SmartDashboard.putNumber("Current PID Value: ", Robot.drivepidsubsystem.getPosition());
    SmartDashboard.putNumber("output: ", output);

    Robot.driveSubsystem.tankDrive(output/1d, output/1d);

    /*
    if (errorPercentage < 2.0 && errorPercentage > -2.0) {
      Robot.driveSubsystem.tankDrive(-output, -output); 
    } else if (errorPercentage > 0) {
      Robot.driveSubsystem.tankDrive(-output * errorPercentage, -output); 
    } else {
      Robot.driveSubsystem.tankDrive(-output, -output * -errorPercentage); 
    }
    */
  }
}
