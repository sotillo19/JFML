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
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.MamdaniRuleBaseType;
import jfml.term.FuzzyTermType;

/**
 * This class creates an XML file with the definition of a Mamdani-type FLS for the Japanese Diet Assessment regression problem:
 *   1) Five input variables with Trapezoidal membership functions:
 *      + Percentage of Calories from Carbohydrate (PCC)
 *      + Percentage of Calories from Protein (PCP)
 *      + Percentage of Calories from Fat (PCF)
 *      + Percentage of Caloric Ratio (PCR)
 *      + Food Group Balance (FGB)
 *
 *   2) Two rules
 *
 * @author Jose Alonso
 */

public class CreateJapaneseDietAssessmentMamdaniExampleXML {

	public static void main(String[] args) {

		FuzzyInferenceSystem japaneseDietAssessment = new FuzzyInferenceSystem("japaneseDietAssessment - MAMDANI");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		japaneseDietAssessment.setKnowledgeBase(kb);

		//FUZZY VARIABLE PCC (Percentage of Calories from Carbohydrate)
		FuzzyVariableType pcc = new FuzzyVariableType("PCC", 0, 100);
		
		//  FUZZY TERM low
		FuzzyTermType pcc_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 55f, 60f }));
		pcc.addFuzzyTerm(pcc_low);
		//  FUZZY TERM medium
		FuzzyTermType pcc_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 55f, 60f, 65f, 70f }));
		pcc.addFuzzyTerm(pcc_medium);
		//  FUZZY TERM high
		FuzzyTermType pcc_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 65f, 70f, 100f, 100f }));
		pcc.addFuzzyTerm(pcc_high);

		kb.addVariable(pcc);

		//FUZZY VARIABLE PCP (Percentage of Calories from Protein)
		FuzzyVariableType pcp = new FuzzyVariableType("PCP", 0, 100);
		
		//  FUZZY TERM low
		FuzzyTermType pcp_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 10f, 15f }));
		pcp.addFuzzyTerm(pcp_low);
		//  FUZZY TERM medium
		FuzzyTermType pcp_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 10f, 15f, 18f, 21f }));
		pcp.addFuzzyTerm(pcp_medium);
		//  FUZZY TERM high
		FuzzyTermType pcp_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 18f, 21f, 100f, 100f }));
		pcp.addFuzzyTerm(pcp_high);

		kb.addVariable(pcp);

		//FUZZY VARIABLE PCF (Percentage of Calories from Fat)
		FuzzyVariableType pcf = new FuzzyVariableType("PCF", 0, 100);
		
		//  FUZZY TERM low
		FuzzyTermType pcf_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 15f, 20f }));
		pcf.addFuzzyTerm(pcf_low);
		//  FUZZY TERM medium
		FuzzyTermType pcf_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 15f, 20f, 24f, 30f }));
		pcf.addFuzzyTerm(pcf_medium);
		//  FUZZY TERM high
		FuzzyTermType pcf_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 24f, 30f, 100f, 100f }));
		pcf.addFuzzyTerm(pcf_high);

		kb.addVariable(pcf);

		//FUZZY VARIABLE PCR (Percentage of Caloric Ratio)
		FuzzyVariableType pcr = new FuzzyVariableType("PCR", 0, 200);
		
		//  FUZZY TERM low
		FuzzyTermType pcr_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 85f, 95f }));
		pcr.addFuzzyTerm(pcr_low);
		//  FUZZY TERM medium
		FuzzyTermType pcr_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 85f, 95f, 105f, 115f }));
		pcr.addFuzzyTerm(pcr_medium);
		//  FUZZY TERM high
		FuzzyTermType pcr_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 105f, 115f, 200f, 200f }));
		pcr.addFuzzyTerm(pcr_high);

		kb.addVariable(pcr);

		//FUZZY VARIABLE FGB (Food Group Balance)
		FuzzyVariableType fgb = new FuzzyVariableType("FGB", 0, 7);
		
		//  FUZZY TERM low
		FuzzyTermType fgb_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 1f, 3f }));
		fgb.addFuzzyTerm(fgb_low);
		//  FUZZY TERM medium
		FuzzyTermType fgb_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 1f, 3f, 4f, 6f }));
		fgb.addFuzzyTerm(fgb_medium);
		//  FUZZY TERM high
		FuzzyTermType fgb_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 4f, 6f, 7f, 7f }));
		fgb.addFuzzyTerm(fgb_high);

		kb.addVariable(fgb);

		// FUZZY VARIABLE DHL (Dietary Healthy Level)
		FuzzyVariableType dhl = new FuzzyVariableType("DHL", 0, 10);
		dhl.setDefaultValue(0f);
		dhl.setAccumulation("MAX");
		dhl.setDefuzzifierName("COG");
		dhl.setType("output");

		//  FUZZY TERM very low
		FuzzyTermType dhl_verylow = new FuzzyTermType("very low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 1.5f, 2.5f }));
		dhl.addFuzzyTerm(dhl_verylow);
		//  FUZZY TERM low
		FuzzyTermType dhl_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 1.5f, 2.5f, 3.5f, 4.5f }));
		dhl.addFuzzyTerm(dhl_low);
		//  FUZZY TERM medium
		FuzzyTermType dhl_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 3.5f, 4.5f, 5.5f, 6.5f }));
		dhl.addFuzzyTerm(dhl_medium);
		//  FUZZY TERM high
		FuzzyTermType dhl_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 5.5f, 6.5f, 7.5f, 8.5f }));
		dhl.addFuzzyTerm(dhl_high);
		//  FUZZY TERM high
		FuzzyTermType dhl_veryhigh = new FuzzyTermType("very high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 7.5f, 8.5f, 10f, 10f }));
		dhl.addFuzzyTerm(dhl_veryhigh);

		kb.addVariable(dhl);

		// RULE BASE
		MamdaniRuleBaseType rb = new MamdaniRuleBaseType("rulebase1");

		// RULE 1
		FuzzyRuleType r1 = new FuzzyRuleType("rule1", "and", "MIN", 1.0f);

		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(pcc, pcc_low));
		ant1.addClause(new ClauseType(pcp, pcp_low));
		ant1.addClause(new ClauseType(pcf, pcf_low));
		ant1.addClause(new ClauseType(pcr, pcr_low));
		ant1.addClause(new ClauseType(fgb, fgb_low));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(dhl, dhl_verylow);
		r1.setAntecedent(ant1);
		r1.setConsequent(con1);

		rb.addRule(r1);

		// RULE 243
		FuzzyRuleType r243 = new FuzzyRuleType("rule243", "and", "MIN", 1.0f);

		AntecedentType ant243 = new AntecedentType();
		ant243.addClause(new ClauseType(pcc, pcc_high));
		ant243.addClause(new ClauseType(pcp, pcp_high));
		ant243.addClause(new ClauseType(pcf, pcf_high));
		ant243.addClause(new ClauseType(pcr, pcr_high));
		ant243.addClause(new ClauseType(fgb, fgb_high));
		ConsequentType con243 = new ConsequentType();
		con243.addThenClause(dhl, dhl_low);
		r243.setAntecedent(ant243);
		r243.setConsequent(con243);

		rb.addRule(r243);

		japaneseDietAssessment.addRuleBase(rb);

		// WRITTING JAPANESE DIET ASSESSMENT EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File japaneseDietAssessmentXMLFile = new File("./XMLFiles/JapaneseDietAssessmentMamdani.xml");
		JFML.writeFSTtoXML(japaneseDietAssessment, japaneseDietAssessmentXMLFile);
	}

}
