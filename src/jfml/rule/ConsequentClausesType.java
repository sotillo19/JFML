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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;
import jfml.term.Term;


/**
 * <p>Java class for consequentClausesType complex type.
 * 
 * <pre>
 * &lt;complexType name="consequentClausesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clause" type="{http://www.ieee1855.org}clauseType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consequentClausesType", propOrder = {
    "clauses"
})
public class ConsequentClausesType {

    @XmlElement(required = true, name="clause")
    protected List<ClauseType> clauses;
    
    /**
     * Default constructor
     */
    public ConsequentClausesType(){
    	super();
    	clauses = new ArrayList<ClauseType>();
    }
    
    /**
     * Constructor using a list of {@link ClauseType }
     * @param clauses list of {@link ClauseType }
     */
    public ConsequentClausesType(List<ClauseType> clauses){
    	this.clauses = clauses;
    }


    /**
     * Gets the value of the clause property.
     * 
     * Objects of the following type(s) are allowed in the list
     * {@link ClauseType }
     * 
     * 
     */
    public List<ClauseType> getClause() {
        if (clauses == null) {
            clauses = new ArrayList<ClauseType>();
        }
        return this.clauses;
    }

    /**
     * Adds a {@link ClauseType } to the list
     * @param c a {@link ClauseType }
     */
    public void addClause(ClauseType c){
    	if (clauses == null) {
            clauses = new ArrayList<ClauseType>();
        }
    	if(c!=null)
    		clauses.add(c);
    }
    
    /**
     * Adds a ClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link Term }
     */
    public void addClause(KnowledgeBaseVariable v, Term t){
    	if (clauses == null) {
            clauses = new ArrayList<ClauseType>();
        }
    	ClauseType c = new ClauseType(v,t);
    	if(c!=null)
    		clauses.add(c);
    }
}
