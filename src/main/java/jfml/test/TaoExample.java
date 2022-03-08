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

import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.compatibility.ImportMatlab;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.membershipfunction.CircularDefinitionType;
import jfml.operator.OrLogicalType;
import jfml.rule.AntecedentType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.MamdaniRuleBaseType;
import jfml.term.FuzzyTermType;

/**
 * This class imports a FRBCS from a Matlab fis file and adds 2 rules with Circular Definition
 * 
 * It is related to the Tao dataset and the FRBC introduced in:
 * 
 *  D. Garcia, A. Gonzalez, and R. Perez, 
 *  "A two-step approach of feature construction for a genetic learning algorithm," 
 *  in 2011 IEEE International Conference on Fuzzy Systems (FUZZ-IEEE 2011), Taipei, Taiwan, 2011, pp. 1255-1262
 *
 * @author Jose Alonso
 */

public class TaoExample {

	public static void main(String[] args) {
		try {
		    // Loading a FLS from a Matlab fis file
            System.out.println("1) FLS imported from Matlab FIS:");
		    FuzzyInferenceSystem fs = (new ImportMatlab()).importFuzzySystem("./XMLFiles/taoMatlab.fis");
            System.out.println(fs.toString());
		
		    System.out.println("2) FLS with new terms and new rules created by JFML:");
		    // System.out.println("2) FLS with new terms created by JFML:");
		    KnowledgeBaseVariable x1, x2, Sumx1x2, C; 
		    CircularDefinitionType mf1, mf2, mf3; 
		    FuzzyTermType t1, t2, t3;
		    x1 = fs.getVariable("x1"); 
		    x2 = fs.getVariable("x2"); 
		    Sumx1x2 = fs.getVariable("Sumx1x2");
		    C = fs.getVariable("Class");

		    // Creating new linguistic terms
		    mf1 = new CircularDefinitionType (new OrLogicalType("MAX", "VeryLow", new OrLogicalType("MAX", "Low", "Medium")), x1);
		    t1 = new FuzzyTermType("VLLM", mf1);
		    ((FuzzyVariableType)x1).addFuzzyTerm(t1);
		    
		    mf2 = new CircularDefinitionType (new OrLogicalType ("MAX", "High", "VeryHigh"), Sumx1x2);
		    t2 = new FuzzyTermType("HVH", mf2);
		    ((FuzzyVariableType)Sumx1x2).addFuzzyTerm(t2);

		    mf3 = new CircularDefinitionType (new OrLogicalType ("MAX", "VeryLow", "Low"), Sumx1x2);
		    t3 = new FuzzyTermType("VLL", mf3);
		    ((FuzzyVariableType)Sumx1x2).addFuzzyTerm(t3);
            //System.out.println(fs.toString());
		
		    // Adding rules r2 and r3 to the RB
            //System.out.println("3) FLS with new rules created by JFML:");
            MamdaniRuleBaseType rb= (MamdaniRuleBaseType)fs.getRuleBase(0);
            
            // Rule 2
            FuzzyRuleType R2 = new FuzzyRuleType("r2", "and", "min", 0.884f);
            AntecedentType ant2= new AntecedentType();
            ant2.addClause(Sumx1x2, "HVH");
            R2.setAntecedent(ant2);
            ConsequentType cons2= new ConsequentType();
            cons2.addThenClause(C, "C1");
            R2.setConsequent(cons2);
            if (rb!=null) {
                rb.addRule(R2);
            }
            // Rule 3
            FuzzyRuleType R3 = new FuzzyRuleType("r3", "and", "min", 0.908f);
            AntecedentType ant3 = new AntecedentType();
            ant3.addClause(x1, "VLLM"); 
            ant3.addClause(Sumx1x2, "VLL");
            R3.setAntecedent(ant3);
            ConsequentType cons3= new ConsequentType();
            cons3.addThenClause(C, "C0");
            R3.setConsequent(cons3);		
            if (rb!=null) {
                rb.addRule(R3);
            }
            System.out.println(fs.toString());
            // Evaluate new input values
            x1.setValue(90); 
            x2.setValue(60); 
            Sumx1x2.setValue(150);
            fs.evaluate();
            System.out.println("3) FLS EVALUATION:");
            System.out.println("(INPUT): " + x1.getName() + "=" + x1.getValue());
            System.out.println("(INPUT): " + x2.getName() + "=" + x2.getValue());
            System.out.println("(INPUT): " + Sumx1x2.getName() + "=" + Sumx1x2.getValue());
            System.out.println("(OUTPUT): " + C.getName() + "=" + C.getValue());
            if (C.getValue() <= 0.5) {
                System.out.println("(CLASS): C0");
            } else {
                System.out.println("(CLASS): C1");
            }
            // Write the FLS to a FLS document
            JFML.writeFSTtoXML(fs, new File("./XMLFiles/taoFML.xml"));
            System.out.println("Output file: ./XMLFiles/taoFML.xml");
		} catch(Exception ex) {
			ex.printStackTrace();
			//System.out.println(ex.toString());
		}
	}
}