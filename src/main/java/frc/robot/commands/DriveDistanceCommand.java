/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveDistanceCommand extends Command {

  private boolean disable = false;

  private double output;
  private double k = 2, t = 0.1;
  private double p = 2,//0.45 * k, 
                 i = 0,//0.54 * k / t, 
                 d = 0;
  private double integral, prev_err, setpoint;

  public DriveDistanceCommand(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.setpoint=setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  private void PID(){
    double error = setpoint - Robot.driveSubsystem.getDistance();
    integral += (error*.02);
    double derivative = (error-prev_err) / .02;
    output = p*error + i*integral + d*derivative;
    output/=setpoint;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute(){
    if(!disable){
      PID();
      Robot.driveSubsystem.tankDrive(output, output);
    }
  }

  public void disable(){
    disable = true;
  }

  public void enable(){
    disable = false;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(setpoint - Robot.driveSubsystem.getDistance()) < 5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
