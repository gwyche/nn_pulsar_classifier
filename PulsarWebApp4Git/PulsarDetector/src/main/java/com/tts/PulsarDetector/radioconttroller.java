package com.tts.PulsarDetector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/")
public class radioconttroller {
	
	@Autowired
	private radioservice radioserviceinstance;
	
    @GetMapping("")
    public String getIndex(Request request, Model model) {
        return "index";
    }
	
    @PostMapping("")
    public String postIndex(Request request, Model model) {
    	List<radio_object> alldata = this.radioserviceinstance.get_all_radio_objects();  //Pull all records from DB and store in List
    	
    	
    	
    	
    	Object[] alldata_array = alldata.toArray();  //Convert list to an array of radio objects
    	
    	
    	int tablelength = alldata_array.length;
    	
    	//ENGAGE NN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int layers = request.getthickness();
        int count = 0;
        
        double learnrate = 10;
        double bias = .1;
        double error;
        //*
        int totalrows = 53500;
        int trainingsamples = 52200;
        int testsamples = 1300;
        int target;

        //*
        int goal = trainingsamples;
        int correct = 0;
        double successrate = 0;
        double[] inputarray = new double[16];

        int hotelement;


        //TRAIN
        r3weights returnedweightobject = r3MLPfunction_w_BP.r3MLPfunction_w_BP(layers, goal, learnrate, bias, alldata_array);

        //EVALUATE
        for(int i = trainingsamples; i < totalrows; i++) {
        	radio_object tempdata = ((radio_object)(alldata_array[i]));
        	inputarray[0] = tempdata.getI1();
        	inputarray[1] = tempdata.getI2();
        	inputarray[2] = tempdata.getI3();
        	inputarray[3] = tempdata.getI4();
        	inputarray[4] = tempdata.getI5();
        	inputarray[5] = tempdata.getI6();
        	inputarray[6] = tempdata.getI7();
        	inputarray[7] = tempdata.getI8();
        	hotelement = tempdata.getTarget();
        	
            error = r3MLPfunction_n_BP.r3MLPfunction_n_BP(layers, goal, learnrate, bias, returnedweightobject, inputarray, hotelement);
            if(error < (10E-4)){
                correct = correct + 1;
            }
        }
        
        double successdecimal = ((double)correct / (double)testsamples) * 100;
        successrate = (int)successdecimal;
        //System.out.println("Evaluation Success Rate: "+successrate+"%");
        
        model.addAttribute("result", successrate);
    	//DISENGAGE NN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	return "index";
    }

    
}