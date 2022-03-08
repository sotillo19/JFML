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

//import java.util.ArrayList;

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
 * This class allows to export a fuzzy system to format FCL (IEC 1131).
 * 
 * @author Jose Alonso
 *
 */
public class ExportFCL extends Export {

	/**
	 * Constructor by default
	 */
	public ExportFCL() {
	}

	@Override
	public void exportFuzzySystem(FuzzyInferenceSystem fuzzySystem, String path) {
        boolean abort= false;
        Object[] RBs= fuzzySystem.getRuleBase().toArray();
        RuleBaseType[] rb= new RuleBaseType[RBs.length];
    	TskRuleBaseType[] rbTsk= new TskRuleBaseType[RBs.length];
    	boolean[] mamdani= new boolean[RBs.length];
    	boolean[] tsk= new boolean[RBs.length];
        for (int n=0; n<RBs.length; n++) {
          Object obj= ((JAXBElement)RBs[n]).getValue();
          if (obj instanceof RuleBaseType) {
        	  // TYPE_MAMDANI = 0;
        	  // TYPE_TSUKAMOTO = 1;
        	  // TYPE_TSK = 2;
        	  // TYPE_ANYA = 3;
        	  // TYPE_OTHER = 4;
        	  int rbType= ((RuleBaseType)obj).getRuleBaseSystemType();
       	      //System.out.println(rbType);
        	  if (rbType==0) {
        		  mamdani[n]=true;
                  tsk[n]= false;
        	  } else if (rbType==2) {
                  tsk[n]= true;
        		  mamdani[n]=false;
        	  } else {
        		  mamdani[n]=false;
                  tsk[n]= false;
        	  }
        	  rb[n]= (RuleBaseType)obj;
          } else if (obj instanceof TskRuleBaseType) {
              tsk[n]= true;
    		  mamdani[n]=false;
        	  rbTsk[n]= (TskRuleBaseType)obj;
          }
        }
        //System.out.println("mam -> "+mamdani);
        //System.out.println("tsk -> "+tsk);
        if (!check(mamdani,tsk)) {
    			System.out.println("WARNING: This fuzzy system cannot be exported to FCL.");
    			System.out.println("    Only the following types of fuzzy systems can be exported to FCL: Mamdani, Sugeno");
       	        System.out.println();
    			abort= true;
        }
		if (!abort) {
			System.out.println("WARNING: FCL does not support edges, does not support ELSE-part of a rule.");
			System.out.println("    If the fuzzy system to export includes edges or ELSE-part in rules, they are ignored.");
	        System.out.println();
			String sName= fuzzySystem.getName();
			sName= sName.replaceAll(" ", "");
			//System.out.println(sName);
            String contents="// Block definition (there may be more than one block per file)\n";
            contents=contents+"FUNCTION_BLOCK "+sName+"\n\n";
            KnowledgeBaseType kb= fuzzySystem.getKnowledgeBase();
            List<KnowledgeBaseVariable> vv= kb.getKnowledgeBaseVariables();
            Iterator<KnowledgeBaseVariable> it= vv.iterator();
            int nInputs= 0;
            int nOutputs= 0;
            for (int n=0; n<vv.size(); n++) {
                 if (((KnowledgeBaseVariable)it.next()).isInput()) {
                	 nInputs++;
                 } else {
                	 nOutputs++;
                 }
            }
            int[] indIn= new int[nInputs];
            String[] nameIn= new String[nInputs];
            int[] indOut= new int[nOutputs];
            String[] nameOut= new String[nOutputs];
            int ci=0;
            int co=0;
            for (int n=0; n<vv.size(); n++) {
                 if (vv.get(n).isInput()) {
                     indIn[ci]= n;
                     nameIn[ci++]= vv.get(n).getName();
                 } else {
                     indOut[co]= n;
                     nameOut[co++]= vv.get(n).getName();
                 }
            }
            contents= contents + "// Define input variables\n";
            contents= contents + "VAR_INPUT\n";
            for (int k=0; k<nInputs; k++) {
                 contents= contents + "    "+nameIn[k].replace(" ", "-")+" : REAL;\n";
            }
            contents= contents + "END_VAR\n\n";
            contents= contents + "// Define output variables\n";
            contents= contents + "VAR_OUTPUT\n";
            for (int k=0; k<nOutputs; k++) {
                 contents= contents + "    "+nameOut[k].replace(" ", "-")+" : REAL;\n";
            }
            contents= contents + "END_VAR\n\n";
            for (int k=0; k<nInputs; k++) {
                contents= contents + "// Fuzzify input variable \'"+nameIn[k].replace(" ", "-")+"\'\n";
                contents= contents + "FUZZIFY "+nameIn[k].replace(" ", "-")+"\n";
                if (check(mamdani, tsk)) {
                    FuzzyVariableType in= (FuzzyVariableType)vv.get(indIn[k]);
                    float[] vrange= {in.getDomainleft(), in.getDomainright()};
                    List<FuzzyTermType> lt= in.getTerms();
                    int nMFs= lt.size();
               	    //System.out.println("nMFs="+nMFs);
                    Iterator<FuzzyTermType> iterm= lt.iterator();
                    for (int m=0; m<nMFs; m++) {
                    	 //System.out.println("    MF"+String.valueOf(m+1));
                    	 FuzzyTermType term= iterm.next();
                         String tname= term.getName();
                         String mfType= (term.getMembershipFunction()).getName().toLowerCase();
                         float[] pp= term.getParam();
                         contents= contents + getTermFCL(tname, mfType, pp, vrange);
                    }
                } 
                contents= contents + "END_FUZZIFY\n\n";
            }
            String[] aggMethod= new String[nOutputs];
            String[] defMethod= new String[nOutputs];
            if (nOutputs>1) {
                System.out.println("WARNING: Multiple outputs");			
                System.out.println("    We consider (in FCL) only the accumulation and defuzzification methods that were defined for the first output");			
      	        System.out.println();
            }
            for (int k=0; k<nOutputs; k++) {
                contents= contents + "// Defuzzify output variable \'"+nameOut[k].replace(" ", "-")+"\'\n";
                contents= contents + "DEFUZZIFY "+nameOut[k].replace(" ", "-")+"\n";
                float[] vrange=new float[2];
                if (mamdani[0]) {
                    FuzzyVariableType out= (FuzzyVariableType)vv.get(indOut[k]);
                    vrange[0]= out.getDomainleft();
                    vrange[1]= out.getDomainright();
                    List<FuzzyTermType> lt= out.getTerms();
                    int nMFs= lt.size();
               	    //System.out.println("nMFs="+nMFs);
                    Iterator<FuzzyTermType> iterm= lt.iterator();
                    for (int m=0; m<nMFs; m++) {
                    	 //System.out.println("    MF"+String.valueOf(m+1));
                    	 FuzzyTermType term= iterm.next();
                         String tname= term.getName();
                         String mfType= (term.getMembershipFunction()).getName().toLowerCase();
                         float[] pp= term.getParam();
                         contents= contents + getTermFCL(tname, mfType, pp, vrange);
                    }
                    aggMethod[k]= out.getAccumulation();
           	        defMethod[k]= out.getDefuzzifierName();
                    if (defMethod.equals("MOM")) {
                    	System.out.println("WARNING: \'MOM\' defMethod is not defined in FCL.");
                    	System.out.println("    We replaced \'MOM\' by \'LM\' in FCL.");
                	    System.out.println();
                    	defMethod[k]="LM";
                    } else if (defMethod[k].equals("WA")) {
                    	defMethod[k]="COGS";
                    } else if (defMethod[k].startsWith("custom")) {
                    	System.out.println("WARNING: Customized DefuzMethod can not be exported to FCL.");
                    	System.out.println("    By default, DefuzMethod is set to \'COG\' (Mamdani) or \'COGS\' (Sugeno) in FCL.");
               	        System.out.println();
                   	    defMethod[k]="COG";
                    }
                } else if (tsk[0]) {
                    TskVariableType out= (TskVariableType)vv.get(indOut[k]);
                    vrange[0]= -100;
                    vrange[1]= 100;
                    List<TskTermType> lt= out.getTerms();
                    int nMFs= lt.size();
                    Iterator<TskTermType> iterm= lt.iterator();
                    for (int m=0; m<nMFs; m++) {
                    	 TskTermType term= iterm.next();
                         String tname= term.getName();
                         //int ord= term.getOrder();
                         List<Float> lv= term.getTskValue();
                         contents= contents + "    TERM "+tname+" := "+lv.get(0)+";\n";
                    }
                    aggMethod[k]= "WA";
           	        defMethod[k]= out.getCombination();
                    if (defMethod[k].equals("MOM")) {
                    	System.out.println("WARNING: \'MOM\' defMethod is not defined in FCL.");
                    	System.out.println("    We replaced \'MOM\' by \'LM\' in FCL.");
                	    System.out.println();
                    	defMethod[k]="LM";
                    } else if (defMethod[k].equals("WA")) {
                    	defMethod[k]="COGS";
                    } else if (defMethod[k].startsWith("custom")) {
                    	System.out.println("WARNING: Customized DefuzMethod can not be exported to FCL.");
                    	System.out.println("    By default, DefuzMethod is set to \'COG\' (Mamdani) or \'COGS\' (Sugeno) in FCL.");
               	        System.out.println();
                       	defMethod[k]="COGS";
                    }
                }
       	        contents= contents + "    RANGE := ("+vrange[0]+" .. "+vrange[1]+");\n";
       	        contents= contents + "    METHOD : "+defMethod[k]+";\n";
       	        contents= contents + "    DEFAULT := "+vrange[0]+";\n";
                contents= contents + "END_DEFUZZIFY\n\n";
            }
            for (int k=0; k<RBs.length; k++) {
                contents= contents + "RULEBLOCK No"+String.valueOf(k+1)+"\n";
                //AND
                //ACT
                //ACU
                int nRules=0;
                String andMethod="";
                //String orMethod="";
                String impMethod=""; // activation
                if ( (mamdani[k]) && (rb!=null) ) {
                    nRules= rb[k].getRules().size();
                    andMethod= rb[k].getAndMethod();
                    //orMethod= rb[k].getOrMethod();
                    impMethod= rb[k].getActivationMethod();
                } else if (tsk[k] && rbTsk!=null) {
                	nRules= rbTsk[k].getTskRules().size();
                    andMethod= rbTsk[k].getAndMethod();
                    //orMethod= rbTsk[k].getOrMethod();
                    impMethod= rbTsk[k].getActivationMethod();
                }
                if (andMethod.equals("DRP")) {
                	System.out.println("WARNING: \'DRP\' andMethod is not defined in FCL.");
                	System.out.println("    We replaced \'DRP\' by \'PROD\' in FCL.");
            	    System.out.println();
                	andMethod="PROD";
                } else if (andMethod.equals("EPROD")) {
                	System.out.println("WARNING: \'EPROD\' andMethod is not defined in FCL.");
                	System.out.println("    We replaced \'EPROD\' by \'PROD\' in FCL.");
            	    System.out.println();
                	andMethod="PROD";
                } else if (andMethod.equals("HPROD")) {
                	System.out.println("WARNING: \'HPROD\' andMethod is not defined in FCL.");
                	System.out.println("    We replaced \'HPROD\' by \'PROD\' in FCL.");
            	    System.out.println();
                	andMethod="prod";
                } else if (andMethod.equals("NILMIN")) {
                	System.out.println("WARNING: \'NILMIN\' andMethod is not defined in FCL.");
                	System.out.println("    We replaced \'NILMIN\' by \'MIN\' in FCL.");
            	    System.out.println();
                	andMethod="MIN";
                } else if (andMethod.startsWith("custom")) {
                	System.out.println("WARNING: Customized andMethod can not be exported to FCL.");
                	System.out.println("    By default, andMethod is set to \'MIN\' in FCL.");
            	    System.out.println();
                	andMethod="MIN";
                }
                if (andMethod.equals("MIN")) {
                    contents= contents + "// Use 'MIN' for 'AND' (also implicit use 'MAX' for 'or' to fulfill DeMorgan's Law)\n";
                } else if (andMethod.equals("PROD")) {
                    contents= contents + "// Use 'PROD' for 'AND' (also implicit use 'SUM' for 'or' to fulfill DeMorgan's Law)\n";
                } else if (andMethod.equals("BDIF")) {
                    contents= contents + "// Use 'BDIF' for 'AND' (also implicit use 'BSUM' for 'or' to fulfill DeMorgan's Law)\n";
                }
                contents= contents + "    AND : "+andMethod+";\n";
                if (impMethod.equals("BDIF")) {
                	System.out.println("WARNING: \'BDIF\' impMethod is not defined in FCL.");
                	System.out.println("    We replaced \'BDIF\' by \'MIN\' in FCL.");
            	    System.out.println();
                	impMethod="MIN";
                } else if (impMethod.equals("DRP")) {
                	System.out.println("WARNING: \'DRP\' impMethod is not defined in FCL.");
                	System.out.println("    We replaced \'DRP\' by \'PROD\' in FCL.");
            	    System.out.println();
                	impMethod="PROD";
                } else if (impMethod.equals("EPROD")) {
                	System.out.println("WARNING: \'EPROD\' impMethod is not defined in FCL.");
                	System.out.println("    We replaced \'EPROD\' by \'PROD\' in FCL.");
            	    System.out.println();
                	impMethod="PROD";
                } else if (impMethod.equals("HPROD")) {
                	System.out.println("WARNING: \'HPROD\' impMethod is not defined in FCL.");
                	System.out.println("    We replaced \'HPROD\' by \'PROD\' in FCL.");
            	    System.out.println();
                	impMethod="PROD";
                } else if (impMethod.equals("NILMIN")) {
                	System.out.println("WARNING: \'NILMIN\' impMethod is not defined in FCL.");
                	System.out.println("    We replaced \'NILMIN\' by \'MIN\' in FCL.");
            	    System.out.println();
                } else if (impMethod.startsWith("custom")) {
                	System.out.println("WARNING: \'Customized activation (implication)\' can not be exported to FCL.");
                	System.out.println("    By default, impMethod is set to \'MIN\' in FCL.");
           	        System.out.println();
                	impMethod="MIN";
                }
                contents= contents + "    ACT : "+impMethod+";\n";
                if (aggMethod[0].equals("PROBOR")) {
                	System.out.println("WARNING: \'PROBOR\' aggMethod is not defined in FCL.");
                	System.out.println("    We replaced \'PROBOR\' by \'BSUM\' in FCL.");
            	    System.out.println();
                	aggMethod[0]="BSUM";
                } else if (aggMethod[0].equals("DRS")) {
                	System.out.println("WARNING: \'DRS\' aggMethod is not defined in FCL.");
                	System.out.println("    We replaced \'DRS\' by \'BSUM\' in FCL.");
            	    System.out.println();
                	aggMethod[0]="BSUM";
                } else if (aggMethod[0].equals("ESUM")) {
                	System.out.println("WARNING: \'ESUM\' aggMethod is not defined in FCL.");
                	System.out.println("    We replaced \'ESUM\' by \'BSUM\' in FCL.");
            	    System.out.println();
                	aggMethod[0]="BSUM";
                } else if (aggMethod[0].equals("HSUM")) {
                	System.out.println("WARNING: \'HSUM\' aggMethod is not defined in FCL.");
                	System.out.println("    We replaced \'HSUM\' by \'probor\' in FCL.");
            	    System.out.println();
                	aggMethod[0]="BSUM";
                } else if (aggMethod[0].equals("NILMAX")) {
                	System.out.println("WARNING: \'NILMAX\' aggMethod is not defined in FCL.");
                	System.out.println("    We replaced \'NILMAX\' by \'MAX\' in FCL.");
            	    System.out.println();
                	aggMethod[0]="MAX";
                } else if (aggMethod[0].equals("WA")) {
                	aggMethod[0]="sum";
                	System.out.println("WARNING: \'WA\' aggMethod is not defined in FCL.");
                	System.out.println("    We replaced \'WA\' by \'BSUM\' in FCL.");
            	    System.out.println();
                	aggMethod[0]="BSUM";
                } else if (aggMethod[0].startsWith("custom")) {
                	System.out.println("WARNING: \'Customized aggMethod\' can not be exported to FCL.");
                	System.out.println("    By default, aggMethod is set \'MAX\' in FCL.");
           	        System.out.println();
                	aggMethod[0]="MAX";
                }
                contents= contents + "    ACCU : "+aggMethod[0]+";\n\n";
                Iterator<FuzzyRuleType> rM= null;
                if (mamdani[k] && rb[k]!=null) {
              	  rM= rb[k].getRules().iterator();
                }
                Iterator<TskFuzzyRuleType> rTsk= null;
                if (tsk[k] && rbTsk[k]!=null) {
                    rTsk= rbTsk[k].getTskRules().iterator();
                }
                for (int r=0; r<nRules; r++) {
                     contents= contents + "    RULE "+String.valueOf(r+1);
                     String ruleFCL= " : IF ";
                  	  //System.out.println("R"+String.valueOf(k+1));
                     float rw= 0;
                     if (mamdani[k]) {
                         FuzzyRuleType rr= rM.next();
                         rw= rr.getWeight();
                         String rC= rr.getConnector();
                         AntecedentType ar= rr.getAntecedent();
                         List<ClauseType> act= ar.getClauses();
                         Iterator<ClauseType> itct= act.iterator();
                         boolean firstCT= true;
                         for (int n=0; n<nInputs; n++) {
                              if (itct.hasNext()) {
                                  ClauseType ct= itct.next();
                         	      if (ct!=null) {
                                      //System.out.println(ct.toString());
                      				  String vn= ((FuzzyVariableType)ct.getVariable()).getName();
                      				  String tn= ((FuzzyTermType)ct.getTerm()).getName();
                          			  if (firstCT) {
                          				  firstCT= false;
                          			  } else {
                          				  ruleFCL= ruleFCL + " " + rC.toUpperCase();
                          			  }
                                      ruleFCL= ruleFCL + " " + vn + " IS " + tn;
                          		   }
                          	   }
                         }
                         ConsequentType cr= rr.getConsequent();
                         ConsequentClausesType cct= cr.getThen();
                         List<ClauseType> lc= cct.getClause();
                         Iterator<ClauseType> ict= lc.iterator();
           			     ruleFCL= ruleFCL + " THEN";
                         firstCT= true;
                         for (int n=0; n<nOutputs; n++) {
                              if (ict.hasNext()) {
                                  ClauseType ct= ict.next();
                                  if (ct!=null) {
                                      //System.out.println(ct.toString());
                      				  String vn= ((FuzzyVariableType)ct.getVariable()).getName();
                      				  String tn= ((FuzzyTermType)ct.getTerm()).getName();
                           		      if (firstCT) {
                              			  firstCT= false;
                              	      } else {
                              			  ruleFCL= ruleFCL + " ,";
                             		  }
                      				  ruleFCL= ruleFCL + " " + vn + " IS " + tn;
                                  }
                         	  }
                         }                     
                     } else {
                         TskFuzzyRuleType rr= rTsk.next();
                         rw= rr.getWeight();
                         String rC= rr.getConnector();
                         AntecedentType ar= rr.getAntecedent();
                         List<ClauseType> act= ar.getClauses();
                         Iterator<ClauseType> itct= act.iterator();
                         boolean firstCT= true;
                         for (int n=0; n<nInputs; n++) {
                              if (itct.hasNext()) {
                                  ClauseType ct= itct.next();
                         	      if (ct!=null) {
                                       //System.out.println(ct.toString());
                      				  String vn= ((FuzzyVariableType)ct.getVariable()).getName();
                      				  String tn= ((FuzzyTermType)ct.getTerm()).getName();
                          			  if (firstCT) {
                          				  firstCT= false;
                          			  } else {
                          				  ruleFCL= ruleFCL + " " + rC.toUpperCase();
                          			  }
                                      ruleFCL= ruleFCL + " " + vn + " IS " + tn;
                          		   }
                          	   }
                         }
                         TskConsequentType cr= rr.getTskConsequent();
                         TskConsequentClausesType cct= cr.getTskThen();
                         List<TskClauseType> lc= cct.getTskClause();
                         Iterator<TskClauseType> ict= lc.iterator();
           			     ruleFCL= ruleFCL + " THEN";
                         firstCT= true;
                         for (int n=0; n<nOutputs; n++) {
                              if (ict.hasNext()) {
                                  TskClauseType ct= ict.next();
                                  if (ct!=null) {
                                      //System.out.println(ct.toString());
                      				  String vn= ((TskVariableType)ct.getVariable()).getName();
                      				  String tn= ((TskTermType)ct.getTerm()).getName();
                           		      if (firstCT) {
                              			  firstCT= false;
                              	      } else {
                              			  ruleFCL= ruleFCL + " ,";
                             		  }
                      				  ruleFCL= ruleFCL + " " + vn + " IS " + tn;
                                  }
                         	  }
                         }                     
                     }
                     contents= contents + ruleFCL +  " WITH " + rw + ";\n";
                }
                contents= contents + "END_RULEBLOCK\n\n";
            }
            
          contents= contents + "END_FUNCTION_BLOCK\n";
          writeFile(path, contents);
		}
	}

