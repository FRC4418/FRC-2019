/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
  public static HatchManipulatorSubsystem hatchManipulatorSubsystem = new HatchManipulatorSubsystem();
   // Creates Climb Subsystem
  public static ClimbSubsystem climbsubsystem = new ClimbSubsystem();
  // Creates Drive Subsystem 
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  // Creates Camera Subsystem
  public static CameraSubsystem cameraSubsystem = new CameraSubsystem();
  //Creates VisionTracking Subsystem
  // public static VisionSubsystem visionSubsystem = new VisionSubsystem();
  public static AutonomousCommandGroup autoGroup = new AutonomousCommandGroup();
  // Create data command
  public static OutputAllDataCommand dataComm = new OutputAllDataCommand();
  
  public static String gameData;
  public static int robotPos = 2;


  public static SendableChooser<Integer> robotPositionChooser;
  public static SendableChooser<String> autoRoutineChooser;

  public static SendableChooser<Boolean> useTeleopInSandstorm;
  
  @Override
  public void robotInit() {
    System.out.print("\n\n\n[[[Entered RobotInit]]]\n");
    CameraServer.getInstance().startAutomaticCapture(0);
  
    //CameraServer.getInstance().startAutomaticCapture(1);
    m_oi = new OI();
    dataComm.start();

    robotPositionChooser = new SendableChooser<Integer>();
    robotPositionChooser.setName("Set Robot Position");
    robotPositionChooser.addDefault("FMS", 0);
    robotPositionChooser.addObject("Left", 1);
    robotPositionChooser.addObject("Center", 2);
    robotPositionChooser.addObject("Right", 3);
    SmartDashboard.putData(robotPositionChooser);

    autoRoutineChooser = new SendableChooser<String>();
    autoRoutineChooser.setName("Set Auto Routine");
    autoRoutineChooser.addDefault("Drive Straight", "straight");
    autoRoutineChooser.addObject("Other One", "justno");

    useTeleopInSandstorm = new SendableChooser<Boolean>();
    useTeleopInSandstorm.setName("Use Teleop in Sandstorm");
    useTeleopInSandstorm.addOption("Teleop", true);
    useTeleopInSandstorm.addOption("Auto", false);
    useTeleopInSandstorm.setDefaultOption("Auto", false);
    SmartDashboard.putData(useTeleopInSandstorm);
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
    System.out.print("\n\n\n[[[State Disabled]]]\n");
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
    System.out.print("\n\n\n[[[State Autonomous]]]\n");
    if(useTeleopInSandstorm.getSelected()) {
      Scheduler.getInstance().run();
    } else {
      autoGroup.start();
    }
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
    System.out.print("\n\n\n[[[State Teleop]]]\n");
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
