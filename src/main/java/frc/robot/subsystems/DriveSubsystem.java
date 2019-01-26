/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDriveCommand;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // API Objects
  // Motors and encoders and gyroscopes and stuff
  // Allocate memory to speed up instantiation later
  private TalonSRX leftDriveMotor1;
  private TalonSRX leftDriveMotor2;
  private TalonSRX rightDriveMotor1;
  private TalonSRX rightDriveMotor2;
  private Encoder leftDriveEncoder;
  private Encoder rightDriveEncoder;
  private AnalogGyro driveGyro;
  private BuiltInAccelerometer driveAccel;
  private Ultrasonic frontDriveDistance;
  private Ultrasonic backDriveDistance;

  private boolean frontSide = true;

  //Instantiate the subsystem
  public DriveSubsystem(){
    leftDriveMotor1 = new TalonSRX(RobotMap.leftDriveMotor1ID);
    leftDriveMotor2 = new TalonSRX(RobotMap.leftDriveMotor2ID);
    rightDriveMotor1 = new TalonSRX(RobotMap.rightDriveMotor1ID);
    rightDriveMotor2 = new TalonSRX(RobotMap.rightDriveMotor2ID);
    leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderChannelAID, RobotMap.leftDriveEncoderChannelBID);
    rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderChannelAID, RobotMap.rightDriveEncoderChannelBID);
    driveGyro = new AnalogGyro(RobotMap.gyroID);
    driveAccel = new BuiltInAccelerometer();
    frontDriveDistance = new Ultrasonic(RobotMap.frontDriveDistancePing, RobotMap.frontDriveDistanceEcho);
    backDriveDistance = new Ultrasonic(RobotMap.backDriveDistancePing, RobotMap.backDriveDistanceEcho);

    leftDriveMotor2.follow(leftDriveMotor1);
    rightDriveMotor2.follow(rightDriveMotor1);

    rightDriveMotor1.setInverted(true);
    rightDriveMotor2.setInverted(true);

    driveGyro.initGyro();
    driveGyro.calibrate();

    leftDriveEncoder.setDistancePerPulse(RobotMap.distancePerPulse);
    rightDriveEncoder.setDistancePerPulse(RobotMap.distancePerPulse);
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();

    frontDriveDistance.setEnabled(true);
    backDriveDistance.setEnabled(true);
  }

  //control left motor
  public void setLeftMotorValue(double motorValue){
    leftDriveMotor1.set(ControlMode.PercentOutput, motorValue);
  }

  //control right motor
  public void setRightMotorValue(double motorValue){
    rightDriveMotor1.set(ControlMode.PercentOutput, motorValue);
  }

  //drive both motors at once
  public void tankDrive(double leftValue, double rightValue){
    if(frontSide){
      leftDriveMotor1.set(ControlMode.PercentOutput, -leftValue);
      rightDriveMotor1.set(ControlMode.PercentOutput, -rightValue);
    }else{
      leftDriveMotor1.set(ControlMode.PercentOutput, rightValue);
      rightDriveMotor1.set(ControlMode.PercentOutput, leftValue);
    }
  }

  //read left motor
  public double getLeftDriveValue(){
    return leftDriveMotor1.getMotorOutputPercent();
  }

  //read right motor
  public double getRightDriveValue(){
    return rightDriveMotor1.getMotorOutputPercent();
  }

  //read gyro angle
  public double getGyroValue(){
    return driveGyro.getAngle();
  }

  //reset gyro
  public void resetGyro(){
    driveGyro.calibrate();
  }

  //read left encoder
  public double getLeftDriveEncoder(){
    return leftDriveEncoder.getDistance();
  }

  //read right encoder
  public double getRightDriveEncoder(){
    return rightDriveEncoder.getDistance();
  }

  //reset left encoder
  public void resetLeftDriveEncoder(){
    leftDriveEncoder.reset();
  }

  //reset right encoder
  public void resetRightDriveEncoder(){
    rightDriveEncoder.reset();
  }

  //read acceleromter
  public double getDriveAccelX(){
    return driveAccel.getX();
  }

  public double getDriveAccelY(){
    return driveAccel.getY();
  }

  public double getDriveAccelZ(){
    return driveAccel.getZ();
  }

  // read front distance
  public double getFrontDriveDistance(){
    return frontDriveDistance.getRangeMM() / 10.0;
  }

  //read back distance
  public double getBackDriveDistance(){
    return backDriveDistance.getRangeMM() / 10.0;
  }

  //enable/disable front distance
  public void setFrontDriveDistanceEnable(boolean enable){
    frontDriveDistance.setEnabled(enable);
  }

  //enable/disable back distance
  public void setBackDriveDistanceEnable(boolean enable){
    backDriveDistance.setEnabled(enable);
  }

  //read which side is front
  public boolean isFrontSide(){
    return frontSide;
  }

  //swap the front and back of the robot
  public void swapFront(){
    frontSide = !frontSide;
  }

  public void stopDrive(){
    leftDriveMotor1.set(ControlMode.PercentOutput, 0);
    rightDriveMotor1.set(ControlMode.PercentOutput, 0);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TeleopDriveCommand());
  }
}
