package frc.robot;

import java.util.ArrayList;

public class PIDController {

    private boolean disable = false;

    private double output;
    private double k, t;
    private double p,i,d;
    private double integral, prev_err, setpoint;
    private double errSum, lastInput, iTerm;
    private double outMax = 1, outMin = -1;
    private ArrayList<Boolean> historicalFinishData = new ArrayList<Boolean>();

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
            if(setpoint < 0){
                double error = setpoint - sensorValue;
                integral += (error*.02);
                iTerm = i * error;
                if(iTerm > outMax){
                    iTerm = outMax;
                }else if(iTerm < outMin){
                    iTerm = outMin;
                }
                double dInput = (sensorValue-lastInput) / 0.2;
                output = p*error + iTerm - d*dInput;
                lastInput = sensorValue;
                output/=(setpoint);
                if(output > outMax){
                    output = outMax;
                }else if(output < outMin){
                    output = outMin;
                }
                return -output;
            }else{
                double error = setpoint - sensorValue;
                integral += (error*.02);
                iTerm = i * error;
                if(iTerm > outMax){
                    iTerm = outMax;
                }else if(iTerm < outMin){
                    iTerm = outMin;
                }
                double dInput = (sensorValue-lastInput) / 0.2;
                output = p*error + iTerm - d*dInput;
                lastInput = sensorValue;
                output/=(setpoint);
                if(output > outMax){
                    output = outMax;
                }else if(output < outMin){
                    output = outMin;
                }
                return output;
            }
        }else{
            return 0;
        }
    }

    public void disable(){
        disable = true;
    }

    public void enable(){
        disable = false;
    }

    public boolean isFinished(double sensorValue) {
        return Math.abs(setpoint - sensorValue) < 2.5;
        /*
        if(Math.abs(setpoint - sensorValue) < 5){
            historicalFinishData.add(true);
        }
        if(historicalFinishData.size()>=3){
            return true;
        }
        return false;
        */
    }
}