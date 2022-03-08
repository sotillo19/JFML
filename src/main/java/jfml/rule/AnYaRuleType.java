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
 * <p>Java class for anYaRuleType complex type.
 * 
 * <pre>
 * &lt;complexType name="anYaRuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anYaAntecedent" type="{http://www.ieee1855.org}anYaAntecedentType"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="consequent" type="{http://www.ieee1855.org}consequentType"/&gt;
 *           &lt;element name="tskConsequent" type="{http://www.ieee1855.org}tskConsequentType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
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
@XmlType(name = "anYaRuleType", propOrder = {
    "anYaAntecedent",
    "consequent",
    "tskConsequent"
})
public class AnYaRuleType extends Rule{

    @XmlElement(required = true)
    protected AnYaAntecedentType anYaAntecedent;
    protected ConsequentType consequent;
    protected TskConsequentType tskConsequent;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "weight")
    protected Float weight;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;

    /**
     * Constructor by default
     */
    public AnYaRuleType(){
    	
    }
    /**
     * Constructor with parameters by default
     * @param name name of the AnYa Rule
     */
    public AnYaRuleType(String name){
    	super();
    	setName(name);
    	setWeight(getWeight());
    }
    
    /**
     * Constructor using the name of the AnYa Rule, the Antecedent {@link AnYaAntecedentType } and the Consequent {@link ConsequentType }
     * @param name the name of the AnYa Rule
     * @param ant the AnYa Antecedent {@link AnYaAntecedentType }
     * @param con the Consequent {@link ConsequentType }
     */
    public AnYaRuleType(String name, AnYaAntecedentType ant, ConsequentType con){
    	super();
    	setName(name);
    	setWeight(getWeight());
    	setAnYaAntecedent(ant);
    	setConsequent(con);
    }
    
    /**
     * Constructor using the name of the AnYa Rule, the Antecedent {@link AnYaAntecedentType } and the TSK Consequent {@link TskConsequentType }
     * @param name the name of the AnYa Rule
     * @param ant the AnYa Antecedent {@link AnYaAntecedentType }
     * @param con the TSK Consequent {@link TskConsequentType }
     */
    public AnYaRuleType(String name, AnYaAntecedentType ant, TskConsequentType con){
    	super();
    	setName(name);
    	setWeight(getWeight());
    	setAnYaAntecedent(ant);
    	setTskConsequent(con);
    }
    


	/**
	 * Constructor using the name and the weight of the AnYa Rule
	 * @param name name of the AnYa rule
	 * @param weight the importance of the rule to be used by the inference engine.
	 */
	public AnYaRuleType(String name, Float weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
    
    
    /**
     * Gets the value of the property anYaAntecedent.
     * 
     * @return
     *     possible object is
     *     {@link AnYaAntecedentType }
     *     
     */
    public AnYaAntecedentType getAnYaAntecedent() {
        return anYaAntecedent;
    }

    /**
     * Sets the value of the property anYaAntecedent.
     * 
     * @param value
     *     allowed object is
     *     {@link AnYaAntecedentType }
     *     
     */
    public void setAnYaAntecedent(AnYaAntecedentType value) {
        this.anYaAntecedent = value;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		String b = getName() +" - ("+getEvaluation()+") IF ";
		
		//ANTECEDENTS
		AnYaAntecedentType ant = getAnYaAntecedent();
		b += ant.toString();
		
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
				
				b += v.getName() +" IS "+ modifier + t.getName() + " ";
			}
			
			if(_else!=null){
				b += " ELSE ";
				for(ClauseType c : _else.getClause()){
					FuzzyTerm t=(FuzzyTerm) c.getTerm();
					KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
					
					String modifier = c.getModifier();
					if(modifier!=null)
						modifier += " ";
					b += v.getName() +" IS "+ modifier + t.getName() + " ";
				}
			}	
			
			b += "[weight="+getWeight()+"]";
		}
		return b;
	}

}
