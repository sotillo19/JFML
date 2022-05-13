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
package jfml.compatibility;

import java.util.List;

import jfml.FuzzyInferenceSystem;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.knowledgebase.variable.TskVariableType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rule.TskConsequentType;
import jfml.rule.TskFuzzyRuleType;
import jfml.rule.Rule;
import jfml.rulebase.FuzzySystemRuleBase;
import jfml.rulebase.MamdaniRuleBaseType;
import jfml.rulebase.TskRuleBaseType;
import jfml.term.FuzzyTermType;
import jfml.term.TskTerm;
import jfml.term.TskTermType;
import jfml.term.Term;

/**
 * This class allows to import a fuzzy system in PMML format.
 * We follow the frbsPMML format developed by Lala Septem Riza for R package frbs.
 * frbs is the only fuzzy sw compatible with PMML standard.
 * 
 * @author Jose Alonso
 *
 */
public class ImportPMML extends Import {
    // Attributes are obtained from pmml reader
    private List<HeaderPMML> headerList;
    private List<Extension> extensionList;
    private List<Application> applicationList;
    private List<DataDictAttr> datadictionaryList;
    private List<DataField> datafieldList;
    private List<ClassValue> classvalueList;
    private List<Interval> intervalList;
    private List<frbsPMML> frbsList;
    private List<MiningField> miningfieldList;
    private List<OutputField> outputfieldList;
    private List<ConjunctionOperator> conjunctionoperatorList;
    private List<DisjunctionOperator> disjunctionoperatorList;
    private List<ImplicationOperator> implicationoperatorList;
    private List<AggregationOperator> aggregationoperatorList;
    private List<MembershipFunctionAttrs> membershipfunctionattrsList;
    private List<MembershipFunction> membershipfunctionList;
    private List<jfml.compatibility.Rule> ruleList;
    private List<TSKThenPart> tskthenpartList;
    private List<Grade> gradeList;

	/**
	 * Constructor by default
	 */
	public ImportPMML() {
	}

