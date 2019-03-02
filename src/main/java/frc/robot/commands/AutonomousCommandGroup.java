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
    addSequential(new FMSCall());
    if(Robot.driverPos == 1) {
      addSequential(new DriveDistanceCommand(119.38));
      Robot.driveSubsystem.swapFront();
      addSequential(new TurnToAngleCommand(9.04));
      addSequential(new DriveDistanceCommand(280.8732));
      addSequential(new TurnToAngleCommand(248.28));
      addSequential(new DriveDistanceCommand(630.6312));
      Robot.driveSubsystem.swapFront();
      addSequential(new TurnToAngleCommand(4.7));
      addSequential(new DriveDistanceCommand(596.9762));
    }
    else if (Robot.driverPos == 2) {
      addSequential(new DriveDistanceCommand(100));
    }
    else if (Robot.driverPos == 3) {
      addSequential(new DriveDistanceCommand(119.38));
      Robot.driveSubsystem.swapFront();
      addSequential(new TurnToAngleCommand(-9.04));
      addSequential(new DriveDistanceCommand(280.8732));
      addSequential(new TurnToAngleCommand(-248.28));
      addSequential(new DriveDistanceCommand(630.6312));
      Robot.driveSubsystem.swapFront();
      addSequential(new TurnToAngleCommand(-4.7));
      addSequential(new DriveDistanceCommand(596.9762));
    }
  }
}
