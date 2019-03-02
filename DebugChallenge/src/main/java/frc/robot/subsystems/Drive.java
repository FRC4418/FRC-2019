/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.commands.driveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX leftDriveA;
  private TalonSRX leftDriveB;
  private TalonSRX rightDriveA;
  private TalonSRX rightDriveB;

  public Drive(){
    leftDriveA = new TalonSRX(1);
    leftDriveB = new TalonSRX(2);
    rightDriveA = new TalonSRX(1);
    rightDriveB = new TalonSRX(2);
  }

  public void drive(){
    Joystick stick = OI.getDriverJoystick();
    leftDriveA.set(ControlMode.PercentOutput, stick.getRawAxis(0));
    leftDriveB.set(ControlMode.PercentOutput, stick.getRawAxis(1));
    rightDriveA.set(ControlMode.PercentOutput, stick.getRawAxis(0));
    rightDriveB.set(ControlMode.PercentOutput, stick.getRawAxis(1));
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new driveCommand());
    // setDefaultCommand(new MySpecialCommand());
  }
}
