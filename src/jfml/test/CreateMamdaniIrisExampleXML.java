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

public class CreateMamdaniIrisExampleXML {

	public static void main(String[] args) {

		FuzzyInferenceSystem iris = new FuzzyInferenceSystem("iris - MAMDANI");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		iris.setKnowledgeBase(kb);

		//FUZZY VARIABLE PetalWidth
		FuzzyVariableType pw = new FuzzyVariableType("PetalWidth", 0.1f, 2.5f);
		
		//  FUZZY TERM low
		FuzzyTermType pw_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0.1f, 0.244f, 1.087f }));
		pw.addFuzzyTerm(pw_low);
		//  FUZZY TERM medium
		FuzzyTermType pw_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0.244f, 1.087f, 1.419f, 1.906f }));
		pw.addFuzzyTerm(pw_medium);
		//  FUZZY TERM high
		FuzzyTermType pw_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 1.419f, 1.906f, 2.5f, 2.5f }));
		pw.addFuzzyTerm(pw_high);

		kb.addVariable(pw);

		// OUTPUT CLASS irisClass
		FuzzyVariableType irisClass = new FuzzyVariableType("irisClass", 1, 3);
		irisClass.setDefaultValue(1f);
		irisClass.setAccumulation("MAX");
		irisClass.setDefuzzifierName("MOM");
		irisClass.setType("output");

		//  FUZZY TERM setosa
		FuzzyTermType irisClass_setosa = new FuzzyTermType("setosa", FuzzyTermType.TYPE_singletonShape, (new float[] { 1f }));
		irisClass.addFuzzyTerm(irisClass_setosa);
		//  FUZZY TERM virginica
		FuzzyTermType irisClass_virginica = new FuzzyTermType("virginica", FuzzyTermType.TYPE_singletonShape, (new float[] { 2f }));
		irisClass.addFuzzyTerm(irisClass_virginica);
		//  FUZZY TERM versicolor
		FuzzyTermType irisClass_versicolor = new FuzzyTermType("versicolor", FuzzyTermType.TYPE_singletonShape, (new float[] { 3f }));
		irisClass.addFuzzyTerm(irisClass_versicolor);

		kb.addVariable(irisClass);

		// RULE BASE
		MamdaniRuleBaseType rb = new MamdaniRuleBaseType("rulebase-iris");

		// RULE 1
		FuzzyRuleType r1 = new FuzzyRuleType("rule1", "and", "MIN", 1.0f);

		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(pw, pw_low));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(irisClass, irisClass_setosa);
		r1.setAntecedent(ant1);
		r1.setConsequent(con1);

		rb.addRule(r1);

		// RULE 2
		FuzzyRuleType r2 = new FuzzyRuleType("rule2", "and", "MIN", 1.0f);

		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(pw, pw_medium));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(irisClass, irisClass_virginica);
		r2.setAntecedent(ant2);
		r2.setConsequent(con2);

		rb.addRule(r2);

		// RULE 3
		FuzzyRuleType r3 = new FuzzyRuleType("rule3", "and", "MIN", 1.0f);

		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(pw, pw_high));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(irisClass, irisClass_versicolor);
		r3.setAntecedent(ant3);
		r3.setConsequent(con3);

		rb.addRule(r3);

		iris.addRuleBase(rb);

		// WRITTING JAPANESE DIET ASSESSMENT EXAMPLE INTO AN XML FILE
		File irisXMLFile = new File("./XMLFiles/GeneratedIrisExampleOUT_Mamdani.xml");
		JFML.writeFSTtoXML(iris, irisXMLFile);
	}

}
