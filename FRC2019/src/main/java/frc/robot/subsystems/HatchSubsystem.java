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
/**
 * Add your docs here.
 */
public class HatchSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
private TalonSRX hatchArmJointMotor;
private Encoder hatchArmJointEncoder;

public HatchSubsystem(int hatchArmJointMotorID, int hatchArmJointEncoderID) {

  hatchArmJointMotor = new TalonSRX(hatchArmJointMotorID);
  hatchArmJointEncoder = new Encoder(hatchArmJointEncoderID,hatchArmJointEncoderID);
  
}

public void setHatchMotorValue(double motorValue){
  hatchArmJointMotor.set(ControlMode.PercentOutput, motorValue);
}

public void getHatchEncoderValue(){
  hatchArmJointEncoder.getDistance();
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