package jfml.test;

import java.io.File;

import jfml.FuzzySystem;
import jfml.JFML;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;

/**
 * This class represents an example of inference with the Tipper System Example 
 * @author sotillo19
 *
 */
public class EvaluateTipperExample {

	
	public static void main(String[] args) {
		File xml = new File("./XMLFiles/TipperExampleFuzzySystemOUT5.xml");
		FuzzySystem fs = JFML.load(xml);
		
		//printing the FuzzySystem
		//System.out.println(fs.toString());
		
		// set inputs values
		KnowledgeBaseVariable input1 =  fs.getVariable("food");
		KnowledgeBaseVariable input2 =  fs.getVariable("service");
		input1.setValue(3);
		input2.setValue(10);
	
		
		// inference
		fs.evaluate();
		
		// get output
		KnowledgeBaseVariable output =  fs.getVariable("tip");
		float value = output.getDefuzzifierValue();
		
		//printing results
		System.out.println(" (INPUT): "+input1.getName()+ "= "+input1.getValue() +", "+input2.getName()+ "= "+input2.getValue());
		System.out.println(" (OUTPUT): "+output.getName()+"= "+ value);
		
		System.out.println(fs.toString());
	}

}
