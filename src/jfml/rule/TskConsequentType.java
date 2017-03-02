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
import javax.xml.bind.annotation.XmlType;

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;
import jfml.term.TskTerm;


/**
 * <p>Java class for tskConsequentType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskConsequentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tskThen" type="{http://www.ieee1855.org}tskConsequentClausesType"/&gt;
 *         &lt;element name="tskElse" type="{http://www.ieee1855.org}tskConsequentClausesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tskConsequentType", propOrder = {
    "tskThen",
    "tskElse"
})
public class TskConsequentType {

    @XmlElement(required = true)
    protected TskConsequentClausesType tskThen;
    protected TskConsequentClausesType tskElse;

    /**
     * Default constructor
     */
    public TskConsequentType(){
    	super();
    }
    
    /**
     * Constructor using a then {@link TskConsequentClausesType} and an else {@link TskConsequentClausesType}
     * @param then a then {@link TskConsequentClausesType}
     * @param _else an else {@link TskConsequentClausesType}
     */
    public TskConsequentType(TskConsequentClausesType then, TskConsequentClausesType _else){
    	super();
    	this.setTskThen(then);
    	this.setTskElse(_else);
    }
    
    /**
     * Adds a Tsk THEN ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link FuzzyTerm }
     */
    public void addTskThenClause(KnowledgeBaseVariable variable, TskTerm term){
    	if(tskThen==null)
    		tskThen = new TskConsequentClausesType();
    	
    	tskThen.addTskClause(variable, term);
    }
    
    /**
     * Adds a Tsk THEN {@link TskClauseType }
     * @param c a Tsk THEN {@link TskClauseType }
     */
    public void addTskThenClause(TskClauseType c){
    	if(tskThen==null)
    		tskThen = new TskConsequentClausesType();
    	
    	tskThen.addTskClause(c);
    }
    
    /**
     * Adds a TSK ELSE ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link FuzzyTerm }
     */
    public void addTskElseClause(KnowledgeBaseVariable variable, TskTerm term){
    	if(tskElse==null)
    		tskElse = new TskConsequentClausesType();
    	
    	tskElse.addTskClause(variable, term);
    }
    
    /**
     * Adds a TSK ELSE {@link TskClauseType }
     * @param c a TSK ELSE {@link TskClauseType }
     */
    public void addTskElseClause(TskClauseType c){
    	if(tskElse==null)
    		tskElse = new TskConsequentClausesType();
    	
    	tskElse.addTskClause(c);
    }
    
    /**
     * Gets the value of the property tskThen.
     * 
     * @return
     *     possible object is
     *     {@link TskConsequentClausesType }
     *     
     */
    public TskConsequentClausesType getTskThen() {
        return tskThen;
    }

    /**
     * Sets the value of the property tskThen.
     * 
     * @param value
     *     allowed object is
     *     {@link TskConsequentClausesType }
     *     
     */
    public void setTskThen(TskConsequentClausesType value) {
        this.tskThen = value;
    }

    /**
     * Gets the value of the property tskElse.
     * 
     * @return
     *     possible object is
     *     {@link TskConsequentClausesType }
     *     
     */
    public TskConsequentClausesType getTskElse() {
        return tskElse;
    }

    /**
     * Sets the value of the property tskElse.
     * 
     * @param value
     *     allowed object is
     *     {@link TskConsequentClausesType }
     *     
     */
    public void setTskElse(TskConsequentClausesType value) {
        this.tskElse = value;
    }

}
