/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class HatchArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
private TalonSRX hatchArmJointMotor;
private Encoder hatchArmJointEncoder;
private boolean isForward;

public HatchArmSubsystem() {

  hatchArmJointMotor = new TalonSRX(RobotMap.hatchArmJointMotorID);
  hatchArmJointEncoder = new Encoder(2, 3/*RobotMap.hatchArmJointEncoderChannelAID, RobotMap.hatchArmJointEncoderChannelBID*/);
  
}

public void setHatchMotorValue(double motorValue){
  hatchArmJointMotor.set(ControlMode.PercentOutput, motorValue);
}
public boolean isIsForward(){
  return isForward;
}

public void toggleIsForward(){
  isForward = !isForward;
}

public void moveHatchArm(){
  resetHatchEncoderValue();
  if (isForward){
    setHatchMotorValue(-50);
  }
  else{
    setHatchMotorValue(50);
  }
}

public boolean isDone(){
  if(isForward){
    return getHatchEncoderValue() < -60;
  }
  else{
    return getHatchEncoderValue() > 60;
  }
}

public double getHatchEncoderValue(){
  return hatchArmJointEncoder.getDistance();
}

public void resetHatchEncoderValue(){
  hatchArmJointEncoder.reset();
}

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}