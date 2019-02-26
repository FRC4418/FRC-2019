package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class FMSCall extends Command {

    DriverStation ds;
    
    public FMSCall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        ds = DriverStation.getInstance();
    }

    private void attemptToGetFMSData() {
        try {
            Robot.driverPos = ds.getLocation();
            Robot.gameData = ds.getGameSpecificMessage();
            if (Robot.gameData == null) {
                Robot.gameData = "";
            }
        } catch (Exception e) {
            System.out.print("Error in getting FMS data: \n" + e.toString());
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        attemptToGetFMSData();
        int retries = 100;
        long nextRunTime = System.currentTimeMillis() + 5000;
        while (Robot.gameData.length() < 2 && retries > 0) {
            if(System.currentTimeMillis() > nextRunTime) {
                nextRunTime = System.currentTimeMillis() + 5000;
                DriverStation.reportError("Gamedata is \"" + Robot.gameData + "\" retrying " + retries, false);
                attemptToGetFMSData();
                retries--;
            }
        }
        DriverStation.reportError("gameData before parse: " + Robot.gameData, false);

        SmartDashboard.putString("Driver Station: ", Integer.toString(Robot.driverPos));
    	SmartDashboard.putString("Game Message: ", Robot.gameData);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
