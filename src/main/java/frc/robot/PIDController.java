package frc.robot;

public class PIDController{
    private boolean disable = false;

    private double output;
    private double k, t;
    private double p,i,d;
    private double integral, prev_err, setpoint;
  
    private Object outActuator;
    private Object inSensor;

    public PIDController(double setpoint, double p, double i, double d, double k, double t) {
      this.setpoint=setpoint;
      this.p = p;
      this.i = i;
      this.d = d;
      this.k = k;
      this.t = t;
    }
  
    public PIDController(double setpoint, double p, double i, double d) {
      this.setpoint=setpoint;
      this.p = p;
      this.i = i;
      this.d = d;
      k=0;
      t=0;
    }

    public PIDController(double setpoint, double k, double t, boolean useFullPID) {
      this.setpoint=setpoint;
      this.k = k;
      this.t = t;
      if(useFullPID){
        p=0.6*k;
        i=1.2*k/t;
        d=3*k*t/40;
      }else{
        p=0.45*k;
        i=0.54*k/t;
      }
    }
    
    public PIDController(double setpoint){
      this.setpoint=setpoint;
      p=1;
      i=0;
      d=0;
      k=0;
      t=0;
    }

    public double getOutput(double sensorValue){
      if(!disable){
        double error = setpoint - Robot.driveSubsystem.getLeftDriveEncoder();
        integral += (error*.02);
        double derivative = (error-prev_err) / .02;
        output = p*error + i*integral + d*derivative;
        output/=Math.abs(setpoint);
      }else{
        output=0;
      }
      return output;
    }

    public void disable(){
      disable = true;
    }
  
    public void enable(){
      disable = false;
    }
  
    public boolean isFinished() {
      return Math.abs(setpoint - Robot.driveSubsystem.getDistance()) < 5;
    }
}