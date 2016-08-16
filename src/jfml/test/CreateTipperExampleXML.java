package jfml.test;

import java.io.File;

import jfml.FuzzySystem;
import jfml.JFML;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.FuzzySystemRuleBaseType;
import jfml.rulebase.RuleBaseType;
import jfml.term.FuzzyTermType;

public class CreateTipperExampleXML {

	public static void main(String[] args) {

		FuzzySystem tipper = new FuzzySystem("tipper");

		// KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		tipper.setKnowledgeBase(kb);

		// FUZZY VARIABLE food
		FuzzyVariableType food = new FuzzyVariableType("food", 0, 10);

		// FUZZY TERM delicious
		FuzzyTermType delicious = new FuzzyTermType("delicious", FuzzyTermType.TYPE_leftLinearShape,
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
		FuzzyTermType excellent = new FuzzyTermType("excellent", FuzzyTermType.TYPE_leftGaussianShape,
				(new float[] { 10f, 2f }));
		service.addFuzzyTerm(excellent);
		// FUZZY TERM good
		FuzzyTermType good = new FuzzyTermType("good", FuzzyTermType.TYPE_piShape, (new float[] { 5f, 4f }));
		service.addFuzzyTerm(good);
		// FUZZY TERM poor
		FuzzyTermType poor = new FuzzyTermType("poor", FuzzyTermType.TYPE_rightGaussianShape, (new float[] { 0f, 2f }));
		service.addFuzzyTerm(poor);

		kb.addVariable(service);

		// FUZZY VARIABLE tip
		FuzzyVariableType tip = new FuzzyVariableType("tip", 0, 20);
		tip.setDefaultValue(0f);
		tip.setAccumulation("MAX");
		tip.setDefuzzifierName("COG");
		tip.setType("output");

		// FUZZY TERM average
		FuzzyTermType average = new FuzzyTermType("average", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 5f, 10f, 15f }));
		tip.addFuzzyTerm(average);
		// FUZZY TERM cheap
		FuzzyTermType cheap = new FuzzyTermType("cheap", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 0f, 5f, 10f }));
		tip.addFuzzyTerm(cheap);
		// FUZZY TERM generous
		FuzzyTermType generous = new FuzzyTermType("generous", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 10f, 15f, 20f }));
		tip.addFuzzyTerm(generous);

		kb.addVariable(tip);

		// RULE BASE
		RuleBaseType fr = new RuleBaseType("rulebase1", FuzzySystemRuleBaseType.TYPE_MAMDANI);

		// RULE 1
		FuzzyRuleType reg1 = new FuzzyRuleType("reg1", "or", "MIN", "MAX", 1.0f);

		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(food, rancid));
		ant1.addClause(new ClauseType(service, poor, "very"));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(tip, cheap);
		reg1.setAntecedent(ant1);
		reg1.setConsequent(con1);

		fr.addRule(reg1);

		// RULE 2
		FuzzyRuleType reg2 = new FuzzyRuleType("reg2", "or", "MIN", "MAX", 1.0f);

		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(service, good));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(tip, average);
		reg2.setAntecedent(ant2);
		reg2.setConsequent(con2);
		fr.addRule(reg2);

		// RULE 3
		FuzzyRuleType reg3 = new FuzzyRuleType("reg3", "or", "MIN", "MAX", 1.0f);

		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(service, excellent));
		ant3.addClause(new ClauseType(food, delicious));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(tip, generous);
		reg3.setAntecedent(ant3);
		reg3.setConsequent(con3);
		fr.addRule(reg3);

		tipper.addRuleBase(fr);

		// WRITTING TIPPER INTO AN XML FILE
		File tipperXMLFile = new File("./XMLFiles/TipperExampleFuzzySystemOUT5.xml");
		JFML.writeFSTtoXML(tipper, tipperXMLFile);
	}

}
