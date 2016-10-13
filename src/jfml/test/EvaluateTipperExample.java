package jfml.test;

import java.io.File;

import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;

/**
 * This class represents an example of inference with the Tipper System Example 
 * @author sotillo19
 *
 */
public class EvaluateTipperExample {

	
	public static void main(String[] args) {
		//File xml = new File("./XMLFiles/TipperExample_Mamdani.xml");
		//File xml = new File("./XMLFiles/TipperExample_TSK.xml");
		//File xml = new File("./XMLFiles/GeneratedTipperExampleOUT_Mamdani3.xml");
		//File xml = new File("./XMLFiles/GeneratedTipperExampleOUT_TSK.xml");
		File xml = new File("./XMLFiles/GeneratedTipperExampleOUT_Tsukamoto2.xml");
		//File xml = new File("./XMLFiles/TipperExampleFuzzySystemOUTANYA1.xml");
		//File xml = new File("./XMLFiles/GeneratedJapaneseDietAssessmentExampleOUT_Mamdani.xml");
		
		
		//loading Fuzzy System from an XML file according the standard IEEE 1855
		FuzzyInferenceSystem fs = JFML.load(xml);
		
		//printing the FuzzySystem
		//System.out.println(fs.toString());
		
		// set inputs values
		KnowledgeBaseVariable input1 =  fs.getVariable("food");
		KnowledgeBaseVariable input2 =  fs.getVariable("service");
		input1.setValue(6);
		input2.setValue(8);
		
		// inference
		fs.evaluate();
		
		
		// get output
		KnowledgeBaseVariable output =  fs.getVariable("tip");
		float value = output.getValue();
		
		//printing results
		System.out.println("RESULTS");
		System.out.println(" (INPUT): "+input1.getName()+ "="+input1.getValue() +", "+input2.getName()+ "="+input2.getValue());
		System.out.println(" (OUTPUT): "+output.getName()+"="+ value);
		
		
		//printing the FuzzySystem
		System.out.println(fs.toString());
		
		
		//copying options
		KnowledgeBaseVariable copy1 = input1.copy();
		KnowledgeBaseVariable copy2 = input2.copy();
		KnowledgeBaseVariable copy3 = output.copy();
		
		copy2.hasTerm("good");
	}

}
