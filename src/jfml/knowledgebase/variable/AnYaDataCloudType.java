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


/**
 * <p>Java class for anYaDataCloudType complex type.
 *  
 * <pre>
 * &lt;complexType name="anYaDataCloudType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}double" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anYaDataCloudType", propOrder = {
    "datum"
})
public class AnYaDataCloudType extends KnowledgeBaseVariable{

    @XmlElement(type = Double.class)
    protected List<Double> datum;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;

    /**
     * Gets the value of the datum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    /*public List<Double> getDatum() {
        if (datum == null) {
            datum = new ArrayList<Double>();
        }
        return this.datum;
    }*/
    public List<Double> getTerms() {
        if (datum == null) {
            datum = new ArrayList<Double>();
        }
        return this.datum;
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
	public void reset() {
		this.value = Float.NaN;
	}

    /*
	@Override
	public float getDefuzzifierValue() {
		// TODO Auto-generated method stub
		return 0;
	}*/

	@Override
	public float getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(float x) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	protected Defuzzifier getDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public boolean isOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
