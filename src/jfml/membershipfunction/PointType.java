package jfml.membershipfunction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pointType complex type.
 * 
 * <pre>
 * &lt;complexType name="pointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="x" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pointType")
public class PointType {

    @XmlAttribute(name = "x", required = true)
    protected float x;
    @XmlAttribute(name = "y", required = true)
    protected float y;

    /**
     * Gets the value of the property x.
     * 
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the value of the property x.
     * 
     */
    public void setX(float value) {
        this.x = value;
    }

    /**
     * Gets the value of the property y.
     * 
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the value of the property y.
     * 
     */
    public void setY(float value) {
        this.y = value;
    }

}
