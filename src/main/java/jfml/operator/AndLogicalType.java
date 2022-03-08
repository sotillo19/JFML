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
import jfml.enumeration.StandardAndMethodType;
import jfml.jaxb.ObjectFactory;
import jfml.term.CircularTermType;


/**
 * <p>Java class for andLogicalType complex type.
 * 
 * <pre>
 * &lt;complexType name="andLogicalType"&gt;
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
 *       &lt;attribute name="t-norm" type="{http://www.ieee1855.org}andMethodType" default="MIN" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre&gt;
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "andLogicalType", propOrder = {
    "content"
})
public class AndLogicalType extends LogicalType{

    @XmlElementRefs({
        @XmlElementRef(name = "and", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "or", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "termName", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "t-norm")
    protected String tNorm;
    
    /**
     * Default constructor
     */
    public AndLogicalType(){
    	
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link String} with the name of term2
     */
    public AndLogicalType(String term1, String term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link AndLogicalType} term2
     */
    public AndLogicalType(String term1, AndLogicalType term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link String} with the name of term1
     * @param term2 {@link OrLogicalType} term2
     */
    public AndLogicalType(String term1, OrLogicalType term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link AndLogicalType} term1
     * @param term2 {@link AndLogicalType} term2
     */
    public AndLogicalType(AndLogicalType term1, AndLogicalType term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link AndLogicalType} term1
     * @param term2 {@link OrLogicalType} term2
     */
    public AndLogicalType(AndLogicalType term1, OrLogicalType term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link OrLogicalType} term1
     * @param term2 {@link AndLogicalType} term2
     */
    public AndLogicalType(OrLogicalType term1, AndLogicalType term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link OrLogicalType} term1
     * @param term2 {@link OrLogicalType} term2
     */
    public AndLogicalType(OrLogicalType term1, OrLogicalType term2){
    	this.tNorm="MIN";
    	this.content = (new AndLogicalType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, String term1, String term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeTermName(new CircularTermType(term1)));
    	content.add(ob.createAndLogicalTypeTermName(new CircularTermType(term2)));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, String term1, AndLogicalType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeTermName(new CircularTermType(term1)));
    	content.add(ob.createAndLogicalTypeAnd(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, String term1, OrLogicalType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeTermName(new CircularTermType(term1)));
    	content.add(ob.createAndLogicalTypeOr(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, AndLogicalType term1, AndLogicalType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeAnd(term1));
    	content.add(ob.createAndLogicalTypeAnd(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, AndLogicalType term1, OrLogicalType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeAnd(term1));
    	content.add(ob.createAndLogicalTypeOr(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, OrLogicalType term1, AndLogicalType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeOr(term1));
    	content.add(ob.createAndLogicalTypeAnd(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 
     * @param term2
     */
    public AndLogicalType(String tNorm, OrLogicalType term1, OrLogicalType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndLogicalTypeOr(term1));
    	content.add(ob.createAndLogicalTypeOr(term2));
    }

    /**
     * 
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link OrLogicalType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndLogicalType }{@code >}
     * {@link JAXBElement }{@code <}{@link CircularTermType }{@code >}
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
     * Gets the value of the property tNorm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTNorm() {
        if (tNorm == null) {
            return "MIN";
        } else {
            return tNorm;
        }
    }

    /**
     * Sets the value of the property tNorm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTNorm(String value) {
        this.tNorm = value;
    }

	@Override
	public String getOperator() {
		return getTNorm();
	}

	@Override
	public Object getContent(int i) {
		if(i==0 || i==1)
			return getContent().get(i).getValue();
		else
			return null;
	}

	/**
	 * - MIN for implementing the operator and with the minimum as defined from Equation (A.14);
	 * - PROD for implementing the operator and with the product as defined from from Equation (A.15);
	 * - BDIF for implementing the operator and with bounded difference as defined from from Equation (A.16);
	 * - DRP for implementing the operator and with the drastic product as defined from from Equation (A.17);
	 * - EPROD for implementing the operator and with the Einstein product as defined from from Equation (A.18);
	 * - HPROD for implementing the operator and with the Hamacher product as defined from from Equation (A.19); 
	 * - NILMIN for implementing the operator and with the Nilpotent minimum as defined from from Equation (A.20);
	 * - custom_\S* for a custom method for operator and.
	 * 
	 * @param x
	 *            degree1 
	 * @param y
	 *            degree2
	 * @return The t-norm
	 */
	@Override
	public float operate(float x, float y) {
		String op = getOperator();
		if (op.equals(StandardAndMethodType.MIN.value()))
			return min(x, y);
		else if (op.equals(StandardAndMethodType.PROD.value()))
			return prod(x, y);
		else if (op.equals(StandardAndMethodType.BDIF.value()))
			return bdif(x, y);
		else if (op.equals(StandardAndMethodType.DRP.value()))
			return drp(x, y);
		else if (op.equals(StandardAndMethodType.EPROD.value()))
			return eprod(x, y);
		else if (op.equals(StandardAndMethodType.HPROD.value()))
			return hprod(x, y);
		else if (op.equals(StandardAndMethodType.NILMIN.value()))
			return nilmin(x, y);
		else if (op.contains("custom"))
			return custom_and(x, y, op);
		else
			return min(x, y);
	}
	


	/**
	 * - custom_\S* for a custom method.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float custom_and(float x, float y, String act) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * - NILMIN for implementing the operator and with the Nilpotent minimum as defined from Equation (A.20);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float nilmin(float x, float y) {
		if (x + y > 1)
			return Math.min(x, y);
		else
			return 0;
	}

	/**
	 * - HPROD for implementing the operator and with the Hamacher product as defined from Equation (A.19); 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float hprod(float x, float y) {
		return (x + y) / (x + y - (x * y));
	}

	/**
	 * 	 
	 * - EPROD for implementing the operator and with the Einstein product as defined from Equation (A.18);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float eprod(float x, float y) {
		return (x + y) / (2 - (x + y - (x * y)));
	}

	/**
	 * 
	 * - DRP for implementing the operator and with the drastic product as defined from Equation (A.17);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float drp(float x, float y) {
		if (y == 1)
			return x;
		if (x == 1)
			return y;
		else
			return 0;
	}

	/**
	 * 
	 * - BDIF for implementing the operator and with bounded difference as defined from Equation (A.16);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float bdif(float x, float y) {
		return Math.max(0, (x + y - 1));
	}

	/**
	 * 
	 * - PROD for implementing the operator and with the product as defined from Equation (A.15);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float prod(float x, float y) {
		return x * y;
	}

	/**
	 * - MIN for implementing the operator and with the minimum as defined from Equation (A.14);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float min(float x, float y) {
		return Math.min(x, y);
	}

}
