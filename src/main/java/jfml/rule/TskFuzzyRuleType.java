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
 * &lt;complexType name="tskFuzzyRuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="antecedent" type="{http://www.ieee1855.org}antecedentType"/&gt;
 *         &lt;element name="tskConsequent" type="{http://www.ieee1855.org}tskConsequentType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="andMethod" type="{http://www.ieee1855.org}andMethodType" default="MIN" /&gt;
 *       &lt;attribute name="orMethod" type="{http://www.ieee1855.org}orMethodType" default="MAX" /&gt;
 *       &lt;attribute name="connector" type="{http://www.ieee1855.org}connectorType" default="and" /&gt;
 *       &lt;attribute name="weight" type="{http://www.ieee1855.org}weightType" default="1.0" /&gt;
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
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
     * @param name name of the TSK Fuzzy Rule
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
     * Constructor using the name of the FuzzyRule, the Antecedent {@link AntecedentType } and the TSK Consequent {@link TskConsequentType }
     * @param name the name of the FuzzyRule
     * @param ant the Antecedent {@link AntecedentType }
     * @param con the Consequent {@link TskConsequentType }
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
     * Constructor using the name of the TSK FuzzyRule, the connector, the AndMethod, the OrMethod and the weight
     * @param name name of the TSK fuzzy rule
     * @param connector the connector used to define the logical operator aimed at connecting the different clauses in antecedent part (and/or)
     * @param andMethod the attribute andMethod is used to define the and algorithm to be used if the chosen connector is and
     * @param orMethod the attribute orMethod is used to define the or algorithm to be used if the chosen connector is or.
     * @param weight the attribute weight is used to define the importance of the rule to be used by the inference engine.
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
	 * Constructor using the name of the TSK fuzzy rule, the connector (and / or), the connector method and the weight of the fuzzy rule
	 * @param name name of the TSK Fuzzy rule
	 * @param connector the connector used to define the logical operator aimed at connecting the different clauses in antecedent part (and/or)
	 * @param connectorMethod the and algorithm to be used if the chosen connector is and or the or algorithm to be used if the chosen connector is or.
	 * @param weight the importance of the rule to be used by the inference engine.
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
	 * Constructor using the name and the weight of the Fuzzy Rule
	 * @param name name of the TSK Fuzzy rule
	 * @param weight the importance of the rule to be used by the inference engine.
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
    
    public boolean isAndMethodDefined(){
    	if(andMethod == null)
    		return false;
    	else
    		return true;
    }
    
    public boolean isOrMethodDefined(){
    	if(orMethod == null)
    		return false;
    	else
    		return true;
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
