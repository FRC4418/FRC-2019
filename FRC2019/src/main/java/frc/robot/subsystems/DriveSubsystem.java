/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.commands.TankDriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX leftDriveMotor1;
  private TalonSRX leftDriveMotor2;
  private TalonSRX rightDriveMotor1;
  private TalonSRX rightDriveMotor2;
  private AnalogGyro gyro;

  public DriveSubsystem(int leftDriveMotor1ID, int leftDriveMotor2ID, int rightDriveMotor1ID, int rightDriveMotor2ID, int gyroID){
    leftDriveMotor1 = new TalonSRX(leftDriveMotor1ID);
    leftDriveMotor2 = new TalonSRX(leftDriveMotor2ID);
    rightDriveMotor1 = new TalonSRX(rightDriveMotor1ID);
    rightDriveMotor2 = new TalonSRX(rightDriveMotor2ID);
    gyro = new AnalogGyro(gyroID);

    leftDriveMotor2.follow(leftDriveMotor1);
    rightDriveMotor2.follow(rightDriveMotor2);

    rightDriveMotor1.setInverted(true);

    gyro.initGyro();
    gyro.calibrate();
  }

  public void setLeftMotorValue(double motorValue){
    leftDriveMotor1.set(ControlMode.PercentOutput, motorValue);
  }

  public void setRightMotorValue(double motorValue){
    rightDriveMotor1.set(ControlMode.PercentOutput, motorValue);
  }

  public void tankDrive(double leftValue, double rightValue){
    leftDriveMotor1.set(ControlMode.PercentOutput, leftValue);
    rightDriveMotor1.set(ControlMode.PercentOutput, rightValue);
  }

  public double getLeftDriveValue(){
    return leftDriveMotor1.getMotorOutputPercent();
  }

  public double getRightDriveValue(){
    return rightDriveMotor1.getMotorOutputPercent();
  }

  public double getGyroValue(){
    return gyro.getAngle();
  }

  public void resetGyro(){
    gyro.calibrate();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new TeleopCommand());

    setDefaultCommand(new TankDriveCommand());
  }
}
