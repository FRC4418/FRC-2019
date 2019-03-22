/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.hal.util.UncleanStatusException;
import frc.robot.Robot;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

   private long time;
   private int x;
   private int y;
   private DigitalOutput limeLight;
   private static SerialPort jevois;
   JSONParser parser = new JSONParser();
   SerialPort port;
   private String sanatizedString = "nothing";
   
   //constructor that needs the port to be passed and sets port to this passed value
   public VisionSubsystem(SerialPort jevois) {
       port = jevois;
       limeLight = new DigitalOutput(RobotMap.VISION_LIGHT_ID);
       limeLight.set(true);
    }

    //updates x and y values based upon parsed json 
    public void updateVision(){

        try{
           String jsonString = this.getString();
  
           if (jsonString != null){
              int tryX = parseX(jsonString);
              int tryY = parseY(jsonString);
     
           if (tryX != 800 && tryY != 800){
              x = tryX;
              y = tryY;
            }
          }
        } catch (Exception e){
        }
  }
   
    //parses the x value
    public int parseX(String jsonString) {
        int badValue = 800;
        try {
            Object object = parser.parse(jsonString);
            System.out.print(jsonString);
            JSONObject jsonObject = (JSONObject) object;

            if (jsonObject != null){ 

                int distString = (int) jsonObject.get("XCntr");
                
                return (Integer.valueOf(distString));
             }
        }
        catch (ParseException e) {

        }
        catch (UncleanStatusException e) {

        }
        catch (ClassCastException e) {

        }
        return badValue;
    }

    //parses y value
    public int parseY(String jsonString) {
        int badValue = 800;
        try {
            Object object = parser.parse(jsonString);
            System.out.print(jsonString);
            JSONObject jsonObject = (JSONObject) object;

            if (jsonObject != null){ 

                int distString = (int) jsonObject.get("YCntr");
                
                return (Integer.valueOf(distString));
             }
        }
        catch (ParseException e) {

        }
        catch (UncleanStatusException e) {

        }
        catch (ClassCastException e) {
            
        }
        return badValue;
    }

    public int getX(){
       
      return x;
    }
  
    public int getY(){
      
      return y;
   }

   //returns json string from port
    public String getString(){
      try {     
         if(port.getBytesReceived()>2){
            
            String unsanatizedString = port.readString();
              
            if(unsanatizedString.length()>5&&!unsanatizedString.isBlank()&&!unsanatizedString.isEmpty()){

                 sanatizedString = unsanatizedString;
              }
           }
        } 
      catch (Exception e) {
         }
       
        return sanatizedString;   
    } 

  //need to edit k in kUSB to be correct port number
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void swapLimeLight() {
      if(limeLight.get()){
          limeLight.set(false);
      }else{
          limeLight.set(true);
      }
  }

  //places the target in a correct position of the camera mapping
  public void moveAccordingToXY(){
      //must be changed to ideal value
      updateVision();
      while (x != 64 || y != 192) {
          //checks which ideal location is closer to determine which way to turn
          int distanceTargetLeft = 9 - x;
          int distanceTargetRight = 8 - y;
          if (distanceTargetLeft > distanceTargetRight) {
              time = System.currentTimeMillis() + 1000;
              while (time > System.currentTimeMillis()) {
                Robot.driveSubsystem.setLeftMotorValue(10);
              }
          } else {
            time = System.currentTimeMillis() + 1000;
            while (time > System.currentTimeMillis()) {
                Robot.driveSubsystem.setRightMotorValue(10);
            }
          }
          //get updated xy values
          updateVision();
      }
      //drives forward until y-coordinate is ideal
      while (y != 0) {
          Robot.driveSubsystem.tankDrive(5, 5);
          updateVision();
      }
      Robot.driveSubsystem.tankDrive(0, 0);
  }
}
