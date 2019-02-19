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
  // Controller abstractions
  private static final int GAMEPAD_AXIS_LEFT_X = 0, // Gamepad axis
                          GAMEPAD_AXIS_LEFT_Y = 1,
                          GAMEPAD_AXIS_RIGHT_X = 4,
                          GAMEPAD_AXIS_RIGHT_Y = 5,
                          GAMEPAD_AXIS_LEFT_TRIGGER = 2,
                          GAMEPAD_AXIS_RIGHT_TRIGGER = 3,
                          GAMEPAD_BUTTON_A = 1, // Gamepad buttons
                          GAMEPAD_BUTON_B = 2,
                          GAMEPAD_BUTON_X = 3,
                          GAMEPAD_BUTTON_Y = 4,
                          GAMEPAD_BUTON_LEFT_BUMPER = 5,
                          GAMEPAD_BUTON_RIGHT_BUMPER = 6,
                          GAMEPAD_BUTTON_BACK = 7,
                          GAMEPAD_BUTTON_START = 8,
                          GAMEPAD_BUTTON_LOGITECH = 9,
                          GAMEPAD_BUTTON_LEFT_JS = 10,
                          GAMEPAD_BUTTON_RIGHT_JS = 11,
                          X3D_AXIS_PITCH = 1, // X3d axis
                          X3D_AXIS_ROLL = 0,
                          X3D_AXIS_YAW = 2,
                          X3D_AXIS_OTHER = 3,
                          X3D_BUTTON_TRIGGER = 1, // X3d buttons
                          X3D_BUTTON_GRIP = 2,
                          X3D_BUTTON_3 = 3,
                          X3D_BUTTON_4 = 4,
                          X3D_BUTTON_5 = 5,
                          X3D_BUTTON_6 = 6,
                          X3D_BUTTON_7 = 7,
                          X3D_BUTTON_8 = 8,
                          X3D_BUTTON_9 = 9,
                          X3D_BUTTON_10 = 10,
                          X3D_BUTTON_11 = 11,
                          X3D_BUTTON_12 = 12;


  // Controller IDs
  public static final int X3D_LEFT_JOYSTICK_ID = 0, // Joysticks IDs
                          X3D_RIGHT_JOYSTICK_ID = 1,
                          GAMEPAD_JOYSTICK_ID = 2,
                          LEFT_TANK_DRIVE_AXIS_ID = X3D_AXIS_PITCH, // Tank drive axis
                          RIGHT_TANK_DRIVE_AXIS_ID = X3D_AXIS_PITCH,
                          FORWARD_ARCADE_DRIVE_AXIS_ID = X3D_AXIS_PITCH, // Arcade drive axis
                          ANGLE_ARCADE_DRIVE_AXIS_ID = X3D_AXIS_ROLL,
                          FRONT_CLIMBER_AXIS_ID = GAMEPAD_AXIS_LEFT_TRIGGER, // Climber axis
                          BACK_CLIMBER_AXIS_ID = GAMEPAD_AXIS_RIGHT_TRIGGER,
                          SWAP_DRIVE_DIRECTION_BUTTON_ID = X3D_BUTTON_TRIGGER, // Functions assigned to buttons
                          HATCH_ARM_AXIS_ID = GAMEPAD_AXIS_RIGHT_X, //Hatch arm axis
                          HATCH_GRAB_BUTTON_ID = 1;//Hatch grab button
                          
                          
  
  // Climb Subsystem IDs
  public static final int CLIMBER_FRONT_ENCODER_CHANNELA_ID = 2, 
                          CLIMBER_FRONT_ENCODER_CHANNELB_ID = 3, 
                          CLIMBER_BACK_ENCODER_CHANNELA_ID = 4, 
                          CLIMBER_BACK_ENCODER_CHANNELB_ID = 5, 
                          CLIMBER_FRONT_TALON_SRX_ID = 30, 
                          CLIMBER_BACK_TALON_SRX_ID = 31;
  public static final double CLIMBER_ENCODER_DISTANCE_PER_PULSE = 360d/256;

  // Hatch Subsystem IDs
  public static final int HATCHE_ARM_JOINT_TALON_SRX_ID = 40, 
                          HATCH_ARM_JOINT_ENCODER_CHANNELA_ID = 0, 
                          HATCH_ARM_JOINT_ENCODER_CHANNELB_ID = 1;
  public static final double HATCH_ARM_ENCODER_DISTANCE_PER_PULSE = 360d/256;

// Hatch Manipulator Subsystem IDs
public static int hatchManipulatorMotorID = 41;
public static int hatchManipulatorMotorIDB = 42;

  // Drive Subsystem IDs
  public static final int DRIVE_LEFT_A_TALON_SRX_ID = 10, 
                          DRIVE_LEFT_B_TALON_SRX_ID = 11,
                          DRIVE_RIGHT_A_TALON_SRX_ID = 20, 
                          DRIVE_RIGHT_B_TALON_SRX_ID = 21, 
                          DRIVE_LEFT_ENCODER_CHANNELA_ID = 6, 
                          DRIVE_LEFT_ENCODER_CHANNELB_ID = 7, 
                          DRIVE_RIGHT_ENCODER_CHANNELA_ID = 8, 
                          DRIVE_RIGHT_ENCODER_CHANNELB_ID = 9, 
                          DRIVE_GYRO_ID = 0,
                          DRIVE_FRONT_DISTANCE_PING_ID = 10, 
                          DRIVE_FRONT_DISTANCE_ECHO_ID = 11, 
                          DRIVE_BACK_DISTANCE_PING_ID = 12, 
                          DRIVE_BACK_DISTANCE_ECHO_ID = 13;
  public static final double DRIVE_ENCODER_DISTANCE_PER_PULSE  = (15.24 * Math.PI) / 256; // diameter * pi = circumference. circumference / 256 = distance per pulse
}
