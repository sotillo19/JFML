package jfml.test;

import java.io.File;
import java.util.ArrayList;

import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.AnYaDataCloudType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.rule.AnYaAntecedentType;
import jfml.rule.AnYaRuleType;
import jfml.rule.ConsequentType;
import jfml.rulebase.AnYaRuleBaseType;
import jfml.term.FuzzyTermType;

/**
 * This class creates an XML file with the definition of a Mamdani-type FLS for the Tipper regression problem:
 *   1) Two input variables (food and service) with Triangular, rightLinear, leftGaussian, gaussian and rightGaussian membership functions
 *   2) Three rules:
 *      + Use of edge "very" in rule1
 *
 * @author Jose Alonso
 */

public class CreateTipperAnYaExampleXML1 {

	public static void main(String[] args) {

		FuzzyInferenceSystem tipper = new FuzzyInferenceSystem("tipper - AnYa");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		tipper.setKnowledgeBase(kb);

		//CLOUD food
		ArrayList<Double> datumFood = new ArrayList<>();
		datumFood.add(1.0);
		datumFood.add(1.7);
		datumFood.add(4.0);
		datumFood.add(3.2);
		AnYaDataCloudType cloudFood = new AnYaDataCloudType("food", datumFood);

		kb.addVariable(cloudFood);

		//CLOUD service
		ArrayList<Double> datumService = new ArrayList<>();
		datumService.add(6.0);
		datumService.add(5.7);
		datumService.add(7.0);
		datumService.add(4.6);
		AnYaDataCloudType cloudService = new AnYaDataCloudType("service", datumService);

		kb.addVariable(cloudService);
		
		//CLOUD service
		ArrayList<Double> datumQuality = new ArrayList<>();
		datumQuality.add(8.0);
		datumQuality.add(7.7);
		datumQuality.add(10.0);
		datumQuality.add(8.6);
		AnYaDataCloudType cloudQuality = new AnYaDataCloudType("quality", datumQuality);

		kb.addVariable(cloudQuality);
		

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
		AnYaRuleBaseType rb = new AnYaRuleBaseType("rulebase1");

		// RULE 1
		AnYaRuleType rule1 = new AnYaRuleType("rule1");

		AnYaAntecedentType ant1 = new AnYaAntecedentType(cloudFood);
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(tip, cheap);
		rule1.setAnYaAntecedent(ant1);
		rule1.setConsequent(con1);

		rb.addAnYaRule(rule1);

		// RULE 2
		AnYaRuleType rule2 = new AnYaRuleType("rule2");

		AnYaAntecedentType ant2 = new AnYaAntecedentType(cloudService);
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(tip, average);
		rule2.setAnYaAntecedent(ant2);
		rule2.setConsequent(con2);

		rb.addAnYaRule(rule2);
		
		// RULE 2
		AnYaRuleType rule3 = new AnYaRuleType("rule3");

		AnYaAntecedentType ant3 = new AnYaAntecedentType(cloudQuality);
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(tip, generous);
		rule3.setAnYaAntecedent(ant3);
		rule3.setConsequent(con3);

		rb.addAnYaRule(rule3);

		tipper.addRuleBase(rb);

		// WRITTING TIPPER EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File tipperXMLFile = new File("./XMLFiles/TipperAnYa1.xml");
			
		JFML.writeFSTtoXML(tipper, tipperXMLFile);
	}

}
