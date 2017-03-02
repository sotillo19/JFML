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

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;

/**
 * <p>Java class for fuzzyRuleType complex type.
 * 
 * 
 * <pre>
 * &lt;complexType name="fuzzyRuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="antecedent" type="{http://www.ieee1855.org}antecedentType"/&gt;
 *         &lt;element name="consequent" type="{http://www.ieee1855.org}consequentType"/&gt;
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
@XmlType(name = "fuzzyRuleType", propOrder = {
    "antecedent",
    "consequent"
})
public class FuzzyRuleType extends Rule{

    @XmlElement(required = true)
    protected AntecedentType antecedent;
    @XmlElement(required = true)
    protected ConsequentType consequent;
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
    public FuzzyRuleType(){
    	
    }
    
    /**
     * Constructor with parameters by default
     * @param name of the Fuzzy Rule
     */
    public FuzzyRuleType(String name){
    	super();
    	setName(name);
    	setConnector(getConnector());
    	setAndMethod(getAndMethod());
    	setOrMethod(getOrMethod());
    	setWeight(getWeight());
    }
    
    /**
     * Constructor using the name of the FuzzyRule, the Antecedent {@link AntecedentType } and the Consequent {@link ConsequentType }
     * @param name the name of the FuzzyRule
     * @param ant the Antecedent {@link AntecedentType }
     * @param con the Consequent {@link ConsequentType }
     */
    public FuzzyRuleType(String name, AntecedentType ant, ConsequentType con){
    	super();
    	setName(name);
    	setConnector(getConnector());
    	setAndMethod(getAndMethod());
    	setOrMethod(getOrMethod());
    	setWeight(getWeight());
    	setAntecedent(ant);
    	setConsequent(con);
    }
    
    /**
     * Constructor using the name of the FuzzyRule, the connector, the AndMethod, the OrMethod and the weight
     * @param name name of the fuzzy rule
     * @param connector the connector used to define the logical operator aimed at connecting the different clauses in antecedent part (and/or)
     * @param andMethod the attribute andMethod is used to define the and algorithm to be used if the chosen connector is and
     * @param orMethod the attribute orMethod is used to define the or algorithm to be used if the chosen connector is or.
     * @param weight the attribute weight is used to define the importance of the rule to be used by the inference engine.
     */
	public FuzzyRuleType(String name, String connector, String andMethod, String orMethod, Float weight) {
		super();
		this.name = name;
		this.connector = connector;
		this.andMethod = andMethod;
		this.orMethod = orMethod;
		this.weight = weight;
	}
	
	/**
	 * Constructor using the name of the fuzzy rule, the connector (and / or), the connector method and the weight of the fuzzy rule
	 * @param name name of the Fuzzy rule
	 * @param connector the connector used to define the logical operator aimed at connecting the different clauses in antecedent part (and/or)
	 * @param connectorMethod the and algorithm to be used if the chosen connector is and or the or algorithm to be used if the chosen connector is or.
	 * @param weight the importance of the rule to be used by the inference engine.
	 */
	public FuzzyRuleType(String name, String connector, String connectorMethod, Float weight) {
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
	 * @param name name of the Fuzzy rule
	 * @param weight the importance of the rule to be used by the inference engine.
	 */
	public FuzzyRuleType(String name, Float weight) {
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
     * Gets the value of the property consequent.
     * 
     * @return
     *     possible object is
     *     {@link ConsequentType }
     *     
     */
    public ConsequentType getConsequent() {
        return consequent;
    }

    /**
     * Sets the value of the property consequent.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsequentType }
     *     
     */
    public void setConsequent(ConsequentType value) {
        this.consequent = value;
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
     * Return true if the And method is defined; false otherwise
     * @return true if the And method is defined; false otherwise
     */
    public boolean isAndMethodDefined(){
    	if(andMethod == null)
    		return false;
    	else
    		return true;
    }
    
    /**
     * Return true if the Or method is defined; false otherwise
     * @return true if the Or method is defined; false otherwise
     */
    public boolean isOrMethodDefined(){
    	if(orMethod == null)
    		return false;
    	else
    		return true;
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
			
			FuzzyTerm t=(FuzzyTerm) c.getTerm();
			KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
			
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
		ConsequentClausesType then = getConsequent().getThen();
		ConsequentClausesType _else = getConsequent().getElse();
		if(then!=null){
			b += " THEN ";
			for(ClauseType c : then.getClause()){
				FuzzyTerm t=(FuzzyTerm) c.getTerm();
				KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
	
				String modifier = c.getModifier();
				if(modifier!=null)
					modifier += " ";
				else
					modifier="";
				
				b += v.getName() +" IS "+ modifier + t.getName() + ", ";
			}
			
			if(_else!=null){
				b += " ELSE ";
				for(ClauseType c : _else.getClause()){
					FuzzyTerm t=(FuzzyTerm) c.getTerm();
					KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
					
					String modifier = c.getModifier();
					if(modifier!=null)
						modifier += " ";
					b += v.getName() +" IS "+ modifier + t.getName() + ", ";
				}
			}
			
			b = b.substring(0, b.length()-2);
			
			b += " [weight="+getWeight()+"]";
		}
		return b;
	}

}
