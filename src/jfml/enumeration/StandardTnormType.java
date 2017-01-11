package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardTnormType.
 * 
 * <pre>
 * &lt;simpleType name="standardTnormType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MIN"/&gt;
 *     &lt;enumeration value="PROD"/&gt;
 *     &lt;enumeration value="BSUM"/&gt;
 *     &lt;enumeration value="DRS"/&gt;
 *     &lt;enumeration value="EPROD"/&gt;
 *     &lt;enumeration value="HPROD"/&gt;
 *     &lt;enumeration value="NILMIN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
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
