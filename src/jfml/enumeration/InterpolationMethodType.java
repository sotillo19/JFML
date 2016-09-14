package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interpolationMethodType.
 * 
 * <pre>
 * &lt;simpleType name="interpolationMethodType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="linear"/>
 *     &lt;enumeration value="lagrange"/>
 *     &lt;enumeration value="spline"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "interpolationMethodType")
@XmlEnum
public enum InterpolationMethodType {

    @XmlEnumValue("linear")
    LINEAR("linear"),
    @XmlEnumValue("lagrange")
    LAGRANGE("lagrange"),
    @XmlEnumValue("spline")
    SPLINE("spline");
    private final String value;

    InterpolationMethodType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterpolationMethodType fromValue(String v) {
        for (InterpolationMethodType c: InterpolationMethodType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
