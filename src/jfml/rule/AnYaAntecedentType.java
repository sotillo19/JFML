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
package jfml.rule;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import jfml.knowledgebase.variable.AnYaDataCloudType;


/**
 * <p>Java class for anYaAntecedentType complex type.
 * 
 * <pre>
 * &lt;complexType name="anYaAntecedentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dataCloudName" type="{http://www.w3.org/2001/XMLSchema}IDREF"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre&gt;
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anYaAntecedentType", propOrder = {
    "dataCloudName"
})
public class AnYaAntecedentType {

    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataCloudName;
    
    /**
     * Default constructor
     */
    public AnYaAntecedentType(){
    	    	
    }
    
    /**
     * Constructor using a data cloud {@link AnYaDataCloudType }
     * @param dataCloud the cloud {@link AnYaDataCloudType }
     */
    public AnYaAntecedentType(AnYaDataCloudType dataCloud){
    	this.dataCloudName = dataCloud;
    }
    

    /**
     * Gets the value of the property dataCloudName.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataCloudName() {
        return dataCloudName;
    }

    /**
     * Sets the value of the property dataCloudName.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDataCloudName(Object value) {
        this.dataCloudName = value;
    }
    
    @Override
    public String toString(){
    	if(dataCloudName instanceof AnYaDataCloudType)
    		return dataCloudName.toString();
    	
		return "";
    }

}
