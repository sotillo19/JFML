/**************************************************************
      GNU GENERAL PUBLIC LICENSE - Version 3 

  JFML: A Java Library for the IEEE Standard for Fuzzy Markup Language
  (IEEE Std 1855-2016). Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <http://www.gnu.org/licenses/>.

  Contact information: <http://www.uco.es/JFML>

  J.M. Soto-Hidalgo & Jose M. Alonso & Jesus Alcala-Fdez
 **************************************************************/
package jfml.test;

import java.io.File;
import java.io.IOException;
import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.compatibility.*;

/**
 * This class represents an example to import a fuzzy system in format FCL (IEC 1131).  
 * It contains an example of a Mamdani controller to mobile robotics for the wall-following behavior.
 * Notice: The FCL format uses only membership functions singletons for outputs in order to simplify the defuzzification.
 * Reference: M. Mucientes, R. Alcala, J. Alcala-Fdez, J. Casillas. Learning Weighted Linguistic Rules to Control an Autonomous Robot. International Journal of Intelligent Systems 24 (2009) 226�251.
 * With these input values:
 * - rd = 0.20
 * - dq = 0.25
 * - o = 20.0
 * - v = 0.25

 * , the output variables should obtain the values:
 * - la = -0.09626
 * - av = 0.20109
 * 
 * @author Jesus Alcala-Fdez
 *
 */
public class RobotImportFCL {

	
	public static void main(String[] args)  throws IOException {
		ImportFCL fcl = new ImportFCL();
		
		//loading Fuzzy System from an file according the standard FCL (IEC 1131)
		FuzzyInferenceSystem fs = fcl.importFuzzySystem("./XMLFiles/robot.fcl");
			
		// set inputs values
		KnowledgeBaseVariable input1 =  fs.getVariable("rd");
		KnowledgeBaseVariable input2 =  fs.getVariable("dq");
		KnowledgeBaseVariable input3 =  fs.getVariable("o");
		KnowledgeBaseVariable input4 =  fs.getVariable("v");
		input1.setValue(0.20f);
		input2.setValue(0.25f);
		input3.setValue(20.0f);
		input4.setValue(0.25f);
		
		// inference
		fs.evaluate();
		
		
		// get output
		KnowledgeBaseVariable output1 =  fs.getVariable("la");
		KnowledgeBaseVariable output2 =  fs.getVariable("av");
		float value1 = output1.getValue();
		float value2 = output2.getValue();
		
		//printing results
		System.out.println("RESULTS");
		System.out.println(" (INPUT): " + input1.getName() + " = " + input1.getValue() + ", " + input2.getName() + " = " + input2.getValue() + ", " + input3.getName()+ " = " + input3.getValue() + ", " + input4.getName()+ " = " + input4.getValue());
		System.out.println(" (OUTPUT): " + output1.getName() + " = " + value1 + ", " + output2.getName() + " = " + value2);
		
		//printing the FuzzySystem
		System.out.println(fs.toString());
		
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();

		File xmlFile = new File("./XMLFiles/RobotMamdani.xml");	
		JFML.writeFSTtoXML(fs, xmlFile);	
	}
}
