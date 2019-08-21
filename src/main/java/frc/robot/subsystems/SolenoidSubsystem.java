/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class SolenoidSubsystem extends Subsystem {
  private Solenoid solenoidOne;
  private Solenoid solenoidTwo;
  private boolean solenoidControlled = true;


  public SolenoidSubsystem() {
    solenoidOne = new Solenoid(RobotMap.solenoidOneID);
    solenoidTwo = new Solenoid(RobotMap.solenoidTwoID);

  }

  public void solenoidOn() {
    solenoidOne.set(true);
    solenoidTwo.set(true);

  } 
  public void solenoidOff() {
    solenoidOne.set(false);
    solenoidTwo.set(false);

  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SolenoidOffCommand());
  }
  
  
}
