package jfml.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tskClauseType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskClauseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="variable" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
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
