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
import jfml.term.TsukamotoTermType;


/**
 * <p>Java class for tsukamotoVariableType complex type.
 * 
 * <pre>
 * &lt;complexType name="tsukamotoVariableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tsukamotoTerm" type="{http://www.ieee1855.org}tsukamotoTermType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="domainleft" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="domainright" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="output" />
 *       &lt;attribute name="combination" type="{http://www.ieee1855.org}combinationType" default="WA" />
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

    /**
     * Gets the value of the tsukamotoTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tsukamotoTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTsukamotoTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TsukamotoTermType }
     * 
     * 
     */
    /*public List<TsukamotoTermType> getTsukamotoTerm() {
        if (tsukamotoTerm == null) {
            tsukamotoTerm = new ArrayList<TsukamotoTermType>();
        }
        return this.tsukamotoTerm;
    }*/
    public List<TsukamotoTermType> getTerms() {
        if (tsukamotoTerm == null) {
            tsukamotoTerm = new ArrayList<TsukamotoTermType>();
        }
        return this.tsukamotoTerm;
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
	public float getDefuzzifierValue() {
		// TODO Auto-generated method stub
		return 0;
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
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
