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
import jfml.term.TskTerm;


/**
 * <p>Java class for tskConsequentClausesType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskConsequentClausesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tskClause" type="{http://www.ieee1855.org}tskClauseType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tskConsequentClausesType", propOrder = {
    "tskClause"
})
public class TskConsequentClausesType {

    @XmlElement(required = true)
    protected List<TskClauseType> tskClause;

    /**
     * Gets the value of the tskClause property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TskClauseType }
     * 
     * 
     */
    public List<TskClauseType> getTskClause() {
        if (tskClause == null) {
            tskClause = new ArrayList<TskClauseType>();
        }
        return this.tskClause;
    }
    
    /**
     * Adds a TskClauseType to the list of clauses
     * @param c a TskClauseType
     */
    public void addTskClause(TskClauseType c){
    	if (tskClause == null) {
    		tskClause = new ArrayList<TskClauseType>();
        }
    	if(c!=null)
    		tskClause.add(c);
    }
    
    /**
     * Adds a TskClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link FuzzyTerm }
     */
    public void addTskClause(KnowledgeBaseVariable v, Term t){
    	if (tskClause == null) {
    		tskClause = new ArrayList<TskClauseType>();
        }
    	TskClauseType c = new TskClauseType(v,t);
    	if(c!=null)
    		tskClause.add(c);
    }

    /**
     * Adds a TskClauseType with a {@link KnowledgeBaseVariable } and a {@link FuzzyTerm }
     * @param variable the {@link KnowledgeBaseVariable }
     * @param term the {@link FuzzyTerm }
     */
    public void addTskClause(KnowledgeBaseVariable v, String t){
    	if (tskClause == null) {
    		tskClause = new ArrayList<TskClauseType>();
        }
    	TskClauseType c = new TskClauseType(v,v.getTerm(t));
    	if(c!=null)
    		tskClause.add(c);
    }
}
