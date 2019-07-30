package com.tts.PulsarDetector;

public class r3MLPfunction_w_BP {

    public static r3weights r3MLPfunction_w_BP(int layers, int goal, double learnrate, double bias, Object[] alldata_array)  {


    		

            //Training Parameters and Neural Net Geometry Controls
    		
            int count = 0;

            //Create an inputarray
            double[] inputarray = new double [16];  //INPUTS

            //Assign inputarray size to variable
            int axis = inputarray.length;

            //Create target array of same length as input array with all zeroes


            int hotelement = 0;


            //Instantiate main components
            r3inputlayer iotensor = new r3inputlayer(inputarray, layers);

            r3weights weightmatrix1 = new r3weights(axis, axis, layers);

            r3neuroncalculator neuroncalculator = new r3neuroncalculator(axis);

            do {
            	//DYNAMIC SETTINGS

                radio_object currentobject = ((radio_object)(alldata_array[count]));
                
                inputarray[0] = currentobject.getI1();
                inputarray[1] = currentobject.getI2();
                inputarray[2] = currentobject.getI3();
                inputarray[3] = currentobject.getI4();
                inputarray[4] = currentobject.getI5();
                inputarray[5] = currentobject.getI6();
                inputarray[6] = currentobject.getI7();
                inputarray[7] = currentobject.getI8();
                hotelement = currentobject.getTarget();
                
                double[] targetarray = new double[axis];
                
                //Designate which element of target should be hot
                targetarray[hotelement] = .9;
            	
            	//END DYNAMIC SETTINGS
                

                //FEED FORWARD//
                for (int inputtinglayer = 0; inputtinglayer < layers; inputtinglayer++) {
                    neuroncalculator.calculate_a_state(iotensor.getrelevantinputs(inputtinglayer), weightmatrix1.getWeightmatrix(), inputtinglayer, bias); //Calculates outputs
                    iotensor.updateiolocation(neuroncalculator.getState(), inputtinglayer + 1);  //Updates input/output tensor
                }

                //Declare Variables for calculating error
                double SE[] = new double[axis];
                double runSE = 0;
                double errderiv[] = new double[axis];
                double deriv_activation[] = new double[axis];
                double difference;

                //Calculate errors
                for (int i = 0; i < axis; i++) {
                    difference = targetarray[i] - iotensor.getrelevantoutputs(layers - 1)[i];

                    SE[i] = .5 * Math.pow(difference, 2);
                    errderiv[i] = iotensor.getrelevantoutputs(layers - 1)[i] - targetarray[i];
                    deriv_activation[i] = 2 / Math.cosh(2 * iotensor.getrelevantoutputs(layers - 1)[i] + 1);
                }

                for (int i = 0; i < axis; i++) {
                    runSE = (runSE + SE[i]);
                }

                //System.out.println("Squared Error: " + runSE);
                //END FEED FORWARD//

                //BACKPROPAGATION//
                //Create tensor for error gradients
                double[][][] errortensor = new double[axis][axis][layers];

                double errorsum[][][] = new double[axis][axis][layers];

                //Create container to store errors for each layer
                double[][] errorplane = new double[axis][axis];

                int depth = layers - 1;

                //Calculate error deltas, working backwards from the output end

                //Calculate error tensor for output layer
                int z = depth;
                for (int i = 0; i < axis; i++) {
                    for (int j = 0; j < axis; j++) {
                        errortensor[i][j][z] = iotensor.getrelevantoutputs(z - 1)[i] * errderiv[j] * deriv_activation[j] * learnrate;
                    }
                }

                //Calculate errorsums for each hidden layer
                for(int q = (depth - 1); q > 0; q--) {
                    for(int r = 0; r < axis; r++) {
                        for(int s = 0; s < axis; s++) {
                            double product_rel_weights = 1;

                            for (int k = depth; k >= q; k--) {
                                product_rel_weights = product_rel_weights * weightmatrix1.getWeightmatrix()[r][s][k];
                            }
                            errorsum[r][s][q] = iotensor.getrelevantoutputs(q)[s] * errderiv[s] * product_rel_weights;
                            errortensor[r][s][q] = iotensor.getrelevantoutputs(q - 1)[r] * errorsum[r][s][q] * deriv_activation[s] * learnrate;
                        }
                    }
                }

                //End delta calculation

                double[][][] correctedweights = new double[axis][axis][layers];

                for(int f = 0; f < axis; f++){
                    for(int g = 0; g < axis; g++){
                        for(int h = 0; h < layers; h++){
                            correctedweights[f][g][h] = weightmatrix1.getWeightmatrix()[f][g][h] - errortensor[f][g][h];
                        }
                    }
                }

                weightmatrix1.setWeightmatrix(correctedweights);
                //END BACKPROPAGATION//
                count++;
            }while(count < goal);
            return weightmatrix1;
        }
    }




