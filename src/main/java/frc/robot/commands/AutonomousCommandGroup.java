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
    addSequential(new GetRobotPosition());
    if(Robot.robotPos == 1) {
      if(Robot.robotLvl == 1) {
        addSequential(new DriveDistanceCommand(100));
      }
      if(Robot.robotLvl == 2) {
        addSequential(new DriveDistanceCommand(100));
      }
      if (Robot.robotLvl == 3) {
        addSequential(new DriveDistanceCommand(100));
      }
    }
    else if (Robot.robotPos == 2) {
      if(Robot.robotLvl == 1) {
        addSequential(new DriveDistanceCommand(100));
      }
      if(Robot.robotLvl == 2) {
        addSequential(new DriveDistanceCommand(100));
      }
      if (Robot.robotLvl == 3) {
        addSequential(new DriveDistanceCommand(100));
      }
    }
    else if (Robot.robotPos == 3) {
      if(Robot.robotLvl == 1) {
        addSequential(new DriveDistanceCommand(100));
      }
      if(Robot.robotLvl == 2) {
        addSequential(new DriveDistanceCommand(100));
      }
      if (Robot.robotLvl == 3) {
        addSequential(new DriveDistanceCommand(100));
      }
    }
  }
}
