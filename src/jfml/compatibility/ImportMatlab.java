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
import java.util.StringTokenizer;

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
 * This class allows to import a fuzzy system in MATLAB format.
 * 
 * @author Jose Alonso
 *
 */
public class ImportMatlab extends Import {

	/**
	 * Constructor by default
	 */
	public ImportMatlab() {
	}

	@Override
	public FuzzyInferenceSystem importFuzzySystem(String path) throws java.io.IOException {
		String fuzzySystemMatlab, beginSentence;
		StringTokenizer line, lines;
		FuzzyInferenceSystem fuzzySystemIEEE= null;
		String name, type;
		String andMethod, orMethod, impMethod, aggMethod, defuzzMethod;
		int nInputs, nOutputs, nRules;
		String[] viname= null;
		String[] voname= null;
		float[] vrange= new float[2];
		KnowledgeBaseType kb= null;
		FuzzyVariableType[] in= null;
		KnowledgeBaseVariable[] out= null;
		FuzzySystemRuleBase rb= null;
		System.out.println("Input File: "+path);
		fuzzySystemMatlab = readFile(path);
		//System.out.println(fuzzySystemMatlab);
		lines = new StringTokenizer(fuzzySystemMatlab, "\n\r");
		while (lines.hasMoreTokens()) {
			line = new StringTokenizer(lines.nextToken(), "\t ");
			beginSentence = line.nextToken();
			//System.out.println(beginSentence);
			if (beginSentence.equals("[System]")) {
				line = new StringTokenizer(lines.nextToken(), "\t ");
				//System.out.println(line.countTokens());
				int ct= line.countTokens();
				//System.out.println(ct);
				name= "";
				String aux= line.nextToken();
				for (int n=0; n<ct; n++) {
					 //System.out.println(aux);
					 if (n==0) {
						 if (ct==1) {
						     name= aux.substring(6, aux.length()-1);
						 } else {
							 name= aux.substring(6);
						 }
					 } else if (n==ct-1) {
						 name= name+" "+aux.substring(0, aux.length()-1);
					 } else {
						 name= name+" "+aux;
					 }
					 if (line.hasMoreTokens()) {
					     aux= line.nextToken();
					 }
				}
				//System.out.println(name);
				line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                type = aux.substring(6, aux.length()-1);
				//System.out.println(type);
        		fuzzySystemIEEE = new FuzzyInferenceSystem(name+" - "+type.toUpperCase());
        		// KNOWLEDGE BASE
        		kb = new KnowledgeBaseType();
        		fuzzySystemIEEE.setKnowledgeBase(kb);
                line = new StringTokenizer(lines.nextToken(), "\t ");
        		aux= line.nextToken();
				if (aux.startsWith("Version=")) {
	                line = new StringTokenizer(lines.nextToken(), "\t ");
					aux= line.nextToken();
				}
                nInputs= (new Integer(aux.substring(10, aux.length()))).intValue();
				//System.out.println(nInputs);
				line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                nOutputs= (new Integer(aux.substring(11, aux.length()))).intValue();
				//System.out.println(nOutputs);
				line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                nRules= (new Integer(aux.substring(9, aux.length()))).intValue();
				//System.out.println(nRules);
				line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                andMethod = aux.substring(11, aux.length()-1);
				//System.out.println(andMethod);
                String andm="MIN";
                if (andMethod.equals("min")) {
                	andm= "MIN";
                } else if (andMethod.equals("prod")) {
                	andm= "PROD";
                } else if (andMethod.startsWith("custom")) {
                	System.out.println("WARNING: Customized AND method");
                	System.out.println("    The import of custom methods is not implemented yet.");
                	System.out.println("    We set \'min\' as AND method");
                	System.out.println();                            
                	andm= "MIN";
                }
                line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                orMethod = aux.substring(10, aux.length()-1);
				//System.out.println(orMethod);
                String orm="MAX";
                if (orMethod.equals("max")) {
                	orm= "MAX";
                } else if (andMethod.equals("probor")) {
                	andm= "PROBOR";
                } else if (andMethod.startsWith("custom")) {
                	System.out.println("WARNING: Customized OR method");
                	System.out.println("    The import of custom methods is not implemented yet.");
                	System.out.println("    We set \'max\' as OR method");
                	System.out.println();                            
                	orm= "MAX";
                }
                line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                impMethod = aux.substring(11, aux.length()-1);
				//System.out.println(impMethod);
                String im="MIN";
                if (impMethod.equals("min")) {
                	im= "MIN";
                } else if (impMethod.equals("prod")) {
                	im= "PROD";
                } else if (impMethod.startsWith("custom")) {
                	System.out.println("WARNING: Customized Implication method");
                	System.out.println("    The import of custom methods is not implemented yet.");
                	System.out.println("    We set \'min\' as Implication method");
                	System.out.println();                            
                	im= "MIN";
                }
				line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                aggMethod = aux.substring(11, aux.length()-1);
				//System.out.println(aggMethod);
                String aggm="MAX";
                if (aggMethod.equals("max")) {
                	aggm= "MAX";
                } else if (aggMethod.equals("sum")) {
                	aggm= "BSUM";
                } else if (aggMethod.equals("probor")) {
                	aggm= "PROBOR";
                } else if (aggMethod.startsWith("custom")) {
                	System.out.println("WARNING: Customized Aggregation method");
                	System.out.println("    The import of custom methods is not implemented yet.");
                	System.out.println("    We set \'max\' as Aggregation method");
                	System.out.println();                            
                	aggm= "MAX";
                }
				line = new StringTokenizer(lines.nextToken(), "\t ");
				aux= line.nextToken();
                defuzzMethod = aux.substring(14, aux.length()-1);
				//System.out.println(defuzzMethod);
                String dm="COA";
                if (defuzzMethod.equals("centroid")) {
                	dm= "COG";
                } else if (defuzzMethod.equals("bisector")) { 
                	dm= "COA";
                } else if (defuzzMethod.equals("mom")) { 
                	dm= "MOM";
                } else if (defuzzMethod.equals("lom")) { 
                	dm= "RM";
                } else if (defuzzMethod.equals("som")) { 
                	dm= "LM";
                } else if (defuzzMethod.equals("wtaver")) { 
                	dm= "WA";
                } else if (defuzzMethod.equals("wtsum")) { 
                	dm= "WA";
                } else if (aggMethod.startsWith("custom")) {
                	System.out.println("WARNING: Customized Defuzzification method");
                	System.out.println("    The import of custom methods is not implemented yet.");
                	System.out.println("    We set \'COA\' as Defuzzification method");
                	System.out.println();                            
                	aggm= "COA";
                }
                lines.nextToken();
            	in= new FuzzyVariableType[nInputs];
            	viname= new String[nInputs];
				//System.out.println("INPUTS");
                for (int n=0; n<nInputs; n++) {
    				//System.out.println("n="+n);
    				line = new StringTokenizer(lines.nextToken(), "\t ");
    				ct= line.countTokens();
    				//System.out.println(ct);
    				viname[n]= "";
    				aux= line.nextToken();
    				for (int m=0; m<ct; m++) {
    					 if (m==0) {
    						 if (ct==1) {
    							 viname[n]= aux.substring(6, aux.length()-1);
    						 } else {
        						 viname[n]= aux.substring(6);
    						 }
    					 } else if (m==ct-1) {
    						 viname[n]= viname[n]+" "+aux.substring(0, aux.length()-1);
    					 } else {
    						 viname[n]= viname[n]+" "+aux;
    					 }
    					 if (line.hasMoreTokens()) {
    					     aux= line.nextToken();
    					 }
    				}
    				//System.out.println(viname[n]);
    				line = new StringTokenizer(lines.nextToken(), "\t ");
    				aux= line.nextToken();
    				//System.out.println(aux);
                    String vrangeaux1 = aux.substring(7, aux.length());
    				//System.out.println(vrangeaux1);
    				vrange[0] = (new Float(vrangeaux1)).floatValue();
    				aux= line.nextToken();
    				//System.out.println(aux);
                    String vrangeaux2 = aux.substring(0, aux.length()-1);
    				//System.out.println(vrangeaux2);
    				vrange[1] = (new Float(vrangeaux2)).floatValue();
    				//System.out.println(vrange[0]);
    				//System.out.println(vrange[1]);
               	    in[n]= new FuzzyVariableType(viname[n], vrange[0], vrange[1]);
    				line = new StringTokenizer(lines.nextToken(), "\t ");
    				aux= line.nextToken();
    				//System.out.println(aux);
    				String vnm= aux.substring(7, aux.length());
                	int nbMFs= (new Integer(vnm)).intValue();
                	FuzzyTermType[] ft= new FuzzyTermType[nbMFs];
               	    for (int k=0; k<nbMFs; k++) {
        				line = new StringTokenizer(lines.nextToken(), "\t ");
        				line.nextToken("=");
        				String auxB= line.nextToken(":");
        				//System.out.println(auxB);
                        StringTokenizer st= new StringTokenizer(auxB);
        				ct= st.countTokens();
        				//System.out.println(ct);
        				String tname= "";
        				String auxB2= st.nextToken();
        				for (int m=0; m<ct; m++) {
        					 if (m==0) {
        						 if (ct==1) {
        							 tname= auxB2.substring(2, auxB2.length()-1);
        						 } else {
            						 tname= auxB2.substring(2);
        						 }
        					 } else if (m==ct-1) {
        						 tname= tname+" "+auxB2.substring(0, auxB2.length()-1);
        					 } else {
        						 tname= tname+" "+auxB2;
        					 }
        					 if (st.hasMoreTokens()){
        					     auxB2= st.nextToken();
        					 }
        				}
        				//String tname= auxB.substring(2,auxB.length()-1);
        				//System.out.println(tname);
        				String auxC= line.nextToken(",");
        				//System.out.println(auxC);
        				String ttype= auxC.substring(2,auxC.length()-1);
        				//System.out.println(ttype);
        				aux= line.nextToken();
        				//System.out.println(aux);
        				float[] pp;
        				if (ttype.equals("trimf")) {
        					pp= this.fillParameters(aux, false);
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("trapmf")) {
        					pp= this.fillParameters(aux, false);
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_trapezoidShape, pp);
        				} else if (ttype.equals("gaussmf")) {
        					pp= this.fillParameters(aux, false);
        					float c= pp[1];
        					float sigma= pp[0];
        					pp[0]= c;
        					pp[1]= sigma;
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_gaussianShape, pp);
        				} else if (ttype.equals("gauss2mf")) {
        					//pp= this.fillParameters(aux);
        					pp= new float[3];
        					pp[0]=vrange[0];
        					pp[1]=(vrange[0]+vrange[1])/2;
        					pp[2]=vrange[1];
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();                            
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("gbellmf")) {
        					//pp= this.fillParameters(aux);
        					pp= new float[3];
        					pp[0]=vrange[0];
        					pp[1]=(vrange[0]+vrange[1])/2;
        					pp[2]=vrange[1];
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();                            
        					//float a= pp[0];
        					//float b= pp[1];
        					//float c= pp[2];
        					//pp= new float[2];
        					//pp[0]= c;
        					//pp[1]= a*a;
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("sigmf")) {
        					pp= this.fillParameters(aux, false);
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_sShape\'");
                        	System.out.println();                            
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_sShape, pp);
        				} else if (ttype.equals("dsigmf")) {
        					//pp= this.fillParameters(aux);
        					pp= new float[3];
        					pp[0]=vrange[0];
        					pp[1]=(vrange[0]+vrange[1])/2;
        					pp[2]=vrange[1];
        					// Difference between two sigmoidal functions membership function
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();
                        	//float a1= pp[0];
                        	//float c1= pp[1];
                        	//float a2= pp[2];
                        	//float c2= pp[3];
                        	//pp= new float[4];
                        	//pp[0]=vrange[0];
                        	//pp[1]=c1;
                        	//pp[2]=c2;
                        	//pp[3]=vrange[1];
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("psigmf")) {
        					//pp= this.fillParameters(aux);
            				// Product of two sigmoidal membership functions
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();
                        	//float a1= pp[0];
                        	//float c1= pp[1];
                        	//float a2= pp[2];
                        	//float c2= pp[3];
                        	pp= new float[3];
                        	pp[0]=vrange[0];
                        	pp[1]=(vrange[0]+vrange[1])/2;
                        	pp[2]=vrange[1];
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("zmf")) {
        					pp= this.fillParameters(aux, false);
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_zShape, pp);
        				} else if (ttype.equals("pimf")) {
        					pp= this.fillParameters(aux, false);
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_trapezoidShape\'");
                        	System.out.println();
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_trapezoidShape, pp);
        				} else if (ttype.equals("smf")) {
        					pp= this.fillParameters(aux, false);
            				ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_sShape, pp);
        				} else if (ttype.equals("constant")) {
        					pp= this.fillParameters(aux, false);
           				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_singletonShape, pp);
        				}
        				in[n].addFuzzyTerm(ft[k]);
               	    }
                    lines.nextToken();
               	    kb.addVariable(in[n]);
                }
                if (type.equals("mamdani")) {
            	    out= new FuzzyVariableType[nOutputs];
                } else {
            	    out= new TskVariableType[nOutputs];
                }
            	voname= new String[nOutputs];
				//System.out.println("OUTPUTS");
                for (int n=0; n<nOutputs; n++) {
    				//System.out.println("n="+n);
    				line = new StringTokenizer(lines.nextToken(), "\t ");
    				ct= line.countTokens();
    				//System.out.println(ct);
    				voname[n]= "";
    				aux= line.nextToken();
    				for (int m=0; m<ct; m++) {
    					 if (m==0) {
    						 if (ct==1) {
    							 voname[n]= aux.substring(6, aux.length()-1);
    						 } else {
        						 voname[n]= aux.substring(6);
    						 }
    					 } else if (m==ct-1) {
    						 voname[n]= voname[n]+" "+aux.substring(0, aux.length()-1);
    					 } else {
    						 voname[n]= voname[n]+" "+aux;
    					 }
    					 if (line.hasMoreTokens()) {
    					     aux= line.nextToken();
    					 }
    				}
    				//System.out.println(voname[n]);
    				line = new StringTokenizer(lines.nextToken(), "\t ");
    				aux= line.nextToken();
    				//System.out.println(aux);
                    String vrangeaux1 = aux.substring(7, aux.length());
    				//System.out.println(vrangeaux1);
    				vrange[0] = (new Float(vrangeaux1)).floatValue();
    				aux= line.nextToken();
    				//System.out.println(aux);
                    String vrangeaux2 = aux.substring(0, aux.length()-1);
    				//System.out.println(vrangeaux2);
    				vrange[1] = (new Float(vrangeaux2)).floatValue();
    				//System.out.println(vrange[0]);
    				//System.out.println(vrange[1]);
                    if (type.equals("mamdani")) {
                   	    out[n]= new FuzzyVariableType(voname[n], vrange[0], vrange[1]);
                	    // check
                	    float dv= (vrange[0]+vrange[1])/2;
                   	    ((FuzzyVariableType)out[n]).setDefaultValue(dv);
                   	    ((FuzzyVariableType)out[n]).setAccumulation(aggm);
                   	    ((FuzzyVariableType)out[n]).setDefuzzifierName(dm);
                   	    ((FuzzyVariableType)out[n]).setType("output");
                    } else {
                	    out[n]= new TskVariableType(voname[n]);
                	    // check
                	    float dv= (vrange[0]+vrange[1])/2;
                	    ((TskVariableType)out[n]).setDefaultValue(dv);
                	    ((TskVariableType)out[n]).setCombination(dm);
                	    ((TskVariableType)out[n]).setType("output");
                    }
    				line = new StringTokenizer(lines.nextToken(), "\t ");
    				aux= line.nextToken();
    				//System.out.println(aux);
    				String vnm= aux.substring(7, aux.length());
                	int nbMFs= (new Integer(vnm)).intValue();
                	Term[] ft;
                    if (type.equals("mamdani")) {
                	    ft= new FuzzyTermType[nbMFs];
                    } else {
                	    ft= new TskTermType[nbMFs];
                    }
                	for (int k=0; k<nbMFs; k++) {
        				line = new StringTokenizer(lines.nextToken(), "\t ");
        				line.nextToken("=");
        				String auxB= line.nextToken(":");
        				//System.out.println(auxB);
                        StringTokenizer st= new StringTokenizer(auxB);
        				ct= st.countTokens();
        				//System.out.println(ct);
        				String tname= "";
        				String auxB2= st.nextToken();
        				for (int m=0; m<ct; m++) {
        					 if (m==0) {
        						 if (ct==1) {
        							 tname= auxB2.substring(2, auxB2.length()-1);
        						 } else {
            						 tname= auxB2.substring(2);
        						 }
        					 } else if (m==ct-1) {
        						 tname= tname+" "+auxB2.substring(0, auxB2.length()-1);
        					 } else {
        						 tname= tname+" "+auxB2;
        					 }
        					 if (st.hasMoreTokens()) {
        					     auxB2= st.nextToken();
        					 }
        				}
        				//String tname= auxB.substring(2,auxB.length()-1);
        				//System.out.println(tname);
        				String auxC= line.nextToken(",");
        				//System.out.println(auxC);
        				String ttype= auxC.substring(2,auxC.length()-1);
        				//System.out.println(ttype);
        				aux= line.nextToken();
        				//System.out.println(aux);
        				float[] pp= null;
        				if (ttype.equals("trimf")) {
        					pp= this.fillParameters(aux, false);
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("trapmf")) {
        					pp= this.fillParameters(aux, false);
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_trapezoidShape, pp);
        				} else if (ttype.equals("gaussmf")) {
        					pp= this.fillParameters(aux, false);
        					float c= pp[1];
        					float sigma= pp[0];
        					pp[0]= c;
        					pp[1]= sigma;
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_gaussianShape, pp);
        				} else if (ttype.equals("gauss2mf")) {
        					//pp= this.fillParameters(aux);
        					pp= new float[3];
        					pp[0]=vrange[0];
        					pp[1]=(vrange[0]+vrange[1])/2;
        					pp[2]=vrange[1];
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();                            
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("gbellmf")) {
        					//pp= this.fillParameters(aux);
        					pp= new float[3];
        					pp[0]=vrange[0];
        					pp[1]=(vrange[0]+vrange[1])/2;
        					pp[2]=vrange[1];
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();                            
        					//float a= pp[0];
        					//float b= pp[1];
        					//float c= pp[2];
        					//pp= new float[2];
        					//pp[0]= c;
        					//pp[1]= a*a;
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("sigmf")) {
        					pp= this.fillParameters(aux, false);
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_sShape\'");
                        	System.out.println();                            
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_sShape, pp);
        				} else if (ttype.equals("dsigmf")) {
        					//pp= this.fillParameters(aux);
        					pp= new float[3];
        					pp[0]=vrange[0];
        					pp[1]=(vrange[0]+vrange[1])/2;
        					pp[2]=vrange[1];
        					// Difference between two sigmoidal functions membership function
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();
                        	//float a1= pp[0];
                        	//float c1= pp[1];
                        	//float a2= pp[2];
                        	//float c2= pp[3];
                        	//pp= new float[4];
                        	//pp[0]=vrange[0];
                        	//pp[1]=c1;
                        	//pp[2]=c2;
                        	//pp[3]=vrange[1];
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("psigmf")) {
        					//pp= this.fillParameters(aux);
            				// Product of two sigmoidal membership functions
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_triangularShape\'");
                        	System.out.println();
                        	//float a1= pp[0];
                        	//float c1= pp[1];
                        	//float a2= pp[2];
                        	//float c2= pp[3];
                        	pp= new float[3];
                        	pp[0]=vrange[0];
                        	pp[1]=(vrange[0]+vrange[1])/2;
                        	pp[2]=vrange[1];
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_triangularShape, pp);
        				} else if (ttype.equals("zmf")) {
        					pp= this.fillParameters(aux, false);
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_zShape, pp);
        				} else if (ttype.equals("pimf")) {
        					pp= this.fillParameters(aux, false);
            				// it does not exist in JFML. Look for the most similar shape
                        	System.out.println("WARNING: Unknown membership function");
                        	System.out.println("    We set \'TYPE_trapezoidShape\'");
                        	System.out.println();
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_trapezoidShape, pp);
        				} else if (ttype.equals("smf")) {
        					pp= this.fillParameters(aux, false);
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_sShape, pp);
        				} else if (ttype.equals("constant")) {
        					pp= this.fillParameters(aux, false);
            				if (type.equals("mamdani"))
            				    ft[k] = new FuzzyTermType(tname, FuzzyTermType.TYPE_singletonShape, pp);
        				} else if (ttype.equals("linear")) {
        					pp= this.fillParameters(aux, true);
        					// this is only for TSK-order1
        				}
        				if (type.equals("mamdani")) {
        					((FuzzyVariableType)out[n]).addFuzzyTerm((FuzzyTermType)ft[k]);
        				} else {
                            if (pp!=null) {
                            	if (pp.length==1) {
        					        ft[k] = new TskTermType(tname, TskTerm._ORDER_0, pp);
                            	} else {
        					        ft[k] = new TskTermType(tname, TskTerm._ORDER_1, pp);
                            	}
        					    ((TskVariableType)out[n]).addTskTerm((TskTermType)ft[k]);
                            } else {
                            	System.out.println("WARNING: Wrong Matlab file");
                            	System.out.println("    TSK: output parameters for linguistic terms are not defined properly");
                            	System.out.println();                            
                   			}
        				}
               	    }
					kb.addVariable(out[n]);
                    lines.nextToken();
                }
				if (type.equals("mamdani")) {
                    rb = new MamdaniRuleBaseType("rulebase1");
    				((MamdaniRuleBaseType)rb).setActivationMethod(im);
				} else {
        		    rb = new TskRuleBaseType("rulebase1", FuzzySystemRuleBase.TYPE_TSK);
    				((TskRuleBaseType)rb).setActivationMethod(im);
				}
				AntecedentType[] ant= new AntecedentType[nRules];
				ConsequentType[] conMamdani= new ConsequentType[nRules];
				TskConsequentType[] conTSK= new TskConsequentType[nRules];
	            //System.out.println("RULES");
                for (int n=0; n<nRules; n++) {
		            //System.out.println("RULE: "+String.valueOf(n+1));
		            if (!lines.hasMoreTokens()) {
						System.out.println("WARNING: Wrong Matlab file");		
						System.out.println("    Wrong number of rules: Rules are missing");		
                    	System.out.println();                            
		            } else {
		            	line = new StringTokenizer(lines.nextToken(), "\t ");
		            	Rule r1;
    				    String rname="r"+String.valueOf(n+1);
    				    int a=0;
			            ant[n] = new AntecedentType();
		                //System.out.println("ANTECEDENTS");
    				    for (int k=0; k<nInputs; k++) {
   				         //System.out.println("k="+k);
    				     aux= line.nextToken();
    				     //System.out.println(aux);
				    	 boolean not= false;
				    	 boolean DC= false;
    				     if (k==nInputs-1) {
    				    	 if (aux.endsWith(",")) {
   						         a= (new Integer(aux.substring(0,aux.length()-1))).intValue();
   						         if (a==0) {
   						        	 DC= true;
   						         } else if (a>0) {
   						        	 a= a-1;
   						         } else if (a<0) {
   						        	 not= true;
   						        	 a= Math.abs(a)-1;
   						         }
    				    	 } else { 
    				    		 a= (new Integer(aux)).intValue();
   						         if (a==0) {
   						        	 DC= true;
   						         } else if (a>0) {
   						        	 a= a-1;
   						         } else if (a<0) {
   						        	 not= true;
   						        	 a= Math.abs(a)-1;
   						         }
    				    	 }
    				     } else {
    				         a= (new Integer(aux)).intValue();
					         if (a==0) {
					        	 DC= true;
					         } else if (a>0) {
   						       	 a= a-1;
					         } else if (a<0) {
   						       	 not= true;
   						       	 a= Math.abs(a)-1;
   						     }
    				     }
    				     /////////
    				     if (!DC) {
  				             KnowledgeBaseVariable v= kb.getVariable(viname[k]);
  				             List tt= v.getTerms();
  				             Term t = (Term)tt.get(a);
    				         if (not) {
    				            ((FuzzyTermType)t).setComplement("TRUE");
    				         }
  	            	         ant[n].addClause(new ClauseType(v, t));
    				     }
    				    }
		                //System.out.println("CONCLUSIONS");
    				    if (type.equals("mamdani")) {
				            conMamdani[n] = new ConsequentType();
   				        } else {
				            conTSK[n] = new TskConsequentType();
   				        }
    				    for (int k=0; k<nOutputs; k++) {
  				         //System.out.println("k="+k);
   				         aux= line.nextToken();
   				         //System.out.println(aux);
   				         if (aux.equals(",")) {
   	   				         aux= line.nextToken();
   				         } else if (aux.startsWith(",")) {
   				        	 aux= aux.substring(1);
   				         }
				    	 boolean not= false;
				    	 boolean DC= false;
			             a= (new Integer(aux)).intValue();
				         if (a==0) {
				        	 DC= true;
				         } else if (a>0) {
					         a= a-1;
					     } else {
					       	 not= true;
					       	 a= Math.abs(a)-1;
					     }
                         if (!DC) {
   				             KnowledgeBaseVariable v= kb.getVariable(voname[k]);
   				             List tt= v.getTerms();
   				             Term t = (Term)tt.get(a);
   				             if (type.equals("mamdani")) {
   				                 conMamdani[k] = new ConsequentType();
   	    				         if (not) {
   	     				            ((FuzzyTermType)t).setComplement("TRUE");
   	     				         }
   						         conMamdani[n].addThenClause((FuzzyVariableType)v, (FuzzyTermType)t);
   	    				     } else {
   				                 conTSK[k] = new TskConsequentType();
   	    				         //if (not) {
   				                   // Complement is not allowed with TSK terms
    	     				       //     ((TskTerm)t).setComplement("TRUE");
    	     				     //}
   						         conTSK[n].addTskThenClause((TskVariableType)v, (TskTermType)t);
   	    				     }
                         }
   				        }
		                aux= line.nextToken();
		                //System.out.println(aux);
    				    float rweight= (new Float(aux.substring(1, aux.length()-1))).floatValue();
    				    //System.out.println(rweight);
		                aux= line.nextToken();
		                aux= line.nextToken();
		                //System.out.println(aux);
                        int connectorID= (new Integer(aux)).intValue();
                        String ruleConnector="";
                        if (connectorID==2) {
                    	    ruleConnector="or";
                        } else if (connectorID==1) {
                    	    ruleConnector="and";
                        } else {
        				    System.out.println("WARNING: Wrong Matlab file");		
        				    System.out.println("    Unknown rule connector");		
                        	System.out.println();                            
                        }
    				    //System.out.println(ruleConnector);
    				    if (type.equals("mamdani")) {
            		        r1 = new FuzzyRuleType(rname, ruleConnector, andm, orm, rweight);
        				    if (ant[n]!=null) {
                       	        ((FuzzyRuleType)r1).setAntecedent(ant[n]);
        				    }
                       	    if (conMamdani[n]!=null) {
                      	        ((FuzzyRuleType)r1).setConsequent(conMamdani[n]);
                       	    }
    				    } else {
            		        r1 = new TskFuzzyRuleType(rname, ruleConnector, andm, orm, rweight);
       					    if (ant[n]!=null) {
                      	        ((TskFuzzyRuleType)r1).setAntecedent(ant[n]);
       					    }
      					    if (conTSK[n]!=null) {
                     	        ((TskFuzzyRuleType)r1).setTskConsequent(conTSK[n]);
      					    }
    				    }
    				    if (type.equals("mamdani")) {
    				        ((MamdaniRuleBaseType)rb).addRule((FuzzyRuleType)r1);
    				    } else {
    				        ((TskRuleBaseType)rb).addTskRule((TskFuzzyRuleType)r1);
    				    }
		            }
                }
                fuzzySystemIEEE.addRuleBase(rb);
			} else {
				System.out.println("WARNING: Wrong Matlab file");		
				System.out.println("    It should begin with the tag [System]");		
            	System.out.println();                            
			}
		}
		return fuzzySystemIEEE;
	}

	public float[] fillParameters(String line, boolean linear) {
        line= line.substring(1,line.length()-1);
        String[] params= line.split(" ");
        float[] result= new float[params.length];
        if (linear) {
        	result[params.length-1]= (new Float(params[0])).floatValue();
        } else {
        	result[0]= (new Float(params[0])).floatValue();
        }
		for (int m=0; m<params.length-1; m++) {
			if (linear) {
                result[m]= (new Float(params[m+1])).floatValue();
			} else {
                result[m+1]= (new Float(params[m+1])).floatValue();
			}
		    //System.out.println(result[m]);
		}
        return result;
	}
}