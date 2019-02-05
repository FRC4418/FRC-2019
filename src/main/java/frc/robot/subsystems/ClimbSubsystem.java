/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  //objects.
  Encoder frontClimbEncoder;
  Encoder backClimbEncoder;
  TalonSRX frontClimbMotor;
  TalonSRX backClimbMotor;

  // Constructor.

  public ClimbSubsystem() {
    frontClimbEncoder = new Encoder(0, 1/*RobotMap.frontClimbEncoderChannelAID, RobotMap.frontClimbEncoderChannelBID*/);
    backClimbEncoder = new Encoder(RobotMap.backClimbEncoderChannelAID, RobotMap.backClimbEncoderChannelBID);
    frontClimbMotor = new TalonSRX(RobotMap.frontClimbMotorID);
    backClimbMotor = new TalonSRX(RobotMap.backClimbMotorID);
    setClimbLegsBack(0);
    setClimbLegsFront(0);
    resetBothClimbEncoders();
  }

  // The following methods are getters.
  public double getFrontEncoderDistanceValue() {
    double distance = frontClimbEncoder.getDistance();
    return distance;
  }

  public double getBackEncoderDistanceValue() {
    double distance = backClimbEncoder.getDistance();
    return distance;
  }

  // The following methods are mutators.
  public void resetBothClimbEncoders() {
      frontClimbEncoder.reset();
      backClimbEncoder.reset();
  }

  public void resetFrontClimbEncoder() {
    frontClimbEncoder.reset();
  }

  public void resetBackClimbEncoder() {
    backClimbEncoder.reset();
  }

  public void setClimbLegsFront(double motorValue) {
    frontClimbMotor.set(ControlMode.PercentOutput, motorValue);
  }

  public void setClimbLegsBack(double motorValue) {
    backClimbMotor.set(ControlMode.PercentOutput, motorValue);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
