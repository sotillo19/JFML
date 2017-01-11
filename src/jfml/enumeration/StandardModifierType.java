package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardModifierType.
 * 
 * <pre>
 * &lt;simpleType name="standardModifierType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="above"/&gt;
 *     &lt;enumeration value="any"/&gt;
 *     &lt;enumeration value="below"/&gt;
 *     &lt;enumeration value="extremely"/&gt;
 *     &lt;enumeration value="intensify"/&gt;
 *     &lt;enumeration value="more_or_less"/&gt;
 *     &lt;enumeration value="norm"/&gt;
 *     &lt;enumeration value="not"/&gt;
 *     &lt;enumeration value="plus"/&gt;
 *     &lt;enumeration value="seldom"/&gt;
 *     &lt;enumeration value="slightly"/&gt;
 *     &lt;enumeration value="somewhat"/&gt;
 *     &lt;enumeration value="very"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
 */
@XmlType(name = "standardModifierType")
@XmlEnum
public enum StandardModifierType {

    @XmlEnumValue("above")
    ABOVE("above"),
    @XmlEnumValue("any")
    ANY("any"),
    @XmlEnumValue("below")
    BELOW("below"),
    @XmlEnumValue("extremely")
    EXTREMELY("extremely"),
    @XmlEnumValue("intensify")
    INTENSIFY("intensify"),
    @XmlEnumValue("more_or_less")
    MORE_OR_LESS("more_or_less"),
    @XmlEnumValue("norm")
    NORM("norm"),
    @XmlEnumValue("not")
    NOT("not"),
    @XmlEnumValue("plus")
    PLUS("plus"),
    @XmlEnumValue("seldom")
    SELDOM("seldom"),
    @XmlEnumValue("slightly")
    SLIGHTLY("slightly"),
    @XmlEnumValue("somewhat")
    SOMEWHAT("somewhat"),
    @XmlEnumValue("very")
    VERY("very");
    private final String value;

    StandardModifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StandardModifierType fromValue(String v) {
        for (StandardModifierType c: StandardModifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
