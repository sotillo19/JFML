package jfml.rule;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;


/**
 * <p>Java class for antecedentType complex type.
 * 
 * <pre>
 * &lt;complexType name="antecedentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clause" type="{http://www.ieee1855.org}clauseType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "antecedentType", propOrder = {
    "clauses"
})
public class AntecedentType {

    @XmlElement(required = true, name="clause")
    protected List<ClauseType> clauses;
    
    public AntecedentType(){
    	super();
    	clauses = new ArrayList<ClauseType>();
    }
    
    public AntecedentType(List<ClauseType> clauses){
    	this.clauses = clauses;
    }

    /**
     * Gets the value of the clause property.

     * Objects of the following type(s) are allowed in the list
     * {@link ClauseType }
     * 
     * 
     */
    public List<ClauseType> getClauses() {
        if (clauses == null) {
            clauses = new ArrayList<ClauseType>();
        }
        return this.clauses;
    }
    
    public void addClause(ClauseType c){
    	if (clauses == null) {
            clauses = new ArrayList<ClauseType>();
        }
    	if(c!=null)
    		clauses.add(c);
    }
    
    public void addClause(KnowledgeBaseVariable variable, FuzzyTerm term){
    	if (clauses == null) {
            clauses = new ArrayList<ClauseType>();
        }
    	ClauseType c = new ClauseType(variable,term);
    	if(c!=null)
    		clauses.add(c);
    }

}
