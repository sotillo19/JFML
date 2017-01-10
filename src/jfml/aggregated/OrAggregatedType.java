package jfml.aggregated;

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
import jfml.rule.ClauseType;


/**
 * <p>Java class for orAggregatedType complex type.
 * 
 * <pre>
 * &lt;complexType name="orAggregatedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="clause" type="{http://www.ieee1855.org}clauseType" maxOccurs="2" minOccurs="2"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element name="and" type="{http://www.ieee1855.org}andAggregatedType"/>
 *             &lt;element name="or" type="{http://www.ieee1855.org}orAggregatedType"/>
 *           &lt;/choice>
 *           &lt;element name="clause" type="{http://www.ieee1855.org}clauseType"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="t-conorm" type="{http://www.ieee1855.org}orMethodType" default="MAX" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orAggregatedType", propOrder = {
    "content"
})
public class OrAggregatedType extends AggregatedType{

    @XmlElementRefs({
        @XmlElementRef(name = "or", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "and", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "clause", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "t-conorm")
    protected String tConorm;
    
    /**
     * Default constructor
     */
    public OrAggregatedType(){
    	
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param c1 {@link String} with the name of term1
     * @param c2 {@link String} with the name of term2
     */
    public OrAggregatedType(ClauseType c1, ClauseType c2){
    	this.tConorm="MAX";
    	new OrAggregatedType(tConorm,c1,c2);
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param c1 {@link String} with the name of term1
     * @param term2 {@link AndAggregatedType} term2
     */
    public OrAggregatedType(ClauseType c1, AndAggregatedType term2){
    	this.tConorm="MAX";
    	new OrAggregatedType(tConorm,c1,term2);
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link AndAggregatedType} term1
     * @param term2 {@link AndAggregatedType} term2
     */
    public OrAggregatedType(AndAggregatedType term1, AndAggregatedType term2){
    	this.tConorm="MAX";
    	new OrAggregatedType(tConorm,term1,term2);
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link AndAggregatedType} term1
     * @param term2 {@link OrAggregatedType} term2
     */
    public OrAggregatedType(AndAggregatedType term1, OrAggregatedType term2){
    	this.tConorm="MAX";
    	new OrAggregatedType(tConorm,term1,term2);
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link OrAggregatedType} term1
     * @param term2 {@link AndAggregatedType} term2
     */
    public OrAggregatedType(OrAggregatedType term1, AndAggregatedType term2){
    	this.tConorm="MAX";
    	new OrAggregatedType(tConorm,term1,term2);
    }
    
    /**
     * Or constructor using default tConorm = MAX
     * @param term1 {@link OrAggregatedType} term1
     * @param term2 {@link OrAggregatedType} term2
     */
    public OrAggregatedType(OrAggregatedType term1, OrAggregatedType term2){
    	this.tConorm="MAX";
    	new OrAggregatedType(tConorm,term1,term2);
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param c1 
     * @param c2
     */
    public OrAggregatedType(String tConorm, ClauseType c1, ClauseType c2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrAggregatedTypeClause(c1));
    	content.add(ob.createOrAggregatedTypeClause(c2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param c1 
     * @param term2
     */
    public OrAggregatedType(String tConorm, ClauseType c1, AndAggregatedType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrAggregatedTypeClause(c1));
    	content.add(ob.createOrAggregatedTypeAnd(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 
     * @param term2
     */
    public OrAggregatedType(String tConorm, AndAggregatedType term1, AndAggregatedType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrAggregatedTypeAnd(term1));
    	content.add(ob.createOrAggregatedTypeAnd(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 
     * @param term2
     */
    public OrAggregatedType(String tConorm, AndAggregatedType term1, OrAggregatedType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrAggregatedTypeAnd(term1));
    	content.add(ob.createOrAggregatedTypeOr(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 
     * @param term2
     */
    public OrAggregatedType(String tConorm, OrAggregatedType term1, AndAggregatedType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrAggregatedTypeOr(term1));
    	content.add(ob.createOrAggregatedTypeAnd(term2));
    }
    
    /**
     * Or constructor using tConorm as method for or operator
     * @param tConorm {@link String} with or operator {@link StandardOrMethodType}
     * @param term1 
     * @param term2
     */
    public OrAggregatedType(String tConorm, OrAggregatedType term1, OrAggregatedType term2){
    	super();
    	this.tConorm = tConorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createOrAggregatedTypeOr(term1));
    	content.add(ob.createOrAggregatedTypeOr(term2));
    }


    /**
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ClauseType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndAggregatedType }{@code >}
     * {@link JAXBElement }{@code <}{@link OrAggregatedType }{@code >}
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
