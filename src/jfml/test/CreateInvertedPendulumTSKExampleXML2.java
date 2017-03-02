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
import jfml.membershipfunction.CircularDefinitionType;
import jfml.operator.OrLogicalType;
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
 * This class creates an XML file with the definition of a TSK-type FLS for the problem of Inverted Pendulum:
 *   1) Triangular and Trapezoidal membership functions
 *   2) Definition of composed linguistic terms such as "A or B" with OrLogicalType and CircularDefinitionType
 *   3) 19 rules (some rules of order-0 and some rules of order-1)
 *
 * @author Jose Alonso
 */

public class CreateInvertedPendulumTSKExampleXML2 {

	public static void main(String[] args) {

		FuzzyInferenceSystem invertedPendulum = new FuzzyInferenceSystem("invertedPendulum - TSK");

		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		invertedPendulum.setKnowledgeBase(kb);

		//FUZZY VARIABLE Angle
		FuzzyVariableType ang = new FuzzyVariableType("Angle", 0, 255);
		
		//  FUZZY TERM VNEG
		FuzzyTermType ang_vneg = new FuzzyTermType("very negative", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 48f, 88f }));
		ang.addFuzzyTerm(ang_vneg);
		//  FUZZY TERM NEG
		FuzzyTermType ang_neg = new FuzzyTermType("negative", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 48f, 88f, 128f }));
		ang.addFuzzyTerm(ang_neg);
		//  FUZZY TERM NEU
		FuzzyTermType ang_neu = new FuzzyTermType("zero", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 88f, 128f, 168f }));
		ang.addFuzzyTerm(ang_neu);
		//  FUZZY TERM POS
		FuzzyTermType ang_pos = new FuzzyTermType("positive", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 128f, 168f, 208f }));
		ang.addFuzzyTerm(ang_pos);
		//  FUZZY TERM VPOS
		FuzzyTermType ang_vpos = new FuzzyTermType("very positive", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 168f, 208f, 255f, 255f }));
		ang.addFuzzyTerm(ang_vpos);
		//  FUZZY TERM VNEG OR NEG
		OrLogicalType ang_or1 = new OrLogicalType("BSUM", "very negative", "negative");
		CircularDefinitionType ang_c1 = new CircularDefinitionType(ang_or1, ang);
        FuzzyTermType ang_vneg_or_neg = new FuzzyTermType("very negative or negative", ang_c1);
		ang.addFuzzyTerm(ang_vneg_or_neg);
		
		//  FUZZY TERM POS OR VPOS
		OrLogicalType ang_or2 = new OrLogicalType("BSUM", "positive", "very positive");
		CircularDefinitionType ang_c2 = new CircularDefinitionType(ang_or2, ang);
		FuzzyTermType ang_pos_or_vpos = new FuzzyTermType("positive or very positive", ang_c2);
		ang.addFuzzyTerm(ang_pos_or_vpos);

		kb.addVariable(ang);

		//FUZZY VARIABLE ChangeAngle (Angular Velocity)
		FuzzyVariableType ca = new FuzzyVariableType("ChangeAngle", 0, 255);
		
		//  FUZZY TERM VNEG
		FuzzyTermType ca_vneg = new FuzzyTermType("very negative", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 48f, 88f }));
		ca.addFuzzyTerm(ca_vneg);
		//  FUZZY TERM NEG
		FuzzyTermType ca_neg = new FuzzyTermType("negative", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 48f, 88f, 128f }));
		ca.addFuzzyTerm(ca_neg);
		//  FUZZY TERM NEU
		FuzzyTermType ca_neu = new FuzzyTermType("zero", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 88f, 128f, 168f }));
		ca.addFuzzyTerm(ca_neu);
		//  FUZZY TERM POS
		FuzzyTermType ca_pos = new FuzzyTermType("positive", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 128f, 168f, 208f }));
		ca.addFuzzyTerm(ca_pos);
		//  FUZZY TERM VPOS
		FuzzyTermType ca_vpos = new FuzzyTermType("very positive", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 168f, 208f, 255f, 255f }));
		ca.addFuzzyTerm(ca_vpos);
		//  FUZZY TERM VNEG OR NEG
		OrLogicalType ca_or1 = new OrLogicalType("BSUM", "very negative", "negative");
		CircularDefinitionType ca_c1 = new CircularDefinitionType(ca_or1, ca);
        FuzzyTermType ca_vneg_or_neg = new FuzzyTermType("very negative or negative", ca_c1);
		ca.addFuzzyTerm(ca_vneg_or_neg);
		//  FUZZY TERM POS OR VPOS
		OrLogicalType ca_or2 = new OrLogicalType("BSUM", "positive", "very positive");
		CircularDefinitionType ca_c2 = new CircularDefinitionType(ca_or2, ang);
		FuzzyTermType ca_pos_or_vpos = new FuzzyTermType("positive or very positive", ca_c2);
		ca.addFuzzyTerm(ca_pos_or_vpos);

		kb.addVariable(ca);

		// TSK VARIABLE FORCE
		TskVariableType force = new TskVariableType("Force");
		force.setDefaultValue(0f);
		force.setCombination("WA");
		force.setType("output");

	    //  FUZZY TERM VNEG
		TskTermType force_vneg = new TskTermType("very negative", TskTerm._ORDER_1, (new float[] { 48f, 0.01f, 0.02f }));
		force.addTskTerm(force_vneg);

	    //  FUZZY TERM NEG
		TskTermType force_neg = new TskTermType("negative", TskTerm._ORDER_0, (new float[] { 88f}));
		force.addTskTerm(force_neg);

		//  FUZZY TERM NEU
		TskTermType force_neu = new TskTermType("zero", TskTerm._ORDER_1, (new float[] { 128f, 0.05f, 0.05f }));
		force.addTskTerm(force_neu);

		//  FUZZY TERM POS
		TskTermType force_pos = new TskTermType("positive", TskTerm._ORDER_0, (new float[] { 168f}));
		force.addTskTerm(force_pos);

		//  FUZZY TERM VPOS
		TskTermType force_vpos = new TskTermType("very positive", TskTerm._ORDER_1, (new float[] { 208f, 0.05f, 0.03f }));
		force.addTskTerm(force_vpos);

		// TSK TERM cheap
		//TskTermType cheap = new TskTermType("cheap", TskTerm._ORDER_1, (new float[] { 1.9f, 5.6f, 6.0f }));
		//tip.addTskTerm(cheap);
		// TSK TERM generous
		// TskTermType generous = new TskTermType("generous", TskTerm._ORDER_1, (new float[] { 0.6f, 1.3f, 1.0f }));
		//tip.addTskTerm(generous);

		kb.addVariable(force);

		// RULE BASE
		TskRuleBaseType rb = new TskRuleBaseType("rulebase1", FuzzySystemRuleBase.TYPE_TSK);

		// RULE 1
		TskFuzzyRuleType r1 = new TskFuzzyRuleType("rule1", "and", "MIN", 1.0f);
		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(ang, ang_vneg_or_neg));
		ant1.addClause(new ClauseType(ca, ca_vneg_or_neg));
		TskConsequentType con1 = new TskConsequentType();
		con1.addTskThenClause(force, force_vneg);
		r1.setAntecedent(ant1);
		r1.setTskConsequent(con1);
		rb.addTskRule(r1);

		// RULE 2
		TskFuzzyRuleType r2 = new TskFuzzyRuleType("rule2", "and", "MIN", 1.0f);
		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(ang, ang_vneg));
		ant2.addClause(new ClauseType(ca, ca_neu));
		TskConsequentType con2 = new TskConsequentType();
		con2.addTskThenClause(force, force_vneg);
		r2.setAntecedent(ant2);
		r2.setTskConsequent(con2);
		rb.addTskRule(r2);

		// RULE 3
		TskFuzzyRuleType r3 = new TskFuzzyRuleType("rule3", "and", "MIN", 1.0f);
		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(ang, ang_vneg));
		ant3.addClause(new ClauseType(ca, ca_pos));
		TskConsequentType con3 = new TskConsequentType();
		con3.addTskThenClause(force, force_neg);
		r3.setAntecedent(ant3);
		r3.setTskConsequent(con3);
		rb.addTskRule(r3);
		
		// RULE 4
		TskFuzzyRuleType r4 = new TskFuzzyRuleType("rule4", "and", "MIN", 1.0f);
		AntecedentType ant4 = new AntecedentType();
		ant4.addClause(new ClauseType(ang, ang_vneg));
		ant4.addClause(new ClauseType(ca, ca_vpos));
		TskConsequentType con4 = new TskConsequentType();
		con4.addTskThenClause(force, force_neu);
		r4.setAntecedent(ant4);
		r4.setTskConsequent(con4);
		rb.addTskRule(r4);

		// RULE 5
		TskFuzzyRuleType r5 = new TskFuzzyRuleType("rule5", "and", "MIN", 1.0f);
		AntecedentType ant5 = new AntecedentType();
		ant5.addClause(new ClauseType(ang, ang_neg));
		ant5.addClause(new ClauseType(ca, ca_neu));
		TskConsequentType con5 = new TskConsequentType();
		con5.addTskThenClause(force, force_neg);
		r5.setAntecedent(ant5);
		r5.setTskConsequent(con5);
		rb.addTskRule(r5);

		// RULE 6
		TskFuzzyRuleType r6 = new TskFuzzyRuleType("rule6", "and", "MIN", 1.0f);
		AntecedentType ant6 = new AntecedentType();
		ant6.addClause(new ClauseType(ang, ang_neg));
		ant6.addClause(new ClauseType(ca, ca_pos));
		TskConsequentType con6 = new TskConsequentType();
		con6.addTskThenClause(force, force_neu);
		r6.setAntecedent(ant6);
		r6.setTskConsequent(con6);
		rb.addTskRule(r6);

		// RULE 7
		TskFuzzyRuleType r7 = new TskFuzzyRuleType("rule7", "and", "MIN", 1.0f);
		AntecedentType ant7 = new AntecedentType();
		ant7.addClause(new ClauseType(ang, ang_neg));
		ant7.addClause(new ClauseType(ca, ca_vpos));
		TskConsequentType con7 = new TskConsequentType();
		con7.addTskThenClause(force, force_pos);
		r7.setAntecedent(ant7);
		r7.setTskConsequent(con7);
		rb.addTskRule(r7);

		// RULE 8
		TskFuzzyRuleType r8 = new TskFuzzyRuleType("rule8", "and", "MIN", 1.0f);
		AntecedentType ant8 = new AntecedentType();
		ant8.addClause(new ClauseType(ang, ang_neu));
		ant8.addClause(new ClauseType(ca, ca_vneg));
		TskConsequentType con8 = new TskConsequentType();
		con8.addTskThenClause(force, force_vneg);
		r8.setAntecedent(ant8);
		r8.setTskConsequent(con8);
		rb.addTskRule(r8);

		// RULE 9
		TskFuzzyRuleType r9 = new TskFuzzyRuleType("rule9", "and", "MIN", 1.0f);
		AntecedentType ant9 = new AntecedentType();
		ant9.addClause(new ClauseType(ang, ang_neu));
		ant9.addClause(new ClauseType(ca, ca_neg));
		TskConsequentType con9 = new TskConsequentType();
		con9.addTskThenClause(force, force_neg);
		r9.setAntecedent(ant9);
		r9.setTskConsequent(con9);
		rb.addTskRule(r9);

		// RULE 10
		TskFuzzyRuleType r10 = new TskFuzzyRuleType("rule10", "and", "MIN", 1.0f);
		AntecedentType ant10 = new AntecedentType();
		ant10.addClause(new ClauseType(ang, ang_neu));
		ant10.addClause(new ClauseType(ca, ca_neu));
		TskConsequentType con10 = new TskConsequentType();
		con10.addTskThenClause(force, force_neu);
		r10.setAntecedent(ant10);
		r10.setTskConsequent(con10);
		rb.addTskRule(r10);

		// RULE 11
		TskFuzzyRuleType r11 = new TskFuzzyRuleType("rule11", "and", "MIN", 1.0f);
		AntecedentType ant11 = new AntecedentType();
		ant11.addClause(new ClauseType(ang, ang_neu));
		ant11.addClause(new ClauseType(ca, ca_pos));
		TskConsequentType con11 = new TskConsequentType();
		con11.addTskThenClause(force, force_pos);
		r11.setAntecedent(ant11);
		r11.setTskConsequent(con11);
		rb.addTskRule(r11);

		// RULE 12
		TskFuzzyRuleType r12 = new TskFuzzyRuleType("rule12", "and", "MIN", 1.0f);
		AntecedentType ant12 = new AntecedentType();
		ant12.addClause(new ClauseType(ang, ang_neu));
		ant12.addClause(new ClauseType(ca, ca_vpos));
		TskConsequentType con12 = new TskConsequentType();
		con12.addTskThenClause(force, force_vpos);
		r12.setAntecedent(ant12);
		r12.setTskConsequent(con12);
		rb.addTskRule(r12);

		// RULE 13
		TskFuzzyRuleType r13 = new TskFuzzyRuleType("rule13", "and", "MIN", 1.0f);
		AntecedentType ant13 = new AntecedentType();
		ant13.addClause(new ClauseType(ang, ang_pos));
		ant13.addClause(new ClauseType(ca, ca_vneg));
		TskConsequentType con13 = new TskConsequentType();
		con13.addTskThenClause(force, force_neg);
		r13.setAntecedent(ant13);
		r13.setTskConsequent(con13);
		rb.addTskRule(r13);

		// RULE 14
		TskFuzzyRuleType r14 = new TskFuzzyRuleType("rule14", "and", "MIN", 1.0f);
		AntecedentType ant14 = new AntecedentType();
		ant14.addClause(new ClauseType(ang, ang_pos));
		ant14.addClause(new ClauseType(ca, ca_neg));
		TskConsequentType con14 = new TskConsequentType();
		con14.addTskThenClause(force, force_neu);
		r14.setAntecedent(ant14);
		r14.setTskConsequent(con14);
		rb.addTskRule(r14);

		// RULE 15
		TskFuzzyRuleType r15 = new TskFuzzyRuleType("rule15", "and", "MIN", 1.0f);
		AntecedentType ant15 = new AntecedentType();
		ant15.addClause(new ClauseType(ang, ang_pos));
		ant15.addClause(new ClauseType(ca, ca_neu));
		TskConsequentType con15 = new TskConsequentType();
		con15.addTskThenClause(force, force_pos);
		r15.setAntecedent(ant15);
		r15.setTskConsequent(con15);
		rb.addTskRule(r15);

		// RULE 16
		TskFuzzyRuleType r16 = new TskFuzzyRuleType("rule16", "and", "MIN", 1.0f);
		AntecedentType ant16 = new AntecedentType();
		ant16.addClause(new ClauseType(ang, ang_vpos));
		ant16.addClause(new ClauseType(ca, ca_vneg));
		TskConsequentType con16 = new TskConsequentType();
		con16.addTskThenClause(force, force_neu);
		r16.setAntecedent(ant16);
		r16.setTskConsequent(con16);
		rb.addTskRule(r16);

		// RULE 17
		TskFuzzyRuleType r17 = new TskFuzzyRuleType("rule17", "and", "MIN", 1.0f);
		AntecedentType ant17 = new AntecedentType();
		ant17.addClause(new ClauseType(ang, ang_vpos));
		ant17.addClause(new ClauseType(ca, ca_neg));
		TskConsequentType con17 = new TskConsequentType();
		con17.addTskThenClause(force, force_pos);
		r17.setAntecedent(ant17);
		r17.setTskConsequent(con17);
		rb.addTskRule(r17);

		// RULE 18
		TskFuzzyRuleType r18 = new TskFuzzyRuleType("rule18", "and", "MIN", 1.0f);
		AntecedentType ant18 = new AntecedentType();
		ant18.addClause(new ClauseType(ang, ang_vpos));
		ant18.addClause(new ClauseType(ca, ca_neu));
		TskConsequentType con18 = new TskConsequentType();
		con18.addTskThenClause(force, force_vpos);
		r18.setAntecedent(ant18);
		r18.setTskConsequent(con18);
		rb.addTskRule(r18);

		// RULE 19
		TskFuzzyRuleType r19 = new TskFuzzyRuleType("rule19", "and", "MIN", 1.0f);
		AntecedentType ant19 = new AntecedentType();
		ant19.addClause(new ClauseType(ang, ang_pos_or_vpos));
		ant19.addClause(new ClauseType(ca, ca_pos_or_vpos));
		TskConsequentType con19 = new TskConsequentType();
		con19.addTskThenClause(force, force_vpos);
		r19.setAntecedent(ant19);
		r19.setTskConsequent(con19);
		rb.addTskRule(r19);

		invertedPendulum.addRuleBase(rb);

		// WRITTING INVERTED PENDULUM EXAMPLE INTO AN XML FILE
		File dirXMLFiles = new File("./XMLFiles/");
		if (!dirXMLFiles.exists())
			dirXMLFiles.mkdir();
		
		File invertedPendulumXMLFile = new File("./XMLFiles/InvertedPendulumTSK2.xml");
		JFML.writeFSTtoXML(invertedPendulum, invertedPendulumXMLFile);
	}

}
