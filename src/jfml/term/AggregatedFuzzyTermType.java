package jfml.term;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for aggregatedFuzzyTermType complex type.
 * 
 * <pre>
 * &lt;complexType name="aggregatedFuzzyTermType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="and" type="{http://www.ieee1855.org}andAggregatedType"/>
 *         &lt;element name="or" type="{http://www.ieee1855.org}orAggregatedType"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggregatedFuzzyTermType", propOrder = {
    "and",
    "or"
})
public class AggregatedFuzzyTermType {

    protected AndAggregatedType and;
    protected OrAggregatedType or;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;

    /**
     * Gets the value of the property and.
     * 
     * @return
     *     possible object is
     *     {@link AndAggregatedType }
     *     
     */
    public AndAggregatedType getAnd() {
        return and;
    }

    /**
     * Sets the value of the property and.
     * 
     * @param value
     *     allowed object is
     *     {@link AndAggregatedType }
     *     
     */
    public void setAnd(AndAggregatedType value) {
        this.and = value;
    }

    /**
     * Gets the value of the property or.
     * 
     * @return
     *     possible object is
     *     {@link OrAggregatedType }
     *     
     */
    public OrAggregatedType getOr() {
        return or;
    }

    /**
     * Sets the value of the property or.
     * 
     * @param value
     *     allowed object is
     *     {@link OrAggregatedType }
     *     
     */
    public void setOr(OrAggregatedType value) {
        this.or = value;
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

}
