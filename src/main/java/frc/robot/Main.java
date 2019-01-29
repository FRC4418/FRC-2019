/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    // START CAN TEST
    System.out.println("\n\n\n\n\n\n******************** Start CAN Test ********************\n\n\n\n\n\n");

    // Remind when close to competition dates to check firmware
    LocalDate date = LocalDate.now();
    if(date.getMonth().equals(Month.MARCH)){
      System.out.println("Make sure the firmware is up to date. It's close to competition!");
      DriverStation.reportWarning("Make sure the firmware is up to date. It's close to competition!", false);
    }

    // Grab expected Talon IDs, the directory where CAN Data is stored on the RIO, and all the files
    int[] expectedTalonIDs = RobotMap.expectedTalonIDs;
    File CANDevicesDIR = new File("/tmp/frc_versions/");
    File[] files = CANDevicesDIR.listFiles();

    // Loop through all the CAN Files, checking for duplicate IDs and inconsistent firmware
    String firmwareVersion = "";
    for(int i = 0; i < files.length; i++){
      try{
        Scanner scanner = new Scanner(files[i]);
        // Go through all lines in the file
        while(scanner.hasNextLine()){
          String line = scanner.nextLine();
          // Nothing to compare PDP firmware to, so skip
          if(!files[i].getName().contains("PDP")){
            // If we have no firmware version to compare against, grab the one in this file
            if(firmwareVersion.equals("")){
              if(line.contains("currentVersion")){
                firmwareVersion = line.substring(line.indexOf("=") + 1);
              }
            // Check if the firmware version stored and in the file are different. If they are, log it
            }else{
              if(line.contains("currentVersion")){
                if(!firmwareVersion.equals(line.substring(line.indexOf("=") + 1))){
                  firmwareVersion = line.substring(line.indexOf("=") + 1);
                  System.out.println("There is inconsistent Talon Firmware. ");
                  DriverStation.reportWarning("There is inconsistent Talon Firmware", false);
                }
              }
            }
          }
          // Check if there are two devices on the id
          if(line.contains("There are 2 devices with this Device ID")){
            System.out.println("There are two devices of type " + files[i].getName().substring(0, files[i].getName().indexOf('-')) 
                               + " on ID " + files[i].getName().substring(files[i].getName().indexOf('-') + 1, 
                               files[i].getName().indexOf('-') + 3));
            DriverStation.reportError("There are two CAN Devices on the Same ID! Check the console!", false);
          }
        }
        scanner.close();
        // Catch if there is not a file
      } catch(FileNotFoundException e){
        System.out.println("Something went horribly wrong. A CAN File that should exist does not. HELP!!!!!");
        DriverStation.reportError("Something went horribly wrong. A CAN File that should exist does not.  HELP!!!!!", false);
      }
    }

    // Grab all the filenames
    ArrayList<String> filenames = new ArrayList<String>(Arrays.asList(CANDevicesDIR.list()));
    // We do not need to check the RIO's java version
    if(filenames.contains("FRC_Lib_Version.ini")){
      filenames.remove("FRC_Lib_Version.ini");
    }
    // Loop through all expected Talons. Remove all filenames that we expect. 
    for(int i = 0; i < expectedTalonIDs.length; i++){
      if (filenames.contains("Talon SRX-" + expectedTalonIDs[i] + "-versions.ini")) {
        filenames.remove("Talon SRX-"+expectedTalonIDs[i]+"-versions.ini");
        System.out.println("Found expected TalonSRX on ID " + expectedTalonIDs[i]);
      }
    }
    // PDP will always be in device range 0-10. Check if it exists. 
    boolean pdpExists = false;
    for(int i = 0; i < 10; i++){
      if(filenames.contains("PDP-"+i+"-versions.ini")){
        filenames.remove("PDP-"+i+"-versions.ini");
        System.out.println("Found expected PDP on ID " + i);
        pdpExists = true;
      }
    }
    if(!pdpExists){
      System.out.println("Did not find PDP in reserved ID range");
      DriverStation.reportWarning("Did not find PDP in reserved ID range", false);
    }
    // If all the CAN files were expected, there should be nothing in the filenames arraylist
    if(filenames.size()==0){
      System.out.println("Found no unexpected devices");
    // Report if you find unexpected devices
    }else{
      DriverStation.reportError("Found unexpected CAN Devices. Check Console. ", false);
      for(int i = 0; i < filenames.size(); i++){
        String devFileName = filenames.get(i);
        System.out.println("Found unexpected device of type " + devFileName.substring(0, devFileName.indexOf('-')) + " on ID "
        + devFileName.substring(devFileName.indexOf('-')+1, devFileName.indexOf('-')+2));
      }
    }
    System.out.println("\n\n\n\n\n\n******************** End CAN Test ********************\n\n\n\n\n\n");
    
    // END CAN TEST

    // END RIO POST

    RobotBase.startRobot(Robot::new);
  }
}
