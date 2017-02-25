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

import jfml.enumeration.StandardAndMethodType;
import jfml.jaxb.ObjectFactory;
import jfml.rule.ClauseType;


/**
 * <p>Java class for andAggregatedType complex type.
 * 
 * <pre>
 * &lt;complexType name="andAggregatedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="clause" type="{http://www.ieee1855.org}clauseType" maxOccurs="2" minOccurs="2"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;sequence&gt;
 *           &lt;choice&gt;
 *             &lt;element name="and" type="{http://www.ieee1855.org}andAggregatedType"/&gt;
 *             &lt;element name="or" type="{http://www.ieee1855.org}orAggregatedType"/&gt;
 *           &lt;/choice&gt;
 *           &lt;element name="clause" type="{http://www.ieee1855.org}clauseType"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="t-norm" type="{http://www.ieee1855.org}andMethodType" default="MIN" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "andAggregatedType", propOrder = {
    "content"
})
public class AndAggregatedType extends AggregatedType{

    @XmlElementRefs({
        @XmlElementRef(name = "or", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "clause", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "and", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "t-norm")
    protected String tNorm;
    
    /**
     * Default constructor
     */
    public AndAggregatedType(){
    	
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param c1 {@link ClauseType} 
     * @param c2 {@link ClauseType} 
     */
    public AndAggregatedType(ClauseType c1, ClauseType c2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,c1,c2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param c1 ClauseType
     * @param term2 {@link AndAggregatedType} term2
     */
    public AndAggregatedType(ClauseType c1, AndAggregatedType term2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,c1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param c1 ClauseType
     * @param term2 {@link OrAggregatedType} term2
     */
    public AndAggregatedType(ClauseType c1, OrAggregatedType term2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,c1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link AndAggregatedType} term1
     * @param term2 {@link AndAggregatedType} term2
     */
    public AndAggregatedType(AndAggregatedType term1, AndAggregatedType term2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link AndAggregatedType} term1
     * @param term2 {@link OrAggregatedType} term2
     */
    public AndAggregatedType(AndAggregatedType term1, OrAggregatedType term2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link OrAggregatedType} term1
     * @param term2 {@link AndAggregatedType} term2
     */
    public AndAggregatedType(OrAggregatedType term1, AndAggregatedType term2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * And constructor using default t-norm = MIN
     * @param term1 {@link OrAggregatedType} term1
     * @param term2 {@link OrAggregatedType} term2
     */
    public AndAggregatedType(OrAggregatedType term1, OrAggregatedType term2){
    	this.tNorm="MIN";
    	this.content = (new AndAggregatedType(tNorm,term1,term2)).getContent();
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param c1 ClauseType
     * @param c2 ClauseType
     */
    public AndAggregatedType(String tNorm, ClauseType c1, ClauseType c2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeClause(c1));
    	content.add(ob.createAndAggregatedTypeClause(c2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param c1 ClauseType
     * @param term2 AndAggregatedType
     */
    public AndAggregatedType(String tNorm, ClauseType c1, AndAggregatedType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeClause(c1));
    	content.add(ob.createAndAggregatedTypeAnd(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param c1 ClauseType
     * @param term2 OrAggregatedType
     */
    public AndAggregatedType(String tNorm, ClauseType c1, OrAggregatedType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeClause(c1));
    	content.add(ob.createAndAggregatedTypeOr(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 AndAggregatedType
     * @param term2 AndAggregatedType
     */
    public AndAggregatedType(String tNorm, AndAggregatedType term1, AndAggregatedType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeAnd(term1));
    	content.add(ob.createAndAggregatedTypeAnd(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 AndAggregatedType
     * @param term2 OrAggregatedType
     */
    public AndAggregatedType(String tNorm, AndAggregatedType term1, OrAggregatedType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeAnd(term1));
    	content.add(ob.createAndAggregatedTypeOr(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 OrAggregatedType
     * @param term2 AndAggregatedType
     */
    public AndAggregatedType(String tNorm, OrAggregatedType term1, AndAggregatedType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeOr(term1));
    	content.add(ob.createAndAggregatedTypeAnd(term2));
    }
    
    /**
     * AND constructor using t-norm as method for and operator
     * @param tNorm {@link String} with AND operator {@link StandardAndMethodType}
     * @param term1 OrAggregatedType
     * @param term2 OrAggregatedType
     */
    public AndAggregatedType(String tNorm, OrAggregatedType term1, OrAggregatedType term2){
    	super();
    	this.tNorm = tNorm;
    	content = new ArrayList<JAXBElement<?>>();
    	
    	ObjectFactory ob = new ObjectFactory();
    	
    	content.add(ob.createAndAggregatedTypeOr(term1));
    	content.add(ob.createAndAggregatedTypeOr(term2));
    }

    /**
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link OrAggregatedType }{@code >}
     * {@link JAXBElement }{@code <}{@link ClauseType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndAggregatedType }{@code >}
     * 
     * @return OrAggregatedType or ClauseType or AndAggregatedType
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

	@Override
	public float operate(float degree1, float degree2) {
		String op = getOperator();
		if (op.equals(StandardAndMethodType.MIN.value()))
			return min(degree1, degree2);
		else if (op.equals(StandardAndMethodType.PROD.value()))
			return prod(degree1, degree2);
		else if (op.equals(StandardAndMethodType.BDIF.value()))
			return bdif(degree1, degree2);
		else if (op.equals(StandardAndMethodType.DRP.value()))
			return drp(degree1, degree2);
		else if (op.equals(StandardAndMethodType.EPROD.value()))
			return eprod(degree1, degree2);
		else if (op.equals(StandardAndMethodType.HPROD.value()))
			return hprod(degree1, degree2);
		else if (op.equals(StandardAndMethodType.NILMIN.value()))
			return nilmin(degree1, degree2);
		else if (op.contains("custom"))
			return custom_and(degree1, degree2, op);
		else
			return min(degree1, degree2);
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
