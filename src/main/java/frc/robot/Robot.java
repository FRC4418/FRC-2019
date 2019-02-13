/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.OI;
import frc.robot.commands.OutputAllDataCommand;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
   // Subsystem instantiation
   // Creates Hatch Subsystem
  public static HatchArmSubsystem hatchArmSubsystem = new HatchArmSubsystem();
  // Create HatchManipulator Subsystem
  public static HatchManipulator hatchManipulatorSubsystem = new HatchManipulator();
   // Creates Climb Subsystem
  public static ClimbSubsystem climbsubsystem = new ClimbSubsystem();
  // Creates Drive Subsystem 
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  // Creates Camera Subsystem
  public static CameraSubsystem cameraSubsystem = new CameraSubsystem();
  public static AutonomousCommandGroup autoGroup = new AutonomousCommandGroup();
  // Create data command
  public static OutputAllDataCommand dataComm = new OutputAllDataCommand();

  public static String gameData;
  public static int driverPos;
  
  @Override
  public void robotInit() {
    m_oi = new OI();
    dataComm.start();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    Scheduler.getInstance().removeAll();
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autoGroup.start();
    if(!dataComm.isRunning()){
      dataComm.start();
    }

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }

  @Override
  public void teleopInit() {
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    if(!dataComm.isRunning()){
      dataComm.start();
    }
  }
}
