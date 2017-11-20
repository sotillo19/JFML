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

import java.util.ArrayList;
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
 * This class allows to export a fuzzy system in format FML to MATLAB format
 * 
 * @author Jose Alonso
 *
 */
public class ExportMatlab extends Export {

	/**
	 * Constructor by default
	 */
	public ExportMatlab() {
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
			System.out.println("WARNING: MATLAB does not support edges, does not support ELSE-part of a rule.");
			System.out.println("    If the fuzzy system to export includes edges or ELSE-part in rules, they are ignored.");
	        System.out.println();
            String contents="[System]\n";
            contents= contents + "Name=\'"+fuzzySystem.getName()+"\'\n";
            if (mamdani) {
                contents= contents + "Type=\'mamdani\'\n";
            } else { 
                contents= contents + "Type=\'sugeno\'\n";
            }
            contents= contents + "Version=2.0\n";
            KnowledgeBaseType kb= fuzzySystem.getKnowledgeBase();
            List<KnowledgeBaseVariable> vv= kb.getKnowledgeBaseVariables();
            Iterator<KnowledgeBaseVariable> it= vv.iterator();
            int nInputs= 0;
            int nOutputs= 0;
            String aggMethod=""; // accumulation
            String defMethod=""; // defuzzifier
            for (int n=0; n<vv.size(); n++) {
                 if (((KnowledgeBaseVariable)it.next()).isInput()) {
                	 nInputs++;
                 } else {
            		 // only one method for accumulation and defuzzification is allowed for MATLAB
            		 // we took the operators defined for the first output in the knowledge base
                	 if ( (mamdani) && (nOutputs==0) ) {
                	     FuzzyVariableType out= (FuzzyVariableType)vv.get(n);
                	     aggMethod= out.getAccumulation();
                	     defMethod= out.getDefuzzifierName();
                	 } else if ( tsk && (nOutputs==0) ) {
                	     TskVariableType out= (TskVariableType)vv.get(n);
                	     aggMethod= out.getCombination();
                	     defMethod= "WA";
                	 }
                	 if (nOutputs > 0) {
                	     System.out.println("WARNING: Only one method for accumulation and one method for defuzzification are allowed for MATLAB");
                	     System.out.println("    In case of more than one output, we consider only the operators defined for the first output in the knowledge base");
                	     System.out.println();
                	 }
                	 nOutputs++;
                 }
            }
            int[] indIn= new int[nInputs];
            String[] iNames= new String[nInputs];
            int[] indOut= new int[nOutputs];
            String[] oNames= new String[nOutputs];
            int ci=0;
            int co=0;
            for (int n=0; n<vv.size(); n++) {
                 if (vv.get(n).isInput()) {
                     indIn[ci]= n;
                     iNames[ci++]= vv.get(n).getName();
                 } else {
                     indOut[co]= n;
                     oNames[co++]= vv.get(n).getName();
                 }
            }
            contents= contents + "NumInputs="+nInputs+"\n";
            contents= contents + "NumOutputs="+nOutputs+"\n";
            if (RBs.length > 1) {
            	System.out.println("WARNING: Only one rule base can be exported to MATLAB");
            	System.out.println("    Since the fuzzy system includes more than one rule base, only the first one is exported");
       	        System.out.println();
            }
            //Object ruleBase;//= listRB.get(0);
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
            contents= contents + "NumRules="+nRules+"\n";
            // Pag28, T10 (IEEE std)
            if (andMethod.equals("MIN")) {
            	andMethod="min";
            } else if (andMethod.equals("PROD")) {
            	andMethod="prod";
            } else if (andMethod.equals("BDIF")) {
            	System.out.println("WARNING: \'BDIF\' andMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'BDIF\' by \'min\' in MATLAB.");
        	    System.out.println();
            	andMethod="min";
            } else if (andMethod.equals("DRP")) {
            	System.out.println("WARNING: \'DRP\' andMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'DRP\' by \'prod\' in MATLAB.");
        	    System.out.println();
            	andMethod="prod";
            } else if (andMethod.equals("EPROD")) {
            	System.out.println("WARNING: \'EPROD\' andMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'EPROD\' by \'prod\' in MATLAB.");
        	    System.out.println();
            	andMethod="prod";
            } else if (andMethod.equals("HPROD")) {
            	System.out.println("WARNING: \'HPROD\' andMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'HPROD\' by \'prod\' in MATLAB.");
        	    System.out.println();
            	andMethod="prod";
            } else if (andMethod.equals("NILMIN")) {
            	System.out.println("WARNING: \'NILMIN\' andMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'NILMIN\' by \'min\' in MATLAB.");
        	    System.out.println();
            	andMethod="min";
            } else if (andMethod.startsWith("custom")) {
            	System.out.println("WARNING: Customized andMethod can not be exported to MATLAB.");
            	System.out.println("    By default, andMethod is set to \'min\' in MATLAB.");
        	    System.out.println();
            	andMethod="min";
            }
            contents= contents + "AndMethod=\'"+andMethod+"\'\n";
            // Pag29, T11 (IEEE std)
            if (orMethod.equals("MAX")) {
            	orMethod="max";
            } else if (orMethod.equals("PROBOR")) {
            	orMethod="probor";
            } else if (orMethod.equals("BSUM")) {
            	System.out.println("WARNING: \'BSUM\' orMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'BSUM\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	orMethod="probor";
            } else if (orMethod.equals("DRS")) {
            	System.out.println("WARNING: \'DRS\' orMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'DRS\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	orMethod="probor";
            } else if (orMethod.equals("ESUM")) {
            	System.out.println("WARNING: \'ESUM\' orMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'ESUM\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	orMethod="probor";
            } else if (orMethod.equals("HSUM")) {
            	System.out.println("WARNING: \'HSUM\' orMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'HSUM\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	orMethod="probor";
            } else if (orMethod.equals("NILMAX")) {
            	System.out.println("WARNING: \'NILMAX\' orMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'NILMAX\' by \'max\' in MATLAB.");
        	    System.out.println();
            	orMethod="max";
            } else if (orMethod.startsWith("custom")) {
            	System.out.println("WARNING: \'Customized orMethod\' can not be exported to MATLAB.");
            	System.out.println("    By default, orMethod is set to \'max\' in MATLAB.");
       	        System.out.println();
            	orMethod="max";
            }
            contents= contents + "OrMethod=\'"+orMethod+"\'\n";
            // Pag40, T23 (IEEE std)
            if (impMethod.equals("MIN")) {
            	impMethod="min";
            } else if (impMethod.equals("PROD")) {
            	impMethod="prod";
            } else if (impMethod.equals("BDIF")) {
            	System.out.println("WARNING: \'BDIF\' impMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'BDIF\' by \'min\' in MATLAB.");
        	    System.out.println();
            	impMethod="min";
            } else if (impMethod.equals("DRP")) {
            	System.out.println("WARNING: \'DRP\' impMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'DRP\' by \'prod\' in MATLAB.");
        	    System.out.println();
            	impMethod="prod";
            } else if (impMethod.equals("EPROD")) {
            	System.out.println("WARNING: \'EPROD\' impMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'EPROD\' by \'prod\' in MATLAB.");
        	    System.out.println();
            	impMethod="prod";
            } else if (impMethod.equals("HPROD")) {
            	System.out.println("WARNING: \'HPROD\' impMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'HPROD\' by \'prod\' in MATLAB.");
        	    System.out.println();
            	impMethod="prod";
            } else if (impMethod.equals("NILMIN")) {
            	System.out.println("WARNING: \'NILMIN\' impMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'NILMIN\' by \'min\' in MATLAB.");
        	    System.out.println();
            	impMethod="min";
            } else if (impMethod.startsWith("custom")) {
            	System.out.println("WARNING: \'Customized activation (implication)\' can not be exported to MATLAB.");
            	System.out.println("    By default, impMethod is set to \'min\' in MATLAB.");
       	        System.out.println();
            	impMethod="min";
            }
            contents= contents + "ImpMethod=\'"+impMethod+"\'\n";
            // Pag23, T2 (IEEE std)
            if (aggMethod.equals("MAX")) {
            	aggMethod="max";
            } else if (aggMethod.equals("PROBOR")) {
            	aggMethod="probor";
            } else if (aggMethod.equals("BSUM")) {
            	System.out.println("WARNING: \'BSUM\' aggMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'BSUM\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	aggMethod="probor";
            } else if (aggMethod.equals("DRS")) {
            	System.out.println("WARNING: \'DRS\' aggMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'DRS\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	aggMethod="probor";
            } else if (aggMethod.equals("ESUM")) {
            	System.out.println("WARNING: \'ESUM\' aggMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'ESUM\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	aggMethod="probor";
            } else if (aggMethod.equals("HSUM")) {
            	System.out.println("WARNING: \'HSUM\' aggMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'HSUM\' by \'probor\' in MATLAB.");
        	    System.out.println();
            	aggMethod="probor";
            } else if (aggMethod.equals("NILMAX")) {
            	System.out.println("WARNING: \'NILMAX\' aggMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'NILMAX\' by \'max\' in MATLAB.");
        	    System.out.println();
            	aggMethod="max";
            } else if (aggMethod.equals("WA")) {
            	aggMethod="sum";
            } else if (aggMethod.startsWith("custom")) {
            	System.out.println("WARNING: \'Customized accumulation\' can not be exported to MATLAB.");
            	System.out.println("    By default, aggMethod is set to \'max\' in MATLAB.");
       	        System.out.println();
            	aggMethod="max";
            }
            contents= contents + "AggMethod=\'"+aggMethod+"\'\n";
            // Pag23, T2 (IEEE std)
            if (defMethod.equals("MOM")) {
            	defMethod="mom";
            } else if (defMethod.equals("LM")) {
            	System.out.println("WARNING: \'LM\' defMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'LM\' by \'som\' in MATLAB.");
        	    System.out.println();
            	defMethod="som";
            } else if (defMethod.equals("RM")) {
            	System.out.println("WARNING: \'RM\' defMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'RM\' by \'lom\' in MATLAB.");
        	    System.out.println();
            	defMethod="lom";
            } else if (defMethod.equals("COG")) {
            	System.out.println("WARNING: \'COG\' defMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'COG\' by \'centroid\' in MATLAB.");
        	    System.out.println();
            	defMethod="centroid";
            } else if (defMethod.equals("COA")) {
            	System.out.println("WARNING: \'COA\' defMethod is not defined in MATLAB.");
            	System.out.println("    We replaced \'COA\' by \'centroid\' in MATLAB.");
        	    System.out.println();
            	defMethod="centroid";
            } else if (defMethod.equals("WA")) {
            	defMethod="wtaver";
            } else if (defMethod.startsWith("custom")) {
            	System.out.println("WARNING: Customized DefuzMethod can not be exported to MATLAB.");
            	System.out.println("    By default, DefuzMethod is set to \'centroid\' (Mamdani) or \'wtaver\' (Sugeno) in MATLAB.");
       	        System.out.println();
       	        if (mamdani) {
            	    defMethod="centroid";
       	        } else {
                	defMethod="wtaver";
       	        }
            }
            contents= contents + "DefuzzMethod=\'"+defMethod+"\'\n";
            contents= contents + "\n";
            for (int k=0; k<nInputs; k++) {
            	//System.out.println("IN"+String.valueOf(k+1));
                contents= contents + "[Input"+String.valueOf(k+1)+"]\n";
                String name="";
                String range="";
                String MF="";
                int nMFs=0;
                if (mamdani || tsk) {
                    FuzzyVariableType in= (FuzzyVariableType)vv.get(indIn[k]);
                    name= in.getName();
                    range=in.getDomainleft()+" "+in.getDomainright();
                    List<FuzzyTermType> lt= in.getTerms();
                    nMFs= lt.size();
               	    //System.out.println("nMFs="+nMFs);
                    Iterator<FuzzyTermType> iterm= lt.iterator();
                    for (int m=0; m<nMFs; m++) {
                    	 //System.out.println("    MF"+String.valueOf(m+1));
                    	 FuzzyTermType term= iterm.next();
                         String tname= term.getName();
                         String mfType= (term.getMembershipFunction()).getName().toLowerCase();
                         String mfMatlab= getMatlabMF(mfType);
                         String params="";
                         float[] pp= term.getParam();
                         if (mfMatlab.equals("pimf")) {
                        	 params=pp[0]+" "+pp[3];
                         } else if (mfMatlab.equals("gaussmf")) {
                        	 params=pp[1]+" "+pp[0];
                         } else if (mfType.startsWith("rightlinear")) {
                        	 params=pp[0]+" "+pp[0]+" "+pp[1];
                         } else if (mfType.startsWith("leftlinear")) {
                        	 params=pp[0]+" "+pp[1]+" "+pp[1];
                         } else if (mfType.startsWith("user")) {
                        	 params=String.valueOf((in.getDomainleft()+in.getDomainright())/2);
                         } else {
                           for (int p=0;p<pp.length; p++) {
                        	 params= params+pp[p];
                        	 if (p < pp.length-1) {
                        		 params= params+" ";
                        	 }
                           }
                         }
                         MF= MF+"MF"+String.valueOf(m+1)+"=\'"+tname+"\':\'"+mfMatlab+"\',["+params+"]";
                         if (m<nMFs-1) {
                        	 MF= MF + "\n";
                         }
                    }
                } 
                contents= contents + "Name=\'"+name+"\'\n";
                contents= contents + "Range=["+range+"]\n";
                contents= contents + "NumMFs="+nMFs+"\n";
                contents= contents + MF +"\n";
                if (k<nInputs-1) {
                    contents= contents + "\n";
                }
            }
            contents= contents + "\n";
            for (int k=0; k<nOutputs; k++) {
                contents= contents + "[Output"+String.valueOf(k+1)+"]\n";
                String name="";
                String range="";
                String MF="";
                int nMFs=0;
                if (mamdani) {
                    FuzzyVariableType out= (FuzzyVariableType)vv.get(indOut[k]);
                    name= out.getName();
                    range=out.getDomainleft()+" "+out.getDomainright();
                    List<FuzzyTermType> lt= out.getTerms();
                    nMFs= lt.size();
                    Iterator<FuzzyTermType> iterm= lt.iterator();
                    for (int m=0; m<nMFs; m++) {
                    	 FuzzyTermType term= iterm.next();
                         String tname= term.getName();
                         String mfType= (term.getMembershipFunction()).getName().toLowerCase();
                         String mfMatlab= getMatlabMF(mfType);
                         String params="";
                         float[] pp= term.getParam();
                         if (mfMatlab.equals("pimf")) {
                        	 params=pp[0]+" "+pp[3];
                         } else if (mfMatlab.equals("gaussmf")) {
                        	 params=pp[1]+" "+pp[0];
                         } else if (mfType.startsWith("rightlinear")) {
                        	 params=pp[0]+" "+pp[0]+" "+pp[1];
                         } else if (mfType.startsWith("leftlinear")) {
                        	 params=pp[0]+" "+pp[1]+" "+pp[1];
                         } else if (mfType.startsWith("user")) {
                        	 params=String.valueOf((out.getDomainleft()+out.getDomainright())/2);
                         } else {
                           for (int p=0;p<pp.length; p++) {
                        	 params= params+pp[p];
                        	 if (p < pp.length-1) {
                        		 params= params+" ";
                        	 }
                           }
                         }
                         MF= MF+"MF"+String.valueOf(m+1)+"=\'"+tname+"\':\'"+mfMatlab+"\',["+params+"]";
                         if (m<nMFs-1) {
                        	 MF= MF + "\n";
                         }
                    }
                } else if (tsk) {
                    TskVariableType out= (TskVariableType)vv.get(indOut[k]);
                    name= out.getName();
                    // How to get range values?
                    // range=out.getDomainleft()+" "+out.getDomainright();
                    // assigned default range
                    range= "0 1";
                    List<TskTermType> lt= out.getTerms();
                    nMFs= lt.size();
                    Iterator<TskTermType> iterm= lt.iterator();
                    for (int m=0; m<nMFs; m++) {
                    	 TskTermType term= iterm.next();
                         String tname= term.getName();
                         int ord= term.getOrder();
                         String mfMatlab;
                         String params="";
                         List<Float> lv= term.getTskValue();
                         if (ord > 0) {
                             mfMatlab= "linear";
                             Iterator<Float> fi= lv.iterator();
                             while (fi.hasNext()) {
                            	 params= params + String.valueOf(fi.next().floatValue());
                            	 if (fi.hasNext()) {
                            		 params= params + " ";
                            	 }
                             }
                         } else {
                             mfMatlab= "constant";
                             params=""+lv.get(0);
                         }
                         MF= MF+"MF"+String.valueOf(m+1)+"=\'"+tname+"\':\'"+mfMatlab+"\',["+params+"]";
                         if (m<nMFs-1) {
                        	 MF= MF + "\n";
                         }
                    }
                }
                contents= contents + "Name=\'"+name+"\'\n";
                contents= contents + "Range=["+range+"]\n";
                contents= contents + "NumMFs="+nMFs+"\n";
                contents= contents + MF +"\n";
                if (k<nOutputs-1) {
                    contents= contents + "\n";
                }
            }
            contents= contents + "\n";
            if (nRules > 0) {
              contents= contents + "[Rules]\n";
              Iterator<FuzzyRuleType> rM= null;
              if (mamdani && rb!=null) {
            	  rM= rb.getRules().iterator();
              }
              Iterator<TskFuzzyRuleType> rTsk= null;
              if (tsk && rbTsk!=null) {
                  rTsk= rbTsk.getTskRules().iterator();
              }
              String rulesMatlab= "";
              for (int k=0; k<nRules; k++) {
            	  //System.out.println("R"+String.valueOf(k+1));
                  if (mamdani) {
                      FuzzyRuleType r= rM.next();
                      AntecedentType ar= r.getAntecedent();
                      List<ClauseType> act1= ar.getClauses();
                      List<ClauseType> act= rankClauses(act1, iNames);
                      Iterator<ClauseType> itct= act.iterator();
                      for (int n=0; n<nInputs; n++) {
                    	   int ind= 0;
                    	   if (itct.hasNext()) {
                               ClauseType ct= itct.next();
                               //System.out.println(ct.toString());
                    		   if (ct!=null) {
                                     FuzzyVariableType v= (FuzzyVariableType)ct.getVariable();
                                     //System.out.println(v.getName());
                                     FuzzyTermType t= (FuzzyTermType)ct.getTerm();
                                     //System.out.println(t.getName());
                                     ind= this.getMatlabMFindex(v, t)+1;
                                 //System.out.println(ind);
                    		   }
                    	   }
                    	   if (n==nInputs-1) {
                    	       rulesMatlab= rulesMatlab + String.valueOf(ind)+",";
                    	   } else {
                    	       rulesMatlab= rulesMatlab + String.valueOf(ind)+" ";
                    	   }
                      }
                      ConsequentType cr= r.getConsequent();
                      ConsequentClausesType cct= cr.getThen();
                      List<ClauseType> lc1= cct.getClause();
                      List<ClauseType> lc= rankClauses(lc1, oNames);
                      Iterator<ClauseType> ict= lc.iterator();
                      for (int n=0; n<nOutputs; n++) {
                   	       int ind= 0;
                   	       if (ict.hasNext()) {
                               ClauseType ct= ict.next();
                               //System.out.println(ct.toString());
                               if (ct!=null) {
                                    FuzzyVariableType v= (FuzzyVariableType)ct.getVariable();
                                    //System.out.println(v.getName());
                                    FuzzyTermType t= (FuzzyTermType)ct.getTerm();
                                    //System.out.println(t.getName());
                                    ind= this.getMatlabMFindex(v, t)+1;
                                 //System.out.println(ind);
                               }
                   	       }
               	           rulesMatlab= rulesMatlab + " " + String.valueOf(ind);
                      }                      
                      float rw= r.getWeight();
                      String rC= r.getConnector();
                      if (rC.equals("and")) {
                          rulesMatlab= rulesMatlab + " ("+String.valueOf(rw)+") : 1\n";
                      } else {
                          rulesMatlab= rulesMatlab + " ("+String.valueOf(rw)+") : 2\n";
                      }
                  } else {
                      TskFuzzyRuleType r= rTsk.next();
                      AntecedentType ar= r.getAntecedent();
                      List<ClauseType> act1= ar.getClauses();
                      List<ClauseType> act= rankClauses(act1,iNames);
                      Iterator<ClauseType> itct= act.iterator();
                      for (int n=0; n<nInputs; n++) {
                    	   boolean not= false;
                    	   int ind= 0;
                    	   if (itct.hasNext()) {
                               ClauseType ct= itct.next();
                               //System.out.println(ct.toString());
                               if (ct!=null) {
                                     FuzzyVariableType v= (FuzzyVariableType)ct.getVariable();
                                     //System.out.println(v.getName());
                                     FuzzyTermType t= (FuzzyTermType)ct.getTerm();
                                     //System.out.println(t.getName());
                                     ind= this.getMatlabMFindex(v, t)+1;
                                     if (t.getComplement().equals("true") || t.getComplement().equals("True") || t.getComplement().equals("TRUE")) {
                                	     not= true;
                                     }
                                 //System.out.println(ind);
                               }
                    	   }
                    	   String cl="";
                    	   if (not) {
                    		   cl= "-";
                    	   }
                    	   if (n==nInputs-1) {
                    	       rulesMatlab= rulesMatlab + cl + String.valueOf(ind)+",";
                    	   } else {
                    	       rulesMatlab= rulesMatlab + cl + String.valueOf(ind)+" ";
                    	   }
                      }
                      TskConsequentType cr= r.getTskConsequent();
                      TskConsequentClausesType cct= cr.getTskThen();
                      List<TskClauseType> lc1= cct.getTskClause();
                      List<TskClauseType> lc= rankTskClauses(lc1,oNames);
                      Iterator<TskClauseType> ict= lc.iterator();
                      for (int n=0; n<nOutputs; n++) {
                   	       int ind= 0;
                   	       if (ict.hasNext()) {
                               TskClauseType ct= ict.next();
                               if (ct!=null) {
                                 //System.out.println(ct.toString());
                                 TskVariableType v= (TskVariableType)ct.getVariable();
                                 //System.out.println(v.getName());
                                 TskTermType t= (TskTermType)ct.getTerm();
                                 //System.out.println(t.getName());
                                 ind= this.getMatlabMFindex(v, t)+1;
                                 //System.out.println(ind);
                               }
                   	       }
               	           rulesMatlab= rulesMatlab + " " + String.valueOf(ind);
                      }                      
                      float rw= r.getWeight();
                      String rC= r.getConnector();
                      if (rC.equals("and")) {
                          rulesMatlab= rulesMatlab + " ("+String.valueOf(rw)+") : 1\n";
                      } else {
                          rulesMatlab= rulesMatlab + " ("+String.valueOf(rw)+") : 2\n";
                      }
                  }
              }
              contents= contents + rulesMatlab;
          }
          writeFile(path, contents);
		}
	}
	
	private String getMatlabMF (String mf) {
        //System.out.println("ExportMatlab: getMatlabMF: input: "+mf);
		String res="";
		if (mf.startsWith("rightlinear")) {
            res= "trimf";
		} else if (mf.startsWith("leftlinear")) {
            res= "trimf";
		} else if (mf.startsWith("pi")) {
			// piShape in JFML has 4 params
			// pimf in Matlab has only 2 params (the left and rigth extremes in JFML)
            res= "pimf";
		} else if (mf.startsWith("gaussian")) {
            res= "gaussmf";
		} else if (mf.startsWith("rightgaussian")) {
            res= "gaussmf";
		} else if (mf.startsWith("leftgaussian")) {
            res= "gaussmf";
		} else if (mf.startsWith("rectangular")) {
            res= "trapmf";
		} else if (mf.startsWith("singleton")) {
            res= "constant";
		} else if (mf.startsWith("s")) {
            res= "smf";
		} else if (mf.startsWith("z")) {
            res= "zmf";
		} else if (mf.startsWith("triangular")) {
            res= "trimf";
		} else if (mf.startsWith("trapezoid")) {
            res= "trapmf";
		} else if (mf.startsWith("user")) {
            res= "constant";
            System.out.println("WARNING: It is not possible to export to MATLAB customized (userShape) membership functions");			
            System.out.println("    This membership function is substituted by a singleton (0)");			
   	        System.out.println();
		} else {
            res= "constant";
            System.out.println("WARNING: Unknown membership function");			
            System.out.println("    This membership function is substituted by a singleton (0)");			
   	        System.out.println();
		}
        //System.out.println("ExportMatlab: getMatlabMF: output: "+res);
		return res;
	}

	private int getMatlabMFindex (FuzzyVariableType v, FuzzyTermType t) {
        int res= 0;
        List<FuzzyTermType> tt= v.getTerms();
        res= tt.indexOf(t);
        return res;
	}

	private int getMatlabMFindex (TskVariableType v, TskTermType t) {
        int res= 0;
        List<TskTermType> tt= v.getTerms();
        res= tt.indexOf(t);
        return res;
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
             while (it.hasNext()) {
            	 ClauseType ct= it.next();
            	 if (ct!=null) {
            	   //System.out.println("    "+ct.toString());
            	   if (ct.toString().contains(names[n]+ " IS ")) {
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

	private List<TskClauseType> rankTskClauses (List<TskClauseType> l, String[] names) {
		List<TskClauseType> res= new ArrayList<TskClauseType>();
		if (names.length > 1) {
		  for (int n=0; n<names.length; n++) {
        	 boolean warning= false;
       	     //System.out.println("n="+n);
       	     //System.out.println("    "+names[n]);
       	     //System.out.println("    "+l.size());
        	 Iterator<TskClauseType> it= l.iterator();
             while (it.hasNext()) {
            	 TskClauseType ct= it.next();
            	 //System.out.println("    "+ct.toString());
            	 if (ct.toString().contains(names[n]+ " IS ")) {
            		 res.add(ct);
            		 warning= true;
            		 break;
            	 }
             }
             if (!warning) {
            	 TskClauseType ct= null;
        		 res.add(ct);
             }
          } 
		} else {
			res= l;
		}
        return res;
	}
}