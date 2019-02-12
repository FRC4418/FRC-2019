package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class FMSCall extends Command {

	
    public FMSCall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driverPos = DriverStation.getInstance().getLocation();
    	Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
    	int retries = 100;
        while (Robot.gameData.length() < 2 && retries > 0) {
            DriverStation.reportError("Gamedata is " + Robot.gameData + " retrying " + retries, false);
            try {
                Thread.sleep(5);
                Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
                if (Robot.gameData == null) {
                    Robot.gameData = "";
                }
            } catch (Exception e) {
            }
            retries--;
        }
        DriverStation.reportError("gameData before parse: " + Robot.gameData, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putString("Driver Station: ", Integer.toString(Robot.driverPos));
    	SmartDashboard.putString("Game Message: ", Robot.gameData);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
