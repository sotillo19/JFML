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
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;
import jfml.term.FuzzyTermType;


/**
 * <p>Java class for clauseType complex type.
 *  
 * <pre>
 * &lt;complexType name="clauseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="variable" type="{http://www.w3.org/2001/XMLSchema}IDREF"/&gt;
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}IDREF"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="modifier" type="{http://www.ieee1855.org}modifierType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clauseType", propOrder = {
    "variable",
    "term"
})
public class ClauseType {

    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object variable;
    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object term;
    
    @XmlAttribute(name = "modifier")
    /**
     * The attribute modifier is used to describe a modification to the linguistic term used in the clause
     */
    protected String modifier;
    
    /**
     * Default constructor
     */
    public ClauseType(){
    	super();
    }
    
    /**
     * Constructor using a variable and a term
     * @param variable possible object is {@link KnowledgeBaseVariable } 
     * @param term possible object is {@link FuzzyTerm }
     */
    public ClauseType(Object variable, Object term){
    	super();
    	this.setVariable(variable);
    	this.setTerm(term);
    }
    
    /**
     * Constructor using a variable, a term and the modifier
     * @param variable possible object is {@link KnowledgeBaseVariable } 
     * @param term possible object is {@link FuzzyTerm }
     * @param modifier a String with the modifier according to {@link StandardModifierType }
     */
    public ClauseType(Object variable, Object term, String modifier){
    	this(variable,term);
    	this.setModifier(modifier);
    }

    /**
     * Gets the value of the property variable.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVariable() {
        return variable;
    }

    /**
     * Sets the value of the property variable.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVariable(Object value) {
        this.variable = value;
    }

    /**
     * Gets the value of the property term.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTerm() {
        return term;
    }

    /**
     * Sets the value of the property term.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTerm(Object value) {
        this.term = value;
    }

    /**
     * Gets the value of the property modifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Sets the value of the property modifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifier(String value) {
        this.modifier = value;
    }
    
    /**
     * Apply a modification to the membership degree x according to modifier property
     *
     * @param x degree
     * @return a modification of the membership degree
     */
    public float modifierMembershipDegree(float x){
    	String m = getModifier();
    	if(m!=null){
    		return modification(m,x);
    	}
    	else
    		return x;
    }
    
    
    /**
     * 
     * @param m modifier
     *  - above for the hedge above as defined from Equation (A.1);
		- any for the hedge any as defined from Equation (A.2);
		- below for the hedge below as defined from Equation (A.3);
		- extremely for the hedge extremely as defined from Equation (A.4);
		- intensify for the hedge intensify as defined from Equation (A.5);
		- more_or_less for the hedge more or less as defined from Equation (A.6);
		- norm for the hedge norm as defined from Equation (A.7);
		- not for the hedge not as defined from Equation (A.8);
		- plus for the hedge plus as defined from Equation (A.9);
		- seldom for the hedge seldom as defined from Equation (A.10);
		- slightly for the hedge slightly as defined from Equation (A.11);
		- somewhat for the hedge somewhat as defined from Equation (A.12);
		- very for the hedge very as defined from Equation (A.13);
		- custom_\S* for a custom hedge.
     * @param x membership degree
     * @return a modification of the membership degree according to the m modifier
     */
    private float modification(String m, float x){
       	if(m.equals("above"))
    		return modifierAbobe(x);
    	else if(m.equals("any"))
    		return modifierAny(x);
    	else if(m.equals("below"))
    		return modifierBelow(x);
    	else if(m.equals("extremely"))
    		return modifierExtremely(x);
    	else if(m.equals("intensify"))
    		return modifierIntensify(x);
    	else if(m.equals("more_or_less"))
    		return modifierMore_or_less(x);
    	else if(m.equals("norm"))
    		return modifierNorm(x);
    	else if(m.equals("not"))
    		return modifierNot(x);
    	else if(m.equals("plus"))
    		return modifierPlus(x);
    	else if(m.equals("seldom"))
    		return modifierSeldom(x);
    	else if(m.equals("slightly"))
    		return modifierSlightly(x);
    	else if(m.equals("somewhat"))
    		return modifierSomewhat(x);
    	else if(m.equals("very"))
    		return modifierVery(x);
    	else if(m.contains("custom"))
    		return modifierCustom(x);
    	else
    		return -1;
    	
    }

    /**
     * - custom_\S* for a custom hedge.
     * @param x
     * @return
     */
	private float modifierCustom(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * - very for the hedge very as defined from Equation (A.13);
	 * 
	 * @param x
	 * @return
	 */
	private float modifierVery(float x) {
		return (float) Math.pow(x, 2);
	}

	/**
	 * 	- somewhat for the hedge somewhat as defined from Equation (A.12);
	 * @param x
	 * @return
	 */
	private float modifierSomewhat(float x) {
		return (float) Math.pow(x, 1/2);
	}

	/**
	 * - slightly for the hedge slightly as defined from Equation (A.11);
	 * @param x
	 * @return
	 */
	private float modifierSlightly(float x) {
		return (modifierIntensify(modifierNorm(modifierPlus(x)*modifierNot(modifierVery(x)))));
	}

	/**
	 * - seldom for the hedge seldom as defined from Equation (A.10);
	 * @param x
	 * @return
	 */
	private float modifierSeldom(float x) {
		if(0<=x && x<=0.5f)
			return (float) Math.pow(x/2, 1/2);
		if(0.5f<x && x<=1f)
			return (float) (1-Math.pow((1-x)/2, 1/2));
		else
			return x;
	}

	/**
	 * - plus for the hedge plus as defined from Equation (A.9);
	 * @param x
	 * @return
	 */
	private float modifierPlus(float x) {
		return (float) Math.pow(x, 5/4);
	}

	/**
	 * - not for the hedge not as defined from Equation (A.8);
	 * @param x
	 * @return
	 */
	private float modifierNot(float x) {
		return 1-x;
	}

	/**
	 * - norm for the hedge norm as defined from Equation (A.7);
	 * @param x
	 * @return
	 */
	private float modifierNorm(float x) {
		return x;
	}

	/**
	 * - more_or_less for the hedge more or less as defined from Equation (A.6);
	 * @param x
	 * @return
	 */
	private float modifierMore_or_less(float x) {
		return (float) Math.pow(x, 1/3);
	}

	/**
	 * - intensify for the hedge intensify as defined from Equation (A.5);
	 * @param x
	 * @return
	 */
	private float modifierIntensify(float x) {
		if(0<=x && x<=0.5f)
			return (float) (2*Math.pow(x, 2));
		if(0.5f<x && x<=1f)
			return 1-2*(1-x);
		else
			return x;
	}

	/**
	 * - extremely for the hedge extremely as defined from Equation (A.4);
	 * @param x
	 * @return
	 */
	private float modifierExtremely(float x) {
		return (float) Math.pow(x, 3);
	}

	/**
	 * - below for the hedge below as defined from Equation (A.3);
	 * @param x
	 * @return
	 */
	private float modifierBelow(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 	- any for the hedge any as defined from Equation (A.2);
	 * @param x
	 * @return
	 */
	private float modifierAny(float x) {
		return 1;
	}

	/**
	 * - above for the hedge above as defined from Equation (A.1);
	 * @param x
	 * @return
	 */
	private float modifierAbobe(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString(){
		
		FuzzyTermType t=null;
		FuzzyVariableType v=null;
		if(getTerm() instanceof FuzzyTermType)
			t = (FuzzyTermType) getTerm();
		if(getVariable() instanceof FuzzyVariableType)
			v = (FuzzyVariableType) getVariable();
		
		String modifier = getModifier();
		if(modifier!=null)
			modifier += " ";
		else
			modifier="";
		
		return (v.getName() +" IS "+ modifier + t.getName());
	}
}
