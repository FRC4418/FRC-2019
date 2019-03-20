/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbControlCommand;
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
  // DigitalInput frontLimitSwitchA;
  // DigitalInput frontLimitSwitchB;
  // DigitalInput backLimitSwitchA;
  // DigitalInput backLimitSwitchB;


  // Constructor.

  public ClimbSubsystem() {
    // frontClimbEncoder = new Encoder(2,3,false,Encoder.EncodingType.k4X);
    // frontClimbEncoder = new Encoder(RobotMap.CLIMBER_FRONT_ENCODER_CHANNELA_ID, RobotMap.CLIMBER_FRONT_ENCODER_CHANNELB_ID);
    // backClimbEncoder = new Encoder(RobotMap.CLIMBER_BACK_ENCODER_CHANNELA_ID, RobotMap.CLIMBER_BACK_ENCODER_CHANNELB_ID);
    frontClimbMotor = new TalonSRX(RobotMap.CLIMBER_FRONT_TALON_SRX_ID);
    backClimbMotor = new TalonSRX(RobotMap.CLIMBER_BACK_TALON_SRX_ID);
    //frontClimbEncoder.setDistancePerPulse(RobotMap.CLIMBER_ENCODER_DISTANCE_PER_PULSE);
    // backClimbEncoder.setDistancePerPulse(RobotMap.CLIMBER_ENCODER_DISTANCE_PER_PULSE);
    // frontLimitSwitchA = new DigitalInput(RobotMap.CLIMBER_FRONT_ENCODER_CHANNELA_ID);
    // frontLimitSwitchB = new DigitalInput(RobotMap.CLIMBER_FRONT_ENCODER_CHANNELB_ID);
    // backLimitSwitchA = new DigitalInput(RobotMap.CLIMBER_BACK_ENCODER_CHANNELA_ID);
    // backLimitSwitchB = new DigitalInput(RobotMap.CLIMBER_BACK_ENCODER_CHANNELB_ID);
    setClimbLegsBack(0);
    setClimbLegsFront(0);
    //resetBothClimbEncoders();
    // frontClimbEncoder.reset();
  }
  
  @Override
  public void initDefaultCommand() {
   setDefaultCommand(new ClimbControlCommand());
}

  //The following methods are getters.
  public double getFrontEncoderDistanceValue() {
    // double distance = frontClimbEncoder.get();
    return 0;
  }

  public double getBackEncoderDistanceValue() {
    // double distance = backClimbEncoder.getDistance();
    return 0;
  }

  // The following methods are mutators.
  public void resetBothClimbEncoders() {
      // frontClimbEncoder.reset();
      // backClimbEncoder.reset();
  }

  public void resetFrontClimbEncoder() {
    // frontClimbEncoder.reset();
  }

  public void resetBackClimbEncoder() {
    // backClimbEncoder.reset();
  }
  // public boolean getFrontLimitSwitchAValue(){
  //   return frontLimitSwitchA.get();
  // }

  // public boolean getFrontLimitSwitchBValue(){
  //   return frontLimitSwitchB.get();
  // }

  // public boolean getBackLimitSwitchAValue(){
  //   return backLimitSwitchA.get();
  // }

  // public boolean getBackLimitSwitchBValue(){
  //   return backLimitSwitchB.get();
  // }

  public void setClimbLegsFront(double motorValue) {
    frontClimbMotor.set(ControlMode.PercentOutput, motorValue);
      
  }

  public void setClimbLegsBack(double motorValue) {
    backClimbMotor.set(ControlMode.PercentOutput, motorValue);

  }
}
