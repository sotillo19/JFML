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

import jfml.term.Term;


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

    public AnYaDataCloudType() {
    	
    }
    
    public AnYaDataCloudType(String name) {
		super();
		setName(name);
	}

	/**
     * Gets the value of the datum property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    public List<Double> getTerms() {
        if (datum == null) {
            datum = new ArrayList<Double>();
        }
        return this.datum;
    }
    
    public void setTerms(List<Double> datum) {
        if (datum != null) 
            this.datum = datum;
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

	@Override
	public float getValue() {
		return this.value;
	}

	@Override
	public void setValue(float x) {
		this.value=x;
	}

	
	@Override
	public boolean isOutput() {
		return false;
	}

	@Override
	public String toString() {
		String s = name + " - [";
		for(Double d : datum)
			s +=d + ", ";
		return s.substring(0, s.length()-2) + "]";
	}

	@Override
	public boolean hasTerm(String name) {
		return false;
	}

	@Override
	public KnowledgeBaseVariable copy() {
		AnYaDataCloudType fv = new AnYaDataCloudType(new String(getName()));
	
		fv.setNetworkAddress(new String(getNetworkAddress()));
		fv.setValue(getValue());
			
		//setting terms
		List<Double> datum = new ArrayList<>();
		for(Double t : getTerms())
			datum.add(new Double(t));
		
		fv.setTerms(datum);
		
		return fv;
	}

	@Override
	public Term getTerm(String name) {
		return null;
	}

}
