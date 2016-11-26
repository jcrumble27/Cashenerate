package com.datapull.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;

public class ParsePDF {
	StringBuilder appender = new StringBuilder();
//	String inner = "<form action='/QuickRunnerWeb/Presentation' method='post'><select name='item'>";
 //   String outer = "</select><input type='submit' value='Select SERVER from dropdown'></form>";
	
	@SuppressWarnings("static-access")
	public String extractcontent() throws IOException
	{
		int delimStart = 70;
        int counter = 0;
        
		PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File("C:/newMartinShasIMGS/GA_Lottery_WinningNumbersCASH3.pdf");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\newMartinShasIMGS\\number.arff", true)));
    
        try {
        	System.out.println("Entering Pull From FIle extractcontent method");
            PDFParser parser = new PDFParser(new FileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            String parsedText = pdfStripper.getText(pdDoc);
            System.out.println(parsedText + "Parsed Text LENGTH" + parsedText.length());
            
            
            
            
           //ThreeDigit[] numsArrayMidday = new ThreeDigit[75];
            ArrayList<Integer> arraylistMid =new ArrayList<Integer>();
            ArrayList<Integer> arraylistEve =new ArrayList<Integer>();
            
           ThreeDigit[] numsArrayEvening = new ThreeDigit[75];
            ThreeDigit threeDigi = new ThreeDigit();
            ThreeDigit threeDigiE = new ThreeDigit();
   
 
            

//			
  			
  			
  			//Display some inline values
  			/*ThreeDigit someFase = numsArrayList.get(0);
  			System.out.println(threeDigi.getDate() + "Date");
  			System.out.println(threeDigi.getDayOrNight() + "DrawTime");
  			System.out.println(threeDigi.getNumber() + "Number");
  			System.out.println(threeDigi.getWinnersNo()+ "How Many luck ppl");
  			System.out.println(threeDigi.getTotalPayout() + "Total Payout");*/
           
  			
  		// ATTEMPT to begin fill of obj array
  			
  	
  			
  		// Fill it with numbers using a for-loop
  			for (int i = 0; i < 10 ; i++){
  				String date="";
  				String draw="";
  				int num =0;
  				int winners =0;
  				int payout = 0;
  				
  			//Populate across daytime content
  				
  		        threeDigi.setDate(parsedText.substring(delimStart,delimStart+10));
  		        System.out.println("Date" + threeDigi.getDate());
  		      threeDigi.setDayOrNight(parsedText.substring(delimStart+13,delimStart+19));
  		        System.out.println("DrawTime" + threeDigi.getDayOrNight());	
  		        
  	  				threeDigi.setNumber(Integer.parseInt(parsedText.substring(delimStart+20,delimStart+25).replaceAll("\\s+","")));

  	  				arraylistMid.add(i,threeDigi.getNumber());
  	  			System.out.println("winning NUMBER" + threeDigi.getNumber());	
  	  			
  	  		//Check for recency day
	  				if(applyRecency(threeDigi.getNumber()) ==true){
	  					System.out.println("A pet value was arrived at on:  " + threeDigi.getDate());	
	  				}
  	  			
  	  					threeDigi.setWinnersNo(Integer.parseInt(parsedText.substring(delimStart+26,delimStart+31).replaceAll(",", "")));
  	  				System.out.println("Winners no" + threeDigi.getWinnersNo());	
  	  					threeDigi.setTotalPayout(Integer.parseInt(parsedText.substring(delimStart+33,delimStart+40).replaceAll(",", "")));
  	  				System.out.println("Total Payout" + threeDigi.getTotalPayout());	
  	  				delimStart = delimStart +40;
  		        
  	  				//Create midday dataset entry
  	  			out.println(new Integer(threeDigi.getNumber()).toString()+","+"true,false,"+new Integer(threeDigi.getWinnersNo()).toString()+","+new Integer(threeDigi.getTotalPayout()).toString()+",false,true");
  	  				
  	  				//Populate across evening content
  	  				
  	  			 threeDigiE.setDate(parsedText.substring(delimStart,delimStart+10));
   		        System.out.println("Date" + threeDigiE.getDate());
   		      threeDigiE.setDayOrNight(parsedText.substring(delimStart+13,delimStart+20));
   		        System.out.println("DrawTime" + threeDigiE.getDayOrNight());	
   		        
  	  					threeDigiE.setNumber(Integer.parseInt(parsedText.substring(delimStart+21,delimStart+26).replaceAll("\\s+","")));
  	  				arraylistEve.add(i,threeDigi.getNumber());
  	  				System.out.println("winning NUMBER" + threeDigiE.getNumber());	
  	  			
  	  			//Check for recency night
  	  				if(applyRecency(threeDigiE.getNumber()) ==true){
  	  					System.out.println("A pet value was arrived at on:  " + threeDigiE.getDate());	
  	  				}
  	  				
  	  					threeDigiE.setWinnersNo(Integer.parseInt(parsedText.substring(delimStart+27,delimStart+32).replaceAll(",", "")));
  	  	  				System.out.println("Winners no" + threeDigiE.getWinnersNo());	
  	  					threeDigiE.setTotalPayout(Integer.parseInt(parsedText.substring(delimStart+34,delimStart+41).replaceAll(",", "")));
  	  	  				System.out.println("Total Payout" + threeDigiE.getTotalPayout());
  	  	  			delimStart = delimStart +41;
  		        
  	  	  	//Create night dataset entry
  	  	  		out.println(new Integer(threeDigiE.getNumber()).toString()+","+"false,true,"+new Integer(threeDigiE.getWinnersNo()).toString()+","+new Integer(threeDigiE.getTotalPayout()).toString()+",false,true");
  	  	  			
  				counter = counter + 1;
  			}
  			
  			//PRINT COUNTER THRU PDF doc
  			System.out.println("Counter = " + counter);	
  			
  			//Print that NUMBER ONLY content currently within mid and eve arraylist
  		
  			for (int i = 0; i < counter ; i++){
  				System.out.println("winning NUMBER Midday" + arraylistMid.get(i));
  				//writerDay.println(arraylistMid.get(i));
  				System.out.println("winning NUMBER Evening" + arraylistEve.get(i));	
  				//writerNight.println(arraylistEve.get(i));
  				
  				out.close();
  			}
  		
  			
  			
  			// WEKA SEGMENT
  			
  			WekaTest m = new WekaTest();
  		    try {
				
  		   // I've commented the code as best I can, at the moment.
  		        // Comments are denoted by "//" at the beginning of the line.
  		        
  		        BufferedReader datafile = m.readDataFile("C:/Investigations/ParserIn/GA/ARFF/ARFF/DataFiles/UCI/number.arff");
  		        
  		        Instances data = new Instances(datafile);
  		        data.setClassIndex(data.numAttributes() - 1);
  		        
  		        // Choose a type of validation split
  		        Instances[][] split = m.crossValidationSplit(data, 10);
  		        
  		        // Separate split into training and testing arrays
  		        Instances[] trainingSplits = split[0];
  		        Instances[] testingSplits  = split[1];
  		        
  		        // Choose a set of classifiers
  		        Classifier[] models = {     new J48(),
  		                                    new PART(),
  		                                    new DecisionTable(),
  		                                    new OneR(),
  		                                    new DecisionStump() };
  		        
  		        // Run for each classifier model
  		        for(int j = 0; j < models.length; j++) {

  		            // Collect every group of predictions for current model in a FastVector
  		            FastVector predictions = new FastVector();
  		            
  		            // For each training-testing split pair, train and test the classifier
  		            for(int i = 0; i < trainingSplits.length; i++) {
  		                Evaluation validation = m.simpleClassify(models[j], trainingSplits[i], testingSplits[i]);
  		                predictions.appendElements(validation.predictions());
  		                
  		                // Uncomment to see the summary for each training-testing pair.
  		                // System.out.println(models[j].toString());
  		            }
  		            
  		            // Calculate overall accuracy of current classifier on all splits
  		            double accuracy = m.calculateAccuracy(predictions);
  		            
  		            // Print current classifier's name and accuracy in a complicated, but nice-looking way.
  		            System.out.println(models[j].getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy) + "\n=====================");
  		        }
  		        
  		    
  		    	
  		    	
  		    	
  		    	
  		    	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  		    
  			
  			
  			
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		return appender.toString();
		
	}
	
	public boolean applyRecency(Integer input) {
		 ArrayList<Integer> petList = new ArrayList<Integer>();
		 boolean found = false;
		 
		    petList.add(320);
		    petList.add(910);
		    petList.add(425);
		    petList.add(218);
		    petList.add(301);
		    petList.add(105);
		    petList.add(508);
		    petList.add(451);
		    petList.add(020);
		    petList.add(929);
		    petList.add(906);
		    petList.add(308);

		    for (int counter = 0; counter < petList.size(); counter++) {
		        if (petList.get(counter).equals(input)) {
		        	System.out.println("Input is: " + input);
		            found =true;
		        }
		        
		    }
			return found;
	}
	
	public void appendToFileMid(String dataset){
		StringBuilder localappend = new StringBuilder();
		localappend.append(dataset+",");
		setFinaldataset(localappend.toString());
		
	}
	
	public void appendToFileEve(String dataset){
		StringBuilder localappend = new StringBuilder();
		localappend.append(dataset+",");
		setFinaldataset(localappend.toString());
		
	}
	
	public String setFinaldataset(String full){
		return full.substring(0,full.length()-1);
	}

}
