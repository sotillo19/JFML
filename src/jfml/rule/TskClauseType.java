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

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;


/**
 * <p>Java class for tskClauseType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskClauseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="variable" type="{http://www.w3.org/2001/XMLSchema}IDREF"/&gt;
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}IDREF"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tskClauseType", propOrder = {
    "variable",
    "term"
})
public class TskClauseType {

    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object variable;
    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object term;
    
    /**
     * Default constructor
     */
    public TskClauseType(){
    	
    }

    /**
     * Constructor using a variable and a term
     * @param variable possible object is {@link KnowledgeBaseVariable } 
     * @param term possible object is {@link FuzzyTerm }
     */
    public TskClauseType(Object variable, Object term){
    	super();
    	this.setVariable(variable);
    	this.setTerm(term);
    }
    
    /**
     * Gets the value of the property variable.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVariable() {
        return variable;
    }

    /**
     * Sets the value of the property variable.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVariable(Object value) {
        this.variable = value;
    }

    /**
     * Gets the value of the property term.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTerm() {
        return term;
    }

    /**
     * Sets the value of the property term.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTerm(Object value) {
        this.term = value;
    }

}
