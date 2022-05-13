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
package jfml.operator;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

import jfml.enumeration.StandardOrMethodType;
import jfml.jaxb.ObjectFactory;
import jfml.term.CircularTermType;


/**
 * <p>Java class for orLogicalType complex type.
 * 
 * <pre>
 * &lt;complexType name="orLogicalType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="termName" type="{http://www.ieee1855.org}circularTermType" maxOccurs="2" minOccurs="2"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;sequence&gt;
 *           &lt;choice&gt;
 *             &lt;element name="and" type="{http://www.ieee1855.org}andLogicalType"/&gt;
 *             &lt;element name="or" type="{http://www.ieee1855.org}orLogicalType"/&gt;
 *           &lt;/choice&gt;
 *           &lt;element name="termName" type="{http://www.ieee1855.org}circularTermType"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="t-conorm" type="{http://www.ieee1855.org}orMethodType" default="MAX" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orLogicalType", propOrder = {
    "content"
})
public class OrLogicalType extends LogicalType {

    @XmlElementRefs({
        @XmlElementRef(name = "and", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "or", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "termName", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "t-conorm")
    protected String tConorm;
    
    /**
     * Default constructor
     */
    public OrLogicalType(){
    	
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link String} with the name of term2
     */
    public OrLogicalType(String term1, String term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link AndLogicalType} term2
     */
    public OrLogicalType(String term1, AndLogicalType term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link OrLogicalType} term2
     */
    public OrLogicalType(String term1, OrLogicalType term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link AndLogicalType} term1
     * @param term2 {@link AndLogicalType} term2
     */
    public OrLogicalType(AndLogicalType term1, AndLogicalType term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link AndLogicalType} term1
     * @param term2 {@link OrLogicalType} term2
     */
    public OrLogicalType(AndLogicalType term1, OrLogicalType term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link OrLogicalType} term1
     * @param term2 {@link AndLogicalType} term2
     */
    public OrLogicalType(OrLogicalType term1, AndLogicalType term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link OrLogicalType} term1
     * @param term2 {@link OrLogicalType} term2
     */
    public OrLogicalType(OrLogicalType term1, OrLogicalType term2){
    	this.tConorm="MAX";
    	this.content = (new OrLogicalType(tConorm,term1,term2)).getContent();
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link String} with the name of term2
     */
    public OrLogicalType(String tConorm, String term1, String term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeTermName(new CircularTermType(term1)));
    	content.add(ob.createOrLogicalTypeTermName(new CircularTermType(term2)));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link AndLogicalType} term2
     */
    public OrLogicalType(String tConorm, String term1, AndLogicalType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeTermName(new CircularTermType(term1)));
    	content.add(ob.createOrLogicalTypeAnd(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link OrLogicalType} term2
     */
    public OrLogicalType(String tConorm, String term1, OrLogicalType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeTermName(new CircularTermType(term1)));
    	content.add(ob.createOrLogicalTypeOr(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link AndLogicalType} term1
     * @param term2 {@link AndLogicalType} term2
     */
    public OrLogicalType(String tConorm, AndLogicalType term1, AndLogicalType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeAnd(term1));
    	content.add(ob.createOrLogicalTypeAnd(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link AndLogicalType} term1
     * @param term2 {@link OrLogicalType} term2
     */
    public OrLogicalType(String tConorm, AndLogicalType term1, OrLogicalType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeAnd(term1));
    	content.add(ob.createOrLogicalTypeOr(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link OrLogicalType} term1
     * @param term2 {@link AndLogicalType} term2
     */
    public OrLogicalType(String tConorm, OrLogicalType term1, AndLogicalType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeOr(term1));
    	content.add(ob.createOrLogicalTypeAnd(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 {@link OrLogicalType} term1
     * @param term2 {@link OrLogicalType} term2
     */
    public OrLogicalType(String tConorm, OrLogicalType term1, OrLogicalType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrLogicalTypeOr(term1));
    	content.add(ob.createOrLogicalTypeOr(term2));
    }

    /**
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link OrLogicalType }{@code >}
     * {@link JAXBElement }{@code <}{@link CircularTermType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndLogicalType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

    /**
     * Gets the value of the property tConorm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTConorm() {
        if (tConorm == null) {
            return "MAX";
        } else {
            return tConorm;
        }
    }

    /**
     * Sets the value of the property tConorm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTConorm(String value) {
        this.tConorm = value;
    }

	@Override
	public String getOperator() {
		return getTConorm();
	}
	
	@Override
	public Object getContent(int i) {
		if(i==0 || i==1)
			return getContent().get(i).getValue();
		else
			return null;
	}

	
	/**
	 * 
	 * @param x value1
	 * @param y value2
	 * 	- MAX for implementing the connector or with the maximum as defined from Equation (A.21);
		- PROBOR for implementing the connector or with the probabilistic sum as defined from Equation (A.22);
		- BSUM for implementing the operator or with the bounded sum as defined from Equation (A.23);
		- DRS for implementing the operator or with the drastic sum as defined from Equation (A.24);
		- ESUM for implementing the operator or with the Einstein sum as defined from Equation (A.25);
		- HSUM for implementing the operator or with the Hamacher sum as defined from Equation (A.26);
		- NILMAX for implementing the operator or with the Nilpotent maximum as defined from Equation (A.27);
		- custom_\S* for a custom method for implementing the connector or.
	 * @return result of orMethod
	 */
	@Override
	public float operate(float x, float y){
		String orMethod = getOperator();
		if(orMethod.equals(StandardOrMethodType.MAX.value()))
			return max(x,y);
		else if(orMethod.equals(StandardOrMethodType.PROBOR.value()))
			return probor(x,y);
		else if(orMethod.equals(StandardOrMethodType.BSUM.value()))
			return bsum(x,y);
		else if(orMethod.equals(StandardOrMethodType.DRS.value()))
			return drs(x,y);
		else if(orMethod.equals(StandardOrMethodType.ESUM.value()))
			return esum(x,y);
		else if(orMethod.equals(StandardOrMethodType.HSUM.value()))
			return hsum(x,y);
		else if(orMethod.equals(StandardOrMethodType.NILMAX.value()))
			return nilmax(x,y);
		else if(orMethod.contains("custom"))
			return custom_or(x,y,orMethod);
		else 
			return max(x,y);
	
	}
	

	/**
	 * custom or method
	 * @param x value1
	 * @param y value2
	 * @param orMethod
	 * @return custom or method
	 */
	private float custom_or(float x, float y, String orMethod) {
		// TODO Auto-generated method stub
		return 0;
	}



    /**
     *
	 *	- NILMAX for implementing the operator or with the Nilpotent maximum as defined from Equation (A.27);
     * @param a
     * @param b
     * @return
     */
	private float nilmax(float a, float b) {
		if(a+b<1)
			return Math.max(a, b);
		else
			return 1;
	}



	/** 	
	 *	- HSUM for implementing the operator or with the Hamacher sum as defined from Equation (A.26);
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private float hsum(float a, float b) {
		return (a+b-(2*a*b))/(1-(a*b));
	}



	/**
	 * 
	 *	- ESUM for implementing the operator or with the Einstein sum as defined from Equation (A.25);
	 * @param a
	 * @param b
	 * @return
	 */
	private float esum(float a, float b) {
		return (a+b)/(1+a*b);
	}




	/**
	 * 
	 * - DRS for implementing the operator or with the drastic sum as defined from Equation (A.24);
	 * @param a
	 * @param b
	 * @return
	 */
	private float drs(float a, float b) {
		if(a==0)
			return b;
		if(b==0)
			return a;
		else
			return 1;
	}




	/**
	 * 
	 *	- BSUM for implementing the operator or with the bounded sum as defined from Equation (A.23);
	 * @param a
	 * @param b
	 * @return
	 */
	private float bsum(float a, float b) {
		return Math.min(1, a+b);
	}



	/**
	 *	- PROBOR for implementing the connector or with the probabilistic sum as defined from Equation (A.22);
	 * @param a
	 * @param b
	 * @return
	 */
	private float probor(float a, float b) {
		return a+b-(a*b);
	}


	/**
	 *  - MAX for implementing the connector or with the maximum as defined from Equation (A.21);
	 * @param x
	 * @param y
	 * @return
	 */
	private float max(float x, float y) {
		return Math.max(x, y);
	}

}
