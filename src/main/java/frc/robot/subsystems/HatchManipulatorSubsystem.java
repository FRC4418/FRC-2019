/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class HatchManipulatorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX hatchManpulatorAMotor,
                    hatchManpulatorBMotor;
  
  public HatchManipulatorSubsystem() {
    hatchManpulatorAMotor = new TalonSRX(RobotMap.hatchManipulatorMotorAID);
    hatchManpulatorBMotor = new TalonSRX(RobotMap.hatchManipulatorMotorBID);

    //hatchManpulatorBMotor.follow(hatchManpulatorAMotor);

    hatchManpulatorAMotor.setNeutralMode(NeutralMode.Coast);
    hatchManpulatorBMotor.setNeutralMode(NeutralMode.Coast);
  }
  
  public void setHatchMotorValue(double motorValue){
    hatchManpulatorAMotor.set(ControlMode.PercentOutput, motorValue);
    hatchManpulatorBMotor.set(ControlMode.PercentOutput, -motorValue);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new UngrabCommand());
  }
}
