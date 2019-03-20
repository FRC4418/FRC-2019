/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import frc.robot.Robot;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.DriverStation;

public class AutonomousCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonomousCommandGroup() {
    //addSequential(new DriveDistanceCommand(50));
    //addSequential(new TurnToAngleCommand(90));
    //addSequential(new DriveDistanceCommand(50));
    //addSequential(new GetRobotPosition());
    addSequential(new SelectAutonomousCommand());
  }
}
