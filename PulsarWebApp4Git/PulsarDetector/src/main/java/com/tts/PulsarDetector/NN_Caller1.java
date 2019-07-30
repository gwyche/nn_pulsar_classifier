package com.tts.PulsarDetector;

import java.io.*;

public class NN_Caller1 {

    public static void main(String[] args) {

        int layers = 100;
        int count = 0;
        int goal = 8000;
        double learnrate = 10;
        double bias = .1;
        double error;
        int testsamples = 30;
        int correct = 0;
        double successrate = 0;
        double[] inputarray = {1,2,1,2,1,0,0,0,5,0,0,0,5,2,3,1};

        int hotelement = 1;


        //TRAIN
        r3weights returnedweightobject = r3MLPfunction_w_BP.r3MLPfunction_w_BP(layers, goal, learnrate, bias);

        //EVALUATE
        for(int i = 0; i < testsamples; i++) {

            error = r3MLPfunction_n_BP.r3MLPfunction_n_BP(layers, goal, learnrate, bias, returnedweightobject, inputarray, hotelement);
            if(error < 10E-12){
                correct++;
            }
        }
        successrate = (correct / testsamples) * 100;
        System.out.println("Evaluation Success Rate: "+successrate+"%");
    }
}
