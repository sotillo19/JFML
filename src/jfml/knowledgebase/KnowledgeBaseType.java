package jfml.knowledgebase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

import jfml.jaxb.ObjectFactory;
import jfml.knowledgebase.variable.AggregatedFuzzyVariableType;
import jfml.knowledgebase.variable.AnYaDataCloudType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.knowledgebase.variable.TskVariableType;
import jfml.knowledgebase.variable.TsukamotoVariableType;


/**
 * <p>Java class for knowledgeBaseType complex type.
 *  
 * <pre>
 * &lt;complexType name="knowledgeBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="2">
 *           &lt;element name="fuzzyVariable" type="{http://www.ieee1855.org}fuzzyVariableType"/>
 *           &lt;element name="tsukamotoVariable" type="{http://www.ieee1855.org}tsukamotoVariableType"/>
 *           &lt;element name="aggregatedFuzzyVariable" type="{http://www.ieee1855.org}aggregatedFuzzyVariableType"/>
 *           &lt;element name="tskVariable" type="{http://www.ieee1855.org}tskVariableType"/>
 *           &lt;element name="anYaDataCloud" type="{http://www.ieee1855.org}anYaDataCloudType"/>
 *           &lt;any processContents='lax' namespace='##other' minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "knowledgeBaseType", propOrder = {
    "variable"
})
public class KnowledgeBaseType {

    @XmlElementRefs({
        @XmlElementRef(name = "tskVariable", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "fuzzyVariable", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tsukamotoVariable", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "aggregatedFuzzyVariable", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "anYaDataCloud", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> variable;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;

    /**
     * Gets the value of the Variable property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TskVariableType }{@code >}
     * {@link JAXBElement }{@code <}{@link FuzzyVariableType }{@code >}
     * {@link JAXBElement }{@code <}{@link TsukamotoVariableType }{@code >}
     * {@link JAXBElement }{@code <}{@link AggregatedFuzzyVariableType }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link AnYaDataCloudType }{@code >}
     * {@link Element }
     * 
     * 
     */
    public List<Object> getVariables() {
        if (variable == null) {
            variable = new ArrayList<Object>();
        }
        return this.variable;
    }
    
    /**
     * 
     * @return List of KnowledgeBaseVariable
     */
    public List<KnowledgeBaseVariable> getKnowledgeBaseVariables(){
    	if(variable!=null){
    		List<KnowledgeBaseVariable> kbvs = new ArrayList<>();
    		for(Object e : variable){
    			if(e instanceof JAXBElement && ((JAXBElement<?>) e).getValue() instanceof KnowledgeBaseVariable)
    				kbvs.add((KnowledgeBaseVariable) ((JAXBElement<?>) e).getValue());
    		}
    		return kbvs;
    	}
    	else
    		return null;
    }
    
    /**
     * 
     * @param var
     */
    public void addVariable(KnowledgeBaseVariable var){
    	if (variable == null) {
    		variable = new ArrayList<Object>();
        }
    	ObjectFactory of = new ObjectFactory();
    	JAXBElement<?> e = null;
    	
    	if(var instanceof FuzzyVariableType)
    		e = of.createKnowledgeBaseTypeFuzzyVariable((FuzzyVariableType) var);
    	
    	else if(var instanceof TsukamotoVariableType)
    		e = of.createKnowledgeBaseTypeTsukamotoVariable((TsukamotoVariableType) var);
    	
    	else if(var instanceof AggregatedFuzzyVariableType)
    		e = of.createKnowledgeBaseTypeAggregatedFuzzyVariable((AggregatedFuzzyVariableType) var);
    	
    	else if(var instanceof TskVariableType)
    		e = of.createKnowledgeBaseTypeTskVariable((TskVariableType) var);
    	
    	else if(var instanceof AnYaDataCloudType)
    		e = of.createKnowledgeBaseTypeAnYaDataCloud((AnYaDataCloudType) var);
    		
    	if(e !=null)
    		this.variable.add(e);
    }
    

    /**
     * Gets the value of the networkAddress property.
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
     * Sets the value of the networkAddress property.
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
    	StringBuffer b = new StringBuffer();
    	b.append("KNOWLEDGEBASE: \n");

		// VARIABLES
		for (Object v : getVariables()) {
			KnowledgeBaseVariable var = null;
			if (((JAXBElement<?>) v).getValue() instanceof KnowledgeBaseVariable) {
				var = (KnowledgeBaseVariable) ((JAXBElement<?>) v).getValue();
				if (var != null)
					b.append("  *" +var.toString() + "\n");
			}
		}
		return b.toString();
    }

}
