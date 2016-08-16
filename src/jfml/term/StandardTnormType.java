package jfml.term;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardTnormType.
 * 
 * <pre>
 * &lt;simpleType name="standardTnormType">
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
@XmlType(name = "standardTnormType")
@XmlEnum
public enum StandardTnormType {

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

    public static StandardTnormType fromValue(String v) {
        return valueOf(v);
    }

}
