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
package jfml.knowledgebase.variable;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.enumeration.StandardCombinationType;
import jfml.term.Term;
import jfml.term.TsukamotoTermType;


/**
 * <p>Java class for tsukamotoVariableType complex type.
 * 
 * <pre>
 * &lt;complexType name="tsukamotoVariableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tsukamotoTerm" type="{http://www.ieee1855.org}tsukamotoTermType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="domainleft" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="domainright" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="output" /&gt;
 *       &lt;attribute name="combination" type="{http://www.ieee1855.org}combinationType" default="WA" /&gt;
 *       &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}float" default="0" /&gt;
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tsukamotoVariableType", propOrder = {
    "tsukamotoTerm"
})
public class TsukamotoVariableType extends KnowledgeBaseVariable{

    @XmlElement(required = true)
    protected List<TsukamotoTermType> tsukamotoTerm;
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
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "combination")
    protected String combination;
    @XmlAttribute(name = "defaultValue")
    protected Float defaultValue;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;

    @XmlTransient
    protected List<WZ> z;
    
    /**
     * Default constructor
     */
    public TsukamotoVariableType(){
    	
    }
    
    /**
     * Constructor with required elements
     * @param name name of the variable
     * @param domainLeft left domain
     * @param domainRight right domain
     */
    public TsukamotoVariableType(String name, float domainLeft, float domainRight){
    	super();
    	this.setName(name);
    	this.setDomainleft(domainLeft);
    	this.setDomainright(domainRight);
    	this.setScale("");
    	this.setType(this.getType());
    }

    
    /**
     * Gets the value of the tsukamotoTerm property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TsukamotoTermType }
     * 
     * 
     */
    public List<TsukamotoTermType> getTerms() {
        if (tsukamotoTerm == null) {
            tsukamotoTerm = new ArrayList<TsukamotoTermType>();
        }
        return this.tsukamotoTerm;
    }
    
    /**
     * Returns the i-th FuzzyTerm
     * @param i
     * @return
     */
    public TsukamotoTermType getTsukamotoTerm(int i) {
        if (tsukamotoTerm != null && i<tsukamotoTerm.size() && i>=0) {
        	return tsukamotoTerm.get(i);
        }
        else return null;
            
    }
    
    /**
     * 
     * @param t
     */
    public void addTsukamotoTerm(TsukamotoTermType t){
    	if (tsukamotoTerm == null) {
    		tsukamotoTerm = new ArrayList<TsukamotoTermType>();
        }
    	this.tsukamotoTerm.add(t);
    }
    
    /**
     * 
     * @param name
     * @param type
     * @param param
     */
    public void addTsukamotoTerm(String name, int type, float[] param){
    	addTsukamotoTerm(new TsukamotoTermType(name,type,param));
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
            return "output";
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
     * Gets the value of the property combination.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombination() {
        if (combination == null) {
            return "WA";
        } else {
            return combination;
        }
    }

    /**
     * Sets the value of the property combination.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombination(String value) {
        this.combination = value;
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
	public boolean isOutput() {
		if(getType().equals("output") || getType().equals("OUTPUT") || getType().equals("Output"))
			return true;
		else
			return false;
	}
    
    @Override
	public float getValue() {
		//calculate Z according to the combination method
		if(Float.isNaN(value))
			value = combination();
			
		return value;
	}
    
	@Override
	public void setValue(float x) {
		this.value=x;
	}

	/**
	 * Adds a evaluation
	 * @param wi value w
	 * @param zi value z
	 */
	public void addEvaluation(float wi, float zi) {
		if(z==null)
			z= new ArrayList<>();
		
		z.add(new WZ(wi,zi));
	}

	private float combination() {
		String comb = getCombination();
		float v=value;
		if(comb.equals(StandardCombinationType.WA.value())){
			v= weightedAverage(this.z);
		}
		else if(comb.contains("custom"))
			v= customCombination(this.z);
		
		return v;
	}

	private float customCombination(List<WZ> z) {
		// TODO Auto-generated method stub
		return 0;
	}

	private float weightedAverage(List<WZ> z) {
		float sum =0;
		float res=0;
		
		for(WZ zi : z){
			sum += zi.getW();
			res += zi.getW()*zi.getZ();
		}
		return res/sum;
	}
	

	@Override
	public String toString() {
		String b = name + " - domain["+getDomainleft() + ", "+getDomainright()+"] - Combination:"+getCombination() + " - ";
    	    	
    	b+= getType() + "\n";
    	
    	for(TsukamotoTermType t : getTerms())
    		b += "\t" + t.toString()  + "\n";
    	
    	return b;
	}

	@Override
	public void reset() {
		this.value = Float.NaN;
	}

	@Override
	public boolean hasTerm(String name) {
		for(TsukamotoTermType t : getTerms())
			if(t.getName().equals(name))
				return true;
		return false;
	}

	@Override
	public KnowledgeBaseVariable copy() {
		TsukamotoVariableType fv = new TsukamotoVariableType(new String(getName()), getDomainleft(), getDomainright());
		
		fv.setType(new String(getType()));
		fv.setNetworkAddress(new String(getNetworkAddress()));
		fv.setScale(new String(getScale()));
		fv.setCombination(new String(getCombination()));
		fv.setValue(getValue());
			
		//setting terms
		for(TsukamotoTermType t : getTerms())
			fv.addTsukamotoTerm((TsukamotoTermType) t.copy());
		
		return fv;
	}

	@Override
	public Term getTerm(String name) {
		for(TsukamotoTermType t : getTerms())
			if(t.getName().equals(name))
				return t;
		
		return null;
	}

}
