/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.RobotMap;
/**
 * Do NOT add any static variables to this class, or any initialization at all.
 * Unless you know what you are doing, do not modify this file except to
 * change the parameter class to the startRobot call.
 */
public final class Main {
  private Main() {
  }

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {

    // START RIO POST

    System.out.println("\n******************** Start CAN Test ********************\n");
    int[] expectedTalonIDs = RobotMap.expectedTalonIDs;
    File CANDevicesDIR = new File("/tmp/frc_versions/");
    ArrayList<String> filenames = new ArrayList<String>(Arrays.asList(CANDevicesDIR.list()));
    if(filenames.contains("FRC_Lib_Version.ini")){
      filenames.remove("FRC_Lib_Version.ini");
    }
    for(int i = 0; i < expectedTalonIDs.length; i++){
      if (filenames.contains("Talon SRX-" + expectedTalonIDs[i] + "-versions.ini")) {
        filenames.remove("Talon SRX-"+expectedTalonIDs[i]+"-versions.ini");
        System.out.println("Found expected TalonSRX on ID " + expectedTalonIDs[i]);
      }
    }
    for(int i = 0; i < 10; i++){
      if(filenames.contains("PDP-"+i+"-versions.ini")){
        filenames.remove("PDP-"+i+"-versions.ini");
        System.out.println("Found expected PDP on ID " + i);
      }
    }
    if(filenames.size()==0){
      System.out.println("Found no unexpected devices");
    }else{
      DriverStation.reportError("CAN POST Failed. Check Console. ", false);
      for(int i = 0; i < filenames.size(); i++){
        String devFileName = filenames.get(i);
        System.out.println("Found unexpected device of type " + devFileName.substring(0, devFileName.indexOf('-')) + " on ID "
        + devFileName.substring(devFileName.indexOf('-')+1, devFileName.indexOf('-')+2));
      }
    }
    System.out.println("\n******************** End CAN Test ********************\n");
    

    // END RIO POST

    RobotBase.startRobot(Robot::new);
  }
}
