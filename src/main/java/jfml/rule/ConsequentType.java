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


/**
 * <p>Java class for consequentType complex type.
 * 
 * <pre>
 * &lt;complexType name="consequentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="then" type="{http://www.ieee1855.org}consequentClausesType"/&gt;
 *         &lt;element name="else" type="{http://www.ieee1855.org}consequentClausesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consequentType", propOrder = {
    "then",
    "_else"
})
public class ConsequentType {

    @XmlElement(required = true)
    protected ConsequentClausesType then;
    @XmlElement(name = "else")
    protected ConsequentClausesType _else;

    /**
     * Default constructor
     */
    public ConsequentType(){
    	super();
    }
    
    /**
     * Constructor using a then {@link ConsequentClausesType} and an else {@link ConsequentClausesType}
     * @param then a then {@link ConsequentClausesType}
     * @param _else an else {@link ConsequentClausesType}
     */
    public ConsequentType(ConsequentClausesType then, ConsequentClausesType _else){
    	super();
    	this.setThen(then);
    	this.setElse(_else);
    }
    
    /**
     * Adds a THEN ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link FuzzyTerm }
     */
    public void addThenClause(KnowledgeBaseVariable variable, FuzzyTerm term){
    	if(then==null)
    		then = new ConsequentClausesType();
    	
    	then.addClause(variable, term);
    }
    
    /**
     * Adds a THEN ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the name of the {@link FuzzyTerm }
     */
    public void addThenClause(KnowledgeBaseVariable variable, String term){
    	if(then==null)
    		then = new ConsequentClausesType();
    	
    	then.addClause(variable, variable.getTerm(term));
    }
    
    /**
     * Adds a THEN {@link ClauseType }
     * @param c a THEN {@link ClauseType }
     */
    public void addThenClause(ClauseType c){
    	if(then==null)
    		then = new ConsequentClausesType();
    	
    	then.addClause(c);
    }
    
    
    /**
     * Adds an ELSE ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link FuzzyTerm }
     */
    public void addElseClause(KnowledgeBaseVariable variable, FuzzyTerm term){
    	if(_else==null)
    		_else = new ConsequentClausesType();
    	
    	_else.addClause(variable, term);
    }
    
    /**
     * Adds an ELSE ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the name of the {@link FuzzyTerm }
     */
    public void addElseClause(KnowledgeBaseVariable variable, String term){
    	if(_else==null)
    		_else = new ConsequentClausesType();
    	
    	_else.addClause(variable, variable.getTerm(term));
    }
    
    /**
     * Adds an ELSE {@link ClauseType }
     * @param c an ELSE {@link ClauseType }
     */
    public void addElseClause(ClauseType c){
    	if(_else==null)
    		_else = new ConsequentClausesType();
    	
    	_else.addClause(c);
    }
   
    
    /**
     * Gets the value of the property then.
     * 
     * @return
     *     possible object is
     *     {@link ConsequentClausesType }
     *     
     */
    public ConsequentClausesType getThen() {
        return then;
    }

    /**
     * Sets the value of the property then.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsequentClausesType }
     *     
     */
    public void setThen(ConsequentClausesType value) {
        this.then = value;
    }

    /**
     * Gets the value of the property else.
     * 
     * @return
     *     possible object is
     *     {@link ConsequentClausesType }
     *     
     */
    public ConsequentClausesType getElse() {
        return _else;
    }

    /**
     * Sets the value of the property else.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsequentClausesType }
     *     
     */
    public void setElse(ConsequentClausesType value) {
        this._else = value;
    }

}
