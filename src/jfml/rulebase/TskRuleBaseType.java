package jfml.rulebase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.TskVariableType;
import jfml.rule.ClauseType;
import jfml.rule.TskClauseType;
import jfml.rule.TskConsequentClausesType;
import jfml.rule.TskFuzzyRuleType;
import jfml.term.FuzzyTermType;
import jfml.term.TskTermType;

/**
 * <p>
 * Java class for tskRuleBaseType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskRuleBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tskRule" type="{http://www.ieee1855.org}tskFuzzyRuleType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="activationMethod" type="{http://www.ieee1855.org}activationMethodType" default="MIN" />
 *       &lt;attribute name="andMethod" type="{http://www.ieee1855.org}andMethodType" default="MIN" />
 *       &lt;attribute name="orMethod" type="{http://www.ieee1855.org}orMethodType" default="MAX" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tskRuleBaseType", propOrder = { "tskRules" })
public class TskRuleBaseType extends FuzzySystemRuleBase {

	@XmlElement(required = true, name = "tskRule")
	protected List<TskFuzzyRuleType> tskRules;
	@XmlAttribute(name = "name", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String name;
	@XmlAttribute(name = "activationMethod")
	protected String activationMethod;
	@XmlAttribute(name = "andMethod")
	protected String andMethod;
	@XmlAttribute(name = "orMethod")
	protected String orMethod;
	@XmlAttribute(name = "networkAddress")
	protected String networkAddress;
	
	/**
	 * Default constructor
	 */
	public TskRuleBaseType(){
		setRuleBaseSystemType(FuzzySystemRuleBase.TYPE_TSK);
	}
	
	/**
     * Constructor using name. Rest elements by default
     * @param name name of the TSK rule base
     * @param type the ruleBaseSystemType 
     */
    public TskRuleBaseType(String name, int type){
    	super();
    	setName(name);
    	setRuleBaseSystemType(type);
    	setActivationMethod(FuzzySystemRuleBase.defaultActivationMethod);
    	setAndMethod(FuzzySystemRuleBase.defaultAndMethod);
    	setOrMethod(FuzzySystemRuleBase.defaultOrMethod);
    	//setNetworkAddress(FuzzySystemRuleBaseType.defaultNetworkAddress);
    }
    
    /**
	 * Constructor using the name, the activation method, the and, the or and the type
	 * @param name name of the TSK rule base
	 * @param activation the method used for the implication process according to {@link StandardActivationMethodType }
	 * @param and the and algorithm to be used
	 * @param or the or algorithm to be used
	 * @param type the ruleBaseSystemType
	 */
    public TskRuleBaseType(String name, String activation, String and, String or, int type){
    	super();
    	setName(name);
    	setActivationMethod(activation);
    	setAndMethod(and);
    	setOrMethod(or);
    	setRuleBaseSystemType(type);
    	//setNetworkAddress(FuzzySystemRuleBaseType.defaultNetworkAddress);
    }

	/**
	 * Gets the value of the tskRule property.
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TskFuzzyRuleType }
	 * 
	 * 
	 */
	public List<TskFuzzyRuleType> getTskRules() {
		if (tskRules == null) {
			tskRules = new ArrayList<TskFuzzyRuleType>();
		}
		return this.tskRules;
	}
	
	/**
	 * Adds a TskFuzzyRuleType to the list of rules
     * @param rule the TskFuzzyRuleType
	 */
	public void addTskRule(TskFuzzyRuleType rule){
    	if (tskRules == null) {
    		tskRules = new ArrayList<TskFuzzyRuleType>();
        }
    	if(rule!=null)
    		tskRules.add(rule);
    }

	/**
	 * Gets the value of the property name.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the property name.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the property activationMethod.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getActivationMethod() {
		if (activationMethod == null) {
			return "MIN";
		} else {
			return activationMethod;
		}
	}

	/**
	 * Sets the value of the property activationMethod.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setActivationMethod(String value) {
		this.activationMethod = value;
	}

	/**
	 * Gets the value of the property andMethod.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAndMethod() {
		if (andMethod == null) {
			return "MIN";
		} else {
			return andMethod;
		}
	}

	/**
	 * Sets the value of the property andMethod.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAndMethod(String value) {
		this.andMethod = value;
	}

	/**
	 * Gets the value of the property orMethod.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrMethod() {
		if (orMethod == null) {
			return "MAX";
		} else {
			return orMethod;
		}
	}

	/**
	 * Sets the value of the property orMethod.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrMethod(String value) {
		this.orMethod = value;
	}

	/**
	 * Gets the value of the property networkAddress.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNetworkAddress() {
		if (networkAddress == null) {
			return "127.0.0.1";
		} else {
			return networkAddress;
		}
	}

	/**
	 * Sets the value of the property networkAddress.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNetworkAddress(String value) {
		this.networkAddress = value;
	}

	@Override
	public void evaluate() {
		// evaluate each rule
		for (TskFuzzyRuleType r : getTskRules()) {
			//if rule r has not defined and / or method, set the rule base and/or method
			if(!r.isAndMethodDefined())
				r.setAndMethod(getAndMethod());
			
			if(!r.isOrMethodDefined())
				r.setOrMethod(getOrMethod());
			
			float w = Float.NaN;
			// evaluate antecedents
			w = evaluateAntecedents(r);

			// Apply weight - the importance of the rule to be used by the inference engine
			w *= r.getWeight();

			// Activation rule consequents: Apply evaluation to consequent linguisticTerms
			implyConsequents(r, w);

			// sets the evaluation value to the rule r
			r.setEvaluation(w);
		}
	}
	
	private float evaluateAntecedents(TskFuzzyRuleType r){
		List<ClauseType> clauses = r.getAntecedent().getClauses();
		float[] degrees = new float[clauses.size()];
		for(int i=0;i<clauses.size();i++){
			ClauseType c= clauses.get(i);
			FuzzyTermType t=null;
			FuzzyVariableType v=null;
			if(c!=null && c.getTerm() instanceof FuzzyTermType)
				t = (FuzzyTermType) c.getTerm();
			if(c.getVariable() instanceof FuzzyVariableType)
				v = (FuzzyVariableType) c.getVariable();
				
			if(t!=null && v!=null)
				degrees[i] = c.modifierMembershipDegree(t.getMembershipValue(v.getValue()));
		}
		
		//aggregate degrees (connector operator)
		return r.aggregation(degrees);
	}
	
	private void implyConsequents(TskFuzzyRuleType r, float ant_evaluation) {
		TskConsequentClausesType then = r.getTskConsequent().getTskThen();
		TskConsequentClausesType _else = r.getTskConsequent().getTskElse();
		if(then!=null){
			for(TskClauseType c : then.getTskClause()){
				TskTermType t=null;
				TskVariableType v=null;
				if(c!=null && c.getTerm() instanceof TskTermType)
					t = (TskTermType) c.getTerm();
				if(c.getVariable() instanceof TskVariableType)
					v = (TskVariableType) c.getVariable();
				
				activationTSK(v,t,ant_evaluation);
			}
			
			if(_else!=null){
				for(TskClauseType c : _else.getTskClause()){
					TskTermType t=null;
					TskVariableType v=null;
					if(c!=null && c.getTerm() instanceof TskTermType)
						t = (TskTermType) c.getTerm();
					if(c.getVariable() instanceof TskVariableType)
						v = (TskVariableType) c.getVariable();
					
					activationTSK(v,t,(1-ant_evaluation));
				}
			}
		}
	}


	private void activationTSK(TskVariableType v, TskTermType t, float ant_evaluation) {
		if(Float.isNaN(t.getEvaluation()))
			t.evaluateTskTerm(v.getInputVariables());
			
		v.addEvaluation(ant_evaluation,t.getEvaluation());
	}

	@Override
	public void reset() {
		for (TskFuzzyRuleType r : getTskRules())
			r.reset();
	}

	@Override
	public String toString() {
		String b="  *"+ getRuleBaseSystemTypeName() + " - " + getName() + ": OR=" + getOrMethod() + "; AND="+getAndMethod() + "; ACTIVATION=" + getActivationMethod() + "\n";
		int numRule=1;
		for(TskFuzzyRuleType r : getTskRules())
			b += "\tRULE "+(numRule++) + ": " +r.toString() +"\n";
			
		return b;
	}

}
