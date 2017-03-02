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
package jfml.knowledgebase;

import java.util.ArrayList;
import java.util.Iterator;
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
 * &lt;complexType name="knowledgeBaseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="2"&gt;
 *           &lt;element name="fuzzyVariable" type="{http://www.ieee1855.org}fuzzyVariableType"/&gt;
 *           &lt;element name="tsukamotoVariable" type="{http://www.ieee1855.org}tsukamotoVariableType"/&gt;
 *           &lt;element name="aggregatedFuzzyVariable" type="{http://www.ieee1855.org}aggregatedFuzzyVariableType"/&gt;
 *           &lt;element name="tskVariable" type="{http://www.ieee1855.org}tskVariableType"/&gt;
 *           &lt;element name="anYaDataCloud" type="{http://www.ieee1855.org}anYaDataCloudType"/&gt;
 *           &lt;any processContents='lax' namespace='##other' minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
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
     * Return a KnowledgeBaseVariable instance identifies by its name 
     * @param name
     * @return the KnowledgeBaseVariable or null if the param name no match
     */
    public KnowledgeBaseVariable getVariable(String name){
    	Iterator<Object> it = getVariables().iterator();

		while (it.hasNext()) {
			Object v = it.next();
			if (((JAXBElement) v).getValue() instanceof KnowledgeBaseVariable) {
				KnowledgeBaseVariable kbvar = (KnowledgeBaseVariable) ((JAXBElement) v).getValue();
				if (kbvar.getName().equals(name))
					return kbvar;
			}
		}
		return null;
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
     * Add a variable to the list of varibles
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
