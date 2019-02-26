/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDriveCommand;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DriverStation;
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

  private double experimentalA;
  private double experimentalC;
  private double experimentalCutoff;

  private double deadzone;

  private enum InputMapModes {
    IMM_LINEAR, IMM_SQUARE, IMM_CUBE, IMM_S
  }

  //Instantiate the subsystem
  public DriveSubsystem() {
    leftDriveMotor1 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_A_TALON_SRX_ID);
    leftDriveMotor2 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_B_TALON_SRX_ID);
    rightDriveMotor1 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_A_TALON_SRX_ID);
    rightDriveMotor2 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_B_TALON_SRX_ID);
    robotDrive = new RobotDrive(leftDriveMotor1, leftDriveMotor2, rightDriveMotor1, rightDriveMotor2);

    leftDriveEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_CHANNELA_ID, RobotMap.DRIVE_LEFT_ENCODER_CHANNELB_ID);
    rightDriveEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_CHANNELA_ID, RobotMap.DRIVE_RIGHT_ENCODER_CHANNELB_ID);
    driveGyro = new AnalogGyro(RobotMap.DRIVE_GYRO_ID);
    driveAccel = new BuiltInAccelerometer();
    frontDriveDistance = new Ultrasonic(RobotMap.DRIVE_FRONT_DISTANCE_PING_ID, RobotMap.DRIVE_FRONT_DISTANCE_ECHO_ID);
    backDriveDistance = new Ultrasonic(RobotMap.DRIVE_BACK_DISTANCE_PING_ID, RobotMap.DRIVE_BACK_DISTANCE_ECHO_ID);

    leftDriveMotor2.follow(leftDriveMotor1);
    rightDriveMotor2.follow(rightDriveMotor1);

    setLeftBrakemode(false);
    setRightBrakemode(false);

    //htDriveMotor1.setInverted(true);
    //rightDriveMotor2.setInverted(true);

    driveGyro.initGyro();
    driveGyro.calibrate();

    leftDriveEncoder.setDistancePerPulse(RobotMap.DRIVE_ENCODER_DISTANCE_PER_PULSE);
    rightDriveEncoder.setDistancePerPulse(RobotMap.DRIVE_ENCODER_DISTANCE_PER_PULSE);
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();

    frontDriveDistance.setEnabled(true);
    backDriveDistance.setEnabled(true);

    experimentalA = -1.3;
    experimentalC = 0.07;
    experimentalCutoff = Math.sqrt(-2 * Math.pow(experimentalC, 2) * Math.log(1 / experimentalA));

    deadzone = .05;
  }

  //control left motor
  public void setLeftMotorValue(double motorValue){
    leftDriveMotor1.set(ControlMode.PercentOutput, motorValue);
  }

  //control right motor
  public void setRightMotorValue(double motorValue){
    rightDriveMotor1.set(ControlMode.PercentOutput, motorValue);
  }

  // set the left breaks to break or coast
  public void setLeftBrakemode(boolean isBraking) {
    // when true, set to breaking mode
    if(isBraking) {
      leftDriveMotor1.setNeutralMode(NeutralMode.Brake);
      leftDriveMotor2.setNeutralMode(NeutralMode.Brake);
    } else { // else set to coast
      leftDriveMotor1.setNeutralMode(NeutralMode.Coast);
      leftDriveMotor2.setNeutralMode(NeutralMode.Coast);
    }
  }

  // set the right breaks to break or coast
  public void setRightBrakemode(boolean isBraking) {
    // when true, set to breaking mode
    if(isBraking) {
      rightDriveMotor1.setNeutralMode(NeutralMode.Brake);
      rightDriveMotor2.setNeutralMode(NeutralMode.Brake);
    } else { // else set to coast
      rightDriveMotor1.setNeutralMode(NeutralMode.Coast);
      rightDriveMotor2.setNeutralMode(NeutralMode.Coast);
    }
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

  // A simple wrapper for tank drive that converts a double array to
  // the correct values
  public void tankDrive(double[] values) {
    tankDrive(values[0], values[1]);
  }

  // A fancy function that causes the left and right values to snap to the average
  // when they are close to the average
  public double[] experimentalTeleopTankDrive(double[] values) {
    double avg = (values[0] + values[1]) / 2;
    double absDiff = Math.abs(values[0] - values[1]); // The difference between the two inputs
    double magnetism; // how strongly the output is magnetized to a value

    // the function dips below 0
    // if in the part of the function above 0
    if(absDiff > experimentalCutoff) {
      // when the difference between the inputs is great, set magnetism to very nearly 1
      // when the difference is not much, near 0.2, have the magnetism begin fall towards 0 
      magnetism = -experimentalA * Math.pow( Math.E, 
                                        (-Math.pow(absDiff, 2 )) / 
                                        (2 * Math.pow( experimentalC, 2 )) ) + 1;
    } else { // else, in the part of the function below 0
      // set the magnetism to 0
      // this creates a small zone in which the magnetism will be 0
      magnetism = 0;
    }

    // the output is attracted to the input if the magnetism is near 1
    // the output is attracted to the average if the magnetism is near 0
    values[0] = values[0]*magnetism + avg*(1-magnetism);
    values[1] = values[1]*magnetism + avg*(1-magnetism);

    return values;
  }

  // apply a deadzone to the inputs
  public double[] deadzoneTankDrive(double[] values) {
    // if the value is within the deadzone set it to 0
    if(Math.abs(values[0]) < deadzone) {
      values[0] = 0;
    }

    // second verse, same as the first
    if(Math.abs(values[1]) < deadzone) {
      values[1] = 0;
    }

    return values;
  }

  public double[] autoBreakTankDrive(double[] values) {
    // if the input is 0, set break, else don't
    if(values[0] == 0) {
      setLeftBrakemode(true);
    } else {
      setLeftBrakemode(false);
    }

    if(values[1] == 0) {
      setRightBrakemode(true);
    } else {
      setRightBrakemode(false);
    }

    return values;
  }

  // Apply a custom curve function to the input
  public double inputMap(double value, InputMapModes inputMapMode) {
    switch(inputMapMode) {
      case IMM_SQUARE: // square the input
        value = Math.pow(value, 2);
        break;
      case IMM_CUBE: // cube the input
        value = Math.pow(value, 3);
        break;
      case IMM_S: // apply an s shaped curve to the input
        // TODO
        break;
      default: // apply no curve
        DriverStation.reportWarning("Using the default input map for some reason, use a different one or fix this ya big dum dum.", false);
      case IMM_LINEAR: // linear is also the defalt, but, without warning messages
        // nothing happens yo, 1:1 mapping
    }
    
    return value;
  }

  // Allow for two values to be mapped at once using different mappings
  public double[] inputMapWrapper(double[] values, InputMapModes inputMapModeFor0, InputMapModes inputMapModeFor1) {
    // apply the map for the first value
    values[0] = inputMap(values[0], inputMapModeFor0);

    // apply the map for the second value
    values[1] = inputMap(values[1], inputMapModeFor1);

    return values;
  }

  // Allow for two values to be mapped at once using the same mapping
  public double[] inputMapWrapper(double[] values, InputMapModes inputMapModeForBoth) {
    // apply the map for the first value
    values[0] = inputMap(values[0], inputMapModeForBoth);

    // apply the map for the second value
    values[1] = inputMap(values[1], inputMapModeForBoth);

    return values;
  }

  public void arcadeDrive(double forwardValue, double angleValue) {
    if(frontSide) {
      robotDrive.arcadeDrive(forwardValue, -angleValue);
    } else {
      robotDrive.arcadeDrive(-forwardValue, -angleValue);
    }
  }

  public void arcadeDrive(double[] values) {
    arcadeDrive(values[0], values[1]);
  }

  // a wrapper around tank drive that sets stuff up to be better optimized for teleop controll
  public void teleopTankDriveWrapper(double leftValue, double rightValue) {
    // Convert to an array to allow for easy data transmission
    double[] values = {leftValue, rightValue};

    // do fancy array manipulation stuffs
    values = inputMapWrapper(values, InputMapModes.IMM_CUBE);
    values = experimentalTeleopTankDrive(values);
    values = deadzoneTankDrive(values);
    values = autoBreakTankDrive(values);

    // use the modified arrays to drive the robot
    tankDrive(values);
  }

  // a wrapper around arcade drive that sets stuff up to be better optimized for teleop controll
  public void teleopArcadeDriveWrapper(double forwardValue, double angleValue) {
    // Convert to an array to allow for easy data transmission
    double[] values = {forwardValue, angleValue};

    // do fancy array manipulation stuffs
    values = inputMapWrapper(values, InputMapModes.IMM_SQUARE, InputMapModes.IMM_SQUARE);
    values = deadzoneTankDrive(values);

    // use the modified arrays to drive the robot
    arcadeDrive(values);
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
