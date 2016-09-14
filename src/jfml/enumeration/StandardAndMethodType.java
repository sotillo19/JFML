package jfml.enumeration;

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
 *     &lt;enumeration value="BDIF"/>
 *     &lt;enumeration value="DRP"/>
 *     &lt;enumeration value="EPROD"/>
 *     &lt;enumeration value="HPROD"/>
 *     &lt;enumeration value="NILMIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "standardAndMethodType")
@XmlEnum
public enum StandardAndMethodType {

    MIN,
    PROD,
    BDIF,
    DRP,
    EPROD,
    HPROD,
    NILMIN;

    public String value() {
        return name();
    }

    public static StandardAndMethodType fromValue(String v) {
        return valueOf(v);
    }

}
