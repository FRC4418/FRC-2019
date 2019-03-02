package frc.robot;

import java.util.ArrayList;
import java.util.Scanner;

public class PIDTuner {

    private ArrayList<Double> vals;
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args){
        ArrayList<Double> sensorValues = new ArrayList<Double>();

        PIDController pid = new PIDController(100, 2, 0, 0);
        EncoderSim encoder = new EncoderSim(0);

        double p = 1;
        double i = 0;
        double d = 0;
        String input = "";
        DrawGraph.createAndShowGui(sensorValues);
        while(!input.equals("stop")){
            System.out.println("Stop?");
            input = console.next();
            if(input.equals("stop")){
                break;
            }
            System.out.println("Autotune?");
            String auto = console.next();
            if(auto.equals("auto")){
                boolean oscillation = false;
                double setpoint = 10;
                p = 0.01;
                i = 0;
                d = 0;
                while(!oscillation){
                    System.out.println("Try with p = " + p);
                    DrawGraph.MAX_SCORE = (int)setpoint * 2;
                    pid = new PIDController(setpoint, p, i, d);
                    encoder = new EncoderSim(0);
                    while(!pid.isFinished(encoder.getSensorValue())){
                        double output = pid.getOutput(encoder.getSensorValue());
                        sensorValues.add(encoder.getSensorValue());
                        encoder.move(output);
                        if(sensorValues.size()>1000){
                            break;
                        }
                    }
                    sensorValues.add(encoder.getSensorValue());

                    double t;
                    double k; 
                    oscillation = isOscillating(sensorValues);
                    if(oscillation){
                        k=p;
                        t=oscillationPeriod(sensorValues);
                        System.out.println("Kp: " + k);
                        System.out.println("t: " + t);
                        break;
                    }
                    sensorValues = new ArrayList<Double>();
                    p+=0.01;
                }
                DrawGraph.updateGui(sensorValues);
                break;
            }
            System.out.println("Use KT?");
            String nextIn = console.next();
            if(nextIn.equals("kt")){
                System.out.println("Enter K: ");
                double k = console.nextDouble();
                System.out.println("Enter T: ");
                double t = console.nextDouble();
                System.out.println("Enter Setpoint: ");
                double setpoint = console.nextDouble();
                DrawGraph.MAX_SCORE = (int)setpoint * 2;
                pid = new PIDController(setpoint, k, t, true);
                encoder = new EncoderSim(0);
            }else{
                System.out.println("Enter P: ");
                p = console.nextDouble();
                System.out.println("Enter I: ");
                i = console.nextDouble();
                System.out.println("Enter D: ");
                d = console.nextDouble();
                System.out.println("Enter setpoint: ");
                double setpoint = console.nextDouble();
                DrawGraph.MAX_SCORE = (int)setpoint * 2;
                pid = new PIDController(setpoint, p, i, d);
                encoder = new EncoderSim(0);
            }
            DrawGraph.updateGui(sensorValues);
            while(!pid.isFinished(encoder.getSensorValue())){
                double output = pid.getOutput(encoder.getSensorValue());
                sensorValues.add(encoder.getSensorValue());
                if(output >= 0){
                    System.out.println("PID Out: +" + output);
                }else{
                    System.out.println("PID Out: " + output);
                }
                encoder.move(output);
                if(sensorValues.size()>1000){
                    break;
                }
                DrawGraph.updateGui(sensorValues);
            }
            sensorValues.add(encoder.getSensorValue());
            System.out.println("Final Sensor Value: " + encoder.getSensorValue());

            System.out.println("Oscillating? " + isOscillating(sensorValues));

            DrawGraph.updateGui(sensorValues);
            sensorValues = new ArrayList<Double>();
        }
    }

    public PIDTuner(ArrayList<Double> vals){
        this.vals = vals;
    }

    public void graphData(){
        DrawGraph.createAndShowGui(vals);
    }

    public static boolean isOscillating(ArrayList<Double> data){ //not sure...
        double max = Double.MIN_VALUE;
        int maxIndex = -1;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i) > max){
                maxIndex = i;
                max = data.get(i);
            }
        }
        int max2Index;
        int count = 2;
        boolean signD = false;
        for(int i = maxIndex; i < data.size() - 1; i++){
            double d = (data.get(i+1) - data.get(i))/0.2;
            boolean sign;
            if(d<0){
                sign = false;
            }else{
                sign = true;
            }
            if(sign!=signD){
                count--;
            }
            signD=sign;
            if(count==0){
                max2Index = i;
                return true;
            }
        }
        return false;
    }

    public static double oscillationPeriod(ArrayList<Double> data){ //not sure...
        double max = Double.MIN_VALUE;
        int maxIndex = -1;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i) > max){
                maxIndex = i;
                max = data.get(i);
            }
        }
        int max2Index = maxIndex;
        int count = 2;
        boolean signD = false;
        for(int i = maxIndex; i < data.size() - 1; i++){
            double d = (data.get(i+1) - data.get(i))/0.2;
            boolean sign;
            if(d<0){
                sign = false;
            }else{
                sign = true;
            }
            if(sign!=signD){
                count--;
            }
            signD=sign;
            if(count==0){
                max2Index = i;
                break;
            }
        }
        return (max2Index - maxIndex) * 0.02;
    }
}

class EncoderSim{
    private double mass = 50;
    private double sensorValue;
    public EncoderSim(double sensorValue){
        this.sensorValue = sensorValue;
    }
    public double getSensorValue(){
        return sensorValue;
    }
    public void move(double output){
        double k = 71501/1875;
        double v = -38.4048;
        double a = 0.21;
        
        double voltage = output * 12;
        
        double vel = 0;
        if(voltage>0){
            vel=(voltage-v)/k;
        }
        if(voltage<0){
            vel=(voltage+v)/k;
        }
        
        //double accel = (voltage - v - k*vel) / a;
        //double force = mass * accel;

        sensorValue+=vel;
    }
}
