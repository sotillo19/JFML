package jfml.term;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.membershipfunction.CustomShapeType;
import jfml.membershipfunction.PointSetMonotonicShapeType;
import jfml.parameter.TwoParamType;


/**
 * <p>Java class for tsukamotoTermType complex type.
 * 
 * <pre>
 * &lt;complexType name="tsukamotoTermType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="rightLinearShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="leftLinearShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="rightGaussianShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="leftGaussianShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="zShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="sShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="pointSetMonotonicShape" type="{http://www.ieee1855.org}pointSetMonotonicShapeType"/>
 *         &lt;element name="customMonotonicShape" type="{http://www.ieee1855.org}customShapeType"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="complement" default="false">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="true|false|TRUE|FALSE|True|False"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tsukamotoTermType", propOrder = {
    "rightLinearShape",
    "leftLinearShape",
    "rightGaussianShape",
    "leftGaussianShape",
    "zShape",
    "sShape",
    "pointSetMonotonicShape",
    "customMonotonicShape"
})
public class TsukamotoTermType {

    protected TwoParamType rightLinearShape;
    protected TwoParamType leftLinearShape;
    protected TwoParamType rightGaussianShape;
    protected TwoParamType leftGaussianShape;
    protected TwoParamType zShape;
    protected TwoParamType sShape;
    protected PointSetMonotonicShapeType pointSetMonotonicShape;
    protected CustomShapeType customMonotonicShape;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "complement")
    protected String complement;

    /**
     * Gets the value of the property rightLinearShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getRightLinearShape() {
        return rightLinearShape;
    }

    /**
     * Sets the value of the property rightLinearShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setRightLinearShape(TwoParamType value) {
        this.rightLinearShape = value;
    }

    /**
     * Gets the value of the property leftLinearShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getLeftLinearShape() {
        return leftLinearShape;
    }

    /**
     * Sets the value of the property leftLinearShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setLeftLinearShape(TwoParamType value) {
        this.leftLinearShape = value;
    }

    /**
     * Gets the value of the property rightGaussianShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getRightGaussianShape() {
        return rightGaussianShape;
    }

    /**
     * Sets the value of the property rightGaussianShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setRightGaussianShape(TwoParamType value) {
        this.rightGaussianShape = value;
    }

    /**
     * Gets the value of the property leftGaussianShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getLeftGaussianShape() {
        return leftGaussianShape;
    }

    /**
     * Sets the value of the property leftGaussianShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setLeftGaussianShape(TwoParamType value) {
        this.leftGaussianShape = value;
    }

    /**
     * Gets the value of the property zShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getZShape() {
        return zShape;
    }

    /**
     * Sets the value of the property zShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setZShape(TwoParamType value) {
        this.zShape = value;
    }

    /**
     * Gets the value of the property sShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getSShape() {
        return sShape;
    }

    /**
     * Sets the value of the property sShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setSShape(TwoParamType value) {
        this.sShape = value;
    }

    /**
     * Gets the value of the property pointSetMonotonicShape.
     * 
     * @return
     *     possible object is
     *     {@link PointSetMonotonicShapeType }
     *     
     */
    public PointSetMonotonicShapeType getPointSetMonotonicShape() {
        return pointSetMonotonicShape;
    }

    /**
     * Sets the value of the property pointSetMonotonicShape.
     * 
     * @param value
     *     allowed object is
     *     {@link PointSetMonotonicShapeType }
     *     
     */
    public void setPointSetMonotonicShape(PointSetMonotonicShapeType value) {
        this.pointSetMonotonicShape = value;
    }

    /**
     * Gets the value of the property customMonotonicShape.
     * 
     * @return
     *     possible object is
     *     {@link CustomShapeType }
     *     
     */
    public CustomShapeType getCustomMonotonicShape() {
        return customMonotonicShape;
    }

    /**
     * Sets the value of the property customMonotonicShape.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomShapeType }
     *     
     */
    public void setCustomMonotonicShape(CustomShapeType value) {
        this.customMonotonicShape = value;
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
     * Gets the value of the property complement.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplement() {
        if (complement == null) {
            return "false";
        } else {
            return complement;
        }
    }

    /**
     * Sets the value of the property complement.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplement(String value) {
        this.complement = value;
    }

}
