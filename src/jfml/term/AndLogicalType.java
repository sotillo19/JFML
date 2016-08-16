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


/**
 * <p>Java class for andLogicalType complex type.
 * 
 * <pre>
 * &lt;complexType name="andLogicalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="termName" type="{http://www.ieee1855.org}circularTermType" maxOccurs="2" minOccurs="2"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element name="and" type="{http://www.ieee1855.org}andLogicalType"/>
 *             &lt;element name="or" type="{http://www.ieee1855.org}orLogicalType"/>
 *           &lt;/choice>
 *           &lt;element name="termName" type="{http://www.ieee1855.org}circularTermType"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="t-norm" type="{http://www.ieee1855.org}andMethodType" default="MIN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "andLogicalType", propOrder = {
    "content"
})
public class AndLogicalType {

    @XmlElementRefs({
        @XmlElementRef(name = "and", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "or", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "termName", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "t-norm")
    protected String tNorm;

    /**
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link OrLogicalType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndLogicalType }{@code >}
     * {@link JAXBElement }{@code <}{@link CircularTermType }{@code >}
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
     * Gets the value of the property tNorm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTNorm() {
        if (tNorm == null) {
            return "MIN";
        } else {
            return tNorm;
        }
    }

    /**
     * Sets the value of the property tNorm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTNorm(String value) {
        this.tNorm = value;
    }

}
