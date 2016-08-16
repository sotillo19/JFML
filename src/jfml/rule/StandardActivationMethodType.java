package jfml.rule;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardActivationMethodType.
 * 
 * <pre>
 * &lt;simpleType name="standardActivationMethodType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MIN"/>
 *     &lt;enumeration value="PROD"/>
 *     &lt;enumeration value="BSUM"/>
 *     &lt;enumeration value="DRS"/>
 *     &lt;enumeration value="EPROD"/>
 *     &lt;enumeration value="HPROD"/>
 *     &lt;enumeration value="NILMIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "standardActivationMethodType")
@XmlEnum
public enum StandardActivationMethodType {

    MIN,
    PROD,
    BSUM,
    DRS,
    EPROD,
    HPROD,
    NILMIN;

    public String value() {
        return name();
    }

    public static StandardActivationMethodType fromValue(String v) {
        return valueOf(v);
    }

}
