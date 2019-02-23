/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class HatchManipulatorCommand extends Command {
  boolean waiter = false;
  long initialTime;
  public HatchManipulatorCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super("HatchManipulator");
    requires(Robot.hatchManipulatorSubsystem);
  }
 
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(OI.hatchManipulatorButton.get() == true) { // if the button is pressed move the motor.
      Robot.hatchManipulatorSubsystem.setHatchMotorValue(-0.5);
    } else {
      if (waiter == false) { // if the waiter boolean is false set inital time to a higher number to prepare a stop.
        initialTime = System.currentTimeMillis() + 3000;
      }
      Robot.hatchManipulatorSubsystem.setHatchMotorValue(0.5); //when the button is released start motor in opposite dirrection.
      waiter = true; //set waiter variable to true in preperation for end.
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (waiter == true) { // when waiter is true return true/false to trigger end.
        return System.currentTimeMillis() > initialTime;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() { // stop motor movement.
    Robot.hatchManipulatorSubsystem.setHatchMotorValue(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
