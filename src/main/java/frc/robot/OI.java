/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
import edu.wpi.first.wpilibj.buttons.JoystickButton;
public class OI {
  // Create joysticks
  private static final Joystick X3D_LEFT = new Joystick(RobotMap.X3D_LEFT_JOYSTICK_ID),
                                X3D_RIGHT = new Joystick(RobotMap.X3D_RIGHT_JOYSTICK_ID),
                                GAMEPAD = new Joystick(RobotMap.GAMEPAD_JOYSTICK_ID);
  
  // Get axis for specific functions
  public static double getLeftTankDriveAxis() {
    return X3D_LEFT.getRawAxis(RobotMap.LEFT_TANK_DRIVE_AXIS_ID);
  }
  public static double getRightTankDriveAxis() {
    return X3D_RIGHT.getRawAxis(RobotMap.RIGHT_TANK_DRIVE_AXIS_ID);
  }

  public static double getForwardArcadeDriveAxis() {
    return X3D_RIGHT.getRawAxis(RobotMap.FORWARD_ARCADE_DRIVE_AXIS_ID);
  }
  public static double getAngleArcadeDriveAxis() {
    return X3D_RIGHT.getRawAxis(RobotMap.ANGLE_ARCADE_DRIVE_AXIS_ID);
  }

  public static double getFrontClimberAxis() {
    return GAMEPAD.getRawAxis(RobotMap.FRONT_CLIMBER_AXIS_ID);
  }
  public static double getBackClimberAxis() {
    return GAMEPAD.getRawAxis(RobotMap.BACK_CLIMBER_AXIS_ID);
  }

  public static double getHatchArmAxis() {
    return GAMEPAD.getRawAxis(RobotMap.HATCH_ARM_AXIS_A_ID) - GAMEPAD.getRawAxis(RobotMap.HATCH_ARM_AXIS_B_ID);
  }

  // Create and assign default buttons
  public static JoystickButton swapFrontButton = new JoystickButton(X3D_RIGHT, RobotMap.SWAP_DRIVE_DIRECTION_BUTTON_ID);
  public static JoystickButton hatchManipulatorButton = new JoystickButton(X3D_RIGHT, 3);
  public static JoystickButton setDirectionForwardButton = new JoystickButton(GAMEPAD, RobotMap.SET_DIRECTION_FORWARD_BUTTON_ID);
  public static JoystickButton setDirectionBackwardButton = new JoystickButton(GAMEPAD, RobotMap.SET_DIRECTION_BACKWARD_BUTTON_ID);
  public static JoystickButton toggleArcadeDriveButton = new JoystickButton(X3D_RIGHT, RobotMap.TOGGLE_ARCADE_DRIVE_BUTOON_ID);
  public static JoystickButton driveStraightButton = new JoystickButton(X3D_RIGHT, RobotMap.DRIVE_STRAIGHT_BUTTON_ID);
  public static JoystickButton visionLightToggle = new JoystickButton(X3D_RIGHT, RobotMap.TOGGLE_VISION_LIGHT_BUTTON_ID);

  public OI(){
    swapFrontButton.whenPressed(new SwitchDirectionCommand());
    hatchManipulatorButton.whileHeld(new GrabCommand());
    //hatchManipulatorButton.whenReleased(new GrabCommand());
    setDirectionForwardButton.whenPressed(new SwitchDirectionCommand(true));
    setDirectionBackwardButton.whenPressed(new SwitchDirectionCommand(false));
    toggleArcadeDriveButton.whenPressed(new ToggleArcadeDriveCommand());
    driveStraightButton.whileHeld(new DriveStraightCommand());
    visionLightToggle.whenPressed(new LimeLightCommand());
  }
}
