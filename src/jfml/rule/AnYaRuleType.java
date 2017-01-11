package jfml.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.term.FuzzyTerm;


/**
 * <p>Java class for anYaRuleType complex type.
 * 
 * <pre>
 * &lt;complexType name="anYaRuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anYaAntecedent" type="{http://www.ieee1855.org}anYaAntecedentType"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="consequent" type="{http://www.ieee1855.org}consequentType"/&gt;
 *           &lt;element name="tskConsequent" type="{http://www.ieee1855.org}tskConsequentType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="weight" type="{http://www.ieee1855.org}weightType" default="1.0" /&gt;
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anYaRuleType", propOrder = {
    "anYaAntecedent",
    "consequent",
    "tskConsequent"
})
public class AnYaRuleType extends Rule{

    @XmlElement(required = true)
    protected AnYaAntecedentType anYaAntecedent;
    protected ConsequentType consequent;
    protected TskConsequentType tskConsequent;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "weight")
    protected Float weight;
    @XmlAttribute(name = "networkAddress")
    protected String networkAddress;

    /**
     * Gets the value of the property anYaAntecedent.
     * 
     * @return
     *     possible object is
     *     {@link AnYaAntecedentType }
     *     
     */
    public AnYaAntecedentType getAnYaAntecedent() {
        return anYaAntecedent;
    }

    /**
     * Sets the value of the property anYaAntecedent.
     * 
     * @param value
     *     allowed object is
     *     {@link AnYaAntecedentType }
     *     
     */
    public void setAnYaAntecedent(AnYaAntecedentType value) {
        this.anYaAntecedent = value;
    }

    /**
     * Gets the value of the property consequent.
     * 
     * @return
     *     possible object is
     *     {@link ConsequentType }
     *     
     */
    public ConsequentType getConsequent() {
        return consequent;
    }

    /**
     * Sets the value of the property consequent.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsequentType }
     *     
     */
    public void setConsequent(ConsequentType value) {
        this.consequent = value;
    }

    /**
     * Gets the value of the property tskConsequent.
     * 
     * @return
     *     possible object is
     *     {@link TskConsequentType }
     *     
     */
    public TskConsequentType getTskConsequent() {
        return tskConsequent;
    }

    /**
     * Sets the value of the property tskConsequent.
     * 
     * @param value
     *     allowed object is
     *     {@link TskConsequentType }
     *     
     */
    public void setTskConsequent(TskConsequentType value) {
        this.tskConsequent = value;
    }

    /**
     * Gets the value of the property name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the property name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the property weight.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public float getWeight() {
        if (weight == null) {
            return  1.0F;
        } else {
            return weight;
        }
    }

    /**
     * Sets the value of the property weight.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWeight(Float value) {
        this.weight = value;
    }

    /**
     * Gets the value of the property networkAddress.
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
     * Sets the value of the property networkAddress.
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
	public float aggregation(float[] degrees) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		String b = getName() +" - ("+getEvaluation()+") IF ";
		
		//ANTECEDENTS
		/*List<ClauseType> clauses = getAntecedent().getClauses();
		for(int i=0;i<clauses.size();i++){
			ClauseType c= clauses.get(i);
			
			FuzzyTerm t=(FuzzyTerm) c.getTerm();
			KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
			
			String modifier = c.getModifier();
			if(modifier!=null)
				modifier += " ";
			else
				modifier="";
			
			b += v.getName() +" IS "+ modifier + t.getName();
			if(i<clauses.size()-1)
				b += " "+getConnector().toUpperCase() + " ";
		}*/
		
		//CONSEQUENTS
		ConsequentClausesType then = getConsequent().getThen();
		ConsequentClausesType _else = getConsequent().getElse();
		if(then!=null){
			b += " THEN ";
			for(ClauseType c : then.getClause()){
				FuzzyTerm t=(FuzzyTerm) c.getTerm();
				KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
	
				String modifier = c.getModifier();
				if(modifier!=null)
					modifier += " ";
				else
					modifier="";
				
				b += v.getName() +" IS "+ modifier + t.getName() + " ";
			}
			
			if(_else!=null){
				b += " ELSE ";
				for(ClauseType c : _else.getClause()){
					FuzzyTerm t=(FuzzyTerm) c.getTerm();
					KnowledgeBaseVariable v=(KnowledgeBaseVariable) c.getVariable();
					
					String modifier = c.getModifier();
					if(modifier!=null)
						modifier += " ";
					b += v.getName() +" IS "+ modifier + t.getName() + " ";
				}
			}	
			
			b += "[weight="+getWeight()+"]";
		}
		return b;
	}

}
