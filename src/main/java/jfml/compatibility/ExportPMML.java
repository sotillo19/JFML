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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBElement;

import jfml.FuzzyInferenceSystem;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.knowledgebase.variable.TskVariableType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentClausesType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rule.TskClauseType;
import jfml.rule.TskConsequentClausesType;
import jfml.rule.TskConsequentType;
import jfml.rule.TskFuzzyRuleType;
import jfml.rulebase.RuleBaseType;
import jfml.rulebase.TskRuleBaseType;
import jfml.term.FuzzyTermType;
import jfml.term.TskTermType;

/**
 * This class allows to export a fuzzy system in format PMML
 * 
 * @author Jose Alonso
 *
 */
public class ExportPMML extends Export {

	/**
	 * Constructor by default
	 */
	public ExportPMML() {
	}

	@Override
	public void exportFuzzySystem(FuzzyInferenceSystem fuzzySystem, String path) {
        boolean abort= false;
    	boolean mamdani= false;
    	boolean tsk= false;
        Object[] RBs= fuzzySystem.getRuleBase().toArray();
        if (RBs.length > 1) {
			System.out.println("WARNING: This fuzzy system cannot be exported to MATLAB (it includes more than one rule base).");
			System.out.println("    Only the first rule base will be exported");
   	        System.out.println();
        } 
        RuleBaseType rb= null;
        TskRuleBaseType rbTsk= null;
        Object obj= ((JAXBElement)RBs[0]).getValue();
        if (obj instanceof RuleBaseType) {
        	// TYPE_MAMDANI = 0;
        	// TYPE_TSUKAMOTO = 1;
        	// TYPE_TSK = 2;
        	// TYPE_ANYA = 3;
        	// TYPE_OTHER = 4;
        	int rbType= ((RuleBaseType)obj).getRuleBaseSystemType();
       	    //System.out.println(rbType);
        	if (rbType==0) {
        		mamdani=true;
        	} else if (rbType==2) {
                tsk= true;
        	}
        	rb= (RuleBaseType)obj;
        } else if (obj instanceof TskRuleBaseType) {
            tsk= true;
        	rbTsk= (TskRuleBaseType)obj;
        }
        //System.out.println("mam -> "+mamdani);
        //System.out.println("tsk -> "+tsk);
        if (!mamdani && !tsk) {
    			System.out.println("WARNING: This fuzzy system cannot be exported to MATLAB.");
    			System.out.println("    Only the following types of fuzzy systems can be exported to MATLAB: Mamdani, Sugeno");
       	        System.out.println();
    			abort= true;
        }
		if (!abort) {
			System.out.println("WARNING: FRBS.PMML does not support ELSE-part of a rule.");
			System.out.println("    If the fuzzy system to export includes ELSE-part in rules they are ignored.");
	        System.out.println();
	        String contents="<frbsPMML version=\'1.0\' xmlns=\'http://www.uco.es/JFML/\' xmlns:xsi=\'http://www.w3.org/2001/XMLSchema-instance\' xsi:schemaLocation=\'http://www.uco.es/JFML/ http://www.uco.es/JFML/\'>\n";
            contents= contents + "<Header copyright=\'Copyright (c) 2018 JFML\'>\n";
            contents= contents + "<Extension name=\'user\' value=\'JFML\' extender=\'frbs\'/>\n";
            contents= contents + "<Application name=\'frbs\' version=\'3.1\'/>\n";
            Date d= new Date();
            //System.out.println(d);
            //<Timestamp>2018-03-17 18:39:45</Timestamp>
            contents= contents + "<Timestamp>" + d + "</Timestamp>\n";
            contents= contents + "</Header>\n";
            KnowledgeBaseType kb= fuzzySystem.getKnowledgeBase();
            List<KnowledgeBaseVariable> vv= kb.getKnowledgeBaseVariables();
            int nVars= vv.size();
            contents= contents + "<DataDictionary numberOfFields=\'" + nVars + "\'>\n";
            NumberFormat formatter = new DecimalFormat("#0.000");
            //System.out.println(formatter.format(4.0));            
            String[] viname= new String[nVars-1];
            String targetName= "";
            List<FuzzyTermType> mamOutTerms= null;
            double[] vorange= new double[2];
            int nbClasses= 0;
       	    String[] cnames= null;
            String aggMethod=""; // accumulation
  		    boolean classif= true;
            for (int n=0; n<nVars; n++) {
            	 String vname="";
            	 double[] vrange= new double[2];
            	 if (n < nVars-1) {
       	             FuzzyVariableType in= (FuzzyVariableType)vv.get(n);
       	             vname= in.getName();
       	             vrange[0]= in.getDomainleft();
       	             vrange[1]= in.getDomainright();
       	             viname[n]= vname;
            	 } else {
            		 if (mamdani) {
           	             FuzzyVariableType out= (FuzzyVariableType)vv.get(n);
           	             vname= out.getName();
                	     aggMethod= out.getAccumulation();
           	             vrange[0]= out.getDomainleft();
           	             vrange[1]= out.getDomainright();
           	             vorange[0]= vrange[0];
           	             vorange[1]= vrange[1];
           	             mamOutTerms= out.getTerms();
           	             cnames= new String[mamOutTerms.size()];
           	             for (int k=0; k<mamOutTerms.size(); k++) {
           	            	 FuzzyTermType ft= mamOutTerms.get(k);
           	            	 cnames[k]= ft.getName();
           	            	 if (ft.getParam().length>1) {
           	            		 classif=false;
           	            	 }
           	             }
           	             if (classif) {
           	            	 nbClasses= mamOutTerms.size();
           	             }
            		 } else {
   	            		 classif=false;
                	     TskVariableType out= (TskVariableType)vv.get(n);
           	             vname= out.getName();
                	     aggMethod= out.getCombination();
           	             vrange[0]= 0;
           	             vrange[1]= 1;
            		 }
            		 targetName= vname;
            	 }
                 if ( (n==nVars-1) && (classif) ) {
            	     contents= contents + "<DataField name=\'"+vname+"\' optype=\'categorical\' dataType=\'string\'>\n";
            	     for (int k=0; k<nbClasses; k++) {
            	          contents= contents + "<Value value=\'"+String.valueOf(k+1)+"\'/>\n";
            	     }
                 } else {
            	     contents= contents + "<DataField name=\'"+vname+"\' optype=\'continuous\' dataType=\'double\'>\n";
                     contents= contents + "<Interval closure=\'closedClosed\' leftMargin=\'"+formatter.format(vrange[0])+"\' rightMargin=\'"+formatter.format(vrange[1])+"\'/>\n";
                 }
                 contents= contents + "</DataField>\n";
            }
            contents= contents + "</DataDictionary>\n";            
            if (mamdani) {
            	if (classif) {
                contents= contents + "<FrbsModel modelName=\'FRBCS\' functionName=\'classification\' algorithmName=\'MANUAL\' targetFieldName=\'"+targetName+"\'>\n";
            	} else {
                    contents= contents + "<FrbsModel modelName=\'MAMDANI\' functionName=\'regression\' algorithmName=\'MANUAL\' targetFieldName=\'"+targetName+"\'>\n";
            	}
            } else {
                contents= contents + "<FrbsModel modelName=\'TSK\' functionName=\'regression\' algorithmName=\'MANUAL\' targetFieldName=\'"+targetName+"\'>\n";
            }
            contents= contents + "<MiningSchema>\n";
            for (int n=0; n<nVars; n++) {
            	if (n < nVars - 1) {
            	    contents= contents + "<MiningField name=\'"+viname[n]+"\' usageType=\'active\'/>\n";
            	} else {
                	contents= contents + "<MiningField name=\'"+targetName+"\' usageType=\'predicted\'/>\n";
            	}
            }
            contents= contents + "</MiningSchema>\n";
            contents= contents + "<Output>\n";
            if (mamdani) {
                contents= contents + "<OutputField name=\'Predicted_"+targetName+"\' feature=\'predictedValue\'/>\n";
                if ( (mamOutTerms!=null) && (cnames!=null) ) {
                  for (int n=0; n<mamOutTerms.size(); n++) {
                	  if (classif) {
            		      contents= contents + "<OutputField name=\'Probability_"+String.valueOf(n+1)+"\' optype=\'continuous\' dataType=\'double\' feature=\'probability\' value=\'"+String.valueOf(n+1)+"\'/>\n";
                	  } else {
            		      contents= contents + "<OutputField name=\'Probability_"+cnames[n]+"\' optype=\'continuous\' dataType=\'double\' feature=\'probability\' value=\'"+String.valueOf(n+1)+"\'/>\n";
                	  }
            	  }
                }
            } else {
            	contents= contents + "<OutputField name=\'Predicted_"+targetName+"\' optype=\'continuous\' dataType=\'double\' feature=\'predictedValue\'/>\n";
            }
            contents= contents + "</Output>\n";
            contents= contents + "<InferenceSchema>\n";
            int nRules=0;
            String andMethod="";
            String orMethod="";
            String impMethod=""; // activation
            if ( (mamdani) && (rb!=null) ) {
                nRules= rb.getRules().size();
                andMethod= rb.getAndMethod();
                orMethod= rb.getOrMethod();
                impMethod= rb.getActivationMethod();
            } else if (tsk && rbTsk!=null) {
            	nRules= rbTsk.getTskRules().size();
                andMethod= rbTsk.getAndMethod();
                orMethod= rbTsk.getOrMethod();
                impMethod= rbTsk.getActivationMethod();
            }
            // Pag28, T10 (IEEE std)
            if (andMethod.equals("MIN")) {
            	andMethod="MIN";
            } else if (andMethod.equals("PROD")) {
            	andMethod="PRODUCT";
            } else if (andMethod.equals("BDIF")) {
            	andMethod="BOUNDED";
            } else if (andMethod.equals("DRP")) {
            	System.out.println("WARNING: \'DRP\' andMethod is not defined in PMML.");
            	System.out.println("    We replaced \'DRP\' by \'PRODUCT\' as ConjunctionOperator in PMML.");
        	    System.out.println();
            	andMethod="PRODUCT";
            } else if (andMethod.equals("EPROD")) {
            	System.out.println("WARNING: \'EPROD\' andMethod is not defined in PMML.");
            	System.out.println("    We replaced \'EPROD\' by \'PRODUCT\' as ConjunctionOperator in PMML.");
        	    System.out.println();
            	andMethod="PRODUCT";
            } else if (andMethod.equals("HPROD")) {
            	andMethod="HAMACHER";
            } else if (andMethod.equals("NILMIN")) {
            	System.out.println("WARNING: \'NILMIN\' andMethod is not defined in PMML.");
            	System.out.println("    We replaced \'NILMIN\' by \'MIN\' as ConjunctionOperator in PMML.");
        	    System.out.println();
            	andMethod="MIN";
            } else if (andMethod.startsWith("custom")) {
            	System.out.println("WARNING: Customized andMethod can not be exported to PMML.");
            	System.out.println("    By default, andMethod is set to \'MIN\' as ConjunctionOperator in PMML.");
        	    System.out.println();
            	andMethod="MIN";
            }
            contents= contents + "<ConjunctionOperator value=\'"+andMethod+"\'/>\n";
            // Pag29, T11 (IEEE std)
            if (orMethod.equals("MAX")) {
            	orMethod="MAX";
            } else if (orMethod.equals("PROBOR")) {
            	orMethod="SUM";
            } else if (orMethod.equals("BSUM")) {
            	orMethod="BOUNDED";
            } else if (orMethod.equals("DRS")) {
            	System.out.println("WARNING: \'DRS\' orMethod is not defined in PMML.");
            	System.out.println("    We replaced \'DRS\' by \'SUM\' as DisjunctionOperator in PMML.");
        	    System.out.println();
            	orMethod="SUM";
            } else if (orMethod.equals("ESUM")) {
            	System.out.println("WARNING: \'ESUM\' orMethod is not defined in PMML.");
            	System.out.println("    We replaced \'ESUM\' by \'SUM\' as DisjunctionOperator in PMML.");
        	    System.out.println();
            	orMethod="SUM";
            } else if (orMethod.equals("HSUM")) {
            	orMethod="HAMACHER";
            } else if (orMethod.equals("NILMAX")) {
            	System.out.println("WARNING: \'NILMAX\' orMethod is not defined in PMML.");
            	System.out.println("    We replaced \'NILMAX\' by \'MAX\' as DisjunctionOperator in PMML.");
        	    System.out.println();
            	orMethod="MAX";
            } else if (orMethod.startsWith("custom")) {
            	System.out.println("WARNING: \'Customized orMethod\' can not be exported to PMML.");
            	System.out.println("    By default, orMethod is set to \'MAX\' as DisjunctionOperator in PMML.");
       	        System.out.println();
            	orMethod="MAX";
            }
            contents= contents + "<DisjunctionOperator value=\'"+orMethod+"\'/>\n";
            // Pag40, T23 (IEEE std)
            if (impMethod.equals("MIN")) {
            	impMethod="MIN";
            } else if (impMethod.equals("PROD")) {
            	System.out.println("WARNING: \'PROD\' impMethod is not defined in PMML.");
            	System.out.println("    We replaced \'PROD\' by \'MIN\' as ImplicationOperator in PMML.");
        	    System.out.println();
            	impMethod="MIN";
            } else if (impMethod.equals("BDIF")) {
            	System.out.println("WARNING: \'BDIF\' impMethod is not defined in PMML.");
            	System.out.println("    We replaced \'BDIF\' by \'MIN\' as ImplicationOperator in PMML.");
        	    System.out.println();
            	impMethod="MIN";
            } else if (impMethod.equals("DRP")) {
            	System.out.println("WARNING: \'DRP\' impMethod is not defined in PMML.");
            	System.out.println("    We replaced \'DRP\' by \'MIN\' as ImplicationOperator in PMML.");
        	    System.out.println();
            	impMethod="MIN";
            } else if (impMethod.equals("EPROD")) {
            	System.out.println("WARNING: \'EPROD\' impMethod is not defined in PMML.");
            	System.out.println("    We replaced \'EPROD\' by \'MIN\' as ImplicationOperator in PMML.");
        	    System.out.println();
            	impMethod="MIN";
            } else if (impMethod.equals("HPROD")) {
            	System.out.println("WARNING: \'HPROD\' impMethod is not defined in PMML.");
            	System.out.println("    We replaced \'HPROD\' by \'MIN\' as ImplicationOperator in PMML.");
        	    System.out.println();
            	impMethod="MIN";
            } else if (impMethod.equals("NILMIN")) {
            	System.out.println("WARNING: \'NILMIN\' impMethod is not defined in PMML.");
            	System.out.println("    We replaced \'NILMIN\' by \'MIN\' as ImplicationOperator in PMML.");
        	    System.out.println();
            	impMethod="MIN";
            } else if (impMethod.startsWith("custom")) {
            	System.out.println("WARNING: \'Customized activation (implication)\' can not be exported to PMML.");
            	System.out.println("    By default, impMethod is set to \'MIN\' as ImplicationOperator in PMML.");
       	        System.out.println();
            	impMethod="MIN";
            }
            contents= contents + "<ImplicationOperator value=\'"+impMethod+"\'/>\n";
            // Pag23, T2 (IEEE std)
            if (aggMethod.equals("MOM")) {
            	aggMethod="MEAN.MAX";
            } else if (aggMethod.equals("LM")) {
        	    aggMethod="FIRST.MAX";
            } else if (aggMethod.equals("RM")) {
        	    aggMethod="LAST.MAX";
            } else if (aggMethod.equals("COG")) {
            	aggMethod="COG";
            } else if (aggMethod.equals("COA")) {
            	System.out.println("WARNING: \'COA\' aggMethod is not defined in PMML.");
            	System.out.println("    We replaced \'COA\' by \'COG\' as AggregationOperator in PMML.");
        	    System.out.println();
        	    aggMethod="COG";
            } else if (aggMethod.equals("WA")) {
            	aggMethod="WAM";
            } else if (aggMethod.startsWith("custom")) {
            	System.out.println("WARNING: Customized aggMethod can not be exported to PMML.");
            	System.out.println("    By default, aggMethod is set to \'COG\' (Mamdani) or \'WAM\' (Sugeno) in PMML.");
       	        System.out.println();
       	        if (mamdani) {
            	    aggMethod="COG";
       	        } else {
                	aggMethod="WAM";
       	        }
            }
            contents= contents + "<AggregationOperator value=\'"+aggMethod+"\'/>\n";
            contents= contents + "</InferenceSchema>\n";
            contents= contents + "<Database>\n";
            for (int n=0; n<nVars-1; n++) {
  	             FuzzyVariableType in= (FuzzyVariableType)vv.get(n);
            	 List<FuzzyTermType> terms= in.getTerms();
            	 int nMFs= terms.size(); 
            	 contents= contents + "<MembershipFunction name=\'"+viname[n]+"\' numberOfLabels=\'"+nMFs+"\'>\n";
            	 for (int k=0; k<nMFs; k++) {
            		 FuzzyTermType t= terms.get(k);
            		 String mfType= "";
            		 String mfName= ((t.getMembershipFunction()).getName()).toLowerCase();
            		 //System.out.println("mfName="+mfName);
                     // 1 -> TRIANGLE
                     // 2 -> TRAPEZOID in left side
                     // 3 -> TRAPEZOID in right side
                     // 4 -> TRAPEZOID in the middle
                     // 5 -> GAUSSIAN
                     // 6 -> SIGMOID
                     // 7 -> BELL
            		 if (mfName.startsWith("rightlinear")) {
            			 mfType= "TRIANGLE";
            		 } else if (mfName.startsWith("leftlinear")) {
            			 mfType= "TRIANGLE";
            		 } else if (mfName.startsWith("pi")) {
            				// piShape in JFML has 4 params
         			     mfType= "TRAPEZOID";
        	             System.out.println("WARNING: It is not possible to export to PMML piShape membership functions");			
        	             System.out.println("    This membership function is substituted by a TRAPEZOID");			
        	   	         System.out.println();
            		 } else if (mfName.startsWith("gaussian")) {
            			 mfType= "GAUSSIAN";
            		 } else if (mfName.startsWith("rightgaussian")) {
            			 mfType= "GAUSSIAN";
            		 } else if (mfName.startsWith("leftgaussian")) {
            			 mfType= "GAUSSIAN";
            		 } else if (mfName.startsWith("rectangular")) {
         			     mfType= "TRAPEZOID";
        	             System.out.println("WARNING: It is not possible to export to PMML rectangular membership functions");			
        	             System.out.println("    This membership function is substituted by a TRAPEZOID");			
        	   	         System.out.println();
            		 } else if (mfName.startsWith("singleton")) {
         				 mfType= "TRIANGLE";
        	             System.out.println("WARNING: It is not possible to export to PMML singleton membership functions");			
        	             System.out.println("    This membership function is substituted by a TRIANGLE");			
        	   	         System.out.println();
           			 } else if (mfName.startsWith("s")) {
          				 mfType= "SIGMOID";
            		 } else if (mfName.startsWith("z")) {
            			 mfType= "TRIANGLE";
            	         System.out.println("WARNING: It is not possible to export to PMML zShape membership functions");			
            	         System.out.println("    This membership function is substituted by a TRIANGLE coverering all the input range");			
            	   	     System.out.println();
           			 } else if (mfName.startsWith("triangular")) {
               			 mfType= "TRIANGLE";
            		 } else if (mfName.startsWith("trapezoid")) {
            			 mfType= "TRAPEZOID";
            		 } else if (mfName.startsWith("user")) {
            			 mfType= "TRIANGLE";
            	         System.out.println("WARNING: It is not possible to export to PMML customized (userShape) membership functions");			
            	         System.out.println("    This membership function is substituted by a TRIANGLE coverering all the input range");			
            	   	     System.out.println();
            		 } else {
            			 mfType= "TRIANGLE";
            	         System.out.println("WARNING: Unknown membership function");			
            	         System.out.println("    This membership function is substituted by a TRIANGLE coverering all the input range");			
            	   	     System.out.println();
            		 }
                     contents= contents + "<FuzzyTerm name=\'"+t.getName()+"\' type=\'"+mfType+"\'>\n";
            		 float[] pp= t.getParam();
                     contents= contents + "<Parameters>\n";
                     if (mfType.equals("GAUSSIAN")) {
                         contents= contents + "<Mean>"+formatter.format(pp[0])+"</Mean>\n";
                         contents= contents + "<Variance>"+formatter.format(pp[1])+"</Variance>\n";
                     } else if (mfType.equals("SIGMOID")) {
                         contents= contents + "<Gamma>"+formatter.format(pp[0])+"</Gamma>\n";
                         contents= contents + "<Middle>"+formatter.format(pp[1])+"</Middle>\n";
                     } else if (mfType.equals("TRAPEZOID")) {
                      	  if (mfName.startsWith("rectangular")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(pp[0])+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(pp[1])+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[1])+"</Right>\n";
                      	  } else if (mfName.startsWith("pi")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(pp[1])+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(pp[2])+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[3])+"</Right>\n";
                      	  } else if (mfName.startsWith("trapezoid")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(pp[1])+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(pp[2])+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[3])+"</Right>\n";
                       	  } else {
                       		  double vl= in.getDomainleft();
                       		  double vr= in.getDomainright();
                              contents= contents + "<Left>"+formatter.format(vl)+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(vl)+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(vr)+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(vr)+"</Right>\n";
                       	  }
                     } else {
                      	  // TRIANGLE
                       	  if (mfName.startsWith("singleton")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<Middle>"+formatter.format(pp[0])+"</Middle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[0])+"</Right>\n";
                       	  } else if (mfName.startsWith("rightlinear")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<Middle>"+formatter.format(pp[1])+"</Middle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[1])+"</Right>\n";
                      	  } else if (mfName.startsWith("leftlinear")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<Middle>"+formatter.format(pp[0])+"</Middle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[1])+"</Right>\n";
                      	  } else if (mfName.startsWith("triangular")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<Middle>"+formatter.format(pp[1])+"</Middle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[2])+"</Right>\n";
                       	  } else {
                      		  double vl= in.getDomainleft();
                       		  double vr= in.getDomainright();
                       		  double m= (vl+vr)/2;
                              contents= contents + "<Left>"+formatter.format(vl)+"</Left>\n";
                              contents= contents + "<Middle>"+formatter.format(m)+"</Middle>\n";
                              contents= contents + "<Right>"+formatter.format(vr)+"</Right>\n";
                       	  }
                     }
                     contents= contents + "</Parameters>\n";
                     contents= contents + "</FuzzyTerm>\n";
            	 }
                 contents= contents + "</MembershipFunction>\n";
            }
            if ( (nbClasses==0) && (mamdani) && (mamOutTerms != null) ) {
            	int nMFs= mamOutTerms.size(); 
           	    contents= contents + "<MembershipFunction name=\'"+targetName+"\' numberOfLabels=\'"+nMFs+"\'>\n";
           	    for (int k=0; k<nMFs; k++) {
           		     FuzzyTermType t= mamOutTerms.get(k);
           		     String mfType= "";
           		     String mfName= ((t.getMembershipFunction()).getName()).toLowerCase();
                     // 1 -> TRIANGLE
                     // 2 -> TRAPEZOID in left side
                     // 3 -> TRAPEZOID in right side
                     // 4 -> TRAPEZOID in the middle
                     // 5 -> GAUSSIAN
                     // 6 -> SIGMOID
                     // 7 -> BELL
           		     if (mfName.startsWith("rightlinear")) {
           			     mfType= "TRIANGLE";
           		     } else if (mfName.startsWith("leftlinear")) {
           			     mfType= "TRIANGLE";
           		     } else if (mfName.startsWith("pi")) {
           				 // piShape in JFML has 4 params
        			     mfType= "TRAPEZOID";
       	                 System.out.println("WARNING: It is not possible to export to PMML piShape membership functions");			
       	                 System.out.println("    This membership function is substituted by a TRAPEZOID");			
       	   	             System.out.println();
           		     } else if (mfName.startsWith("gaussian")) {
           			     mfType= "GAUSSIAN";
           		     } else if (mfName.startsWith("rightgaussian")) {
           			     mfType= "GAUSSIAN";
           		     } else if (mfName.startsWith("leftgaussian")) {
           			     mfType= "GAUSSIAN";
           		     } else if (mfName.startsWith("rectangular")) {
        			     mfType= "TRAPEZOID";
       	                 System.out.println("WARNING: It is not possible to export to PMML rectangular membership functions");			
       	                 System.out.println("    This membership function is substituted by a TRAPEZOID");			
       	   	             System.out.println();
           		     } else if (mfName.startsWith("singleton")) {
        			 	 mfType= "TRIANGLE";
       	                 System.out.println("WARNING: It is not possible to export to PMML singleton membership functions");			
       	                 System.out.println("    This membership function is substituted by a TRIANGLE");			
       	   	             System.out.println();
          			 } else if (mfName.startsWith("s")) {
         				 mfType= "SIGMOID";
           		     } else if (mfName.startsWith("z")) {
           			     mfType= "TRIANGLE";
           	             System.out.println("WARNING: It is not possible to export to PMML zShape membership functions");			
           	             System.out.println("    This membership function is substituted by a TRIANGLE coverering all the input range");			
           	   	         System.out.println();
          			 } else if (mfName.startsWith("triangular")) {
              			 mfType= "TRIANGLE";
           		     } else if (mfName.startsWith("trapezoid")) {
           			     mfType= "TRAPEZOID";
           		     } else if (mfName.startsWith("user")) {
           			     mfType= "TRIANGLE";
           	             System.out.println("WARNING: It is not possible to export to PMML customized (userShape) membership functions");			
           	             System.out.println("    This membership function is substituted by a TRIANGLE coverering all the input range");			
           	   	         System.out.println();
           		     } else {
           			     mfType= "TRIANGLE";
           	             System.out.println("WARNING: Unknown membership function");			
           	             System.out.println("    This membership function is substituted by a TRIANGLE coverering all the input range");			
           	   	         System.out.println();
           		     }
                     contents= contents + "<FuzzyTerm name=\'"+t.getName()+"\' type=\'"+mfType+"\'>\n";
           		     float[] pp= t.getParam();
                     contents= contents + "<Parameters>\n";
                     if (mfType.equals("GAUSSIAN")) {
                         contents= contents + "<Mean>"+formatter.format(pp[0])+"</Mean>\n";
                         contents= contents + "<Variance>"+formatter.format(pp[1])+"</Variance>\n";
                     } else if (mfType.equals("SIGMOID")) {
                         contents= contents + "<Gamma>"+formatter.format(pp[0])+"</Gamma>\n";
                         contents= contents + "<Middle>"+formatter.format(pp[1])+"</Middle>\n";
                     } else if (mfType.equals("TRAPEZOID")) {
                     	  if (mfName.startsWith("rectangular")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(pp[0])+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(pp[1])+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[1])+"</Right>\n";
                     	  } else if (mfName.startsWith("pi")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(pp[1])+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(pp[2])+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[3])+"</Right>\n";
                     	  } else if (mfName.startsWith("trapezoid")) {
                              contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(pp[1])+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(pp[2])+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(pp[3])+"</Right>\n";
                      	  } else {
                      		  double vl= vorange[0];
                      		  double vr= vorange[1];
                              contents= contents + "<Left>"+formatter.format(vl)+"</Left>\n";
                              contents= contents + "<LeftMiddle>"+formatter.format(vl)+"</LeftMiddle>\n";
                              contents= contents + "<RightMiddle>"+formatter.format(vr)+"</RightMiddle>\n";
                              contents= contents + "<Right>"+formatter.format(vr)+"</Right>\n";
                      	  }
                    } else {
                     	  // TRIANGLE
                      	  if (mfName.startsWith("singleton")) {
                             contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                             contents= contents + "<Middle>"+formatter.format(pp[0])+"</Middle>\n";
                             contents= contents + "<Right>"+formatter.format(pp[0])+"</Right>\n";
                      	  } else if (mfName.startsWith("rightlinear")) {
                             contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                             contents= contents + "<Middle>"+formatter.format(pp[1])+"</Middle>\n";
                             contents= contents + "<Right>"+formatter.format(pp[1])+"</Right>\n";
                     	  } else if (mfName.startsWith("leftlinear")) {
                             contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                             contents= contents + "<Middle>"+formatter.format(pp[0])+"</Middle>\n";
                             contents= contents + "<Right>"+formatter.format(pp[1])+"</Right>\n";
                     	  } else if (mfName.startsWith("triangular")) {
                             contents= contents + "<Left>"+formatter.format(pp[0])+"</Left>\n";
                             contents= contents + "<Middle>"+formatter.format(pp[1])+"</Middle>\n";
                             contents= contents + "<Right>"+formatter.format(pp[2])+"</Right>\n";
                      	  } else {
                     		  double vl= vorange[0];
                      		  double vr= vorange[1];
                      		  double m= (vl+vr)/2;
                             contents= contents + "<Left>"+formatter.format(vl)+"</Left>\n";
                             contents= contents + "<Middle>"+formatter.format(m)+"</Middle>\n";
                             contents= contents + "<Right>"+formatter.format(vr)+"</Right>\n";
                      	  }
                    }
                    contents= contents + "</Parameters>\n";
                    contents= contents + "</FuzzyTerm>\n";
           	    }
                contents= contents + "</MembershipFunction>\n";
            }
            contents= contents + "</Database>\n";
            contents= contents + "<Rulebase numberOfRules=\'"+nRules+"\'>\n";
            Iterator<FuzzyRuleType> rM= null;
            Iterator<TskFuzzyRuleType> rTsk= null;
            if (nRules > 0) {
                if (mamdani && rb!=null) {
              	  rM= rb.getRules().iterator();
                }
                if (tsk && rbTsk!=null) {
                    rTsk= rbTsk.getTskRules().iterator();
                }
            }
            for (int n=0; n<nRules; n++) {
            	float rw= 1;
            	String rC= "";
            	AntecedentType ar= null;
            	ConsequentType mamCr= null;
            	TskConsequentType tskCr= null;
            	if (mamdani) {
                    FuzzyRuleType r= rM.next();
                    rC=r.getConnector();
                    ar= r.getAntecedent();
                    mamCr= r.getConsequent();
                    //System.out.println("nbClauses="+nbClauses);
                    rw= r.getWeight();
                } else {
                    TskFuzzyRuleType r= rTsk.next();
                    rC=r.getConnector();
                    ar= r.getAntecedent();
                    tskCr= r.getTskConsequent();
                    rw= r.getWeight();
                }
            	contents= contents + "<Rule id=\'"+String.valueOf(n+1)+"\'>\n";
                if (ar!=null) {
                    List<ClauseType> act1= ar.getClauses();
                    List<ClauseType> act= rankClauses(act1,viname);
                    int nbClauses= act.size();
              	    contents= contents + "<If>\n";
              	    int k=0;
              	    int cp= 0;
                    if (nbClauses == 1) {
                        ClauseType ct= act.get(k);
                        String vn= "";
                        String tn= "";
             		    if (ct!=null) {
                              //System.out.println(ct.toString());
                              FuzzyVariableType v= (FuzzyVariableType)ct.getVariable();
                              vn= v.getName();
                              //System.out.println(vn);
                              FuzzyTermType t= (FuzzyTermType)ct.getTerm();
                              tn= t.getName();
                              //System.out.println(tn);
             		    }
                    	contents= contents + "<SimplePredicate field=\'"+vn+"\' value=\'"+tn+"\'/>\n";
                    } else if (nbClauses >= 2) {
                    	int cont= nbClauses;
                        while (cont >= 2) {
                        	if (cont==2) {
                            	contents= contents + "<CompoundPredicate booleanOperator=\'"+rC+"\'>\n";
                                ClauseType ct1= act.get(k);
                                String vn= "";
                                String tn= "";
                     		    if (ct1!=null) {
                                      //System.out.println(ct.toString());
                                      FuzzyVariableType v= (FuzzyVariableType)ct1.getVariable();
                                      vn= v.getName();
                                      //System.out.println(vn);
                                      FuzzyTermType t= (FuzzyTermType)ct1.getTerm();
                                      tn= t.getName();
                                      //System.out.println(tn);
                     		    }
                     		    if ( (vn.equals("")) && (tn.equals("")) ) {
                            	    contents= contents + "<SimplePredicate field=\'"+viname[k]+"\' value=\'dont_care'/>\n";
                     		    } else {
                            	    contents= contents + "<SimplePredicate field=\'"+vn+"\' value=\'"+tn+"\'/>\n";
                     		    }
                            	k++;
                                ClauseType ct2= act.get(k);
                                vn= "";
                                tn= "";
                     		    if (ct2!=null) {
                                      //System.out.println(ct.toString());
                                      FuzzyVariableType v= (FuzzyVariableType)ct2.getVariable();
                                      vn= v.getName();
                                      //System.out.println(vn);
                                      FuzzyTermType t= (FuzzyTermType)ct2.getTerm();
                                      tn= t.getName();
                                      //System.out.println(tn);
                     		    }
                     		    if ( (vn.equals("")) && (tn.equals("")) ) {
                            	    contents= contents + "<SimplePredicate field=\'"+viname[k]+"\' value=\'dont_care'/>\n";
                     		    } else {
                            	    contents= contents + "<SimplePredicate field=\'"+vn+"\' value=\'"+tn+"\'/>\n";
                     		    }
                            	k++;
                            	contents= contents + "</CompoundPredicate>\n";
                        	} else {
                            	contents= contents + "<CompoundPredicate booleanOperator=\'"+rC+"\'>\n";
                                ClauseType ct= act.get(k);
                                String vn= "";
                                String tn= "";
                     		    if (ct!=null) {
                                      //System.out.println(ct.toString());
                                      FuzzyVariableType v= (FuzzyVariableType)ct.getVariable();
                                      vn= v.getName();
                                      //System.out.println(vn);
                                      FuzzyTermType t= (FuzzyTermType)ct.getTerm();
                                      tn= t.getName();
                                      //System.out.println(tn);
                     		    }
                     		    if ( (vn.equals("")) && (tn.equals("")) ) {
                            	    contents= contents + "<SimplePredicate field=\'"+viname[k]+"\' value=\'dont_care'/>\n";
                     		    } else {
                            	    contents= contents + "<SimplePredicate field=\'"+vn+"\' value=\'"+tn+"\'/>\n";
                     		    }
                            	k++;
                            	cp++;
                        	}
                        	cont--;
                        }
                    }
                    while (cp > 0) {
                    	contents= contents + "</CompoundPredicate>\n";
                    	cp--;
                    }
                    contents= contents + "</If>\n";
                }
                if (mamdani) {
                	if (mamCr != null) { 
                        contents= contents + "<Then>\n";
                        ConsequentClausesType cct= mamCr.getThen();
                        List<ClauseType> lc1= cct.getClause();
                        ClauseType ct1= lc1.get(0);
                        String vn= "";
                        String tn= "";
                        int cindex= 1;
             		    if (ct1!=null) {
                              //System.out.println(ct1.toString());
                              FuzzyVariableType v= (FuzzyVariableType)ct1.getVariable();
                              vn= v.getName();
                              //System.out.println(vn);
                              FuzzyTermType t= (FuzzyTermType)ct1.getTerm();
                              tn= t.getName();
                              //System.out.println(tn);
                              List<FuzzyTermType> ft= v.getTerms();
                              for (int k=0; k<ft.size(); k++) {
                            	  if (ft.get(k).getName().equals(tn)) {
                            		  cindex=k+1;
                            		  break;
                            	  }
                              }
             		    }
             		    if (classif) {
                            contents= contents + "<SimplePredicate field=\'"+vn+"\' value=\'"+String.valueOf(cindex)+"\'/>\n";
             		    } else {
                            contents= contents + "<SimplePredicate field=\'"+vn+"\' value=\'"+tn+"\'/>\n";
             		    }
                    }
                } else {
                    contents= contents + "<Then type=\'LinearFunction\'>\n";
                    TskConsequentClausesType cct= tskCr.getTskThen();
                    List<TskClauseType> lc1= cct.getTskClause();
                    TskClauseType ct= lc1.get(0);
                    if (ct!=null) {
                      TskVariableType v= (TskVariableType)ct.getVariable();
                      //System.out.println(v.getName());
                      TskTermType t= (TskTermType)ct.getTerm();
                      //System.out.println(t.getName());
                      List pp= t.getTskValue();
                      if (pp.size() > 1) {
                          for (int k=1; k<pp.size(); k++) {
                    	   //System.out.println("pp["+k+"]="+pp.get(k));
                           contents= contents + "<Coefficient field=\'"+viname[k-1]+"\' value=\'"+formatter.format(pp.get(k))+"\'/>\n";
                          }
                      } else {
                          for (int k=1; k<nVars; k++) {
                           contents= contents + "<Coefficient field=\'"+viname[k-1]+"\' value=\'"+formatter.format(0)+"\'/>\n";
                          }
                      }
                      contents= contents + "<Constant value=\'"+formatter.format(pp.get(0))+"\'/>\n";
                      //
                    }
                }
                contents= contents + "</Then>\n";
                contents= contents + "<Grade>"+formatter.format(rw)+"</Grade>\n";
                contents= contents + "</Rule>\n";
            }
            contents= contents + "</Rulebase>\n";
            contents= contents + "</FrbsModel>\n";
            contents= contents + "</frbsPMML>";
            
          writeFile(path, contents);
		}
	}

	private List<ClauseType> rankClauses (List<ClauseType> l, String[] names) {
		List<ClauseType> res= new ArrayList<ClauseType>();
		if (names.length > 1) {
          for (int n=0; n<names.length; n++) {
        	 boolean warning= false;
       	     //System.out.println("n="+n);
       	     //System.out.println("    "+names[n]);
       	     //System.out.println("    "+l.size());
        	 Iterator<ClauseType> it= l.iterator();
             while (it.hasNext() && !warning) {
            	 ClauseType ct= it.next();
            	 if (ct!=null) {
            	   //System.out.println("    "+ct.toString());
            	   if (ct.toString().startsWith(names[n]+ " IS ")) {
            		 res.add(ct);
            		 warning= true;
            		 break;
            	   }
            	 }
             }
             if (!warning) {
            	 ClauseType ct= null;
        		 res.add(ct);
             }
          }
		} else {
			res= l;
		}
        return res;
	}
}