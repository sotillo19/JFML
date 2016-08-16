package jfml.test;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jfml.jaxb.FuzzySystemType;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.parameter.OneParamType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.FuzzySystemRuleBaseType;
import jfml.rulebase.RuleBaseType;
import jfml.term.FuzzyTermType;


public class XMLReadWriteExample {
	
	//JAXBElement<FuzzySystemType> fls;
	
	public XMLReadWriteExample() {
		
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public JAXBElement<FuzzySystemType> readFSTfromXML(File input, boolean print){
		JAXBElement<FuzzySystemType> fst=null;
		try{
			JAXBContext jc = JAXBContext.newInstance(FuzzySystemType.class);
			Unmarshaller u = jc.createUnmarshaller();

			fst = (JAXBElement<FuzzySystemType>) u.unmarshal(input);
			
			if(print){
				Marshaller m = jc.createMarshaller();

				// output pretty printed
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(fst, System.out);
			}

		}catch (JAXBException e) {
			e.printStackTrace();	
		}
		
		return fst;
	}
	
	/*public void writeFLStoXML(JAXBElement<FuzzySystemType> fls, File output){
		try {
			JAXBContext jc = JAXBContext.newInstance(FuzzySystemType.class);
			Marshaller m = jc.createMarshaller();

			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(fls, output);
			//jaxbMarshaller.marshal(fls, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}*/
	
	/*@SuppressWarnings({ "unchecked" })
	public JAXBElement<?> readXML(Class type, File input){
		JAXBElement<?> elem=null;
		try{
			JAXBContext jc = JAXBContext.newInstance(type);
			Unmarshaller u = jc.createUnmarshaller();

			elem = (JAXBElement<?>) u.unmarshal(input);

		}catch (JAXBException e) {
			e.printStackTrace();	
		}
		
		return elem;
	}*/
	
	public void writeFSTtoXML(FuzzySystemType fst, File output){
		writeFSTtoXML(fst.getJAXBElement(),output);
	}
	
	public void writeFSTtoXML(JAXBElement<?> element, File output){
		try {
			@SuppressWarnings("rawtypes")
			Class type = element.getDeclaredType();
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller m = jc.createMarshaller();

			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(element, output);
			//m.marshal(element, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void writeFSTtoXML(JAXBElement<?> element, Class type, File output){
		try {
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller m = jc.createMarshaller();

			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(element, output);
			//m.marshal(element, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void printFuzzySystemType(JAXBElement<FuzzySystemType> fls){
		System.out.println("NAME: "+fls.getValue().getName());
		System.out.println("NETWORKADDRESS: "+fls.getValue().getNetworkAddress());

		System.out.println();
		System.out.println("KNOWLEDGEBASE: ");
		
		List<Object> vars = fls.getValue().getKnowledgeBase().getVariables();
		
		for(int i=0;i<vars.size();i++){
			@SuppressWarnings("rawtypes")
			JAXBElement v =  (JAXBElement) vars.get(i);
			FuzzyVariableType fv = null;
			if(v.getValue() instanceof FuzzyVariableType)
				fv = (FuzzyVariableType)v.getValue();
			
			
			if(fv!=null){
				System.out.println("  FUZZYVARIABLE "+(i+1)+": "+fv.getName());
				
				List<FuzzyTermType> terms = fv.getTerms();
				
				for(int j=0;j<terms.size();j++){
					FuzzyTermType t = terms.get(j);
					System.out.println("    FUZZYTERM "+(j+1)+": "+t.getName());
				}
			}
		}
		
		List<Object> rules = fls.getValue().getRuleBase();
		
		for(int i=0;i<rules.size();i++){
			@SuppressWarnings("rawtypes")
			JAXBElement v =  (JAXBElement) rules.get(i);
			RuleBaseType rb = null;
			if(v.getValue() instanceof RuleBaseType)
				rb = (RuleBaseType)v.getValue();
			
			
			System.out.println();
			
			if(rb!=null){
				System.out.println("RULEBASE "+(i+1)+": "+rb.getName());
				
				List<FuzzyRuleType> rs = rb.getRules();
				
				for(int j=0;j<rs.size();j++){
					FuzzyRuleType r = rs.get(j);
					System.out.println("    RULE "+(j+1)+": "+r.getName());
					
					AntecedentType ant = r.getAntecedent();
					System.out.println("      ANTECEDENT: ");
					
					List<ClauseType> clauses = ant.getClauses();
					for(int k=0;k<clauses.size();k++){
						ClauseType c = clauses.get(k);
						System.out.println("       CLAUSE "+(k+1)+": ");
						System.out.println("         VAR: "+c.getVariable());
						System.out.println("         TERM: "+c.getTerm());
					}
					
					ConsequentType con = r.getConsequent();
					System.out.println("      CONSEQUENT: ");
					
					if(con.getThen()!=null){
						System.out.println("         THEN: ");
						clauses = con.getThen().getClause();
						
						for(int k=0;k<clauses.size();k++){
							ClauseType c = clauses.get(k);
							System.out.println("          CLAUSE "+(k+1)+": ");
							System.out.println("             VAR: "+c.getVariable().toString());
							System.out.println("             TERM: "+c.getTerm().toString());
						}
					}
					if(con.getElse()!=null){
						System.out.println("         ELSE: ");
						clauses = con.getElse().getClause();
						
						for(int k=0;k<clauses.size();k++){
							ClauseType c = clauses.get(k);
							System.out.println("         CLAUSE "+(k+1)+": ");
							System.out.println("            VAR: "+c.getVariable().toString());
							System.out.println("            TERM: "+c.getTerm().toString());
						}
					}
				}
			}
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public void exampleReadWriteFromXML(){
		File input = new File("./XMLFiles/ExampleTipperSystemMamdani.xml");
		File output = new File("./XMLFiles/ExampleTipperSystemMamdaniOUT2.xml");
		
		//READING XML
		JAXBElement<FuzzySystemType> fls = readFSTfromXML(input,false);
		printFuzzySystemType(fls);
		
		
		//CHANGING VALUES
		FuzzySystemType fs = fls.getValue();
		
		fs.setName("Tipper System - Mamdani");
		
		List<Object> vars = fls.getValue().getKnowledgeBase().getVariables();

		JAXBElement v =  (JAXBElement) vars.get(0);
		FuzzyVariableType fv = null;
		if(v.getValue() instanceof FuzzyVariableType)
			fv = (FuzzyVariableType)v.getValue();
		if(fv!=null)
			fv.setName("Comida");

		v =  (JAXBElement) vars.get(1);
		fv = null;
		if(v.getValue() instanceof FuzzyVariableType)
			fv = (FuzzyVariableType)v.getValue();
		if(fv!=null)
			fv.setName("Servicio");
		
		v =  (JAXBElement) vars.get(2);
		fv = null;
		if(v.getValue() instanceof FuzzyVariableType)
			fv = (FuzzyVariableType)v.getValue();
		if(fv!=null)
			fv.setName("Propina");

		List<FuzzyTermType> t = fv.getTerms();
		FuzzyTermType ft = new FuzzyTermType();
		ft.setName("wonderful");
		
		OneParamType p1 = new OneParamType();
		p1.setParam1(22f);
		ft.setSingletonShape(p1);
		t.add(ft);
		
		//WRITTING XML
		writeFSTtoXML(fls, output);
		printFuzzySystemType(fls);
	}

	/*public void exampleCreateFuzzyVariable(){
		FuzzyVariableType fv = null;
		
		fv = new FuzzyVariableType("food",0,10);
		//fv.setName("food");
		//fv.setDomainleft(0);
		//fv.setDomainright(10f);
		//fv.setScale("");
		//fv.setType("input");
		
		fv.fuzzyTerm = new ArrayList<FuzzyTermType>();
		
		//FUZZY TERM 1
		FuzzyTermType ft1 = new FuzzyTermType();
		ft1.setName("delicious");
		ft1.setComplement("false");
		
		TwoParamType two = new TwoParamType();
		two.setParam1(5.5f);
		two.setParam2(10.0f);
		ft1.setLeftLinearShape(two);
		fv.fuzzyTerm.add(ft1);
		
		//FUZZY TERM 2
		FuzzyTermType ft2 = new FuzzyTermType();
		ft2.setName("rancid");
		ft2.setComplement("false");
		
		ThreeParamType three = new ThreeParamType();
		three.setParam1(0.0f);
		three.setParam2(2f);
		three.setParam3(5.5f);
		ft2.setTriangularShape(three);
		fv.fuzzyTerm.add(ft2);
		
		QName xml = new QName("http://www.ieee1855.org", FuzzyVariableType.XML_ELEMENT_NAME, XMLConstants.DEFAULT_NS_PREFIX);
		JAXBElement<FuzzyVariableType> fvt = new JAXBElement<FuzzyVariableType>(xml, FuzzyVariableType.class, KnowledgeBaseType.class, fv);
		
		File output = new File("./XMLFiles/ExampleFuzzyVariableOUT.xml");
		
		writeXML(fvt, FuzzyVariableType.class, output);
	}
	
	public void exampleReadFuzzyVariable(){
		File input = new File("./XMLFiles/ExampleFuzzyVariableOUT.xml");
		
		//READING XML
		@SuppressWarnings("unchecked")
		JAXBElement<FuzzyVariableType> elem = (JAXBElement<FuzzyVariableType>) readXML(FuzzyVariableType.class, input);
		
		FuzzyVariableType fv = null;
		if(elem.getValue() instanceof FuzzyVariableType)
			fv = (FuzzyVariableType)elem.getValue();
		
		
		if(fv!=null){
			System.out.println("FUZZYVARIABLE: "+fv.getName());
			
			List<FuzzyTermType> terms = fv.getFuzzyTerms();
			
			for(int j=0;j<terms.size();j++){
				FuzzyTermType t = terms.get(j);
				System.out.println("    FUZZYTERM "+(j+1)+": "+t.getName());
			}
		}
	}*/

	public void createTipperFuzzySystemExample(File output){
		FuzzySystemType tipper = new FuzzySystemType("tipper");
		
		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		tipper.setKnowledgeBase(kb);
					
		
		//FUZZY VARIABLE food
		FuzzyVariableType food = new FuzzyVariableType("food",0,10);
		
		//FUZZY TERM delicious
		FuzzyTermType delicious = new FuzzyTermType("delicious", FuzzyTermType.TYPE_leftLinearShape, (new float[]{5.5f,10f}));
		food.addFuzzyTerm(delicious);
		
		//FUZZY TERM rancid
		FuzzyTermType rancid = new FuzzyTermType("rancid", FuzzyTermType.TYPE_triangularShape, (new float[]{0f,2f,5.5f}));
		food.addFuzzyTerm(rancid);
		
		kb.addVariable(food);
		
		//FUZZY VARIABLE service
		FuzzyVariableType service = new FuzzyVariableType("service",0,10);
		
		//FUZZY TERM excellent
		FuzzyTermType excellent = new FuzzyTermType("excellent", FuzzyTermType.TYPE_leftGaussianShape, (new float[]{10f,2f}));
		service.addFuzzyTerm(excellent);
		//FUZZY TERM good
		FuzzyTermType good = new FuzzyTermType("good", FuzzyTermType.TYPE_piShape, (new float[]{5f,4f}));
		service.addFuzzyTerm(good);
		//FUZZY TERM poor
		FuzzyTermType poor = new FuzzyTermType("poor", FuzzyTermType.TYPE_rightGaussianShape, (new float[]{0f,2f}));
		service.addFuzzyTerm(poor);
		
		kb.addVariable(service);
		
		//FUZZY VARIABLE tip
		FuzzyVariableType tip = new FuzzyVariableType("tip",0,20);
		tip.setDefaultValue(0f);
		tip.setAccumulation("MAX");
		tip.setDefuzzifierName("COG");
		tip.setType("output");
		
		//FUZZY TERM average
		FuzzyTermType average = new FuzzyTermType("average", FuzzyTermType.TYPE_triangularShape, (new float[]{5f,10f,15f}));
		tip.addFuzzyTerm(average);
		//FUZZY TERM cheap
		FuzzyTermType cheap = new FuzzyTermType("cheap", FuzzyTermType.TYPE_triangularShape, (new float[]{0f,5f,10f}));
		tip.addFuzzyTerm(cheap);
		//FUZZY TERM generous
		FuzzyTermType generous = new FuzzyTermType("generous", FuzzyTermType.TYPE_triangularShape, (new float[]{10f,15f,20f}));
		tip.addFuzzyTerm(generous);
		
		kb.addVariable(tip);
			
		
		//RULE BASE
		
		RuleBaseType fr = new RuleBaseType("rulebase1",FuzzySystemRuleBaseType.TYPE_MAMDANI);
		
		//RULE 1
		FuzzyRuleType reg1 = new FuzzyRuleType("reg1","or","MIN","MAX",1.0f);
		
		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(food, rancid));
		ant1.addClause(new ClauseType(service, poor, "very"));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(tip, cheap);
		reg1.setAntecedent(ant1);
		reg1.setConsequent(con1);
		
		fr.addRule(reg1);
		
		//RULE 2
		FuzzyRuleType reg2 = new FuzzyRuleType("reg2","or","MIN","MAX",1.0f);
		
		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(service, good));	
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(tip, average);
		reg2.setAntecedent(ant2);
		reg2.setConsequent(con2);
		fr.addRule(reg2);
		
		//RULE 3
		FuzzyRuleType reg3 = new FuzzyRuleType("reg3","or","MIN","MAX",1.0f);
		
		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(service, excellent));	
		ant3.addClause(new ClauseType(food, delicious));	
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(tip, generous);
		reg3.setAntecedent(ant3);
		reg3.setConsequent(con3);
		fr.addRule(reg3);
		
		tipper.addRuleBase(fr);

		writeFSTtoXML(tipper, output);
	}
	

	public void createTipperFuzzySystemExample1(File output){
		FuzzySystemType tipper = new FuzzySystemType("tipper");
		
		//KNOWLEDGE BASE
		KnowledgeBaseType kb = new KnowledgeBaseType();
		tipper.setKnowledgeBase(kb);
					
		
		//FUZZY VARIABLE food
		FuzzyVariableType food = new FuzzyVariableType("food",0,10);
		
		//FUZZY TERM delicious
		FuzzyTermType delicious = new FuzzyTermType("delicious", FuzzyTermType.TYPE_rightLinearShape, (new float[]{5.5f,10f}));
		food.addFuzzyTerm(delicious);
		
		//FUZZY TERM rancid
		FuzzyTermType rancid = new FuzzyTermType("rancid", FuzzyTermType.TYPE_triangularShape, (new float[]{0f,2f,5.5f}));
		food.addFuzzyTerm(rancid);
		
		kb.addVariable(food);
		
		//FUZZY VARIABLE service
		FuzzyVariableType service = new FuzzyVariableType("service",0,10);
		
		//FUZZY TERM excellent
		FuzzyTermType excellent = new FuzzyTermType("excellent", FuzzyTermType.TYPE_rightLinearShape, (new float[]{10f,2f}));
		service.addFuzzyTerm(excellent);
		//FUZZY TERM good
		FuzzyTermType good = new FuzzyTermType("good", FuzzyTermType.TYPE_gaussianShape, (new float[]{5f,4f}));
		service.addFuzzyTerm(good);
		//FUZZY TERM poor
		FuzzyTermType poor = new FuzzyTermType("poor", FuzzyTermType.TYPE_leftLinearShape, (new float[]{0f,2f}));
		service.addFuzzyTerm(poor);
		
		kb.addVariable(service);
		
		//FUZZY VARIABLE tip
		FuzzyVariableType tip = new FuzzyVariableType("tip",0,20);
		tip.setDefaultValue(0f);
		tip.setAccumulation("MAX");
		tip.setDefuzzifierName("COG");
		tip.setType("output");
		
		//FUZZY TERM average
		FuzzyTermType average = new FuzzyTermType("average", FuzzyTermType.TYPE_triangularShape, (new float[]{5f,10f,15f}));
		tip.addFuzzyTerm(average);
		//FUZZY TERM cheap
		FuzzyTermType cheap = new FuzzyTermType("cheap", FuzzyTermType.TYPE_triangularShape, (new float[]{0f,5f,10f}));
		tip.addFuzzyTerm(cheap);
		//FUZZY TERM generous
		FuzzyTermType generous = new FuzzyTermType("generous", FuzzyTermType.TYPE_triangularShape, (new float[]{10f,15f,20f}));
		tip.addFuzzyTerm(generous);
		
		kb.addVariable(tip);
			
		
		//RULE BASE
		
		RuleBaseType fr = new RuleBaseType("rulebase1",FuzzySystemRuleBaseType.TYPE_MAMDANI);
		
		//RULE 1
		FuzzyRuleType reg1 = new FuzzyRuleType("reg1","or","MIN","MAX",1.0f);
		
		AntecedentType ant1 = new AntecedentType();
		ant1.addClause(new ClauseType(food, rancid));
		ant1.addClause(new ClauseType(service, poor, "very"));
		ConsequentType con1 = new ConsequentType();
		con1.addThenClause(tip, cheap);
		reg1.setAntecedent(ant1);
		reg1.setConsequent(con1);
		
		fr.addRule(reg1);
		
		//RULE 2
		FuzzyRuleType reg2 = new FuzzyRuleType("reg2","or","MIN","MAX",1.0f);
		
		AntecedentType ant2 = new AntecedentType();
		ant2.addClause(new ClauseType(service, good));	
		ConsequentType con2 = new ConsequentType();
		con2.addThenClause(tip, average);
		reg2.setAntecedent(ant2);
		reg2.setConsequent(con2);
		fr.addRule(reg2);
		
		//RULE 3
		FuzzyRuleType reg3 = new FuzzyRuleType("reg3","or","MIN","MAX",1.0f);
		
		AntecedentType ant3 = new AntecedentType();
		ant3.addClause(new ClauseType(service, excellent));	
		ant3.addClause(new ClauseType(food, delicious));	
		ConsequentType con3 = new ConsequentType();
		con3.addThenClause(tip, generous);
		reg3.setAntecedent(ant3);
		reg3.setConsequent(con3);
		fr.addRule(reg3);
		
		tipper.addRuleBase(fr);

		writeFSTtoXML(tipper, output);
	}
	

	public static void main(String[] args) {
		XMLReadWriteExample example = new XMLReadWriteExample();
		
		//EXAMPLE - READ A FLS SYSTEM, PRINT, MODIFY AND SAVE IT IN A XML FILE
		//example.exampleReadWriteFromXML();
		
		
		//EXAMPLE - CREATE A TIPPER FUZZYSYSTEM AND SAVE IN A XML FILE
		File tipperExample = new File("./XMLFiles/TipperExampleFuzzySystemOUT2.xml");
		//example.createTipperFuzzySystemExample1(tipperExample);
		
		//EXAMPLE - READ A FUZZYSYSTEM FROM A XML FILE AND PRINT IT
		example.readFSTfromXML(tipperExample,true);
	}

}
