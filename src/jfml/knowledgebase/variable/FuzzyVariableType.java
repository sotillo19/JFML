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
import jfml.defuzzifier.DefuzzifierCenterOfArea;
import jfml.defuzzifier.DefuzzifierCenterOfGravity;
import jfml.defuzzifier.DefuzzifierCenterOfGravitySingletons;
import jfml.defuzzifier.DefuzzifierDiscrete;
import jfml.defuzzifier.DefuzzifierLeftMostMax;
import jfml.defuzzifier.DefuzzifierMeanMax;
import jfml.defuzzifier.DefuzzifierRightMostMax;
import jfml.enumeration.StandardAccumulationType;
import jfml.enumeration.StandardDefuzzifierType;
import jfml.membershipfunction.MembershipFunction;
import jfml.membershipfunction.SingletonMembershipFunction;
import jfml.term.FuzzyTermType;


/**
 * <p>Java class for the fuzzyVariableType complex type from IEEE Standard 1855.
 * 
 * <pre>
 * &lt;complexType name="fuzzyVariableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fuzzyTerm" type="{http://www.ieee1855.org}fuzzyTermType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="domainleft" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="domainright" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="type" type="{http://www.ieee1855.org}typeType" default="input" />
 *       &lt;attribute name="accumulation" type="{http://www.ieee1855.org}accumulationType" default="MAX" />
 *       &lt;attribute name="defuzzifier" type="{http://www.ieee1855.org}defuzzifierType" default="COG" />
 *       &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}float" default="0" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fuzzyVariableType", propOrder = {
    "fuzzyTerm"
})

public class FuzzyVariableType extends FuzzyVariable{

    @XmlElement(required = true)
    protected List<FuzzyTermType> fuzzyTerm;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "scale")
    protected String scale;
    @XmlAttribute(name = "domainleft", required = true)
    protected float domainleft;
    @XmlAttribute(name = "domainright", required = true)
    protected float domainright;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "accumulation")
    protected String accumulation;
    @XmlAttribute(name = "defuzzifier")
    protected String defuzzifierName;
    @XmlAttribute(name = "defaultValue")
    protected Float defaultValue;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;
    
    /**
     * Default constructor
     */
    public FuzzyVariableType(){
    	
    }
    
    /**
     * Constructor with required elements
     * @param name
     * @param domainLeft
     * @param domainRight
     */
    public FuzzyVariableType(String name, float domainLeft, float domainRight){
    	super();
    	this.setName(name);
    	this.setDomainleft(domainLeft);
    	this.setDomainright(domainRight);
    	this.setScale("");
    	this.setType(this.getType());
    }

    /**
     * Gets the value of the fuzzyTerm property. An ArrayList of FuzzyTermType 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FuzzyTermType }
     * 
     * 
     */
    public List<FuzzyTermType> getTerms() {
        if (fuzzyTerm == null) {
            fuzzyTerm = new ArrayList<FuzzyTermType>();
        }
        return this.fuzzyTerm;
    }
    
    /**
     * Returns the i-th FuzzyTerm
     * @param i
     * @return
     */
    public FuzzyTermType getFuzzyTerm(int i) {
        if (fuzzyTerm != null && i<fuzzyTerm.size() && i>=0) {
        	return fuzzyTerm.get(i);
        }
        else return null;
            
    }
    
    /**
     * 
     * @param ft
     */
    public void addFuzzyTerm(FuzzyTermType ft){
    	if (fuzzyTerm == null) {
            fuzzyTerm = new ArrayList<FuzzyTermType>();
        }
    	this.fuzzyTerm.add(ft);
    }
    
    /**
     * 
     * @param name
     * @param type
     * @param param
     */
    public void addFuzzyTerm(String name, int type, float[] param){
    	addFuzzyTerm(new FuzzyTermType(name,type,param));
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
     * Gets the value of the property scale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScale() {
        return scale;
    }

    /**
     * Sets the value of the property scale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScale(String value) {
        this.scale = value;
    }

    /**
     * Gets the value of the property domainleft.
     * 
     */
    public float getDomainleft() {
        return domainleft;
    }

    /**
     * Sets the value of the property domainleft.
     * 
     */
    public void setDomainleft(float value) {
        this.domainleft = value;
    }

    /**
     * Gets the value of the property domainright.
     * 
     */
    public float getDomainright() {
        return domainright;
    }

    /**
     * Sets the value of the property domainright.
     * 
     */
    public void setDomainright(float value) {
        this.domainright = value;
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
     * Gets the value of the property accumulation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccumulation() {
        if (accumulation == null) {
            return "MAX";
        } else {
            return accumulation;
        }
    }

    /**
     * Sets the value of the property accumulation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccumulation(String value) {
        this.accumulation = value;
    }

    /**
     * Gets the value of the property defuzzifier.
     *  - MOM for the defuzzifier method named mean of maxima as defined from Equation (A.42);
		- LM for the defuzzifier method named leftmost maximum as defined from Equation (A.43);
		- RM for the defuzzifier method named rightmost maximum as defined from Equation (A.44);
		- COG for the defuzzifier method named center of gravity as defined from Equation (A.45);
		- COA for the defuzzifier method nmed center of area as defined from Equation (A.46);
		- custom_\S* for a custom defuzzifier method.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefuzzifierName() {
        if (defuzzifierName == null) {
            return "COG";
        } else {
            return defuzzifierName;
        }
    }

    /**
     * Sets the value of the property defuzzifier.
     *  - MOM for the defuzzifier method named mean of maxima as defined from Equation (A.42);
		- LM for the defuzzifier method named leftmost maximum as defined from Equation (A.43);
		- RM for the defuzzifier method named rightmost maximum as defined from Equation (A.44);
		- COG for the defuzzifier method named center of gravity as defined from Equation (A.45);
		- COA for the defuzzifier method nmed center of area as defined from Equation (A.46);
		- custom_\S* for a custom defuzzifier method.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefuzzifierName(String value) {
        this.defuzzifierName = value;
    }

    /**
     * Gets the value of the property defaultValue.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public float getDefaultValue() {
        if (defaultValue == null) {
            return  0.0F;
        } else {
            return defaultValue;
        }
    }

    /**
     * Sets the value of the property defaultValue.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setDefaultValue(Float value) {
        this.defaultValue = value;
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
    public String toString(){
    	String b = name + " - domain["+getDomainleft() + ", "+getDomainright()+"] - ";
    	
    	if(isOutput()){
    		b += "Accumulation:"+getAccumulation()+ "; "+"Defuzzifier:"+getDefuzzifierName() + " - ";
    	}
    	
    	b+= getType() + "\n";
    	
    	for(FuzzyTermType t : getTerms())
    		b += "\t" + t.toString()  + "\n";
    	
    	return b;
    }
    
    /**
     *  If the variable is input type, return value setter. 
     *  If the variable is output, return the deffuzifier value or the calculated tsk value
     *  
     *  Defuzzifier method:
     *  - MOM for the defuzzifier method named mean of maxima as defined from Equation (A.42);
		- LM for the defuzzifier method named leftmost maximum as defined from Equation (A.43);
		- RM for the defuzzifier method named rightmost maximum as defined from Equation (A.44);
		- COG for the defuzzifier method named center of gravity as defined from Equation (A.45);
		- COA for the defuzzifier method nmed center of area as defined from Equation (A.46);
		- custom_\S* for a custom defuzzifier method.
     * @return the defuzzification value
     */
	@Override
	public float getValue() {
		if(this.getDefuzzifier()!=null && this.isOutput() && Float.isNaN(value))
    		return getDefuzzifier().defuzzify();
    	else
    		return value;
	}

	@Override
	public void setValue(float x) {
		if(x >= getDomainleft() && x<= getDomainright())
			this.value = x;
	}

	/**
	 * 
	 * 	- MAX for implementing the connector or with the maximum as defined from Equation (A.21);
		- PROBOR for implementing the connector or with the probabilistic sum as defined from Equation (A.22);
		- BSUM for implementing the operator or with the bounded sum as defined from Equation (A.23);
		- DRS for implementing the operator or with the drastic sum as defined from Equation (A.24);
		- ESUM for implementing the operator or with the Einstein sum as defined from Equation (A.25);
		- HSUM for implementing the operator or with the Hamacher sum as defined from Equation (A.26);
		- NILMAX for implementing the operator or with the Nilpotent maximum as defined from Equation (A.27);
		- custom_\S* for a custom method for implementing the connector or.
	 * @return
	 */
	public float accumulation(float x, float y) {
		String acc=getAccumulation();
			if(acc.equals(StandardAccumulationType.MAX.value()))
				return max(x,y);
			else if(acc.equals(StandardAccumulationType.PROBOR.value()))
				return probor(x,y);
			else if(acc.equals(StandardAccumulationType.BSUM.value()))
				return bsum(x,y);
			else if(acc.equals(StandardAccumulationType.DRS.value()))
				return drs(x,y);
			else if(acc.equals(StandardAccumulationType.ESUM.value()))
				return esum(x,y);
			else if(acc.equals(StandardAccumulationType.HSUM.value()))
				return hsum(x,y);
			else if(acc.equals(StandardAccumulationType.NILMAX.value()))
				return nilmax(x,y);
			else if(acc.contains("custom"))
				return custom_accumulation(x,y, acc);
			else 
				return max(x,y);
		
	}
	
	/**
	 * This functions implements custom accumulation methods
	 * @param x
	 * @param y
	 * @param acc String a short name of the accumulation method.
	 * @return accumulation value
	 */
	private float custom_accumulation(float x, float y, String acc) {
		// TODO Auto-generated method stub
		float res = max(x,y);
		if(acc.contains("custom")){
			if(acc.contains("NSUM"))
				res = (float) (( x +y )  / Math.max(1.0, x + y));
			//else if(acc.contains("OTHER"))
				//res = other
		}
		return res;
	}

	private float max(float a, float b) {
		return Math.max(a, b);
	}

	private float probor(float a, float b) {
		return a+b-(a*b);
	}
	
	private float bsum(float a, float b) {
		return Math.min(1, a+b);
	}
	
	private float drs(float a, float b) {
		if(a==0)
			return b;
		if(b==0)
			return a;
		else
			return 1;
	}
	
	private float esum(float a, float b) {
		return (a+b)/(1+a*b);
	}

	private float hsum(float a, float b) {
		return (a+b-(2*a*b))/(1-(a*b));
	}
	
	private float nilmax(float a, float b) {
		if(a+b<1)
			return Math.max(a, b);
		else
			return 1;
	}

	/**
	 *  - MOM for the defuzzifier method named mean of maxima as defined from Equation (A.42);
		- LM for the defuzzifier method named leftmost maximum as defined from Equation (A.43);
		- RM for the defuzzifier method named rightmost maximum as defined from Equation (A.44);
		- COG for the defuzzifier method named center of gravity as defined from Equation (A.45);
		- COA for the defuzzifier method nmed center of area as defined from Equation (A.46);
		- custom_\S* for a custom defuzzifier method.
	 */
	@Override
	public Defuzzifier getDefuzzifier() {
		if(defuzzifier==null && isOutput()){
			String def = getDefuzzifierName();
			if(def.equals(StandardDefuzzifierType.MOM.value()))
				defuzzifier = new DefuzzifierMeanMax(getDomainleft(), getDomainright(),getTerms());
			else if(def.equals(StandardDefuzzifierType.LM.value()))
				defuzzifier = new DefuzzifierLeftMostMax(getDomainleft(), getDomainright(),getTerms());
			else if(def.equals(StandardDefuzzifierType.RM.value()))
				defuzzifier = new DefuzzifierRightMostMax(getDomainleft(), getDomainright(),getTerms());
			else if(def.equals(StandardDefuzzifierType.COG.value()))
				defuzzifier = new DefuzzifierCenterOfGravity(getDomainleft(), getDomainright(),getTerms());
			else if(def.equals(StandardDefuzzifierType.COA.value()))
				defuzzifier = new DefuzzifierCenterOfArea(getDomainleft(), getDomainright(),getTerms());
			else if(def.contains("custom"))
				defuzzifier = custom_Defuzzifier(def);
			else
				defuzzifier= new DefuzzifierCenterOfGravity(getDomainleft(), getDomainright(),getTerms());
		}
		return defuzzifier;
	}

	/**
	 * This function implements custom defuzzifier
	 * @param def String with a short name of the custom defuzzifier
	 * @return a instance of defuzzifier
	 */
	private Defuzzifier custom_Defuzzifier(String def) {
		//TODO implements custom defuzzifiers
		Defuzzifier df = new DefuzzifierCenterOfGravity(getDomainleft(), getDomainright(),getTerms());
		if(def.contains("custom")){
			if(def.contains("COGS")){
				df = new DefuzzifierCenterOfGravitySingletons(getDomainleft(), getDomainright());
				for(FuzzyTermType t : getTerms()){
					MembershipFunction mf = t.getMembershipFunction();
					if(mf instanceof SingletonMembershipFunction){
						((DefuzzifierDiscrete) df).setPoint(((SingletonMembershipFunction) mf).getFi(0),0);
					}
					else throw new RuntimeException("MembershipFunction is not Singleton.\n");
				}
			}
			//else if(def.contains("OTHER"))
				//df = new OtherDefuzzifier();
		}
		return df;
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
		this.defuzzifier = null;
	}
}
