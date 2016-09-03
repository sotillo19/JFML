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
 * &lt;complexType name="tskConsequentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tskThen" type="{http://www.ieee1855.org}tskConsequentClausesType"/>
 *         &lt;element name="tskElse" type="{http://www.ieee1855.org}tskConsequentClausesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
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

    public TskConsequentType(){
    	super();
    }
    
    public TskConsequentType(TskConsequentClausesType then, TskConsequentClausesType _else){
    	super();
    	this.setTskThen(then);
    	this.setTskElse(_else);
    }
    
    public void addTskThenClause(KnowledgeBaseVariable variable, TskTerm term){
    	if(tskThen==null)
    		tskThen = new TskConsequentClausesType();
    	
    	tskThen.addTskClause(variable, term);
    }
    
    public void addTskThenClause(TskClauseType c){
    	if(tskThen==null)
    		tskThen = new TskConsequentClausesType();
    	
    	tskThen.addTskClause(c);
    }
    
    public void addTskElseClause(KnowledgeBaseVariable variable, TskTerm term){
    	if(tskElse==null)
    		tskElse = new TskConsequentClausesType();
    	
    	tskElse.addTskClause(variable, term);
    }
    
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
