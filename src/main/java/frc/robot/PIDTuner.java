package frc.robot;

import java.util.ArrayList;
import java.util.Random;

public class PIDTuner {

    private ArrayList<Double> vals;

    public static void main(String[] args){
        ArrayList<Double> sensorValues = new ArrayList<Double>();

        PIDController pid = new PIDController(1300, 800,0,0);
        EncoderSim encoder = new EncoderSim(0);

        while(!pid.isFinished(encoder.getSensorValue())){
            double output = pid.getOutput(encoder.getSensorValue());
            sensorValues.add(encoder.getSensorValue());
            encoder.move(output);
            System.out.println(encoder.getSensorValue());
        }

        DrawGraph.createAndShowGui(sensorValues);
    }

    public PIDTuner(ArrayList<Double> vals){
        this.vals = vals;
    }

    public void graphData(){
        DrawGraph.createAndShowGui(vals);
    }
}

class EncoderSim{
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
        double voltage = output * 12;
        double velocity = 0;
        if(voltage>0){
            velocity=(voltage-v)/k;
        }
        if(voltage<0){
            velocity=(voltage+v)/k;
        }
        sensorValue+=velocity*0.02;
    }
}