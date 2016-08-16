package jfml.term;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardModifierType.
 * 
 * <pre>
 * &lt;simpleType name="standardModifierType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="above"/>
 *     &lt;enumeration value="any"/>
 *     &lt;enumeration value="below"/>
 *     &lt;enumeration value="extremely"/>
 *     &lt;enumeration value="intensify"/>
 *     &lt;enumeration value="more_or_less"/>
 *     &lt;enumeration value="norm"/>
 *     &lt;enumeration value="not"/>
 *     &lt;enumeration value="plus"/>
 *     &lt;enumeration value="seldom"/>
 *     &lt;enumeration value="slightly"/>
 *     &lt;enumeration value="somewhat"/>
 *     &lt;enumeration value="very"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
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
