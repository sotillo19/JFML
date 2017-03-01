package jfml.test;

import java.io.File;
import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.TsukamotoVariableType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.TsukamotoRuleBaseType;
import jfml.term.FuzzyTermType;
import jfml.term.TsukamotoTermType;

/**
 * This class creates an XML file with the definition of a Tsukamoto-type FLS for the Tipper regression problem:
 *   1) Two input variables (food and service) with Triangular, rightLinear, leftGaussian, gaussian and rightGaussian membership functions
 *   2) One output with monotone (leftLinear, z and rightGaussian) membership functions
 *   3) Three rules 
 *
 * @author Jose Alonso
 */

public class CreateTipperTsukamotoExampleXML1 {

	public static void main(String[] args) {

		FuzzyInferenceSystem tipper = new FuzzyInferenceSystem("tipper - TSUKAMOTO");

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

		// TSUKAMOTO VARIABLE tip
		TsukamotoVariableType tip = new TsukamotoVariableType("tip", 0, 20);
		tip.setDefaultValue(0f);
		tip.setCombination("WA");
		tip.setType("output");

		// TSUKAMOTO TERM cheap
		TsukamotoTermType cheap = new TsukamotoTermType("cheap", FuzzyTermType.TYPE_leftLinearShape,
						(new float[] { 0f, 10f }));
		tip.addTsukamotoTerm(cheap);
		// TSUKAMOTO TERM average
		TsukamotoTermType average = new TsukamotoTermType("average", FuzzyTermType.TYPE_zShape,
				(new float[] { 5f, 15f }));
		tip.addTsukamotoTerm(average);
		// TSUKAMOTO TERM generous
		TsukamotoTermType generous = new TsukamotoTermType("generous", FuzzyTermType.TYPE_rightGaussianShape,
				(new float[] { 20f, 10f }));
		tip.addTsukamotoTerm(generous);

		kb.addVariable(tip);

		// RULE BASE
		//RuleBaseType rb = new RuleBaseType("rulebase1", FuzzySystemRuleBase.TYPE_TSUKAMOTO);
		TsukamotoRuleBaseType rb = new TsukamotoRuleBaseType("rulebase1");

		// RULE 1
		FuzzyRuleType reg1 = new FuzzyRuleType("rule1", "or", "MAX", 1.0f);

		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(food, rancid));
		ant1.addClause(new ClauseType(service, poor, "very"));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(tip, cheap);
		reg1.setAntecedent(ant1);
		reg1.setConsequent(con1);

		rb.addRule(reg1);

		// RULE 2
		FuzzyRuleType reg2 = new FuzzyRuleType("rule2", "or", "MAX", 1.0f);

		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(service, good));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(tip, average);
		reg2.setAntecedent(ant2);
		reg2.setConsequent(con2);
		rb.addRule(reg2);

		// RULE 3
		FuzzyRuleType reg3 = new FuzzyRuleType("rule3", "or", "MAX", 1.0f);

		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(service, excellent));
		ant3.addClause(new ClauseType(food, delicious));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(tip, generous);
		reg3.setAntecedent(ant3);
		reg3.setConsequent(con3);
		rb.addRule(reg3);

		tipper.addRuleBase(rb);

		// WRITTING TIPPER EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File tipperXMLFile = new File("./XMLFiles/TipperTsukamoto1.xml");
		JFML.writeFSTtoXML(tipper, tipperXMLFile);
	}

}
