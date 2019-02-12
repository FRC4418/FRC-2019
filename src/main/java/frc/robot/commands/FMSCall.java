package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class FMSCall extends Command {

	
    public FMSCall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        frc.robot.Robot.driverPos = DriverStation.getInstance().getLocation();
    	frc.robot.Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
    	int retries = 100;
        while (frc.robot.Robot.gameData.length() < 2 && retries > 0) {
            DriverStation.reportError("Gamedata is " + frc.robot.Robot.gameData + " retrying " + retries, false);
            try {
                Thread.sleep(5);
                frc.robot.Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();
                if (frc.robot.Robot.gameData == null) {
                    frc.robot.Robot.gameData = "";
                }
            } catch (Exception e) {
            }
            retries--;
        }
        DriverStation.reportError("gameData before parse: " + frc.robot.Robot.gameData, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putString("Driver Station: ", Integer.toString(frc.robot.Robot.driverPos));
    	SmartDashboard.putString("Game Message: ", frc.robot.Robot.gameData);
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
