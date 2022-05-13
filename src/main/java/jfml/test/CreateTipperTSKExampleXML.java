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
import jfml.knowledgebase.variable.TskVariableType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.TskConsequentType;
import jfml.rule.TskFuzzyRuleType;
import jfml.rulebase.FuzzySystemRuleBase;
import jfml.rulebase.TskRuleBaseType;
import jfml.term.FuzzyTermType;
import jfml.term.TskTerm;
import jfml.term.TskTermType;

/**
 * This class creates an XML file with the definition of a TSK-type FLS for the Tipper ruleression problem:
 *   1) Two input variables (food and service) with Triangular, rightLinear, leftGaussian, gaussian and rightGaussian membership functions
 *   2) Three rules with order-0 and order-1 in the rule consequents
 *
 * @author Jose Alonso
 */

public class CreateTipperTSKExampleXML {

	public static void main(String[] args) {

		FuzzyInferenceSystem tipper = new FuzzyInferenceSystem("tipper - TSK");

		// KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		tipper.setKnowledgeBase(kb);

		// FUZZY VARIABLE food
		FuzzyVariableType food = new FuzzyVariableType("food", 0, 10);

		// FUZZY TERM delicious
		FuzzyTermType delicious = new FuzzyTermType("delicious", FuzzyTermType.TYPE_rightLinearShape,
				(new float[] { 5.5f, 10f }));
		food.addFuzzyTerm(delicious);

		// FUZZY TERM rancid
		FuzzyTermType rancid = new FuzzyTermType("rancid", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 0f, 2f, 5.5f }));
		food.addFuzzyTerm(rancid);

		kb.addVariable(food);

		// FUZZY VARIABLE service
		FuzzyVariableType service = new FuzzyVariableType("service", 0, 10);

		// FUZZY TERM excellent
		FuzzyTermType excellent = new FuzzyTermType("excellent", FuzzyTermType.TYPE_rightGaussianShape,
				(new float[] { 10f, 2f }));
		service.addFuzzyTerm(excellent);
		// FUZZY TERM good
		FuzzyTermType good = new FuzzyTermType("good", FuzzyTermType.TYPE_gaussianShape, (new float[] { 5f, 2f }));
		service.addFuzzyTerm(good);
		// FUZZY TERM poor
		FuzzyTermType poor = new FuzzyTermType("poor", FuzzyTermType.TYPE_leftGaussianShape, (new float[] { 0f, 2f }));
		service.addFuzzyTerm(poor);

		kb.addVariable(service);

		// TSK VARIABLE tip
		TskVariableType tip = new TskVariableType("tip");
		tip.setDefaultValue(0f);
		tip.setCombination("WA");
		tip.setType("output");

		// TSK TERM average
		TskTermType average = new TskTermType("average", TskTerm._ORDER_0, (new float[] { 1.6f}));
		tip.addTskTerm(average);
		// TSK TERM cheap
		TskTermType cheap = new TskTermType("cheap", TskTerm._ORDER_1,
				(new float[] { 1.9f, 5.6f, 6.0f }));
		tip.addTskTerm(cheap);
		// TSK TERM generous
		TskTermType generous = new TskTermType("generous", TskTerm._ORDER_1,
				(new float[] { 0.6f, 1.3f, 1.0f }));
		tip.addTskTerm(generous);

		kb.addVariable(tip);

		// RULE BASE
		TskRuleBaseType fr = new TskRuleBaseType("rulebase1", FuzzySystemRuleBase.TYPE_TSK);
		fr.setActivationMethod("PROD");

		// RULE 1
		TskFuzzyRuleType rule1 = new TskFuzzyRuleType("rule1", "or", "MAX", 1.0f);

		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(food, rancid));
		ant1.addClause(new ClauseType(service, poor));
		TskConsequentType con1 = new TskConsequentType();
		con1.addTskThenClause(tip, cheap);
		rule1.setAntecedent(ant1);
		rule1.setTskConsequent(con1);

		fr.addTskRule(rule1);

		// RULE 2
		TskFuzzyRuleType rule2 = new TskFuzzyRuleType("rule2", "or", "MAX", 1.0f);

		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(service, good));
		TskConsequentType con2 = new TskConsequentType();
		con2.addTskThenClause(tip, average);
		rule2.setAntecedent(ant2);
		rule2.setTskConsequent(con2);
		fr.addTskRule(rule2);

		// RULE 3
		TskFuzzyRuleType rule3 = new TskFuzzyRuleType("rule3", "or", "MAX", 1.0f);

		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(service, excellent));
		ant3.addClause(new ClauseType(food, delicious));
		TskConsequentType con3 = new TskConsequentType();
		con3.addTskThenClause(tip, generous);
		rule3.setAntecedent(ant3);
		rule3.setTskConsequent(con3);
		fr.addTskRule(rule3);

		tipper.addRuleBase(fr);

		// WRITTING TIPPER EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File tipperXMLFile = new File("./XMLFiles/TipperTSK.xml");
		JFML.writeFSTtoXML(tipper, tipperXMLFile);
	}

}
