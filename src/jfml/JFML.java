package jfml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jfml.jaxb.FuzzySystemType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.knowledgebase.variable.TsukamotoVariableType;
import jfml.membershipfunction.CircularDefinitionType;
import jfml.rule.AnYaRuleType;
import jfml.rule.ClauseType;
import jfml.rule.FuzzyRuleType;
import jfml.rule.TskClauseType;
import jfml.rule.TskFuzzyRuleType;
import jfml.rulebase.AnYaRuleBaseType;
import jfml.rulebase.FuzzySystemRuleBase;
import jfml.rulebase.RuleBaseType;
import jfml.rulebase.TskRuleBaseType;
import jfml.term.FuzzyTerm;
import jfml.term.FuzzyTermType;
import jfml.term.Term;
import jfml.term.TskTerm;

public class JFML {

	/**
	 * Static method to create a fuzzySystem from a xml file according to the IEEE1855 standard 
	 * @param xml allowed object is
     *     {@link File }
	 * @return a fuzzy System instance
	 */
	public static FuzzyInferenceSystem load(File xml) {
		return new FuzzyInferenceSystem(readFSTfromXML(xml, false).getValue());
	}

	@SuppressWarnings({ "unchecked" })
	private static JAXBElement<FuzzySystemType> readFSTfromXML(File input, boolean print) {
		JAXBElement<FuzzySystemType> fst = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(FuzzySystemType.class);
			Unmarshaller u = jc.createUnmarshaller();

			fst = (JAXBElement<FuzzySystemType>) u.unmarshal(input);

			/**
			 * Check Terms with the same name
			 */
			checkingTerms(fst.getValue().getRuleBase());
			/**
			 * initialize membership functions -- reading from XML file they are not
			 * initialized
			 */
			initializeMembershipFunctions(fst.getValue());

			if (print) {
				Marshaller m = jc.createMarshaller();

				// output pretty printed
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(fst, System.out);
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return fst;
	}

	@SuppressWarnings("rawtypes")
	private static void checkingTerms(List<Object> ruleBase) {
		for(Object o : ruleBase){
			FuzzySystemRuleBase rb = null;
			if(((JAXBElement) o).getValue() instanceof FuzzySystemRuleBase){
				rb = (FuzzySystemRuleBase) ((JAXBElement) o).getValue();
				if(rb instanceof RuleBaseType){
					for(FuzzyRuleType r : ((RuleBaseType) rb).getRules()){
						for(ClauseType c : r.getAntecedent().getClauses()){
							Term t = (Term) c.getTerm();
							KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
							for(Object ft : v.getTerms()){
								if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
									c.setTerm(ft);
							}
						}
						if(r.getConsequent().getThen()!=null){
							for(ClauseType c : r.getConsequent().getThen().getClause()){
								Term t = (Term) c.getTerm();
								KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
								for(Object ft : v.getTerms()){
									if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
										c.setTerm(ft);
								}
							}
						}
						if(r.getConsequent().getElse()!=null){
							for(ClauseType c : r.getConsequent().getElse().getClause()){
								Term t = (FuzzyTerm) c.getTerm();
								KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
								for(Object ft : v.getTerms()){
									if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
										c.setTerm(ft);
								}
							}
						}
					}
				}
				else if(rb instanceof TskRuleBaseType){
					for(TskFuzzyRuleType r : ((TskRuleBaseType) rb).getTskRules()){
						for(ClauseType c : r.getAntecedent().getClauses()){
							Term t = (Term) c.getTerm();
							KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
							for(Object ft : v.getTerms()){
								if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
									c.setTerm(ft);
							}
						}
						if(r.getTskConsequent().getTskThen()!=null){
							for(TskClauseType c : r.getTskConsequent().getTskThen().getTskClause()){
								Term t = (Term) c.getTerm();
								KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
								for(Object ft : v.getTerms()){
									if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
										c.setTerm(ft);
								}
							}
						}
						if(r.getTskConsequent().getTskElse()!=null){
							for(TskClauseType c : r.getTskConsequent().getTskElse().getTskClause()){
								Term t = (Term) c.getTerm();
								KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
								for(Object ft : v.getTerms()){
									if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
										c.setTerm(ft);
								}
							}
						}
					}
				}
				else if(rb instanceof AnYaRuleBaseType){
					for(AnYaRuleType r : ((AnYaRuleBaseType) rb).getAnYaRules()){
						if(r.getConsequent().getThen()!=null){
							for(ClauseType c : r.getConsequent().getThen().getClause()){
								Term t = (Term) c.getTerm();
								KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
								for(Object ft : v.getTerms()){
									if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
										c.setTerm(ft);
								}
							}
						}
						if(r.getConsequent().getElse()!=null){
							for(ClauseType c : r.getConsequent().getElse().getClause()){
								Term t = (Term) c.getTerm();
								KnowledgeBaseVariable v = (KnowledgeBaseVariable) c.getVariable();
								for(Object ft : v.getTerms()){
									if(ft instanceof Term && ((Term) ft).getName().equals(t.getName()) && !t.equals(ft))
										c.setTerm(ft);
								}
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static void initializeMembershipFunctions(FuzzySystemType fst) {
		for (Object v : fst.getKnowledgeBase().getVariables()) {
			KnowledgeBaseVariable var = null;
			if (((JAXBElement) v).getValue() instanceof KnowledgeBaseVariable)
				var = (KnowledgeBaseVariable) ((JAXBElement) v).getValue();
			if (var != null) {
				for (Object t : var.getTerms()) {
					float d_left = 0, d_right = 0;
					if (t instanceof FuzzyTerm) {
						if (var instanceof FuzzyVariableType) {
							d_left = ((FuzzyVariableType) var).getDomainleft();
							d_right = ((FuzzyVariableType) var).getDomainright();
						} else if (var instanceof TsukamotoVariableType) {
							d_left = ((TsukamotoVariableType) var).getDomainleft();
							d_right = ((TsukamotoVariableType) var).getDomainright();
						}

						((FuzzyTerm) t).initializeMembershipFunction(d_left, d_right);
						
						if(t instanceof FuzzyTermType && ((FuzzyTermType) t).getCircularDefinition()!=null)
							((FuzzyTermType) t).getCircularDefinition().setVariable(var);
					}
					
					// TODO initialize other terms
				}
			}
		}

	}

	private static void writeFSTtoXML(JAXBElement<?> element, File output) {
		try {
			@SuppressWarnings("rawtypes")
			Class type = element.getDeclaredType();
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller m = jc.createMarshaller();
			
			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(element, output);
			// m.marshal(element, System.out);
			
			//removePrefixNS(output, "ns2");

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Static method to write a FuzzySystem in a xml file
	 * @param fst allowed object is
     *     {@link FuzzySystemType }
	 * 
	 * @param output
	 * allowed object is
     *     {@link File }
	 * 
	 */
	public static void writeFSTtoXML(FuzzySystemType fst, File output) {
		writeFSTtoXML(fst.getJAXBElement(), output);
	}

	@SuppressWarnings("rawtypes")
	public void writeFSTtoXML(JAXBElement<?> element, Class type, File output) {
		try {
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller m = jc.createMarshaller();
			

			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(element, output);
			// m.marshal(element, System.out);
			
			//removePrefixNS(output, "ns2");

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void removePrefixNS(File file, String prefix){
		BufferedReader bf = null;
		String s;
		String b="";

		try {
			bf = new BufferedReader(new FileReader(file));
			do{
				s = bf.readLine();
				if(s!=null)
					b += s.replaceAll(prefix+":", "").replaceAll(":"+prefix, "") + "\r\n";
			}
			while (s != null);
			
			bf.close();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(b);
			out.close();
		} 
		catch (IOException io) {
			System.err.println(io.getMessage());
		}
	}
}
