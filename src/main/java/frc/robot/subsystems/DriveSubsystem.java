/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDriveCommand;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
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
  private WPI_TalonSRX leftDriveMotor1;
  private WPI_TalonSRX leftDriveMotor2;
  private WPI_TalonSRX rightDriveMotor1;
  private WPI_TalonSRX rightDriveMotor2;
  private RobotDrive robotDrive;
  private Encoder leftDriveEncoder;
  private Encoder rightDriveEncoder;
  private AnalogGyro driveGyro;
  private BuiltInAccelerometer driveAccel;
  private Ultrasonic frontDriveDistance;
  private Ultrasonic backDriveDistance;

  private boolean frontSide = true;
  private boolean arcadeDrive = false;

  //Instantiate the subsystem
  public DriveSubsystem() {
    leftDriveMotor1 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_A_TALON_SRX_ID);
    leftDriveMotor2 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_B_TALON_SRX_ID);
    rightDriveMotor1 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_A_TALON_SRX_ID);
    rightDriveMotor2 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_B_TALON_SRX_ID);
    robotDrive = new RobotDrive(leftDriveMotor1, leftDriveMotor2, rightDriveMotor1, rightDriveMotor2);

    leftDriveEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_CHANNELA_ID, RobotMap.DRIVE_LEFT_ENCODER_CHANNELB_ID);
    rightDriveEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_CHANNELA_ID, RobotMap.DRIVE_RIGHT_ENCODER_CHANNELB_ID);
    //driveGyro = new AnalogGyro(RobotMap.gyroID);
    driveAccel = new BuiltInAccelerometer();
    frontDriveDistance = new Ultrasonic(RobotMap.DRIVE_FRONT_DISTANCE_PING_ID, RobotMap.DRIVE_FRONT_DISTANCE_ECHO_ID);
    backDriveDistance = new Ultrasonic(RobotMap.DRIVE_BACK_DISTANCE_PING_ID, RobotMap.DRIVE_BACK_DISTANCE_ECHO_ID);

    leftDriveMotor2.follow(leftDriveMotor1);
    rightDriveMotor2.follow(rightDriveMotor1);

    //htDriveMotor1.setInverted(true);
    //rightDriveMotor2.setInverted(true);

    //driveGyro.initGyro();
    //driveGyro.calibrate();

    leftDriveEncoder.setDistancePerPulse(RobotMap.DRIVE_ENCODER_DISTANCE_PER_PULSE);
    rightDriveEncoder.setDistancePerPulse(RobotMap.DRIVE_ENCODER_DISTANCE_PER_PULSE);
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
    //robotDrive.tankDrive(leftValue, rightValue);
    if(frontSide){
      robotDrive.tankDrive(-leftValue, -rightValue);
    }else{
      robotDrive.tankDrive(rightValue, leftValue);
    }
  }

  public void arcadeDrive(double forwardValue, double angleValue) {
    if(frontSide) {
      robotDrive.arcadeDrive(forwardValue, -angleValue);
    } else {
      robotDrive.arcadeDrive(-forwardValue, -angleValue);
    }
  }

  public void teleopTankDrive(double leftValue, double rightValue){
    //function is cubic divided by 100 thousand. Math can be tweaked to tune handling, but it is pretty good now
    double leftOutput = (Math.pow(leftValue, 3)/100000);
    double rightOutput = (Math.pow(rightValue, 3)/100000);
    tankDrive(leftOutput, rightOutput);
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
    return 0.0;//driveGyro.getAngle();
  }

  //reset gyro
  public void resetGyro(){
    //driveGyro.calibrate();
  }

  //read left encoder
  public double getLeftDriveEncoder(){
    return leftDriveEncoder.getDistance();
  }

  //read right encoder
  public double getRightDriveEncoder(){
    return -rightDriveEncoder.getDistance();
  }

  public double getDistance(){
    if(frontSide){
      return (getRightDriveEncoder() + getLeftDriveEncoder()) / 2.0;
    }else{
      return (getRightDriveEncoder() + getLeftDriveEncoder()) / -2.0;
    }
  }

  //reset left encoder
  public void resetLeftDriveEncoder(){
    leftDriveEncoder.reset();
  }

  //reset right encoder
  public void resetRightDriveEncoder(){
    rightDriveEncoder.reset();
  }

  public void resetEncoders(){
    resetLeftDriveEncoder();
    resetRightDriveEncoder();
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
    resetEncoders();
  }

  public boolean isArcadeDrive() {
    return arcadeDrive;
  }

  public void setArcadeDrive(boolean mode) {
    arcadeDrive = mode;
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
