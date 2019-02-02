/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  
  // Climb Subsystem IDs
  public static int frontClimbEncoderChannelAID = 2, 
                    frontClimbEncoderChannelBID = 3, 
                    backClimbEncoderChannelAID = 4, 
                    backClimbEncoderChannelBID = 5, 
                    frontClimbMotorID = 30, 
                    backClimbMotorID = 31;

  // Hatch Subsystem IDs
  public static int hatchArmJointMotorID = 40, 
                    hatchArmJointEncoderChannelAID = 0, 
                    hatchArmJointEncoderChannelBID = 1;
  public static double climbEncoderDistancePerPulse = 360d / 256d;

  // Drive Subsystem IDs
  public static int leftDriveMotor1ID = 10, 
                    leftDriveMotor2ID = 11, 
                    rightDriveMotor1ID = 20, 
                    rightDriveMotor2ID = 21, 
                    leftDriveEncoderChannelAID = 6, 
                    leftDriveEncoderChannelBID = 7, 
                    rightDriveEncoderChannelAID = 8, 
                    rightDriveEncoderChannelBID = 9, 
                    gyroID = 0,
                    frontDriveDistancePing = 10, 
                    frontDriveDistanceEcho = 11, 
                    backDriveDistancePing = 12, 
                    backDriveDistanceEcho = 13;
  public static double distancePerPulse  = (15.24 * Math.PI) / 256; // diameter * pi = circumference. circumference / 256 = distance per pulse
}
