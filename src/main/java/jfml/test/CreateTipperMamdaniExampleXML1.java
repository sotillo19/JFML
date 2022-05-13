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
 * This class creates an XML file with the definition of a Mamdani-type FLS for the Tipper ruleression problem:
 *   1) Two input variables (food and service) with Triangular, rightLinear, leftGaussian, gaussian and rightGaussian membership functions
 *   2) Three rules:
 *      + Use of edge "very" in rule1
 *
 * @author Jose Alonso
 */

public class CreateTipperMamdaniExampleXML1 {

	public static void main(String[] args) {

		FuzzyInferenceSystem tipper = new FuzzyInferenceSystem("tipper - MAMDANI");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		tipper.setKnowledgeBase(kb);

		//FUZZY VARIABLE food
		FuzzyVariableType food = new FuzzyVariableType("food", 0, 10);
		
		//  FUZZY TERM rancid
		FuzzyTermType rancid = new FuzzyTermType("rancid", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 0f, 2f, 5.5f }));
		food.addFuzzyTerm(rancid);
		//  FUZZY TERM delicious
		FuzzyTermType delicious = new FuzzyTermType("delicious", FuzzyTermType.TYPE_rightLinearShape,
				(new float[] { 5.5f, 10f }));
		food.addFuzzyTerm(delicious);

		kb.addVariable(food);

		//FUZZY VARIABLE service
		FuzzyVariableType service = new FuzzyVariableType("service", 0, 10);

		//  FUZZY TERM poor
		FuzzyTermType poor = new FuzzyTermType("poor", FuzzyTermType.TYPE_leftGaussianShape, (new float[] { 0f, 2f }));
		service.addFuzzyTerm(poor);
		//  FUZZY TERM good
		FuzzyTermType good = new FuzzyTermType("good", FuzzyTermType.TYPE_gaussianShape, (new float[] { 5f, 4f }));
		service.addFuzzyTerm(good);
		//  FUZZY TERM excellent
		FuzzyTermType excellent = new FuzzyTermType("excellent", FuzzyTermType.TYPE_rightGaussianShape, (new float[] { 10f, 2f }));
		service.addFuzzyTerm(excellent);
		
		kb.addVariable(service);

		// FUZZY VARIABLE tip
		FuzzyVariableType tip = new FuzzyVariableType("tip", 0, 20);
		tip.setDefaultValue(0f);
		tip.setAccumulation("MAX");
		tip.setDefuzzifierName("COG");
		tip.setType("output");

		// FUZZY TERM cheap
		FuzzyTermType cheap = new FuzzyTermType("cheap", FuzzyTermType.TYPE_triangularShape,
						(new float[] { 0f, 5f, 10f }));
		tip.addFuzzyTerm(cheap);
		// FUZZY TERM average
		FuzzyTermType average = new FuzzyTermType("average", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 5f, 10f, 15f }));
		tip.addFuzzyTerm(average);
		// FUZZY TERM generous
		FuzzyTermType generous = new FuzzyTermType("generous", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 10f, 15f, 20f }));
		tip.addFuzzyTerm(generous);

		kb.addVariable(tip);

		// RULE BASE
		MamdaniRuleBaseType rb = new MamdaniRuleBaseType("rulebase1");

		// RULE 1
		FuzzyRuleType rule1 = new FuzzyRuleType("rule1", "or", "MAX", 1.0f);

		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(food, rancid));
		ant1.addClause(new ClauseType(service, poor, "very"));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(tip, cheap);
		rule1.setAntecedent(ant1);
		rule1.setConsequent(con1);

		rb.addRule(rule1);

		// RULE 2
		FuzzyRuleType rule2 = new FuzzyRuleType("rule2", "or", "MAX", 1.0f);

		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(service, good));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(tip, average);
		rule2.setAntecedent(ant2);
		rule2.setConsequent(con2);
		rb.addRule(rule2);

		// RULE 3
		FuzzyRuleType rule3 = new FuzzyRuleType("rule3", "or", "MAX", 1.0f);

		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(service, excellent));
		ant3.addClause(new ClauseType(food, delicious));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(tip, generous);
		rule3.setAntecedent(ant3);
		rule3.setConsequent(con3);
		rb.addRule(rule3);

		tipper.addRuleBase(rb);

		// WRITTING TIPPER EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File tipperXMLFile = new File("./XMLFiles/TipperMamdani1.xml");
		JFML.writeFSTtoXML(tipper, tipperXMLFile);
	}

}
