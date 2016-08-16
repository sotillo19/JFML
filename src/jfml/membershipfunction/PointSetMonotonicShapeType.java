package jfml.membershipfunction;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pointSetMonotonicShapeType complex type.
 * 
 * <pre>
 * &lt;complexType name="pointSetMonotonicShapeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="point" type="{http://www.ieee1855.org}pointType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="interpolationMethod" type="{http://www.ieee1855.org}monotonicInterpolationMethodType" default="linear" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pointSetMonotonicShapeType", propOrder = {
    "point"
})
public class PointSetMonotonicShapeType {

    @XmlElement(required = true)
    protected List<PointType> point;
    @XmlAttribute(name = "interpolationMethod")
    protected MonotonicInterpolationMethodType interpolationMethod;

    /**
     * Gets the value of the point property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the point property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointType }
     * 
     * 
     */
    public List<PointType> getPoint() {
        if (point == null) {
            point = new ArrayList<PointType>();
        }
        return this.point;
    }

    /**
     * Gets the value of the property interpolationMethod.
     * 
     * @return
     *     possible object is
     *     {@link MonotonicInterpolationMethodType }
     *     
     */
    public MonotonicInterpolationMethodType getInterpolationMethod() {
        if (interpolationMethod == null) {
            return MonotonicInterpolationMethodType.LINEAR;
        } else {
            return interpolationMethod;
        }
    }

    /**
     * Sets the value of the property interpolationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link MonotonicInterpolationMethodType }
     *     
     */
    public void setInterpolationMethod(MonotonicInterpolationMethodType value) {
        this.interpolationMethod = value;
    }

}
