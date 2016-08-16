package jfml.term;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

import jfml.rule.ClauseType;


/**
 * <p>Java class for orAggregatedType complex type.
 * 
 * <pre>
 * &lt;complexType name="orAggregatedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="clause" type="{http://www.ieee1855.org}clauseType" maxOccurs="2" minOccurs="2"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element name="and" type="{http://www.ieee1855.org}andAggregatedType"/>
 *             &lt;element name="or" type="{http://www.ieee1855.org}orAggregatedType"/>
 *           &lt;/choice>
 *           &lt;element name="clause" type="{http://www.ieee1855.org}clauseType"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="t-conorm" type="{http://www.ieee1855.org}orMethodType" default="MAX" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orAggregatedType", propOrder = {
    "content"
})
public class OrAggregatedType {

    @XmlElementRefs({
        @XmlElementRef(name = "or", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "and", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "clause", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "t-conorm")
    protected String tConorm;

    /**
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ClauseType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndAggregatedType }{@code >}
     * {@link JAXBElement }{@code <}{@link OrAggregatedType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

    /**
     * Gets the value of the property tConorm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTConorm() {
        if (tConorm == null) {
            return "MAX";
        } else {
            return tConorm;
        }
    }

    /**
     * Sets the value of the property tConorm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTConorm(String value) {
        this.tConorm = value;
    }

}
