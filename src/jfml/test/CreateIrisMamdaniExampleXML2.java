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
 *   1) Four input variables (SepalLength, SepalWith, PetalLength and PetalWidth) with Triangular and Trapezoidal membership functions
 *   2) Three rules (one per output class)
 *
 * @author Jose Alonso
 */

public class CreateIrisMamdaniExampleXML2 {

	public static void main(String[] args) {

		FuzzyInferenceSystem iris = new FuzzyInferenceSystem("iris - MAMDANI");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		iris.setKnowledgeBase(kb);

		//FUZZY VARIABLE SepalLength
		FuzzyVariableType sl = new FuzzyVariableType("SepalLength", 4.3f, 7.9f);
		
		//  FUZZY TERM low
		FuzzyTermType sl_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 4.3f, 4.3f, 5.019f, 6.048f }));
		sl.addFuzzyTerm(sl_low);
		//  FUZZY TERM medium
		FuzzyTermType sl_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 5.019f, 6.048f, 7.05f }));
		sl.addFuzzyTerm(sl_medium);
		//  FUZZY TERM high
		FuzzyTermType sl_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 6.048f, 7.05f, 7.9f, 7.9f }));
		sl.addFuzzyTerm(sl_high);
		//  FUZZY TERM NOT(low)
		FuzzyTermType sl_not_low = new FuzzyTermType("NOT(low)", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 4.3f, 4.3f, 5.019f, 6.048f }));
		sl_not_low.setComplement("true");
		sl.addFuzzyTerm(sl_not_low);

		kb.addVariable(sl);

		//FUZZY VARIABLE SepalWidth
		FuzzyVariableType sw = new FuzzyVariableType("SepalWidth", 2f, 4.4f);
		
		//  FUZZY TERM low
		FuzzyTermType sw_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 2f, 2f, 2.585f, 3.119f }));
		sw.addFuzzyTerm(sw_low);
		//  FUZZY TERM medium
		FuzzyTermType sw_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 2.585f, 3.119f, 3.758f }));
		sw.addFuzzyTerm(sw_medium);
		//  FUZZY TERM high
		FuzzyTermType sw_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 3.119f, 3.758f, 4.4f, 4.4f }));
		sw.addFuzzyTerm(sw_high);
		//  FUZZY TERM NOT(high)
		FuzzyTermType sw_not_high = new FuzzyTermType("NOT(high)", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 3.119f, 3.758f, 4.4f, 4.4f }));
				
		sw_not_high.setComplement("true");
		sw.addFuzzyTerm(sw_not_high);

		kb.addVariable(sw);
		
		//FUZZY VARIABLE PetalLength
		FuzzyVariableType pl = new FuzzyVariableType("PetalLength", 1f, 6.9f);
		
		//  FUZZY TERM low
		FuzzyTermType pl_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 1f, 1f, 1.464f, 4.432f }));
		pl.addFuzzyTerm(pl_low);
		//  FUZZY TERM medium
		FuzzyTermType pl_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 1.464f, 4.432f, 5.826f }));
		pl.addFuzzyTerm(pl_medium);
		//  FUZZY TERM high
		FuzzyTermType pl_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 4.432f, 5.826f, 6.9f, 6.9f }));
		pl.addFuzzyTerm(pl_high);
		//  FUZZY TERM NOT(low)
		FuzzyTermType pl_not_low = new FuzzyTermType("NOT(low)", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 1f, 1f, 1.464f, 4.432f }));

		pl_not_low.setComplement("true");
		pl.addFuzzyTerm(pl_not_low);

		kb.addVariable(pl);

		//FUZZY VARIABLE PetalWidth
		FuzzyVariableType pw = new FuzzyVariableType("PetalWidth", 0.1f, 2.5f);
		
		//  FUZZY TERM low
		FuzzyTermType pw_low = new FuzzyTermType("low", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0.f, 0.1f, 0.244f, 1.337f }));
		pw.addFuzzyTerm(pw_low);
		//  FUZZY TERM medium
		FuzzyTermType pw_medium = new FuzzyTermType("medium", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 0.244f, 1.337f, 2.074f }));
		pw.addFuzzyTerm(pw_medium);
		//  FUZZY TERM high
		FuzzyTermType pw_high = new FuzzyTermType("high", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 1.337f, 2.074f, 2.5f, 2.5f }));
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
		ant2.addClause(new ClauseType(sw, sw_not_high));
		ant2.addClause(new ClauseType(pl, pl_medium));
		ant2.addClause(new ClauseType(pw, pw_medium));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(irisClass, irisClass_virginica);
		r2.setAntecedent(ant2);
		r2.setConsequent(con2);

		rb.addRule(r2);

		// RULE 3
		FuzzyRuleType r3 = new FuzzyRuleType("rule3", "and", "MIN", 1.0f);

		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(sl, sl_not_low));
		ant3.addClause(new ClauseType(pl, pl_not_low));
		ant3.addClause(new ClauseType(pw, pw_high));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(irisClass, irisClass_versicolor);
		r3.setAntecedent(ant3);
		r3.setConsequent(con3);

		rb.addRule(r3);

		iris.addRuleBase(rb);

		// WRITTING IRIS EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File irisXMLFile = new File("./XMLFiles/IrisMamdani2.xml");
		JFML.writeFSTtoXML(iris, irisXMLFile);
	}

}
