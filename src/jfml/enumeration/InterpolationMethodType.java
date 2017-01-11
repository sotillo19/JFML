package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interpolationMethodType.
 * 
 * <pre>
 * &lt;simpleType name="interpolationMethodType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="linear"/&gt;
 *     &lt;enumeration value="lagrange"/&gt;
 *     &lt;enumeration value="spline"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
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
