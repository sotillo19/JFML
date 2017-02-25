package jfml.test;

import java.io.File;
import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.membershipfunction.CircularDefinitionType;
import jfml.operator.AndLogicalType;
import jfml.operator.OrLogicalType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.MamdaniRuleBaseType;
import jfml.term.CircularTermType;
import jfml.term.FuzzyTermType;

/**
 * This class creates an XML file with the definition of a Mamdani-type FLS for the problem of Inverted Pendulum:
 *   1) Triangular and Trapezoidal membership functions
 *   2) Definition of composed linguistic terms such as "A or B" with OrLogicalType and CircularDefinitionType
 *   3) 19 rules
 *
 * @author Jose Alonso
 */

public class CreateInvertedPendulumMamdaniExampleXML2 {

	public static void main(String[] args) {

		FuzzyInferenceSystem invertedPendulum = new FuzzyInferenceSystem("invertedPendulum - MAMDANI");

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
		//FuzzyTermType ang_vneg_or_neg = new FuzzyTermType("very negative or negative", FuzzyTermType.TYPE_trapezoidShape, (new float[] { 0f, 0f, 88f, 128f }));
		//AndLogicalType and1 = new AndLogicalType("MIN", "zero", "positive");
		//OrLogicalType or1 = new OrLogicalType("BSUM", "very negative", and1);
		//CircularDefinitionType c1 = new CircularDefinitionType(or1, ang);
		//FuzzyTermType ang_vneg_or_neg = new FuzzyTermType("VN or (zero AND positive)", c1);
		//ang.addFuzzyTerm(ang_vneg_or_neg);
		OrLogicalType ang_or1 = new OrLogicalType("BSUM", "very negative", "negative");
		CircularDefinitionType ang_c1 = new CircularDefinitionType(ang_or1, ang);
        FuzzyTermType ang_vneg_or_neg = new FuzzyTermType("very negative or negative", ang_c1);
		ang.addFuzzyTerm(ang_vneg_or_neg);
		
		//  FUZZY TERM POS OR VPOS
		//FuzzyTermType ang_pos_or_vpos = new FuzzyTermType("positive or very positive", FuzzyTermType.TYPE_trapezoidShape,(new float[] { 128f, 168f, 255f, 255f }));
		//OrLogicalType or2 = new OrLogicalType("MAX", "positive", "very positive");
		OrLogicalType ang_or2 = new OrLogicalType("BSUM", "positive", "very positive");
		CircularDefinitionType ang_c2 = new CircularDefinitionType(ang_or2, ang);
		FuzzyTermType ang_pos_or_vpos = new FuzzyTermType("positive or very positive", ang_c2);
		ang.addFuzzyTerm(ang_pos_or_vpos);

		kb.addVariable(ang);

		//FUZZY VARIABLE ChangeAngle
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
		//FuzzyTermType ca_vneg_or_neg = new FuzzyTermType("very negative or negative", FuzzyTermType.TYPE_trapezoidShape, (new float[] { 0f, 0f, 88f, 128f }));
		OrLogicalType ca_or1 = new OrLogicalType("BSUM", "very negative", "negative");
		CircularDefinitionType ca_c1 = new CircularDefinitionType(ca_or1, ca);
        FuzzyTermType ca_vneg_or_neg = new FuzzyTermType("very negative or negative", ca_c1);
		ca.addFuzzyTerm(ca_vneg_or_neg);
		//  FUZZY TERM POS OR VPOS
		//FuzzyTermType ca_pos_or_vpos = new FuzzyTermType("positive or very positive", FuzzyTermType.TYPE_trapezoidShape, (new float[] { 128f, 168f, 255f, 255f }));
		OrLogicalType ca_or2 = new OrLogicalType("BSUM", "positive", "very positive");
		CircularDefinitionType ca_c2 = new CircularDefinitionType(ca_or2, ang);
		FuzzyTermType ca_pos_or_vpos = new FuzzyTermType("positive or very positive", ca_c2);
		ca.addFuzzyTerm(ca_pos_or_vpos);

		kb.addVariable(ca);

		// FUZZY VARIABLE FORCE
		FuzzyVariableType force = new FuzzyVariableType("Force", 0, 255);
		force.setDefaultValue(0f);
		force.setAccumulation("MAX");
		force.setDefuzzifierName("COG");
		force.setType("output");

		//  FUZZY TERM VNEG
		FuzzyTermType force_vneg = new FuzzyTermType("very negative", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 0f, 0f, 48f, 88f }));
		force.addFuzzyTerm(force_vneg);
		//  FUZZY TERM NEG
		FuzzyTermType force_neg = new FuzzyTermType("negative", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 48f, 88f, 128f }));
		force.addFuzzyTerm(force_neg);
		//  FUZZY TERM NEU
		FuzzyTermType force_neu = new FuzzyTermType("zero", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 88f, 128f, 168f }));
		force.addFuzzyTerm(force_neu);
		//  FUZZY TERM POS
		FuzzyTermType force_pos = new FuzzyTermType("positive", FuzzyTermType.TYPE_triangularShape,
				(new float[] { 128f, 168f, 208f }));
		force.addFuzzyTerm(force_pos);
		//  FUZZY TERM VPOS
		FuzzyTermType force_vpos = new FuzzyTermType("very positive", FuzzyTermType.TYPE_trapezoidShape,
				(new float[] { 168f, 208f, 255f, 255f }));
		force.addFuzzyTerm(force_vpos);

		kb.addVariable(force);

		// RULE BASE
		MamdaniRuleBaseType rb = new MamdaniRuleBaseType("rulebase1");

		// RULE 1
		FuzzyRuleType r1 = new FuzzyRuleType("rule1", "and", "MIN", 1.0f);
		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(ang, ang_vneg_or_neg));
		ant1.addClause(new ClauseType(ca, ca_vneg_or_neg));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(force, force_vneg);
		r1.setAntecedent(ant1);
		r1.setConsequent(con1);
		rb.addRule(r1);

		// RULE 2
		FuzzyRuleType r2 = new FuzzyRuleType("rule2", "and", "MIN", 1.0f);
		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(ang, ang_vneg));
		ant2.addClause(new ClauseType(ca, ca_neu));
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(force, force_vneg);
		r2.setAntecedent(ant2);
		r2.setConsequent(con2);
		rb.addRule(r2);

		// RULE 3
		FuzzyRuleType r3 = new FuzzyRuleType("rule3", "and", "MIN", 1.0f);
		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(ang, ang_vneg));
		ant3.addClause(new ClauseType(ca, ca_pos));
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(force, force_neg);
		r3.setAntecedent(ant3);
		r3.setConsequent(con3);
		rb.addRule(r3);
		
		// RULE 4
		FuzzyRuleType r4 = new FuzzyRuleType("rule4", "and", "MIN", 1.0f);
		AntecedentType ant4 = new AntecedentType();
		ant4.addClause(new ClauseType(ang, ang_vneg));
		ant4.addClause(new ClauseType(ca, ca_vpos));
		ConsequentType con4 = new ConsequentType();
		con4.addThenClause(force, force_neu);
		r4.setAntecedent(ant4);
		r4.setConsequent(con4);
		rb.addRule(r4);

		// RULE 5
		FuzzyRuleType r5 = new FuzzyRuleType("rule5", "and", "MIN", 1.0f);
		AntecedentType ant5 = new AntecedentType();
		ant5.addClause(new ClauseType(ang, ang_neg));
		ant5.addClause(new ClauseType(ca, ca_neu));
		ConsequentType con5 = new ConsequentType();
		con5.addThenClause(force, force_neg);
		r5.setAntecedent(ant5);
		r5.setConsequent(con5);
		rb.addRule(r5);

		// RULE 6
		FuzzyRuleType r6 = new FuzzyRuleType("rule6", "and", "MIN", 1.0f);
		AntecedentType ant6 = new AntecedentType();
		ant6.addClause(new ClauseType(ang, ang_neg));
		ant6.addClause(new ClauseType(ca, ca_pos));
		ConsequentType con6 = new ConsequentType();
		con6.addThenClause(force, force_neu);
		r6.setAntecedent(ant6);
		r6.setConsequent(con6);
		rb.addRule(r6);

		// RULE 7
		FuzzyRuleType r7 = new FuzzyRuleType("rule7", "and", "MIN", 1.0f);
		AntecedentType ant7 = new AntecedentType();
		ant7.addClause(new ClauseType(ang, ang_neg));
		ant7.addClause(new ClauseType(ca, ca_vpos));
		ConsequentType con7 = new ConsequentType();
		con7.addThenClause(force, force_pos);
		r7.setAntecedent(ant7);
		r7.setConsequent(con7);
		rb.addRule(r7);

		// RULE 8
		FuzzyRuleType r8 = new FuzzyRuleType("rule8", "and", "MIN", 1.0f);
		AntecedentType ant8 = new AntecedentType();
		ant8.addClause(new ClauseType(ang, ang_neu));
		ant8.addClause(new ClauseType(ca, ca_vneg));
		ConsequentType con8 = new ConsequentType();
		con8.addThenClause(force, force_vneg);
		r8.setAntecedent(ant8);
		r8.setConsequent(con8);
		rb.addRule(r8);

		// RULE 9
		FuzzyRuleType r9 = new FuzzyRuleType("rule9", "and", "MIN", 1.0f);
		AntecedentType ant9 = new AntecedentType();
		ant9.addClause(new ClauseType(ang, ang_neu));
		ant9.addClause(new ClauseType(ca, ca_neg));
		ConsequentType con9 = new ConsequentType();
		con9.addThenClause(force, force_neg);
		r9.setAntecedent(ant9);
		r9.setConsequent(con9);
		rb.addRule(r9);


		// RULE 10
		FuzzyRuleType r10 = new FuzzyRuleType("rule10", "and", "MIN", 1.0f);
		AntecedentType ant10 = new AntecedentType();
		ant10.addClause(new ClauseType(ang, ang_neu));
		ant10.addClause(new ClauseType(ca, ca_neu));
		ConsequentType con10 = new ConsequentType();
		con10.addThenClause(force, force_neu);
		r10.setAntecedent(ant10);
		r10.setConsequent(con10);
		rb.addRule(r10);

		// RULE 11
		FuzzyRuleType r11 = new FuzzyRuleType("rule11", "and", "MIN", 1.0f);
		AntecedentType ant11 = new AntecedentType();
		ant11.addClause(new ClauseType(ang, ang_neu));
		ant11.addClause(new ClauseType(ca, ca_pos));
		ConsequentType con11 = new ConsequentType();
		con11.addThenClause(force, force_pos);
		r11.setAntecedent(ant11);
		r11.setConsequent(con11);
		rb.addRule(r11);

		// RULE 12
		FuzzyRuleType r12 = new FuzzyRuleType("rule12", "and", "MIN", 1.0f);
		AntecedentType ant12 = new AntecedentType();
		ant12.addClause(new ClauseType(ang, ang_neu));
		ant12.addClause(new ClauseType(ca, ca_vpos));
		ConsequentType con12 = new ConsequentType();
		con12.addThenClause(force, force_vpos);
		r12.setAntecedent(ant12);
		r12.setConsequent(con12);
		rb.addRule(r12);

		// RULE 13
		FuzzyRuleType r13 = new FuzzyRuleType("rule13", "and", "MIN", 1.0f);
		AntecedentType ant13 = new AntecedentType();
		ant13.addClause(new ClauseType(ang, ang_pos));
		ant13.addClause(new ClauseType(ca, ca_vneg));
		ConsequentType con13 = new ConsequentType();
		con13.addThenClause(force, force_neg);
		r13.setAntecedent(ant13);
		r13.setConsequent(con13);
		rb.addRule(r13);

		// RULE 14
		FuzzyRuleType r14 = new FuzzyRuleType("rule14", "and", "MIN", 1.0f);
		AntecedentType ant14 = new AntecedentType();
		ant14.addClause(new ClauseType(ang, ang_pos));
		ant14.addClause(new ClauseType(ca, ca_neg));
		ConsequentType con14 = new ConsequentType();
		con14.addThenClause(force, force_neu);
		r14.setAntecedent(ant14);
		r14.setConsequent(con14);
		rb.addRule(r14);

		// RULE 15
		FuzzyRuleType r15 = new FuzzyRuleType("rule15", "and", "MIN", 1.0f);
		AntecedentType ant15 = new AntecedentType();
		ant15.addClause(new ClauseType(ang, ang_pos));
		ant15.addClause(new ClauseType(ca, ca_neu));
		ConsequentType con15 = new ConsequentType();
		con15.addThenClause(force, force_pos);
		r15.setAntecedent(ant15);
		r15.setConsequent(con15);
		rb.addRule(r15);

		// RULE 16
		FuzzyRuleType r16 = new FuzzyRuleType("rule16", "and", "MIN", 1.0f);
		AntecedentType ant16 = new AntecedentType();
		ant16.addClause(new ClauseType(ang, ang_vpos));
		ant16.addClause(new ClauseType(ca, ca_vneg));
		ConsequentType con16 = new ConsequentType();
		con16.addThenClause(force, force_neu);
		r16.setAntecedent(ant16);
		r16.setConsequent(con16);
		rb.addRule(r16);

		// RULE 17
		FuzzyRuleType r17 = new FuzzyRuleType("rule17", "and", "MIN", 1.0f);
		AntecedentType ant17 = new AntecedentType();
		ant17.addClause(new ClauseType(ang, ang_vpos));
		ant17.addClause(new ClauseType(ca, ca_neg));
		ConsequentType con17 = new ConsequentType();
		con17.addThenClause(force, force_pos);
		r17.setAntecedent(ant17);
		r17.setConsequent(con17);
		rb.addRule(r17);

		// RULE 18
		FuzzyRuleType r18 = new FuzzyRuleType("rule18", "and", "MIN", 1.0f);
		AntecedentType ant18 = new AntecedentType();
		ant18.addClause(new ClauseType(ang, ang_vpos));
		ant18.addClause(new ClauseType(ca, ca_neu));
		ConsequentType con18 = new ConsequentType();
		con18.addThenClause(force, force_vpos);
		r18.setAntecedent(ant18);
		r18.setConsequent(con18);
		rb.addRule(r18);

		// RULE 19
		FuzzyRuleType r19 = new FuzzyRuleType("rule19", "and", "MIN", 1.0f);
		AntecedentType ant19 = new AntecedentType();
		ant19.addClause(new ClauseType(ang, ang_pos_or_vpos));
		ant19.addClause(new ClauseType(ca, ca_pos_or_vpos));
		ConsequentType con19 = new ConsequentType();
		con19.addThenClause(force, force_vpos);
		r19.setAntecedent(ant19);
		r19.setConsequent(con19);
		rb.addRule(r19);

		invertedPendulum.addRuleBase(rb);

		// WRITTING INVERTED PENDULUM EXAMPLE INTO AN XML FILE
		File invertedPendulumXMLFile = new File("./XMLFiles/InvertedPendulumMamdani2.xml");
		JFML.writeFSTtoXML(invertedPendulum, invertedPendulumXMLFile);
	}

}