	private String getTermFCL(String tname, String mfType, float[] pp, float[] vrange) {
		String res= "    TERM "+tname+" := ";
		 if (mfType.startsWith("rightlinear")) {
			 res= res + "("+pp[0]+", 0) ("+pp[1]+", 1);";
		 } else if (mfType.startsWith("leftlinear")) {
			 res= res + "("+pp[0]+", 1) ("+pp[1]+", 0);";
		 } else if (mfType.startsWith("pi")) {
			 res= res + "("+pp[0]+", 0) ("+pp[1]+", 1) ("+pp[2]+", 1) ("+pp[3]+", 0);";
		 } else if (mfType.startsWith("gaussian")) {
	         System.out.println("WARNING: FCL only recognized pice-wise linear functions");			
	         System.out.println("    The gaussian membership ("+tname+") function is substituted by a triangular membership funciton centered in c");			
	   	     System.out.println();
	   	     res= res + "("+vrange[0]+", 0) ("+pp[0]+", 1) ("+vrange[1]+", 0);";
		 } else if (mfType.startsWith("rightgaussian")) {
	         System.out.println("WARNING: FCL only recognized pice-wise linear functions");			
	         System.out.println("    The rightGaussian membership ("+tname+") function is substituted by a rightlinear membership funciton");			
	   	     System.out.println();
	   	     res= res + "("+vrange[0]+", 0) ("+pp[0]+", 1);";
		 } else if (mfType.startsWith("leftgaussian")) {
	         System.out.println("WARNING: FCL only recognized pice-wise linear functions");			
	         System.out.println("    The leftGaussian membership ("+tname+") function is substituted by a leftlinear membership funciton");			
	   	     System.out.println();
	   	     res= res + "("+pp[0]+", 0) ("+vrange[1]+", 1);";
		 } else if (mfType.startsWith("rectangular")) {
			 res= res + "("+pp[0]+", 0) ("+pp[1]+", 1) ("+pp[2]+", 1) ("+pp[3]+", 0);";
		 } else if (mfType.startsWith("singleton")) {
			 res= res + pp[0]+";";
		 } else if (mfType.startsWith("s")) {
	         System.out.println("WARNING: FCL only recognized pice-wise linear functions");			
	         System.out.println("    The S membership function ("+tname+") is substituted by a rightlinear membership funciton");			
	   	     System.out.println();
	   	     res= res + "("+pp[0]+", 0) ("+pp[1]+", 1);";
		 } else if (mfType.startsWith("z")) {
	         System.out.println("WARNING: FCL only recognized pice-wise linear functions");			
	         System.out.println("    The Z membership function ("+tname+") is substituted by a leftlinear membership funciton");			
	   	     System.out.println();
	   	     res= res + "("+pp[0]+", 0) ("+pp[1]+", 1);";
		 } else if (mfType.startsWith("triangular")) {
			 res= res + "("+pp[0]+", 0) ("+pp[1]+", 1) ("+pp[2]+", 0);";
		 } else if (mfType.startsWith("trapezoid")) {
			 res= res + "("+pp[0]+", 0) ("+pp[1]+", 1) ("+pp[2]+", 1) ("+pp[3]+", 0);";
		 } else if (mfType.startsWith("user")) {
            System.out.println("WARNING: It is not possible to export to FCL customized (userShape) membership functions");			
            System.out.println("    The user membership function ("+tname+") is substituted by a triangular membership function");			
  	        System.out.println();
  	        float mean= (vrange[0]+vrange[1])/2;
  	        res= res + "("+vrange[0]+", 0) ("+mean+", 1) ("+vrange[1]+", 0);";
		 } else {
            System.out.println("WARNING: Unknown membership function");			
            System.out.println("    This membership function ("+tname+") is substituted by a triangular membership function");			
  	        System.out.println();
  	        float mean= (vrange[0]+vrange[1])/2;
  	        res= res + "("+vrange[0]+", 0) ("+mean+", 1) ("+vrange[1]+", 0);";
		}
		return res+"\n";
    }
	private boolean check(boolean[] aa, boolean[] bb) {
		if (aa.length!=bb.length) {
			return false;
		}
		for (int n=0; n<bb.length; n++) {
			 if ((aa[n]==false) && (bb[n]==false)) {
				 return false;
			 }
		}
		return true;
	}
}