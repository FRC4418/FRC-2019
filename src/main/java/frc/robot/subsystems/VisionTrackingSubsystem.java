/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Robot;

public class VisionTrackingSubsystem {
    //need to edit k in kUSB to be correct port number
    private static SerialPort jevois = new SerialPort(921600, SerialPort.Port.kUSB1);
    //private static DriveSubsystem driveSubsystem = new DriveSubsystem();

    public VisionTrackingSubsystem() {
    }

    //gets current xy coordinates and adjusts according to those coordinates
    public void startTracking() {
        int xy[] = getXY();
        while (xy[0] != 800) {
            moveAccordingToXY(xy);
        }
    }

    //returns the data derived from serial message sent by camera
    public int[] getXY() {
        //creates xy array with nonsense values originally and parses string containing data to adjust nonsensical values
        int[] xy = {800,800};
        String delims = "[,]";
        String data = jevois.readString();
        String[] parsedData = data.split(delims);
        //tests if tracking in the first place
        if (parsedData[0] == "Trk: 1") {
            String stringX = parsedData[1];
            String stringY = parsedData[2];
            int x = stringX.charAt(8);
            int y = stringY.charAt(8);
            xy[0] = x;
            xy[1] = y;
        }
        //returns array of coordinates nonsensical or not
        return xy;
    }

    //places the target in a correct position of the camera mapping
    public void moveAccordingToXY(int xy[]){
        //must be changed to ideal value
        while (xy[0] != 8 || xy[0] != 9) {
            //checks which ideal location is closer to determine which way to turn
            int distanceTargetLeft = 9 - xy[0];
            int distanceTargetRight = 8 - xy[0];
            if (distanceTargetLeft > distanceTargetRight) {
                Robot.driveSubsystem.setLeftMotorValue(10);
            } else {
                Robot.driveSubsystem.setRightMotorValue(10);
            }
            //get updated xy values
            int newXY[] = getXY();
            //recursively call function again with updated xy values
            moveAccordingToXY(newXY);
        }
        //drives forward until y-coordinate is ideal
        while (xy[1] != 12) {
            Robot.driveSubsystem.tankDrive(5, 5);
            int newXY[] = getXY();
            moveAccordingToXY(newXY);
        }
    }

}
