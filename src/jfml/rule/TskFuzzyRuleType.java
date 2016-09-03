package jfml.rule;

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
import jfml.term.FuzzyTermType;
import jfml.term.TskTermType;


/**
 * <p>Java class for tskFuzzyRuleType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskFuzzyRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="antecedent" type="{http://www.ieee1855.org}antecedentType"/>
 *         &lt;element name="tskConsequent" type="{http://www.ieee1855.org}tskConsequentType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="andMethod" type="{http://www.ieee1855.org}andMethodType" default="MIN" />
 *       &lt;attribute name="orMethod" type="{http://www.ieee1855.org}orMethodType" default="MAX" />
 *       &lt;attribute name="connector" type="{http://www.ieee1855.org}connectorType" default="and" />
 *       &lt;attribute name="weight" type="{http://www.ieee1855.org}weightType" default="1.0" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tskFuzzyRuleType", propOrder = {
    "antecedent",
    "tskConsequent"
})
public class TskFuzzyRuleType extends Rule{

    @XmlElement(required = true)
    protected AntecedentType antecedent;
    @XmlElement(required = true)
    protected TskConsequentType tskConsequent;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "andMethod")
    protected String andMethod;
    @XmlAttribute(name = "orMethod")
    protected String orMethod;
    @XmlAttribute(name = "connector")
    protected String connector;
    @XmlAttribute(name = "weight")
    protected Float weight;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;

    /**
     * Constructor by default
     */
    public TskFuzzyRuleType(){
    	
    }
    /**
     * Constructor with parameters by default
     * @param name
     */
    public TskFuzzyRuleType(String name){
    	super();
    	setName(name);
    	setConnector(getConnector());
    	setAndMethod(getAndMethod());
    	setOrMethod(getOrMethod());
    	setWeight(getWeight());
    }
    
    /**
     * Constructor with parameters by default
     * @param name
     */
    public TskFuzzyRuleType(String name, AntecedentType ant, TskConsequentType con){
    	super();
    	setName(name);
    	setConnector(getConnector());
    	setAndMethod(getAndMethod());
    	setOrMethod(getOrMethod());
    	setWeight(getWeight());
    	setAntecedent(ant);
    	setTskConsequent(con);
    }
    
    /**
	 * @param name
	 * @param connector
	 * @param andMethod
	 * @param orMethod
	 * @param weight
	 * @param networkAddress
	 */
	public TskFuzzyRuleType(String name, String connector, String andMethod, String orMethod, Float weight) {
		super();
		this.name = name;
		this.connector = connector;
		this.andMethod = andMethod;
		this.orMethod = orMethod;
		this.weight = weight;
	}
	
	/**
	 * @param name
	 * @param connector
	 * @param andMethod
	 * @param orMethod
	 * @param weight
	 * @param networkAddress
	 */
	public TskFuzzyRuleType(String name, String connector, String connectorMethod, Float weight) {
		super();
		this.name = name;
		this.connector = connector;
		if(connector.equals("or"))
			this.orMethod = connectorMethod;
		else if(connector.equals("and"))
			this.andMethod = connectorMethod;
		this.weight = weight;
	}


	/**
	 * 
	 * @param name
	 * @param weight
	 */
	public TskFuzzyRuleType(String name, Float weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	
    /**
     * Gets the value of the property antecedent.
     * 
     * @return
     *     possible object is
     *     {@link AntecedentType }
     *     
     */
    public AntecedentType getAntecedent() {
        return antecedent;
    }

    /**
     * Sets the value of the property antecedent.
     * 
     * @param value
     *     allowed object is
     *     {@link AntecedentType }
     *     
     */
    public void setAntecedent(AntecedentType value) {
        this.antecedent = value;
    }

    /**
     * Gets the value of the property tskConsequent.
     * 
     * @return
     *     possible object is
     *     {@link TskConsequentType }
     *     
     */
    public TskConsequentType getTskConsequent() {
        return tskConsequent;
    }

    /**
     * Sets the value of the property tskConsequent.
     * 
     * @param value
     *     allowed object is
     *     {@link TskConsequentType }
     *     
     */
    public void setTskConsequent(TskConsequentType value) {
        this.tskConsequent = value;
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
     * Gets the value of the property connector.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnector() {
        if (connector == null) {
            return "and";
        } else {
            return connector;
        }
    }

    /**
     * Sets the value of the property connector.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnector(String value) {
        this.connector = value;
    }

    /**
     * Gets the value of the property weight.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public float getWeight() {
        if (weight == null) {
            return  1.0F;
        } else {
            return weight;
        }
    }

    /**
     * Sets the value of the property weight.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWeight(Float value) {
        this.weight = value;
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

	@Override
	public float aggregation(float[] degrees) {
		if(getConnector().equals("AND") || getConnector().equals("and"))
			return and(getAndMethod(),degrees);
		else
			return or(getOrMethod(),degrees);
	}

	@Override
	public String toString() {
String b = getName() +" - ("+getEvaluation()+") IF ";
		
		//ANTECEDENTS
		List<ClauseType> clauses = getAntecedent().getClauses();
		for(int i=0;i<clauses.size();i++){
			ClauseType c= clauses.get(i);
			FuzzyTermType t=null;
			FuzzyVariableType v=null;
			if(c!=null && c.getTerm() instanceof FuzzyTermType)
				t = (FuzzyTermType) c.getTerm();
			if(c.getVariable() instanceof FuzzyVariableType)
				v = (FuzzyVariableType) c.getVariable();
			
			String modifier = c.getModifier();
			if(modifier!=null)
				modifier += " ";
			else
				modifier="";
			
			b += v.getName() +" IS "+ modifier + t.getName();
			if(i<clauses.size()-1)
				b += " "+getConnector().toUpperCase() + " ";
		}
		
		//CONSEQUENTS
		TskConsequentClausesType then = getTskConsequent().getTskThen();
		TskConsequentClausesType _else = getTskConsequent().getTskElse();
		if(then!=null){
			b += " THEN ";
			for(TskClauseType c : then.getTskClause()){
				TskTermType t=null;
				TskVariableType v=null;
				if(c!=null && c.getTerm() instanceof TskTermType)
					t = (TskTermType) c.getTerm();
				if(c.getVariable() instanceof TskVariableType)
					v = (TskVariableType) c.getVariable();
				
				b += v.getName() +" IS "+ t.getName() + " ";
			}
			
			if(_else!=null){
				b += " ELSE ";
				for(TskClauseType c : _else.getTskClause()){
					FuzzyTermType t=null;
					FuzzyVariableType v=null;
					if(c!=null && c.getTerm() instanceof FuzzyTermType)
						t = (FuzzyTermType) c.getTerm();
					if(c.getVariable() instanceof FuzzyVariableType)
						v = (FuzzyVariableType) c.getVariable();
					
					b += v.getName() +" IS "+ t.getName() + " ";
				}
			}	
			
			b += "[weight="+getWeight()+"]";
		}
		return b;
	}

}
