package jfml.term;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.aggregated.AggregatedType;
import jfml.aggregated.AndAggregatedType;
import jfml.aggregated.OrAggregatedType;
import jfml.knowledgebase.variable.FuzzyVariable;
import jfml.rule.ClauseType;


/**
 * <p>Java class for aggregatedFuzzyTermType complex type.
 * 
 * <pre>
 * &lt;complexType name="aggregatedFuzzyTermType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="and" type="{http://www.ieee1855.org}andAggregatedType"/>
 *         &lt;element name="or" type="{http://www.ieee1855.org}orAggregatedType"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author sotillo19
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggregatedFuzzyTermType", propOrder = {
    "and",
    "or"
})
public class AggregatedFuzzyTermType extends FuzzyTerm{

    protected AndAggregatedType and;
    protected OrAggregatedType or;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    
    /**
     * Default constructor
     */
    public AggregatedFuzzyTermType(){
    	super();
    }
    
    /**
     * Constructor using an AggregatedType {@link AggregatedType }. 
     *
     * @param agg an instance of AndAggregatedType {@link AndAggregatedType } or OrAggregatedType {@link OrAggregatedType }
     */
    public AggregatedFuzzyTermType(AggregatedType agg){
    	super();
    	if(agg instanceof AndAggregatedType)
    		this.and=(AndAggregatedType) agg;
    	else if(agg instanceof OrAggregatedType)
    		this.or=(OrAggregatedType) agg;
    }

    /**
     * Constructor using the name and the And {@link AndAggregatedType } and the OrAggregatedType {@link OrAggregatedType }
     * @param name the name of the variable
     * @param and the And {@link AndAggregatedType }
     * @param or the Or {@link OrAggregatedType }
     */
    public AggregatedFuzzyTermType(String name, AndAggregatedType and, OrAggregatedType or) {
		super();
		this.name=name;
		this.and=and;
		this.or=or;
	}

	/**
     * Gets the value of the property and.
     * 
     * @return
     *     possible object is
     *     {@link AndAggregatedType }
     *     
     */
    public AndAggregatedType getAnd() {
        return and;
    }

    /**
     * Sets the value of the property and.
     * 
     * @param value
     *     allowed object is
     *     {@link AndAggregatedType }
     *     
     */
    public void setAnd(AndAggregatedType value) {
        this.and = value;
    }

    /**
     * Gets the value of the property or.
     * 
     * @return
     *     possible object is
     *     {@link OrAggregatedType }
     *     
     */
    public OrAggregatedType getOr() {
        return or;
    }

    /**
     * Sets the value of the property or.
     * 
     * @param value
     *     allowed object is
     *     {@link OrAggregatedType }
     *     
     */
    public void setOr(OrAggregatedType value) {
        this.or = value;
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
    
    @Override
	public String toString() {
		String s = name + " - ";
		if(getAnd()!=null)
			s += printAggregated(getAnd());
		else if(getOr()!=null)
			s += printAggregated(getOr());
		return s;
	}
    
    private String printAggregated(AggregatedType agg) {
		String s="";
		String s1, s2;
		
		if(agg instanceof AndAggregatedType)
			s = " AND ";
		else
			s = " OR ";
		
		Object o1 = agg.getContent(0);
		Object o2 = agg.getContent(1);

		if(o1 instanceof ClauseType){
			s1 = o1.toString();
		}
		else
			s1 = printAggregated((AggregatedType) o1);
		
		if(o2 instanceof ClauseType){
			s2 = o2.toString();
		}
		else
			s2 = printAggregated((AggregatedType) o2);
		
		return s1 + s + s2;
	}

	@Override
	public float getMembershipValue(float x) {
		if(getAnd()!=null)
			return evaluateAggregated(x,getAnd());
		else if(getOr()!=null)
			return evaluateAggregated(x, getOr());
		
		return Float.NaN;
	}
	
	private float evaluateAggregated(float x, AggregatedType agg) {
		float degree1 = 0, degree2 = 0;
		
		Object o1 = agg.getContent(0);
		Object o2 = agg.getContent(1);

		if(o1 instanceof ClauseType){
			FuzzyTerm t1 = (FuzzyTerm) ((ClauseType) o1).getTerm();
			FuzzyVariable v = (FuzzyVariable) ((ClauseType) o1).getVariable();
			if(v==null || t1==null)
				throw new RuntimeException("The term is not found in the variable");
			degree1 = ((ClauseType) o1).modifierMembershipDegree(t1.getMembershipValue(v.getValue()));
		}
		else
			degree1 = evaluateAggregated(x,(AggregatedType) o1);
		
		if(o2 instanceof ClauseType){
			FuzzyTerm t2 = (FuzzyTerm) ((ClauseType) o2).getTerm();
			FuzzyVariable v = (FuzzyVariable) ((ClauseType) o2).getVariable();
			if(v==null || t2==null)
				throw new RuntimeException("The term is not found in the variable");
			degree2 = ((ClauseType) o2).modifierMembershipDegree(t2.getMembershipValue(v.getValue()));
		}
		else
			degree2 = evaluateAggregated(x,(AggregatedType) o2);
		
		return agg.operate(degree1,degree2);
	}

	@Override
	public String getComplement() {
		return null;
	}

	@Override
	public FuzzyTerm copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
