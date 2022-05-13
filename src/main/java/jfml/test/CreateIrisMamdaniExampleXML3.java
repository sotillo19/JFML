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
 * This class creates an XML file with the definition of a Mamdani-type FLS for the Iris classification problem:
 *   1) 1 input variable (PetalWidth) with 12 different membership functions
 *   2) 12 rules (4 per output class, 1 per membership function in the input variable)
 *
 * @author Jose Alonso
 */

public class CreateIrisMamdaniExampleXML3 {

	public static void main(String[] args) {

		FuzzyInferenceSystem iris = new FuzzyInferenceSystem("iris - MAMDANI");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		iris.setKnowledgeBase(kb);

		//FUZZY VARIABLE PetalWidth
		FuzzyVariableType pw = new FuzzyVariableType("PetalWidth", 0.1f, 2.5f);
		
		//  FUZZY TERM low
		FuzzyTermType pw_lowLIN = new FuzzyTermType("lowLIN", FuzzyTermType.TYPE_leftLinearShape,
				(new float[] { 0.f, 0.8f }));
		pw.addFuzzyTerm(pw_lowLIN);
		FuzzyTermType pw_lowGAU = new FuzzyTermType("lowGAU", FuzzyTermType.TYPE_leftGaussianShape,
				(new float[] { 0.5f, 0.2f }));
		pw.addFuzzyTerm(pw_lowGAU);
		FuzzyTermType pw_lowPi = new FuzzyTermType("lowPi", FuzzyTermType.TYPE_piShape,
				(new float[] { 1.f, 1.2f }));
		pw.addFuzzyTerm(pw_lowPi);
		FuzzyTermType pw_lowZ = new FuzzyTermType("lowZ", FuzzyTermType.TYPE_zShape,
				(new float[] { 1.f, 0.2f }));
		pw.addFuzzyTerm(pw_lowZ);
		//  FUZZY TERM medium
		FuzzyTermType pw_mediumTRI = new FuzzyTermType("mediumTRI", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 0.5f, 1.f, 1.5f }));
		pw.addFuzzyTerm(pw_mediumTRI);
		FuzzyTermType pw_mediumTRA = new FuzzyTermType("mediumTRA", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0.25f, 1.f, 2.f, 2.25f }));
		pw.addFuzzyTerm(pw_mediumTRA);
		FuzzyTermType pw_mediumGAU = new FuzzyTermType("mediumGAU", FuzzyTermType.TYPE_gaussianShape,
				(new float[] { 1.f, 0.2f }));
		pw.addFuzzyTerm(pw_mediumGAU);
		FuzzyTermType pw_mediumREC = new FuzzyTermType("mediumREC", FuzzyTermType.TYPE_rectangularShape,
				(new float[] { 1.f, 2.f }));
		pw.addFuzzyTerm(pw_mediumREC);
		//  FUZZY TERM high
		FuzzyTermType pw_highLIN = new FuzzyTermType("highLIN", FuzzyTermType.TYPE_rightLinearShape,
				(new float[] { 1.5f, 2.5f }));
		pw.addFuzzyTerm(pw_highLIN);
		FuzzyTermType pw_highGAU = new FuzzyTermType("highGAU", FuzzyTermType.TYPE_rightGaussianShape,
				(new float[] { 2.f, 0.2f }));
		pw.addFuzzyTerm(pw_highGAU);
		FuzzyTermType pw_highSIN = new FuzzyTermType("highSIN", FuzzyTermType.TYPE_singletonShape,
				(new float[] { 2.f }));
		pw.addFuzzyTerm(pw_highSIN);
		FuzzyTermType pw_highS = new FuzzyTermType("highS", FuzzyTermType.TYPE_sShape,
				(new float[] { 2.f, 0.2f }));
		pw.addFuzzyTerm(pw_highS);

		kb.addVariable(pw);

		// OUTPUT CLASS irisClass
		FuzzyVariableType irisClass = new FuzzyVariableType("irisClass", 1, 3);
		irisClass.setDefaultValue(1f);
		irisClass.setAccumulation("MAX");
		irisClass.setDefuzzifierName("LM");
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
		ant1.addClause(new ClauseType(pw, pw_lowLIN));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(irisClass, irisClass_setosa);
		r1.setAntecedent(ant1);
		r1.setConsequent(con1);
		rb.addRule(r1);

		// RULE 2
		FuzzyRuleType r2 = new FuzzyRuleType("rule2", "and", "MIN", 1.0f);
		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(pw, pw_lowGAU));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(irisClass, irisClass_setosa);
		r2.setAntecedent(ant2);
		r2.setConsequent(con2);
		rb.addRule(r2);

		// RULE 3
		FuzzyRuleType r3 = new FuzzyRuleType("rule3", "and", "MIN", 1.0f);
		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(pw, pw_lowPi));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(irisClass, irisClass_setosa);
		r3.setAntecedent(ant3);
		r3.setConsequent(con3);
		rb.addRule(r3);

		// RULE 4
		FuzzyRuleType r4 = new FuzzyRuleType("rule4", "and", "MIN", 1.0f);
		AntecedentType ant4 = new AntecedentType();
		ant4.addClause(new ClauseType(pw, pw_lowZ));
		ConsequentType con4 = new ConsequentType();
		con4.addThenClause(irisClass, irisClass_setosa);
		r4.setAntecedent(ant4);
		r4.setConsequent(con4);
		rb.addRule(r4);
		
		// RULE 5
		FuzzyRuleType r5 = new FuzzyRuleType("rule5", "and", "MIN", 1.0f);
		AntecedentType ant5 = new AntecedentType();
		ant5.addClause(new ClauseType(pw, pw_mediumTRI));
		ConsequentType con5 = new ConsequentType();
		con5.addThenClause(irisClass, irisClass_virginica);
		r5.setAntecedent(ant5);
		r5.setConsequent(con5);
		rb.addRule(r5);

		// RULE 6
		FuzzyRuleType r6 = new FuzzyRuleType("rule6", "and", "MIN", 1.0f);
		AntecedentType ant6 = new AntecedentType();
		ant6.addClause(new ClauseType(pw, pw_mediumTRA));
		ConsequentType con6 = new ConsequentType();
		con6.addThenClause(irisClass, irisClass_virginica);
		r6.setAntecedent(ant6);
		r6.setConsequent(con6);
		rb.addRule(r6);

		// RULE 7
		FuzzyRuleType r7 = new FuzzyRuleType("rule7", "and", "MIN", 1.0f);
		AntecedentType ant7 = new AntecedentType();
		ant7.addClause(new ClauseType(pw, pw_mediumGAU));
		ConsequentType con7 = new ConsequentType();
		con7.addThenClause(irisClass, irisClass_virginica);
		r7.setAntecedent(ant7);
		r7.setConsequent(con7);
		rb.addRule(r7);

		// RULE 8
		FuzzyRuleType r8 = new FuzzyRuleType("rule8", "and", "MIN", 1.0f);
		AntecedentType ant8 = new AntecedentType();
		ant8.addClause(new ClauseType(pw, pw_mediumREC));
		ConsequentType con8 = new ConsequentType();
		con8.addThenClause(irisClass, irisClass_virginica);
		r8.setAntecedent(ant8);
		r8.setConsequent(con8);
		rb.addRule(r8);

		// RULE 9
		FuzzyRuleType r9 = new FuzzyRuleType("rule9", "and", "MIN", 1.0f);
		AntecedentType ant9 = new AntecedentType();
		ant9.addClause(new ClauseType(pw, pw_highLIN));
		ConsequentType con9 = new ConsequentType();
		con9.addThenClause(irisClass, irisClass_versicolor);
		r9.setAntecedent(ant9);
		r9.setConsequent(con9);
		rb.addRule(r9);

		// RULE 10
		FuzzyRuleType r10 = new FuzzyRuleType("rule10", "and", "MIN", 1.0f);
		AntecedentType ant10 = new AntecedentType();
		ant10.addClause(new ClauseType(pw, pw_highGAU));
		ConsequentType con10 = new ConsequentType();
		con10.addThenClause(irisClass, irisClass_versicolor);
		r10.setAntecedent(ant10);
		r10.setConsequent(con10);
		rb.addRule(r10);

		// RULE 11
		FuzzyRuleType r11 = new FuzzyRuleType("rule11", "and", "MIN", 1.0f);
		AntecedentType ant11 = new AntecedentType();
		ant11.addClause(new ClauseType(pw, pw_highSIN));
		ConsequentType con11 = new ConsequentType();
		con11.addThenClause(irisClass, irisClass_versicolor);
		r11.setAntecedent(ant11);
		r11.setConsequent(con11);
		rb.addRule(r11);

		// RULE 12
		FuzzyRuleType r12 = new FuzzyRuleType("rule12", "and", "MIN", 1.0f);
		AntecedentType ant12 = new AntecedentType();
		ant12.addClause(new ClauseType(pw, pw_highS));
		ConsequentType con12 = new ConsequentType();
		con12.addThenClause(irisClass, irisClass_versicolor);
		r12.setAntecedent(ant12);
		r12.setConsequent(con12);
		rb.addRule(r12);

		iris.addRuleBase(rb);

		// WRITTING IRIS EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File irisXMLFile = new File("./XMLFiles/IrisMamdani3.xml");
		JFML.writeFSTtoXML(iris, irisXMLFile);
	}

}