	@Override
	public FuzzyInferenceSystem importFuzzySystem(String path) throws java.io.IOException {
		System.out.println("Input File: "+path);
        pmmlReader pmmlList = new pmmlReader(path);
        FuzzyInferenceSystem fuzzySystemIEEE= null;
		String name, type;
		String andMethod, orMethod, impMethod, aggMethod, defuzzMethod;
		int nInputs, nOutputs, nRules;
		String[] viname= null;
		String[] voname= null;
		KnowledgeBaseType kb= null;
		FuzzyVariableType[] in= null;
		KnowledgeBaseVariable[] out= null;
		FuzzySystemRuleBase rb= null;
		headerList = pmmlList.getHeaderList();
        extensionList = pmmlList.getExtensionList();
        applicationList = pmmlList.getApplicationList();
        datadictionaryList = pmmlList.getDataDictionaryList();
        datafieldList = pmmlList.getDataFieldList();
        classvalueList = pmmlList.getClassValueList();
        intervalList = pmmlList.getIntervalList();
        frbsList = pmmlList.getfrbsList();
        miningfieldList = pmmlList.getMiningFieldList();
        outputfieldList = pmmlList.getOutputFieldList();
        conjunctionoperatorList = pmmlList.getConjunctionOperator();
        disjunctionoperatorList = pmmlList.getDisjunctionOperator();
        implicationoperatorList = pmmlList.getImplicationOperator();
        aggregationoperatorList = pmmlList.getAggregationOperator();
        membershipfunctionattrsList = pmmlList.getMembershipFunctionAttrs();
        membershipfunctionList = pmmlList.getMembershipFunction();
        ruleList = pmmlList.getRule();
        tskthenpartList = pmmlList.getTSKThenPart();
        gradeList = pmmlList.getGrade();
        //String nameSim= headerList.get(0).toString().substring(57);
        //System.out.println("nameSim="+nameSim);
        String methodType = frbsList.get(0).getAlgorithmName().toString();
        //System.out.println("methodType="+methodType);
        double[][] rangeDataOri = new double[2][intervalList.size()];
        for (int i = 0; i < intervalList.size(); i++){
            rangeDataOri[0][i] = intervalList.get(i).getLeftMargin();
            rangeDataOri[1][i] = intervalList.get(i).getRightMargin();
        }
        String typeModel = frbsList.get(0).getModelName().toString();
        //System.out.println("typeModel="+typeModel);
        // FRBCS, MAMDANI, TSK
        int numVar = membershipfunctionattrsList.size();
        //System.out.println("nInputs="+numVar);
        int[] numLabels = null;
        boolean mamdani= false;
        boolean tsk= false;
        if (typeModel.equalsIgnoreCase("FRBCS")){
        	System.out.println("WARNING: Unknown type Model: FRBCS");
        	System.out.println("    We set \'Mamdani\' type Model");
        	System.out.println();                            
            numLabels = new int[numVar + 1];
            for (int i = 0;i < numVar; i++){
                numLabels[i] = Integer.parseInt(membershipfunctionattrsList.get(i).getNumberOfLabels());
            }
            numLabels[numVar] = classvalueList.size();
            nInputs= numVar;
            nOutputs= 1;
        } else {
        	// MAMDANI or TSK
            numLabels = new int[numVar];
            for (int i = 0;i < numVar; i++){
                numLabels[i] = Integer.parseInt(membershipfunctionattrsList.get(i).getNumberOfLabels());
            }
            if (typeModel.equalsIgnoreCase("MAMDANI")) {
                nInputs= numVar-1;
                mamdani= true;
            } else {
            	nInputs= numVar;
            	tsk= true;
            }
            nOutputs= 1;
        }
        if (conjunctionoperatorList!=null) {
            andMethod = conjunctionoperatorList.get(0).getValue().toString();
        } else {
        	andMethod="null";
        }
        // 1 or MIN means standard t-norm
        // 2 or HAMACHER means Hamacher product
        // 3 or YAGER means Yager class
        // 4 or PRODUCT means product
        // 5 or BOUNDED means bounded product
        //System.out.println("andMethod="+andMethod);
        String andm="MIN";
        if (andMethod.equals("MIN") || andMethod.equals("1")) {
        	andm= "MIN";
        } else if (andMethod.equals("PRODUCT") || andMethod.equals("4")) {
        	andm= "PROD";
        } else if (andMethod.equals("BOUNDED") || andMethod.equals("5")) {
        	andm= "BDIF";
        } else if (andMethod.equals("HAMACHER") || andMethod.equals("2")) {
        	andm= "HPROD";
        } else {
        	System.out.println("WARNING: Unknown AND method");
        	System.out.println("    We set \'MIN\' as AND method");
        	System.out.println();                            
        	andm= "MIN";
        }
        if (disjunctionoperatorList!=null) {
            orMethod = disjunctionoperatorList.get(0).getValue().toString();
        } else {
        	orMethod="null";
        }
        // 1 or MAX means standard s-norm
        // 2 or HAMACHER means Hamacher sum
        // 3 or YAGER means Yager class
        // 4 or SUM means sum
        // 5 or BOUNDED means bounded sum
        //System.out.println("orMethod="+orMethod);
        String orm="MAX";
        if (orMethod.equals("MAX") || orMethod.equals("1")) {
        	orm= "MAX";
        } else if (orMethod.equals("SUM") || orMethod.equals("4")) {
        	orm= "PROBOR";
        } else if (orMethod.equals("BOUNDED") || orMethod.equals("5")) {
        	orm= "BSUM";
        } else if (orMethod.equals("YAGER") || orMethod.equals("3")) {
        	orm= "BSUM";
        } else if (orMethod.equals("HAMACHER") || orMethod.equals("2")) {
        	orm= "HSUM";
        } else {
        	System.out.println("WARNING: Unknown OR method");
        	System.out.println("    We set \'MAX\' as OR method");
        	System.out.println();                            
        	orm= "MAX";
        }
        aggMethod= orMethod;
        String aggm= orm;
        impMethod = implicationoperatorList.get(0).getValue().toString();
        // DIENES_RESHER
        // LUKASIEWICZ
        // ZADEH
        // GOGUEN
        // GODEL
        // SHARP
        // MIZUMOTO
        // DUBOIS_PRADE
        // MIN
        //System.out.println("impMethod="+impMethod);
        String im="MIN";
        if (!impMethod.equals("MIN")) {
        	System.out.println("WARNING: Unknown IMPLICATION method");
        	System.out.println("    We set \'MIN\' as IMPLICATION method");
        	System.out.println();                            
        	im= "MIN";
        }
        if (aggregationoperatorList!=null) {
            defuzzMethod= aggregationoperatorList.get(0).getValue().toString();
        } else {
        	defuzzMethod="null";
        }
        // DefuzzMethod
        // 1 or WAM means weighted average method
        // 2 or FIRST.MAX means first maxima
        // 3 or LAST.MAX means last maxima
        // 4 or MEAN.MAX means mean maxima
        // 5 or COG means modified center of gravity (COG)
        //System.out.println("defuzzMethod="+defuzzMethod);
        String dm="COA";
        if (defuzzMethod.equals("WAM") || defuzzMethod.equals("1")) {
        	dm= "WA";
        } else if (defuzzMethod.equals("FIRST.MAX") || defuzzMethod.equals("2")) { 
        	dm= "LM";
        } else if (defuzzMethod.equals("LAST.MAX") || defuzzMethod.equals("3")) { 
        	dm= "RM";
        } else if (defuzzMethod.equals("MEAN.MAX") || defuzzMethod.equals("4")) { 
        	dm= "MOM";
        } else if (defuzzMethod.equals("COG") || defuzzMethod.equals("5")) { 
        	dm= "COG";
        } else {
        	System.out.println("WARNING: Unknown Defuzzification method");
        	if (tsk) {
        	    System.out.println("    We set \'WA\' as Defuzzification method");
        	    System.out.println();                            
        	    dm= "WA";
        	} else {
        	    System.out.println("    We set \'MOM\' as Defuzzification method");
        	    System.out.println();                            
        	    dm= "MOM";
        	}
        }
        int numDataVars = datafieldList.size();
        //System.out.println("numDataVars="+numDataVars);
        //System.out.println("nInputs="+nInputs);
        //System.out.println("nOutputs="+nOutputs);
        viname= new String[nInputs];
        voname= new String[nOutputs];
        for (int i = 0; i < numDataVars; i++){
        	if (i <nInputs) {
                viname[i] = datafieldList.get(i).getName();
        	} else {
                voname[i-nInputs] = datafieldList.get(i).getName();
        	}
        }
		fuzzySystemIEEE = new FuzzyInferenceSystem(methodType+" - "+typeModel.toUpperCase());
		// KNOWLEDGE BASE
		kb = new KnowledgeBaseType();
		fuzzySystemIEEE.setKnowledgeBase(kb);
        if (typeModel.equalsIgnoreCase("FRBCS")){
        	double[][] varinpMF = consVarMF(membershipfunctionList);
        	String[] labsNames = nameInputVars(membershipfunctionList);
    		//System.out.println("nInputs="+nInputs);
        	in= new FuzzyVariableType[nInputs];
    		//System.out.println("INPUTS");
        	int cont=0;
            for (int n=0; n<nInputs; n++) {
        		//System.out.println("viname[n]="+viname[n]);
           	    in[n]= new FuzzyVariableType(viname[n], (float)rangeDataOri[0][n], (float)rangeDataOri[1][n]);
            	int nbMFs= numLabels[n];
        		//System.out.println("numLabels[n]="+numLabels[n]);
            	FuzzyTermType[] ft= new FuzzyTermType[nbMFs];
           	    for (int k=0; k<nbMFs; k++) {
                    String tname= labsNames[cont];
           	    	float[] pp= null;
                    int ttype= (int)varinpMF[0][cont];
                    // 1 -> TRIANGLE
                    // 2 -> TRAPEZOID in left side
                    // 3 -> TRAPEZOID in right side
                    // 4 -> TRAPEZOID in the middle
                    // 5 -> GAUSSIAN
                    // 6 -> SIGMOID
                    // 7 -> BELL
                    if (ttype==1) {
                    	pp= new float[3];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_triangularShape;
                    } else if (ttype==2) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[1][cont];
                    	pp[2]= (float)varinpMF[2][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==3) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==4) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	pp[3]= (float)varinpMF[4][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==5) {
                    	pp= new float[2];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_gaussianShape;
                    } else if (ttype==6) {
                    	System.out.println("WARNING: Unknown Membership Function Type: SIGMOID");
                    	System.out.println("    We set \'sShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[2];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_sShape;
                    } else if (ttype==7) {
                    	System.out.println("WARNING: Unknown Membership Function Type: BELL");
                    	System.out.println("    We set \'piShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[2][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_piShape;
                    }
                    if (pp!=null) {                    
                    	ft[k] = new FuzzyTermType(tname, ttype, pp);
                        if (tname.startsWith("NOT(")) {
                        	ft[k].setComplement("true");
                        }
               	    	in[n].addFuzzyTerm(ft[k]);
                    }
           	    	cont++;
           	    }
           	    kb.addVariable(in[n]);
            }
    		//System.out.println("nOutputs="+nOutputs);
        	out= new FuzzyVariableType[nOutputs];
    		//System.out.println("OUTPUTS");
            for (int n=0; n<nOutputs; n++) {
            	Object[] obj= classvalueList.toArray();            	
            	int nbMFs= obj.length;
        		//System.out.println("voname[n]="+voname[n]);
        		//System.out.println("nbMFs="+nbMFs);
           	    out[n]= new FuzzyVariableType(voname[n], 1, nbMFs);
        	    float dv= 1;
           	    ((FuzzyVariableType)out[n]).setDefaultValue(dv);
           	    ((FuzzyVariableType)out[n]).setAccumulation(aggm);
           	    ((FuzzyVariableType)out[n]).setDefuzzifierName(dm);
           	    ((FuzzyVariableType)out[n]).setType("output");
           	    FuzzyTermType[] ft= new FuzzyTermType[nbMFs];
            	for (int k=0; k<nbMFs; k++) {
					float[] pp= new float[1];
					pp[0]= k+1;
					String tname= "Class " + String.valueOf(k+1);
    				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_singletonShape, pp);
					((FuzzyVariableType)out[n]).addFuzzyTerm((FuzzyTermType)ft[k]);
            	}           	    
           	    kb.addVariable(out[n]);
            }
            rb = new MamdaniRuleBaseType("rulebase1");
			((MamdaniRuleBaseType)rb).setActivationMethod(im);
			double[] gradeCertainty = null;
			if (gradeList != null) {
                gradeCertainty = consGradeCertainty(gradeList);
			}
            Rule[] rules = consRules(typeModel, ruleList, gradeCertainty, kb, andm, orm, null, voname[0]);
            for (int n=0; n<rules.length; n++) {
		        ((MamdaniRuleBaseType)rb).addRule((FuzzyRuleType)rules[n]);
            }
            
        } else if (typeModel.equalsIgnoreCase("MAMDANI")){
        	double[][] varMF = consVarMF(membershipfunctionList);
            int sumLabels = SuppCollection.sumArray(numLabels);
            int labelOutput = numLabels[numLabels.length-1];
            double[][] varinpMF = SuppCollection.getCol(varMF, 0, (sumLabels - labelOutput - 1));
            double[][] varoutMF = SuppCollection.getCol(varMF, (sumLabels - labelOutput), (sumLabels - 1));                                       
            String[] tempLabNames = nameInputVars(membershipfunctionList);
            String[] nameInputLabels = SuppCollection.getCol(tempLabNames, 0, (sumLabels - labelOutput - 1));
            String[] nameOutputLabels = SuppCollection.getCol(tempLabNames, (sumLabels - labelOutput), (sumLabels - 1));
    		//System.out.println("nInputs="+nInputs);
        	in= new FuzzyVariableType[nInputs];
    		//System.out.println("INPUTS");
        	int cont=0;
            for (int n=0; n<nInputs; n++) {
        		//System.out.println("viname[n]="+viname[n]);
           	    in[n]= new FuzzyVariableType(viname[n], (float)rangeDataOri[0][n], (float)rangeDataOri[1][n]);
            	int nbMFs= numLabels[n];
        		//System.out.println("numLabels[n]="+numLabels[n]);
            	FuzzyTermType[] ft= new FuzzyTermType[nbMFs];
           	    for (int k=0; k<nbMFs; k++) {
                    String tname= nameInputLabels[cont];
           	    	float[] pp= null;
                    int ttype= (int)varinpMF[0][cont];
                    // 1 -> TRIANGLE
                    // 2 -> TRAPEZOID in left side
                    // 3 -> TRAPEZOID in right side
                    // 4 -> TRAPEZOID in the middle
                    // 5 -> GAUSSIAN
                    // 6 -> SIGMOID
                    // 7 -> BELL
                    if (ttype==1) {
                    	pp= new float[3];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_triangularShape;
                    } else if (ttype==2) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[1][cont];
                    	pp[2]= (float)varinpMF[2][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==3) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==4) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	pp[3]= (float)varinpMF[4][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==5) {
                    	pp= new float[2];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_gaussianShape;
                    } else if (ttype==6) {
                    	System.out.println("WARNING: Unknown Membership Function Type: SIGMOID");
                    	System.out.println("    We set \'sShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[2];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_sShape;
                    } else if (ttype==7) {
                    	System.out.println("WARNING: Unknown Membership Function Type: BELL");
                    	System.out.println("    We set \'piShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[2][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_piShape;
                    }
                    if (pp!=null) {                    
    				    ft[k] = new FuzzyTermType(tname, ttype, pp);
                        if (tname.startsWith("NOT(")) {
                        	ft[k].setComplement("true");
                        }
               	    	in[n].addFuzzyTerm(ft[k]);
                    }
           	    	cont++;
           	    }
           	    kb.addVariable(in[n]);
            }
    		//System.out.println("nOutputs="+nOutputs);
        	out= new FuzzyVariableType[nOutputs];
        	cont=0;
    		//System.out.println("OUTPUTS");
            for (int n=0; n<nOutputs; n++) {
        		//System.out.println("voname[n]="+voname[n]);
           	    int nbMFs= labelOutput;
           	    out[n]= new FuzzyVariableType(voname[n], 1, nbMFs);
     	        float dv= 1;
        	    ((FuzzyVariableType)out[n]).setDefaultValue(dv);
        	    ((FuzzyVariableType)out[n]).setAccumulation(aggm);
        	    ((FuzzyVariableType)out[n]).setDefuzzifierName(dm);
        	    ((FuzzyVariableType)out[n]).setType("output");
        		//System.out.println("numLabels[n]="+numLabels[n]);
            	FuzzyTermType[] ft= new FuzzyTermType[nbMFs];
           	    for (int k=0; k<nbMFs; k++) {
                    String tname= nameOutputLabels[cont];
           	    	float[] pp= null;
                    int ttype= (int)varoutMF[0][cont];
                    // 1 -> TRIANGLE
                    // 2 -> TRAPEZOID in left side
                    // 3 -> TRAPEZOID in right side
                    // 4 -> TRAPEZOID in the middle
                    // 5 -> GAUSSIAN
                    // 6 -> SIGMOID
                    // 7 -> BELL
                    if (ttype==1) {
                    	pp= new float[3];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[2][cont];
                    	pp[2]= (float)varoutMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_triangularShape;
                    } else if (ttype==2) {
                    	pp= new float[4];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[1][cont];
                    	pp[2]= (float)varoutMF[2][cont];
                    	pp[3]= (float)varoutMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==3) {
                    	pp= new float[4];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[2][cont];
                    	pp[2]= (float)varoutMF[3][cont];
                    	pp[3]= (float)varoutMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==4) {
                    	pp= new float[4];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[2][cont];
                    	pp[2]= (float)varoutMF[3][cont];
                    	pp[3]= (float)varoutMF[4][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==5) {
                    	pp= new float[2];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_gaussianShape;
                    } else if (ttype==6) {
                    	System.out.println("WARNING: Unknown Membership Function Type: SIGMOID");
                    	System.out.println("    We set \'sShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[2];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_sShape;
                    } else if (ttype==7) {
                    	System.out.println("WARNING: Unknown Membership Function Type: BELL");
                    	System.out.println("    We set \'piShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[4];
                    	pp[0]= (float)varoutMF[1][cont];
                    	pp[1]= (float)varoutMF[2][cont];
                    	pp[2]= (float)varoutMF[2][cont];
                    	pp[3]= (float)varoutMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_piShape;
                    }
                    if (pp!=null) {                    
    				    ft[k] = new FuzzyTermType(tname, ttype, pp);
                        if (tname.startsWith("NOT(")) {
                        	ft[k].setComplement("true");
                        }
    					((FuzzyVariableType)out[n]).addFuzzyTerm((FuzzyTermType)ft[k]);
                    }
           	    	cont++;
           	    }
            	//System.out.println("voname[n]="+voname[n]);
        		//System.out.println("nbMFs="+nbMFs);
           	    kb.addVariable(out[n]);
            }
            rb = new MamdaniRuleBaseType("rulebase1");
			((MamdaniRuleBaseType)rb).setActivationMethod(im);
			double[] gradeCertainty = null;
			if (gradeList != null) {
                gradeCertainty = consGradeCertainty(gradeList);
			}
            Rule[] rules = consRules(typeModel, ruleList, gradeCertainty, kb, andm, orm, null, voname[0]);
            for (int n=0; n<rules.length; n++) {
		        ((MamdaniRuleBaseType)rb).addRule((FuzzyRuleType)rules[n]);
            }

        } else if (typeModel.equalsIgnoreCase("TSK")){  
        	double[][] varinpMF = consVarMF(membershipfunctionList);
            String[] nameInputLabels = nameInputVars(membershipfunctionList);
    		//System.out.println("nInputs="+nInputs);
        	in= new FuzzyVariableType[nInputs];
    		//System.out.println("INPUTS");
        	int cont=0;
            for (int n=0; n<nInputs; n++) {
        		//System.out.println("viname[n]="+viname[n]);
           	    in[n]= new FuzzyVariableType(viname[n], (float)rangeDataOri[0][n], (float)rangeDataOri[1][n]);
            	int nbMFs= numLabels[n];
        		//System.out.println("numLabels[n]="+numLabels[n]);
            	FuzzyTermType[] ft= new FuzzyTermType[nbMFs];
           	    for (int k=0; k<nbMFs; k++) {
                    String tname= nameInputLabels[cont];
           	    	float[] pp= null;
                    int ttype= (int)varinpMF[0][cont];
                    // 1 -> TRIANGLE
                    // 2 -> TRAPEZOID in left side
                    // 3 -> TRAPEZOID in right side
                    // 4 -> TRAPEZOID in the middle
                    // 5 -> GAUSSIAN
                    // 6 -> SIGMOID
                    // 7 -> BELL
                    if (ttype==1) {
                    	pp= new float[3];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_triangularShape;
                    } else if (ttype==2) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[1][cont];
                    	pp[2]= (float)varinpMF[2][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==3) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==4) {
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[3][cont];
                    	pp[3]= (float)varinpMF[4][cont];
                    	ttype= FuzzyTermType.TYPE_trapezoidShape;
                    } else if (ttype==5) {
                    	pp= new float[2];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_gaussianShape;
                    } else if (ttype==6) {
                    	System.out.println("WARNING: Unknown Membership Function Type: SIGMOID");
                    	System.out.println("    We set \'sShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[2];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	ttype= FuzzyTermType.TYPE_sShape;
                    } else if (ttype==7) {
                    	System.out.println("WARNING: Unknown Membership Function Type: BELL");
                    	System.out.println("    We set \'piShape\' as MF type");
                    	System.out.println();                            
                    	pp= new float[4];
                    	pp[0]= (float)varinpMF[1][cont];
                    	pp[1]= (float)varinpMF[2][cont];
                    	pp[2]= (float)varinpMF[2][cont];
                    	pp[3]= (float)varinpMF[3][cont];
                    	ttype= FuzzyTermType.TYPE_piShape;
                    }
                    if (pp!=null) {                    
    				    ft[k] = new FuzzyTermType(tname, ttype, pp);
               	    	in[n].addFuzzyTerm(ft[k]);
                    }
           	    	cont++;
           	    }
           	    kb.addVariable(in[n]);
            }
    		//System.out.println("nOutputs="+nOutputs);
        	out= new TskVariableType[nOutputs];
    		//System.out.println("OUTPUTS");
            double[][] funcTSK = consFuncTSK(tskthenpartList);
            for (int n=0; n<nOutputs; n++) {
        		//System.out.println("voname[n]="+voname[n]);
        	    out[n]= new TskVariableType(voname[n]);
            	int nbMFs= numLabels[n];
        	    float dv= ((float)rangeDataOri[0][nInputs]+(float)rangeDataOri[1][nInputs])/2;
        	    ((TskVariableType)out[n]).setDefaultValue(dv);
        	    ((TskVariableType)out[n]).setCombination(dm);
        	    ((TskVariableType)out[n]).setType("output");
        	    nbMFs= funcTSK.length;
        		//System.out.println("nbMFs="+nbMFs);
            	Term[] ft= new TskTermType[nbMFs];
            	for (int k=0; k<nbMFs; k++) {
            		 String tname= "t"+String.valueOf(k+1); 
            		 boolean ordZero= true;
				     float[] pp= new float[nInputs+1];
			    	 pp[0]= (float)funcTSK[k][nInputs];
				     for (int i=1; i<pp.length; i++) {
				    	  pp[i] = (float)funcTSK[k][i-1];
				    	  if ( (pp[i] != 0) && i < nInputs) {
				    		  ordZero= false;
				    	  }
				     }
				     if (ordZero) {
				    	 pp = new float[1];
				    	 pp[0]= (float)funcTSK[k][nInputs];
				     }
            	     if (pp.length==1) {
			             ft[k] = new TskTermType(tname, TskTerm._ORDER_0, pp);
            	     } else {
			             ft[k] = new TskTermType(tname, TskTerm._ORDER_1, pp);
            	     }
			         ((TskVariableType)out[n]).addTskTerm((TskTermType)ft[k]);
            	}
        	    //System.out.println("voname[n]="+voname[n]);
        		//System.out.println("nbMFs="+nbMFs);
           	    kb.addVariable(out[n]);
            }
		    rb = new TskRuleBaseType("rulebase1", FuzzySystemRuleBase.TYPE_TSK);
			((TskRuleBaseType)rb).setActivationMethod(im);
			double[] gradeCertainty = null;
			if (gradeList != null) {
                gradeCertainty = consGradeCertainty(gradeList);
			}
            Rule[] rules = consRules(typeModel, ruleList, gradeCertainty, kb, andm, orm, funcTSK, voname[0]);
            for (int n=0; n<rules.length; n++) {
		        ((TskRuleBaseType)rb).addTskRule((TskFuzzyRuleType)rules[n]);
            }
        }
        if (rb!=null)
            fuzzySystemIEEE.addRuleBase(rb);

        return fuzzySystemIEEE;
	}

    /**
     * It is used to construct parameter values of membership functions.
     * @param membershipfunctionList a list of the membership function tag.
     * @return parameter values of membership functions.
     */
    private double[][] consVarMF(List<MembershipFunction> membershipfunctionList){
            int numTerms = membershipfunctionList.size();
            double[][] varMF = new double[5][numTerms];
            // 1 -> TRIANGLE
            // 2 -> TRAPEZOID in left side
            // 3 -> TRAPEZOID in right side
            // 4 -> TRAPEZOID in the middle
            // 5 -> GAUSSIAN
            // 6 -> SIGMOID
            // 7 -> BELL
            for (int i = 0; i < numTerms; i++){
                if (membershipfunctionList.get(i).getType().equalsIgnoreCase("TRIANGLE"))
                    varMF[0][i] = Double.parseDouble("1");
                else if (membershipfunctionList.get(i).getType().equalsIgnoreCase("TRAPEZOID"))
                    varMF[0][i] = Double.parseDouble("4");
                else if (membershipfunctionList.get(i).getType().equalsIgnoreCase("GAUSSIAN"))
                    varMF[0][i] = Double.parseDouble("5");
                
                String[] paramVal = membershipfunctionList.get(i).getValue().toString().split("\\s+");
                int numParam = paramVal.length;
                for (int j = 1; j < numParam; j++){
                    varMF[j][i] = Double.parseDouble(paramVal[j]);
                }
            }
            return varMF;
    }
    /**
     * It is used to construct names of input variables.
     * @param membershipfunctionList a list of the membership function tag.
     * @return names of input variables
     */
    private String[] nameInputVars(List<MembershipFunction> membershipfunctionList){
            int numTerms = membershipfunctionList.size();
            String[] nameTerms = new String[numTerms];
            
            for (int i = 0; i < numTerms; i++){
                nameTerms[i] = membershipfunctionList.get(i).getName();
            }
            return nameTerms;
    }
    /**
     * It is used to construct a set of rules.
     * @param typeModel a type of model.
     * @param ruleList a list of the rule tag.
     * @param ruleWeights a vector of rule weights.
     * @param ftsk a matrix with the tsk consequents for all rules
     * @return a set of rules.
     */
    private Rule[] consRules(String typeModel, List<jfml.compatibility.Rule> ruleList, double[] ruleWeights, KnowledgeBaseType kb, String andm, String orm, double[][] ftsk, String vOutputName){
            int numRules = ruleList.size();
            Rule[] rules = new Rule[numRules];
            List<String> varRule = null;
            List<String> valueRule = null;
            List<String> operatorRule = null;
			AntecedentType[] ant= new AntecedentType[numRules];
			ConsequentType[] conMamdani= new ConsequentType[numRules];
			TskConsequentType[] conTSK= new TskConsequentType[numRules];
            if (typeModel.equalsIgnoreCase("FRBCS") || typeModel.equalsIgnoreCase("MAMDANI")){
                for (int i = 0; i < numRules; i++){
				    String rname="r"+String.valueOf(i+1);
		            ant[i] = new AntecedentType();
		            conMamdani[i] = new ConsequentType();
                	varRule = ruleList.get(i).getVarRule();
                    valueRule = ruleList.get(i).getValueRule();
                    operatorRule = ruleList.get(i).getOperator();
                    //String rule = "";
                    String ruleConnector="and";
                    //System.out.println("varRule.size()="+varRule.size());
                    for (int j = 0; j < varRule.size(); j++){
                         //System.out.println("varRule.get(j)="+varRule.get(j));
			             boolean DC=false;
                    	 KnowledgeBaseVariable v= kb.getVariable(varRule.get(j));
				         List tt= v.getTerms();
				         Term t= null;
				         String vr= valueRule.get(j);
				         if (vr.equals("dont_care")) {
				             DC=true;
				         }
			             String hedge= "";
				         if (!DC) {
				           if (vr.startsWith("extremely")) {
				        	   hedge= "extremely";
				        	   vr= vr.substring(10);
				           } else if (vr.startsWith("very")) {
				        	   hedge= "very";
				        	   vr= vr.substring(5);
				           } else if (vr.startsWith("somewhat")) {
				        	   hedge= "somewhat";
				        	   vr= vr.substring(9);
				           } else if (vr.startsWith("slightly")) {
				        	   hedge= "more_or_less";
				        	   vr= vr.substring(9);
				           } else if (vr.startsWith("not")) {
				        	   hedge= "not";
				        	   vr= vr.substring(4);
				           }
				           int k=0;
				           while (k<tt.size()) {
	 				         t = (Term)tt.get(k++);
	 				         if ( (t.getName().equals(vr)) || (t.getName().equals("Class "+valueRule.get(j))) ) {
	 				    	     //System.out.println("t.getName()="+vr);
	 				      	     break;
	 				         } else {
	 				    	     t = null;
	 				         }
				           }
				         }
		                 //System.out.println("j="+j);
                         if (j <= (varRule.size() - 2)) {
                        	 if (j < operatorRule.size()) {
                        	     String connectorID= operatorRule.get(j);
    		                     //System.out.println("connectorID="+connectorID);
                                 if (connectorID.equals("and")) {
                        	         ruleConnector="and";
                                 } else if (connectorID.equals("or")) {
                        	         ruleConnector="or";
                                 } else {
            				         System.out.println("WARNING: Unknown rule connector");		
                                     System.out.println("    We set \'and\' as rule connector");
                            	     System.out.println();                            
                        	         ruleConnector="and";
                                 }
                                 //rule += (varRule.get(j) + " is " + hedge + " " + vr + " " + operatorRule.get(j)) + " ";
                        	 } /*else {
                               if (j == (varRule.size() - 2)){
                                   rule += (varRule.get(j) + " is " + hedge + " " + vr) + " THEN ";
                               }
                        	 }*/
 				             if ( (!DC) && (v!=null) && (t!=null) ) {
                                 if (!hedge.equals("")) {
 				            	     ant[i].addClause(new ClauseType(v, t, hedge));
                                 } else {
     				            	 ant[i].addClause(new ClauseType(v, t));
                                 }
 				             }
                         } else if (j == (varRule.size() - 1)){
                            //rule += (varRule.get(j) + " is " + valueRule.get(j));
 				            if ( (!DC) && (v!=null) && (t!=null) ) {
						        //conMamdani[i].addThenClause((FuzzyVariableType)v, (FuzzyTermType)t);
                                if (!hedge.equals("")) {
						            conMamdani[i].addThenClause(new ClauseType(v, t, hedge));
                                } else {
						            conMamdani[i].addThenClause(new ClauseType(v, t));
                                }
 				            }
                         }
                    }
                    //System.out.println("IF " + rule);
                    if (ruleWeights != null) {
                        rules[i] = new FuzzyRuleType(rname, ruleConnector, andm, orm, (float)ruleWeights[i]);
                    } else {
                        rules[i] = new FuzzyRuleType(rname, ruleConnector, andm, orm, (new Float(1.0)).floatValue());
                    }
				    if (ant[i]!=null) {
               	        ((FuzzyRuleType)rules[i]).setAntecedent(ant[i]);
				    }
               	    if (conMamdani[i]!=null) {
              	        ((FuzzyRuleType)rules[i]).setConsequent(conMamdani[i]);
               	    }
                }
            } else {
            	// TSK
                for (int i = 0; i < numRules; i++){
				    String rname="r"+String.valueOf(i+1);
		            ant[i] = new AntecedentType();
		            conTSK[i] = new TskConsequentType();
                	varRule = ruleList.get(i).getVarRule();
                    valueRule = ruleList.get(i).getValueRule();
                    operatorRule = ruleList.get(i).getOperator();
                    //String rule = "";
                    String ruleConnector="and";
                    //System.out.println("varRule.size()="+varRule.size());
                    for (int j = 0; j < varRule.size()+1; j++){
                    	 boolean DC= false;
				         Term t= null;
			             String hedge= "";
			             KnowledgeBaseVariable v= null;
                    	 if (j < varRule.size()) {
                           //System.out.println("varRule.get(j)="+varRule.get(j));
                    	   v= kb.getVariable(varRule.get(j));
				           List tt= v.getTerms();
				           String vr= valueRule.get(j);
				           if (vr.equals("dont_care")) {
				        	   DC=true;
				           }
				           if (!DC) {
				             if (vr.startsWith("extremely")) {
				        	     hedge= "extremely";
				        	     vr= vr.substring(10);
				             } else if (vr.startsWith("very")) {
				        	     hedge= "very";
				        	     vr= vr.substring(5);
				             } else if (vr.startsWith("somewhat")) {
				        	     hedge= "somewhat";
				        	     vr= vr.substring(9);
				             } else if (vr.startsWith("slightly")) {
				        	     hedge= "more_or_less";
				        	     vr= vr.substring(9);
				             } else if (vr.startsWith("not")) {
				        	     hedge= "not";
				        	     vr= vr.substring(4);
				             }
				             int k=0;
				             while (k<tt.size()) {
	 				           t = (Term)tt.get(k++);
	 				           if ( (t.getName().equals(vr)) || (t.getName().equals("Class "+valueRule.get(j))) ) {
	 				    	       //System.out.println("t.getName()="+vr);
	 				      	       break;
	 				           } else {
	 				    	       t = null;
	 				           }
				             }
				           }
                    	 }
		                 //System.out.println("j="+j);
                         if (j <= (varRule.size() - 1)) {
                        	 if (j < operatorRule.size()) {
                        	     String connectorID= operatorRule.get(j);
    		                     //System.out.println("connectorID="+connectorID);
                                 if (connectorID.equals("and")) {
                        	         ruleConnector="and";
                                 } else if (connectorID.equals("or")) {
                        	         ruleConnector="or";
                                 } else {
            				         System.out.println("WARNING: Unknown rule connector");		
                                     System.out.println("    We set \'and\' as rule connector");
                            	     System.out.println();                            
                        	         ruleConnector="and";
                                 }
                                 //rule += (varRule.get(j) + " is " + hedge + " " + vr + " " + operatorRule.get(j)) + " ";
                        	 } /*else {
                               if (j == (varRule.size() - 2)){
                                   rule += (varRule.get(j) + " is " + hedge + " " + vr) + " THEN ";
                               }
                        	 }*/
 				             if ( (!DC) && (v!=null) && (t!=null)) {
                                 //System.out.println("v.getName()= "+v.getName());
                                 //System.out.println("t.getName()= "+t.getName());
 				            	 if (!hedge.equals("")) {
 				            	     ant[i].addClause(new ClauseType(v, t, hedge));
                                 } else {
     				            	 ant[i].addClause(new ClauseType(v, t));
                                 }
 				             }
                         } else if (j == varRule.size()){
                        	 //System.out.println("vOutputName="+vOutputName);
                      	     v= kb.getVariable(vOutputName);
  				             t= v.getTerm("t"+String.valueOf(i+1));
                        	 //rule += (varRule.get(j) + " is " + valueRule.get(j));
 				             if ( (v!=null) && (t!=null) ) {
  						        conTSK[i].addTskThenClause((TskVariableType)v, (TskTermType)t);
 				             }
                         }
                    }
                    //System.out.println("IF " + rule);
                    rules[i] = new TskFuzzyRuleType(rname, ruleConnector, andm, orm, (new Float(1.0)).floatValue());
					if (ant[i]!=null) {
              	       ((TskFuzzyRuleType)rules[i]).setAntecedent(ant[i]);
					}
					if (conTSK[i]!=null) {
             	       ((TskFuzzyRuleType)rules[i]).setTskConsequent(conTSK[i]);
					}
                }
            }
            return rules;
    }
    /**
     * It is used to construct grade of certainty.
     * @param gradeList a list of the grade tag.
     * @return grade of certainty.
     */
    private double[] consGradeCertainty(List<Grade> gradeList){
            int numRules = gradeList.size();
            double[] grade = new double[numRules];
            for (int i = 0; i < numRules; i++){
                grade[i] = Double.parseDouble(gradeList.get(i).getValue().get(0));
            }
            return grade;
    }  
    /**
     * It is used to construct functions of the TSK model.
     * @param tskthenpartList a list of the then tag.
     * @return functions of the TSK model.
     */
    private double[][] consFuncTSK(List<TSKThenPart> tskthenpartList){
            int numRules = tskthenpartList.size();
            int numCols = tskthenpartList.get(0).getValue().size();
            //System.out.println("numRules="+numRules);
            //System.out.println("numCols="+numCols);
            double[][] funcTSK = new double[numRules][numCols];
            for (int i = 0; i < numRules; i++){
                for (int j = 0; j < numCols; j++){
                    funcTSK[i][j] = Double.parseDouble(tskthenpartList.get(i).getValue().get(j));
                }
            }
            return funcTSK;
    }
}
