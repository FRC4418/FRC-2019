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

  // Constructor.

  public ClimbSubsystem() {
    frontClimbEncoder = new Encoder(RobotMap.CLIMBER_FRONT_ENCODER_CHANNELA_ID, RobotMap.CLIMBER_FRONT_ENCODER_CHANNELB_ID);
    backClimbEncoder = new Encoder(RobotMap.CLIMBER_BACK_ENCODER_CHANNELA_ID, RobotMap.CLIMBER_BACK_ENCODER_CHANNELB_ID);
    frontClimbMotor = new TalonSRX(RobotMap.CLIMBER_FRONT_TALON_SRX_ID);
    backClimbMotor = new TalonSRX(RobotMap.CLIMBER_BACK_TALON_SRX_ID);
    frontClimbEncoder.setDistancePerPulse(RobotMap.CLIMBER_ENCODER_DISTANCE_PER_PULSE);
    backClimbEncoder.setDistancePerPulse(RobotMap.CLIMBER_ENCODER_DISTANCE_PER_PULSE);
    setClimbLegsBack(0);
    setClimbLegsFront(0);
    resetBothClimbEncoders();
  }
  
  @Override
  public void initDefaultCommand() { // create control command.
   setDefaultCommand(new ClimbControlCommand());
}

  // The following methods are getters.
  public double getFrontEncoderDistanceValue() { // gets the front climb encoder's value in distance.
    double distance = frontClimbEncoder.getDistance();
    return distance;
  }

  public double getBackEncoderDistanceValue() { // gets the back climb encoder's value in distance.
    double distance = backClimbEncoder.getDistance();
    return distance;
  }

  // The following methods are mutators.
  public void resetBothClimbEncoders() { // resets both encoders to 0.
      frontClimbEncoder.reset();
      backClimbEncoder.reset();
  }

  public void resetFrontClimbEncoder() { //resets the front encoder to 0.
    frontClimbEncoder.reset();
  }

  public void resetBackClimbEncoder() { //resets the back encoder to 0.
    backClimbEncoder.reset();
  }

  public void setClimbLegsFront(double motorValue) { // starts the front motor.
    frontClimbMotor.set(ControlMode.PercentOutput, motorValue);
  }

  public void setClimbLegsBack(double motorValue) { // starts the back motor.
    backClimbMotor.set(ControlMode.PercentOutput, motorValue);
  }

}
