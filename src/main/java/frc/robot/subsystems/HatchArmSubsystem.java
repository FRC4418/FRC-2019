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
import frc.robot.commands.HatchArmCommand;
/**
 * Add your docs here.
 */
public class HatchArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
private TalonSRX hatchArmJointMotor;
private Encoder hatchArmJointEncoder;

public HatchArmSubsystem() {
  hatchArmJointMotor = new TalonSRX(RobotMap.HATCHE_ARM_JOINT_TALON_SRX_ID);
  hatchArmJointMotor.setNeutralMode(NeutralMode.Brake);

  hatchArmJointEncoder = new Encoder(RobotMap.HATCH_ARM_JOINT_ENCODER_CHANNELA_ID, RobotMap.HATCH_ARM_JOINT_ENCODER_CHANNELB_ID);
  hatchArmJointEncoder.setDistancePerPulse(RobotMap.HATCH_ARM_ENCODER_DISTANCE_PER_PULSE);
}


public void setHatchMotorValue(double motorValue){
  //sets motor to axis input
  hatchArmJointMotor.set(ControlMode.PercentOutput, motorValue);
}

public double getHatchEncoderValue(){
  return hatchArmJointEncoder.getDistance();
}

public void resetHatchEncoderValue(){
  hatchArmJointEncoder.reset();
}

  

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchArmCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}