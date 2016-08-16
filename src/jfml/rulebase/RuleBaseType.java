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

import jfml.defuzzifier.DefuzzifierContinuous;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentClausesType;
import jfml.rule.FuzzyRuleType;
import jfml.term.FuzzyTermType;

/**
 * <p>Java class for ruleBaseType complex type.
 * 
 * <pre>
 * &lt;complexType name="ruleBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rule" type="{http://www.ieee1855.org}fuzzyRuleType" maxOccurs="unbounded"/>
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
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ruleBaseType", propOrder = {
    "rules"
})
public class RuleBaseType extends FuzzySystemRuleBaseType{

    @XmlElement(required = true, name="rule")
    protected List<FuzzyRuleType> rules;
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
    public RuleBaseType(){
    	
    }

    /**
     * Constructor using name. Rest elements by default
     * @param name
     * @param type
     */
    public RuleBaseType(String name, int type){
    	super();
    	setName(name);
    	setRuleBaseSystemType(type);
    	setActivationMethod(FuzzySystemRuleBaseType.defaultActivationMethod);
    	setAndMethod(FuzzySystemRuleBaseType.defaultAndMethod);
    	setOrMethod(FuzzySystemRuleBaseType.defaultOrMethod);
    	//setNetworkAddress(FuzzySystemRuleBaseType.defaultNetworkAddress);
    }
    
    /**
     * 
     * @param name
     * @param activation
     * @param and
     * @param or
     */
    public RuleBaseType(String name, String activation, String and, String or, int type){
    	super();
    	setName(name);
    	setActivationMethod(activation);
    	setAndMethod(and);
    	setOrMethod(or);
    	setRuleBaseSystemType(type);
    	//setNetworkAddress(FuzzySystemRuleBaseType.defaultNetworkAddress);
    }
    
    /**
     * Gets the value of the rule property.
     * 
     * Objects of the following type(s) are allowed in the list
     * {@link FuzzyRuleType }
     * 
     * 
     */
    public List<FuzzyRuleType> getRules() {
        if (rules == null) {
            rules = new ArrayList<FuzzyRuleType>();
        }
        return this.rules;
    }
    
    public void addRule(FuzzyRuleType rule){
    	if (rules == null) {
            rules = new ArrayList<FuzzyRuleType>();
        }
    	if(rule!=null)
    		rules.add(rule);
    }

    /**
     * Gets the value of the property name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the property name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the property activationMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationMethod() {
        if (activationMethod == null) {
            return FuzzySystemRuleBaseType.defaultActivationMethod;
        } else {
            return activationMethod;
        }
    }

    /**
     * Sets the value of the property activationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationMethod(String value) {
        this.activationMethod = value;
    }

    /**
     * Gets the value of the property andMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAndMethod(String value) {
        this.andMethod = value;
    }

    /**
     * Gets the value of the property orMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrMethod(String value) {
        this.orMethod = value;
    }

    /**
     * Gets the value of the property networkAddress.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkAddress(String value) {
        this.networkAddress = value;
    }
	
	public void evaluate() {
		//evaluate each rule
		for(FuzzyRuleType r : getRules()){
			float value = -1;
			//evaluate antecedents
			value = evaluateAntecedents(r);
			
			// Apply weight - the importance of the rule to be used by the inference engine
			value *= r.getWeight();
			
			// Activation rule consequents: Apply evaluation to consequent linguisticTerms
			implyConsequents(r, value);
			
			// sets the evaluation value to the rule r
			r.setEvaluation(value);
		}
	}
	
	private void implyConsequents(FuzzyRuleType r, float ant_evaluation) {
		ConsequentClausesType then = r.getConsequent().getThen();
		ConsequentClausesType _else = r.getConsequent().getElse();
		if(then!=null){
			ClauseType c = then.getClause().get(0);
			FuzzyTermType t=null;
			FuzzyVariableType v=null;
			if(c!=null && c.getTerm() instanceof FuzzyTermType)
				t = (FuzzyTermType) c.getTerm();
			if(c.getVariable() instanceof FuzzyVariableType)
				v = (FuzzyVariableType) c.getVariable();
			
			activation(v,t,ant_evaluation);
			
			if(_else!=null){
				c = _else.getClause().get(0);
				if(c!=null && c.getTerm() instanceof FuzzyTermType)
					t = (FuzzyTermType) c.getTerm();
				if(c.getVariable() instanceof FuzzyVariableType)
					v = (FuzzyVariableType) c.getVariable();
				
				activation(v,t,(1-ant_evaluation));
			}
		}
	}

	private void activation(FuzzyVariableType v, FuzzyTermType t, float ant_evaluation) {
		DefuzzifierContinuous defuzzifier = (DefuzzifierContinuous) v.getDefuzzifier();
		float membership, y, x, aggregated = 0;

		x = defuzzifier.getMin();
		double step = defuzzifier.getStepSize();

		int i, length = defuzzifier.getLength();

		// Add membership degrees function to deffuzifier
		for (i = 0; i < length; i++, x += step) {
			membership = t.getMembershipValue(x);

			//IMPLICATION (activation process)
			y = activation(ant_evaluation, membership);

			// ACCUMULATION
			aggregated = v.accumulation(defuzzifier.getValue(i), y);
			defuzzifier.setValue(i, aggregated);
		}
	}
	
	

	private float evaluateAntecedents(FuzzyRuleType r){
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
	
	/**
	    - MIN for implementing the implication with the minimum as defined from Equation (A.28);
		- PROD for implementing the implication with the product as defined from Equation (A.29);
		- BDIF for implementing the implication with bounded difference as defined from Equation (A.30);
		- DRP for implementing the implication with the drastic product as defined from Equation (A.31);
		- EPROD for implementing the implication with the Einstein product as defined from Equation (A.32);
		- HPROD for implementing the implication with the Hamacher product as defined from Equation (A.33);
		- NILMIN for implementing the implication with the Nilpotent minimum as defined from Equation (A.34); 
		- custom_\S* for a custom implication method.
	 * @param x degree of antecedent
	 * @param y degree of consequent
	 * @return The degree of membership of the consequent part according to the activation method {@link getActivationMethod()}
	 */
	private float activation(float x, float y){
		String act=getActivationMethod();
		if(act.equals("MIN"))
			return min(x,y);
		else if(act.equals("PROD"))
			return prod(x,y);
		else if(act.equals("BDIF"))
			return bdif(x,y);
		else if(act.equals("DRP"))
			return drp(x,y);
		else if(act.equals("EPROD"))
			return eprod(x,y);
		else if(act.equals("HPROD"))
			return hprod(x,y);
		else if(act.equals("NILMIN"))
			return nilmin(x,y);
		else if(act.contains("custom"))
			return custom(x,y);
		else
			return min(x,y);
	}

	/**
		- custom_\S* for a custom implication method.
	 * @param x 
	 * @param y 
	 * @return
	 */
	private float custom(float x, float y) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
		- NILMIN for implementing the implication with the Nilpotent minimum as defined from Equation (A.34); 
	 * @param x 
	 * @param y 
	 * @return
	 */
	private float nilmin(float x, float y) {
		if(x+y>1)
			return Math.min(x, y);
		else
			return 0;
	}

	/**
		- HPROD for implementing the implication with the Hamacher product as defined from Equation (A.33);
	 * @param x
	 * @param y
	 * @return
	 */
	private float hprod(float x, float y) {
		return (x+y)/(x+y-(x*y));
	}

	/**
		- EPROD for implementing the implication with the Einstein product as defined from Equation (A.32);
	 * @param x
	 * @param y
	 * @return
	 */
	private float eprod(float x, float y) {
		return (x+y)/(2-(x+y-(x*y)));
	}

	/**
		- DRP for implementing the implication with the drastic product as defined from Equation (A.31);
	 * @param x
	 * @param y
	 * @return
	 */
	private float drp(float x, float y) {
		if(y==1)
			return x;
		if(x==1)
			return y;
		else
			return 0;
	}

	/**
		- BDIF for implementing the implication with bounded difference as defined from Equation (A.30);
	 * @param x
	 * @param y
	 * @return
	 */
	private float bdif(float x, float y) {
		return Math.max(0, (x+y-1));
	}

	/**
		- PROD for implementing the implication with the product as defined from Equation (A.29);
	 * @param x
	 * @param y
	 * @return
	 */
	private float prod(float x, float y) {
		return x*y;
	}

	/**
	 - MIN for implementing the implication with the minimum as defined from Equation (A.28);
	 * @param x
	 * @param y
	 * @return
	 */
	private float min(float x, float y) {
		return Math.min(x, y);
	}

	@Override
	public void reset() {
		for(FuzzyRuleType r : getRules())
			r.reset();
	}

	@Override
	public String toString() {
		String b= getRuleBaseSystemTypeName() + " - " + getName() + ": OR=" + getOrMethod() + "; AND="+getAndMethod() + "; ACTIVATION=" + getActivationMethod() + "\n";
		int numRule=1;
		for(FuzzyRuleType r : getRules())
			b += "\t RULE "+(numRule++) + ": " +r.toString() +"\n";
			
		return b;
	}
    
}
