package jfml.membershipfunction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import jfml.term.AndLogicalType;
import jfml.term.OrLogicalType;


/**
 * <p>Java class for circularDefinitionType complex type.
 * 
 * <pre>
 * &lt;complexType name="circularDefinitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="and" type="{http://www.ieee1855.org}andLogicalType"/>
 *         &lt;element name="or" type="{http://www.ieee1855.org}orLogicalType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "circularDefinitionType", propOrder = {
    "and",
    "or"
})
public class CircularDefinitionType {

    protected AndLogicalType and;
    protected OrLogicalType or;

    /**
     * Gets the value of the property and.
     * 
     * @return
     *     possible object is
     *     {@link AndLogicalType }
     *     
     */
    public AndLogicalType getAnd() {
        return and;
    }

    /**
     * Sets the value of the property and.
     * 
     * @param value
     *     allowed object is
     *     {@link AndLogicalType }
     *     
     */
    public void setAnd(AndLogicalType value) {
        this.and = value;
    }

    /**
     * Gets the value of the property or.
     * 
     * @return
     *     possible object is
     *     {@link OrLogicalType }
     *     
     */
    public OrLogicalType getOr() {
        return or;
    }

    /**
     * Sets the value of the property or.
     * 
     * @param value
     *     allowed object is
     *     {@link OrLogicalType }
     *     
     */
    public void setOr(OrLogicalType value) {
        this.or = value;
    }

	public PointSetShapeType copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
