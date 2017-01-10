package jfml.knowledgebase.variable;

import java.util.ArrayList;
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

import jfml.defuzzifier.Defuzzifier;
import jfml.term.AggregatedFuzzyTermType;
import jfml.term.Term;


/**
 * <p>Java class for aggregatedFuzzyVariableType complex type.
 * 
 * <pre>
 * &lt;complexType name="aggregatedFuzzyVariableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggregatedFuzzyTerm" type="{http://www.ieee1855.org}aggregatedFuzzyTermType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="type" type="{http://www.ieee1855.org}typeType" fixed="input" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggregatedFuzzyVariableType", propOrder = {
    "aggregatedFuzzyTerm"
})
public class AggregatedFuzzyVariableType extends FuzzyVariable{

    @XmlElement(required = true)
    protected List<AggregatedFuzzyTermType> aggregatedFuzzyTerm;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;
    
    /**
     * Default constructor
     */
    public AggregatedFuzzyVariableType(){
    	
    }
    
    /**
     * Constructor with required parameters
     * @param name is used to define a unique name for the aggregated fuzzy variable      
     */
    public AggregatedFuzzyVariableType(String name){
    	super();
    	this.setName(name);
    	this.setType(getType());
    }
    
    /**
     * Constructor with required parameters
     * @param name is used to define a unique name for the aggregated fuzzy variable
     * @param type is used to define the position of the aggregated fuzzy variable into rule (consequent part or antecedent part). Output or input
     */
    public AggregatedFuzzyVariableType(String name, String type){
    	super();
    	this.setName(name);
    	this.setType(type);
    }

    /**
     * Gets the value of the aggregatedFuzzyTerm property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AggregatedFuzzyTermType }
     * 
     * 
     */
    public List<AggregatedFuzzyTermType> getTerms() {
        if (aggregatedFuzzyTerm == null) {
            aggregatedFuzzyTerm = new ArrayList<AggregatedFuzzyTermType>();
        }
        return this.aggregatedFuzzyTerm;
    }
    
    /**
     * Returns the i-th AggregatedFuzzyTermType
     * @param i
     * @return the i-th AggregatedFuzzyTermType
     */
    public AggregatedFuzzyTermType getAggregatedFuzzyTerm(int i) {
        if (aggregatedFuzzyTerm != null && i<aggregatedFuzzyTerm.size() && i>=0) {
        	return aggregatedFuzzyTerm.get(i);
        }
        else return null;
            
    }
    
    /**
     * Add a AggregatedFuzzyTermType
     * @param aft {@link AggregatedFuzzyTermType }
     */
    public void addAggregatedFuzzyTerm(AggregatedFuzzyTermType aft){
    	if (aggregatedFuzzyTerm == null) {
    		aggregatedFuzzyTerm = new ArrayList<AggregatedFuzzyTermType>();
        }
    	this.aggregatedFuzzyTerm.add(aft);
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
     * Gets the value of the property type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "input";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the property type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
	public boolean isOutput() {
		if(getType().equals("output") || getType().equals("OUTPUT") || getType().equals("Output"))
			return true;
		else
			return false;
	}
    
    @Override
	public void reset() {
		this.value = Float.NaN;
	}


	@Override
	public float getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Defuzzifier getDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasTerm(String name) {
		for(AggregatedFuzzyTermType t : getTerms())
			if(t.getName().equals(name))
				return true;
		return false;
	}

	@Override
	public KnowledgeBaseVariable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Term getTerm(String name) {
		for(AggregatedFuzzyTermType t : getTerms())
			if(t.getName().equals(name))
				return t;
		return null;
	}
	
	@Override
	public String toString() {
		String s = name + " - " + getType() + "\n";
		for(AggregatedFuzzyTermType aft : getTerms())
			s += "\t" + aft.toString()  + "\n";
		return s;
	}

	

}
